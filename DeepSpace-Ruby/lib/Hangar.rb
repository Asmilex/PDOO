# encoding:utf-8
require_relative 'Weapon'
require_relative 'ShieldBooster'

module Deepspace
class Hangar
    attr_reader :maxElements, :weapons, :shields

    def initialize (capacity)
        @maxElements = capacity
        @weapons     = Array.new
        @shields     = Array.new
    end

    def self.newCopy (h)
        auxiliar = Hangar.new(h.maxElements)

        h.shields.each { |escudos| auxiliar.addShieldBooster(escudos) }
        h.weapons.each { | armas | auxiliar.addWeapon(armas)}

        auxiliar
    end

    def spaceAvailable
        @shields.size + @weapons.size < @maxElements
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
            @shields.push(s)
            true
        else
            false
        end
    end

    def removeShieldBooster (s)
        @shields.delete_at(s)
    end

    def removeWeapon (w)
        @weapons.delete_at(w)
    end

    def getUIversion
        HangarToUI.new(self)
    end

    def to_s
        "\t-> Elementos máximos: #{@maxElements} \n\t-> Tamaño armas: #{@weapons.size} \n\t-> Tamaño escudos: #{@shields.size}"
    end
end
end
