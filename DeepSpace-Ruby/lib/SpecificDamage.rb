# encodgin:utf-8

require_relative "Damage"
require_relative "SpecificDamageToUI"

module Deepspace
    class SpecificDamage < Damage
        
        public_class_method :new
        
        def initialize(w,s)
            super(s)
            @weapons = w
        end

        def copy
            SpecificDamage.new(@weapons, @nShields)
        end

        attr_reader :weapons

        def adjust(w,s)
            wCopy = @weapons.clone # Creamos copia para no modificar atributo

            armas_ajustadas = w.map do |weap|
                wCopy.delete_at(wCopy.index(weap.type) || wCopy.length)
            end

            armas_ajustadas.compact!

            SpecificDamage.new(armas_ajustadas, adjustShields(s))
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
        end

        def getUIversion
            SpecificDamageToUI.new(self)
        end
        
        def to_s
            super+getUIversion.getWeaponInfo
        end

    end

end
