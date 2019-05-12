# encoding:utf-8

require_relative "Damage"
require_relative "NumericDamageToUI"

module Deepspace
    class NumericDamage < Damage
        
        def initialize(w,s)
            super(s)
            @nWeapons = w
        end

        def copy
            NumericDamage.new(@nWeapons, @nShields)
        end

        attr_reader :nWeapons

        def adjust(w,s)
            NumericDamage.new([@nWeapons,w.length].min, super(s))
        end

        def discardWeapon(w)
            if @nWeapons > 0
                @nWeapons -= 1
            end
        end

        def hasNoEffect
            if @nWeapons == 0
                super
            else
                false
        end

        def getUIversion
            NumericDamageToUI.new(self)
        end
        
        def to_s
            getUIversion.to_s
        end
        
        


    end

end
