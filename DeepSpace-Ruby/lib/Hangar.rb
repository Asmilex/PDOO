# encoding:utf-8
require_relative "WeaponType"
require_relative "Weapon"
require_relative "ShieldBooster"
require_relative "HangarToUI"

module Deepspace
class Hangar
    attr_reader :maxElements, :weapons, :shieldBoosters

    def initialize (capacity)
        @maxElements    = capacity
        @weapons        = Array.new
        @shieldBoosters = Array.new
    end

    def self.newCopy (h)
        auxiliar = Hangar.new(h.maxElements)

        h.shieldBoosters.each { |escudos| auxiliar.addShieldBooster(escudos) }
        h.weapons.each { | armas | auxiliar.addWeapon(armas)}

        auxiliar
    end

    def shields
        @shieldBoosters
    end

    def spaceAvailable
        @shieldBoosters.size + @weapons.size < @maxElements
    end

    def addWeapon (w)
        if spaceAvailable
            @weapons.push(w)
            true
        else
            false
        end
    end

    def addShieldBooster (s)
        if spaceAvailable
            @shieldBoosters.push(s)
            true
        else
            false
        end
    end

    def removeShieldBooster (s)
        @shieldBoosters.delete_at(s)
    end

    def removeWeapon (w)
        @weapons.delete_at(w)
    end

    def getUIversion
        HangarToUI.new(self)
    end

    def to_s
        "\t-> Elementos máximos: #{@maxElements} \n\t-> Tamaño armas: #{@weapons.size} \n\t-> Tamaño escudos: #{@shieldBoosters.size}"
    end
end
end
