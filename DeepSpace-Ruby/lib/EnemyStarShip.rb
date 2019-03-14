# encoding:utf-8
require_relative 'Loot'
require_relative 'Damage'
require_relative 'ShotResult'

module Deepspace
class EnemyStarShip

#
# ──────────────────────────────────────────────────────────── CONSTRUCTORES ─────
#

    def initialize (n, a, s, l, d)
        @name        = n;
        @loot        = l;
        @damage      = d;
        @ammoPower   = a;
        @shieldPower = s;
    end

    def self.newCopy(s)
        new (s.name, s.loot, s.damage, s.ammoPower, s.shieldPower)
    end


    def getUIversion()
        new EnemyToUI.new(self)
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

    attr_reader :name, :loot, :damgage, :ammoPower, :shieldPower
end
end