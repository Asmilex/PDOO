# encoding:utf-8
require_relative "GameUniverseToUI"
require_relative "GameStateController"
require_relative "Dice"
require_relative "CombatResult"
require_relative "GameCharacter"
require_relative "ShotResult"
require_relative "SpaceStation"
require_relative "CardDealer"
require_relative "EnemyStarShip"
require_relative "PowerEfficientSpaceStation"
require_relative "BetaPowerEfficientSpaceStation"
require_relative "SpaceCity"
require_relative "Transformation"

module Deepspace
class GameUniverse
    @@WIN = 10

    def initialize
        @turns               = 0
        @currentStationIndex = -1

        @dice          = Dice.new
        @spaceStations = Array.new
        @gameState     = GameStateController.new

        @currentEnemy
        @currentStation
        @haveSpaceCity = false
    end


#
# ────────────────────────────────────────────────────────────────── COMBATS ─────
#

    def combatGo (station, enemy)
        ch = @dice.firstShot
        combatResult = CombatResult::ENEMYWINS

        # Determinar quién gana: si enemigo o estación. Depende de quién empiece
        if ch == GameCharacter::ENEMYSTARSHIP
            fire   = enemy.fire
            result = station.receiveShot(fire)

            if result == ShotResult::RESIST
               fire      = station.fire
               result    = enemy.receiveShot(fire)
               enemyWins = (result == ShotResult::RESIST)
            else
                enemyWins = true
            end
        else
            fire      = station.fire
            result    = enemy.receiveShot(fire)
            enemyWins = (result == ShotResult::RESIST)
        end


        if enemyWins
            s     = station.getSpeed
            moves = @dice.spaceStationMoves(s)

            if !moves
                damage = enemy.damage
                station.setPendingDamage( damage )
                combatResult = CombatResult::ENEMYWINS
            else
                station.move
                combatResult = CombatResult::STATIONESCAPES
            end
        else
            aLoot = enemy.loot
            setransforma = station.setLoot( aLoot )
            if setransforma == Transformation::GETEFFICIENT
                makeStationEfficient
                combatResult = CombatResult::STATIONWINSANDCONVERTS
            elsif setransforma == Transformacion::SPACECITY
                createSpaceCity
                combatResult = CombatResult::STATIONWINSANDCONVERTS
            else
                combatResult = CombatResult::STATIONWINS
            end
        end

        @gameState.next(@turns, @spaceStations.length)

        combatResult
    end


    def combat
        state = @gameState.state

        if state == GameState::BEFORECOMBAT || state == GameState::INIT
            combatGo(@currentStation, @currentEnemy)
        else
            CombatResult::NOCOMBAT
        end
    end

#
# ───────────────────────────────────────────────────────────────── DISCARDS ─────
#

    def discardHangar
        if @gameState.state == GameState::INIT or @gameState.state == GameState::AFTERCOMBAT
            @currentStation.discardHangar
        end
    end

    def discardShieldBooster(i)
        if @gameState.state == GameState::INIT or @gameState.state == GameState::AFTERCOMBAT
            @currentStation.discardShieldBooster(i)
        end
    end

    def discardShieldBoosterInHangar(i)
        if @gameState.state == GameState::INIT or @gameState.state == GameState::AFTERCOMBAT
            @currentStation.discardShieldBoosterInHangar(i)
        end
    end

    def discardWeapon(i)
        if @gameState.state == GameState::INIT or @gameState.state == GameState::AFTERCOMBAT
            @currentStation.discardWeapon(i)
        end
    end

    def discardWeaponInHangar(i)
        if @gameState.state == GameState::INIT or @gameState.state == GameState::AFTERCOMBAT
            @currentStation.discardWeaponInHangar(i)
        end
    end

#
# ─────────────────────────────────────────────────────────────────── MOUNTS ─────
#

    def mountShieldBooster(i)
        if @gameState.state == GameState::INIT or @gameState.state == GameState::AFTERCOMBAT
            @currentStation.mountShieldBooster(i)
        end
    end

    def mountWeapon(i)
        if @gameState.state == GameState::INIT or @gameState.state == GameState::AFTERCOMBAT
            @currentStation.mountWeapon(i)
        end
    end

#
# ──────────────────────────────────────────────────────────────────── OTROS ─────
#

    def state
        @gameState.state
    end

    def getUIversion
        GameUniverseToUI.new(@currentStation, @currentEnemy)
    end

    def haveAWinner
        return @currentStation.nMedals >= @@WIN
    end

    def init (names) #String[]
        state = @gameState.state

        if state == GameState::CANNOTPLAY
            dealer = CardDealer.instance # Debería ser atributo de instancia?

            for i in 0..names.size-1
                # Crear y preparar naves para los jugadores
                supplies = dealer.nextSuppliesPackage

                station = SpaceStation.new(names[i], supplies)

                nh = @dice.initWithNHangars
                nw = @dice.initWithNWeapons
                ns = @dice.initWithNShields

                lo = Loot.new(0, nw, ns, nh, 0)
                station.setLoot(lo)

                @spaceStations.push(station)
            end

            @currentStationIndex = @dice.whoStarts(names.size)
            @currentStation      = @spaceStations[@currentStationIndex]
            @currentEnemy        = dealer.nextEnemy

            @gameState.next(@turns, @spaceStations.size)
        end
    end

    def nextTurn
        state = @gameState.state
        return_value = false

        if state == GameState::AFTERCOMBAT
            if @currentStation.validState
                @currentStationIndex = (@currentStationIndex + 1) % @spaceStations.size
                @turns += 1

                @currentStation = @spaceStations[@currentStationIndex]

                @currentStation.cleanUpMountedItems

                dealer = CardDealer.instance

                @currentEnemy = dealer.nextEnemy

                @gameState.next(@turns, @spaceStations.size)
            end
        end


    end

private

    def createSpaceCity
        if @haveSpaceCity == false
            vector = Arrayn.new
            @spaceStations.each{ |station| 
                if station != @currentStation
                    vector << station
                 end
            }

            @currentStation = SpaceCity.new(@currentStation, vector)
            @spaceStations[@currentStationIndex] = @currentStation
            @haveSpaceCity = true
        end
    end

    def makeStationEfficient
        @currentStation = PowerEfficientSpaceStation.new(@currentStation)
        if @dice.extraEfficiency
            @currentStation = BetaPowerEfficientSpaceStation.new(@currentStation)
        end

        @spaceStations[@currentStationIndex] = @currentStation
    end            

public    

    def to_s
        getUIversion.to_s
    end
end
end
