# encoding:utf-8

require_relative 'WeaponType'
require_relative 'Weapon'
require_relative 'DamageToUI'

module Deepspace
class Damage
    attr_reader :nShields, :nWeapons, :weapons

    def initialize (s)
        @nShields = s
    end

    def self.newNumericWeapon(s, w)
        new(s)
        @nWeapons = w
    end

    def self.newSpecificWeapon(s, wl)
        new(s)
        @weapons = Array.new
        @weapons = wl
    end

    def getUIversion
        DamageToUI.new(self)
    end

    def arrayContainsType (w, t)
        for i in 0...w.size
            if w[i].type == t
                return i
            end
        end
    end

    def adjust (w, s)
        nuevo_escudo = [s.size, @nShields].min

        if @weapons.size == 0
            nuevo_dano = [w.size, @nWeapons].min

            return Damage.newNumericWeapon(nuevo_dano, nuevo_escudo)
        else
            Damage.newSpecificWeapon(nuevo_escudo, @weapons & w)
        end
    end

    def discardWeapon (w)
        if @weapons.size != 0
            @weapons.add(w.type)
        elsif @nWeapons > 0
            @nWeapons--
        end
    end

    def discardShieldBooster
        if @nShields > 0
            @nShields--
        end
    end

    def hasNoEffect
        # FIXME , creo?
        @nShields == 0 and @nWeapons == 0 and @weapons.size == 0
    end

    private_class_method :new
end
end