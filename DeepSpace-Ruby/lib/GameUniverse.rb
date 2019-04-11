# encoding:utf-8
module Deepspace
class GameUniverse
    attr_reader :gameState,

    @@WIN = 10

    def initialize
        @turns         = 0
        @dice          = Dice.new
        @gameState     = GameStateController.new

        @currentEnemy
        @spaceStations
        @currentStation
        @currentStationIndex
    end

    def combat (station, enemy)
        ch = @dice.firstShot

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

            if moves
                damage = enemy.damage

                station.setPendingDamage( damage )

                combatResult = CombatResult::ENEMYWINS
            else
                station.move

                combatResult = CombatResult::STATIONESCAPES
            end

        else
            aLoot = enemy.loot

            station.setLoot( aLoot )

            combatResult = CombatResult::STATIONWINS
        end

        @gameState.next(@turns, @spaceStations.size)

        combatResult
    end


    def combatGO
        state = @gameState.state

        if state == GameState::BEFORECOMBAT or state == GameState::INIT
            combat(@currentStation, @currentEnemy)
        else
            CombatResult::NOCOMBAT
        end
    end

#
# ───────────────────────────────────────────────────────────────── DISCARDS ─────
#

    def discardHangar
        if @gameState.state = GameState::INIT or @gameState.state = GameState::AFTERCOMBAT
            @currentStation.discardHangar
        end
    end

    def discardShieldBooster(i)
        if @gameState.state = GameState::INIT or @gameState.state = GameState::AFTERCOMBAT
            @currentStation.discardShieldBooster(i)
        end
    end

    def discardShieldBoosterInHangar(i)
        if @gameState.state = GameState::INIT or @gameState.state = GameState::AFTERCOMBAT
            @currentStation.discardShieldBoosterInHangar(i)
        end
    end

    def discardWeapon(i)
        if @gameState.state = GameState::INIT or @gameState.state = GameState::AFTERCOMBAT
            @currentStation.discardWeapon(i)
        end
    end

    def discardWeaponInHangar(i)
        if @gameState.state = GameState::INIT or @gameState.state = GameState::AFTERCOMBAT
            @currentStation.discardWeaponInHangar(i)
        end
    end

#
# ─────────────────────────────────────────────────────────────────── MOUNTS ─────
#

    def mountShieldBooster(i)
        if @gameState.state = GameState::INIT or @gameState.state = GameState::AFTERCOMBAT
            @currentStation.mountShieldBooster(i)
        end
    end

    def mountWeapon(i)
        if @gameState.state = GameState::INIT or @gameState.state = GameState::AFTERCOMBAT
            @currentStation.mountWeapon(i)
        end
    end

#
# ──────────────────────────────────────────────────────────────────── OTROS ─────
#

    def state
        @gameState
    end

    def getUIversion
        # FIXME falta estacion y enemigo
        GameUniverseToUI.new()
    end

    def haveAWinner
        if @currentStation.nMedals = 10
            true
        else
            false
        end
    end

    def init (names) #String[]
        state = @gameState.state

        if state == GameState::CANNOTPLAY
            @spaceStations = Array.new

            dealer = CardDealer.instance # Debería ser atributo de instancia?

            for i in 1..names.size
                # Crear y preparar naves para los jugadores
                supplies = dealer.nextSuppliesPackage

                station = SpaceStation.new(names[i], supplies)

                @spaceStations.push(station)

                nh = @dice.initWithNHangars
                nw = @dice.initWithNWeapons
                ns = @dice.initWithNShields

                lo = Loot.new(0, nw, ns, nh, 0)

                station.setLoot(lo)
            end

            @currentStationIndex = @dice.whoStarts(names.size)
            @currentStation = @spaceStations.at(@currentStationIndex)
            @currentEnemy = dealer.nextEnemy

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

                return_value = true
            end
        end

        return_value
    end

    def to_s
        "\t-> turnos: #{@turns} \n\t-> Dado: #{@dice.to_s} \n\t-> Estación actual: #{@currentStation.to_s} \n\t-> Enemigo actual: #{@currentEnemy.to_s} \n\t-> Estado: #{@gameState.to_s}"
    end
end
end