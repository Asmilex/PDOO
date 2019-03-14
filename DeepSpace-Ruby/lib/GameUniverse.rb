# encoding:utf-8

class GameUniverse
    attr_reader :gameState,

    def initialize
        @@WIN = 10

        @turns         = 0
        @dice          = Dice.new
        @spaceStations = Array.new
        @gameState     = GameStateController.new

        @currentEnemy
        @currentStation
        @currentIndexStation
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
        # TODO siguiente práctica
    end

    def nextTurn
        # TODO siguiente práctica
    end
end