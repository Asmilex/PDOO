# encoding:utf-8

require_relative "Dice"

module Deepspace
    class BetaPowerEfficientSpaceStation < PowerEfficientSpaceStation
        @@EXTRAEFFICIENCY = 1.2
        
        def initialize (estation)
            super
            @dice = Dice.new
        end

        def fire
            if @dice.extraEfficiency
                disparo = super*@@EXTRAEFFICIENCY
            else
                super
            end
        end

        def to_s
            getUIversion.to_s
        end

    end

end
