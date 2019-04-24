# encoding:utf-8
require_relative 'Loot'
require_relative 'Damage'
require_relative 'ShotResult'
require_relative 'EnemyToUI'

module Deepspace
class EnemyStarShip

#
# ──────────────────────────────────────────────────────────── CONSTRUCTORES ─────
#

    def initialize (n, a, s, l, d)
        @name        = n
        @ammoPower   = a
        @shieldPower = s
        @loot        = l
        @damage      = d
    end

    def self.newCopy(s)
        new(s.name, s.ammoPower, s.shieldPower, s.loot, s.damage)
    end


    def getUIversion()
        EnemyToUI.new(self)
    end

#
# ─────────────────────────────────────────────────────────────── INTERFACES ─────
#

    def protection
        @shieldPower
    end

    def fire
        @ammoPower
    end

    def receiveShot (shot)
        if shot >= @shieldPower
            ShotResult::DONOTRESIST
        else
            ShotResult::RESIST
        end
    end

    def to_s
        getUIversion.to_s
    end

    attr_reader :name, :loot, :damage, :ammoPower, :shieldPower
end
end
