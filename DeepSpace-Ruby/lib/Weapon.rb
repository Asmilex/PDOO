# encoding:utf-8

require_relative "WeaponToUI"
require_relative "WeaponType"

module Deepspace
class Weapon
#
# ──────────────────────────────────────────────────────────── CONSTRUCTORES ─────
#

    def initialize (nombre, tipo, usos)
        @name = nombre
        @type = tipo
        @uses = usos
    end

    def self.newCopy(s)
        new(s.name, s.type, s.uses)
    end

#
# ─────────────────────────────────────────────────────────────── INTERFACES ─────
#

    attr_reader :name, :type, :uses

    def power
        @type.power
    end

    def useIt
        if @uses > 0
            @uses -= 1
            power
        else
            1.0
        end
    end

    def getUIversion
        WeaponToUI.new(self)
    end

    def to_s
        getUIversion.to_s
    end
end
end
