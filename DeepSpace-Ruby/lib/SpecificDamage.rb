# encodgin:utf-8

require_relative "Damage"
require_relative "SpecificDamageToUI"

module Deepspace
    class SpecificDamage < Damage
        
        def initialize(w,s)
            super(s)
            @weapons = w
        end

        def copy
            NumericDamage.new(@nWeapons, @nShields)
        end

        attr_reader :weapons

        def adjust(w,s)
            wCopy = @weapons.clone # Creamos copia para no modificar atributo

            armas_ajustadas = weapons.map do |w|
                wCopy.delete_at(wCopy.index(w.type) || wCopy.length)
            end

            armas_ajustadas.compact!

            self.class.newSpecificWeapons(armas_ajustadas, nSh)
            SpecificDamage.new(armas_ajustadas, super(s))
        end

        def discardWeapon(w)
            i = @weapons.index(w.type)
            if i!= nil
                @weapons.delete_at(i)
            end 
        end

        def hasNoEffect
            if @weapons == nil || @weapons.length == 0
                super
            else
                false
        end

        def getUIversion
            SpecificDamageToUI.new(self)
        end
        
        def to_s
            getUIversion.to_s
        end
        
        


    end

end
