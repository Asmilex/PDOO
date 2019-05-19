#encoding:utf-8

require_relative "Transformation"
require_relative "SpaceStation"
require_relative "PowerEfficientSpaceStationToUI"

module Deepspace
    class PowerEfficientSpaceStation < SpaceStation
        @@EFFICIENCYFACTOR = 1.1

        def initialize (station)
            createStation(station)
        end

        def fire
            disparo = super*@@EFFICIENCYFACTOR
        end

        def protection
            proteccion = super*@@EFFICIENCYFACTOR
        end

        def setLoot(s)
            super
            if s.getefficient
                Transformation::GETEFFICIENT
            else
                Transformation::NOTRANSFORM
            end
        end

        def to_s
            "Power Efficient Station Space Station"+super
        end
    end

end
