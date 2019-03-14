# encoding:utf-8
module Deepspace
class Damage
    def initialize (s)
        @nShields = s
    end

    def self.newNumericWeapon(s, w)
        new(s)
        @nWeapons = w
    end

    def self.newSpecificWeapon(s, wl)
        new(s)
        @weapons = wl
    end

    private_class_method :new
end
end