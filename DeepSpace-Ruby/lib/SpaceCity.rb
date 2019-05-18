# encoding: utf-8

module Deepspace
class SpaceCity < SpaceStation
    attr_reader :base, :collaborators

    def initialize (base, rest) # base: SpaceStation, rest: Array<SpaceStation>
        super
        @collaborators = rest
        @base          = base
    end

    def fire
        disparo = super
        @collaborators.each { |station|
            disparo += station.fire
        }
        disparo
    end

    def protection
        proteccion = super
        @collaboratos.each { |station|
            proteccion += station.protection
        }
        proteccion
    end

    def setLoot (loot)
        super
        Transformation::NOTRANSFORM
    end

    def to_s
        getUIversion.to_s
    end
end
end
