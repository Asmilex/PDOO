# encoding:utf-8

require_relative 'WeaponType'
require_relative 'Weapon'
require_relative 'DamageToUI'

module Deepspace
class Damage
    attr_reader :nShields, :nWeapons, :weapons

#
# ──────────────────────────────────────────────────────────── CONSTRUCTORES ─────
#

    def initialize (w, s, wl)
        @nShields = s
        @nWeapons = w

        @weapons = Array.new
        @weapons = wl
    end

    def self.newNumericWeapons(w, s)
        new(w, s, nil)
    end

    def self.newSpecificWeapons(wl, s)
        new(-1, s, wl)
    end


    private_class_method :new

#
# ─────────────────────────────────────────────────────────────────── VARIOS ─────
#

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

    def to_s
        getUIversion.to_s
    end

#
# ─────────────────────────────────────────────────────────────────── AJUSTE ─────
#


    def adjust(weapons, s)
        nSh = [@nShields, s.length].min

        if @nWeapons == -1
            wCopy = @weapons.clone # Creamos copia para no modificar atributo

            armas_ajustadas = weapons.map do |w|
                wCopy.delete_at(wCopy.index(w.type) || wCopy.length)
            end

            armas_ajustadas.compact!

            self.class.newSpecificWeapons(armas_ajustadas, nSh)
        else
            self.class.newNumericWeapons( [@nWeapons, weapons.length].min, nSh )
        end
    end

    def discardWeapon(w) # discardWeapon (w: Weapon) : void
        if @weapons != nil and @nWeapons == -1
            @weapons.delete_if {|x| x == w.type}
        elsif @weapons == nil and @nWeapons != -1
            @nWeapons = @nWeapons > 0 ? @nWeapons -= 1 : 0
        end
    end

    def discardShieldBooster
        if(@nShields > 0)
            @nShields -= 1
        end
    end

    def hasNoEffect
        @nShields == 0 and (@nWeapons == 0 or @nWeapons == -1) and (@weapons == nil or @weapons.size == 0)
    end
end
end
