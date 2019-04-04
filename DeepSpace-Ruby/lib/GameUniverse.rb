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
        # TODO siguiente práctica
    end

    def combatGO
        # TODO siguiente práctica
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
        # TODO siguiente práctica
    end

    def to_s
        "\t-> turnos: #{@turns} \n\t-> Dado: #{@dice.to_s} \n\t-> Estación actual: #{@currentStation.to_s} \n\t-> Enemigo actual: #{@currentEnemy.to_s} \n\t-> Estado: #{@gameState.to_s}"
    end
end
end