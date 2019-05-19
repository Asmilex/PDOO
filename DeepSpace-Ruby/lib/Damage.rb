# encoding:utf-8

require_relative 'WeaponType'
require_relative 'Weapon'
require_relative 'DamageToUI'

module Deepspace
class Damage
    attr_reader :nShields

#
# ──────────────────────────────────────────────────────────── CONSTRUCTORES ─────
#

    def initialize (w, s, wl)
        @nShields = s
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
    end

    def adjustShields(s)
        [@nShields, s.length].min
    end

    def discardWeapon(w) # discardWeapon (w: Weapon) : void
    end

    def copy
    end

    def discardShieldBooster
        if(@nShields > 0)
            @nShields -= 1
        end
    end

    def hasNoEffect
        @nShields == 0
    end
end
end
