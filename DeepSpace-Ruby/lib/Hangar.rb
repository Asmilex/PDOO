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

        h.shields.each { |escudos| auxiliar.addShieldBoosters(escudos) }
        h.weapons.each { | armas | auxiliar.addWeapon(armas)}

        auxiliar
    end

    def SpaceAvaliable()
        @shields.size + @weapons.size < @maxElements
    end

    def addWeapon (w)
        if self.SpaceAvaliable
            @weapons.push(w)
            true
        else
            false
        end
    end

    def addShieldBoosters (s)
        if self.SpaceAvaliable
            @shields.push(s)
            true
        else
            false
        end
    end

    def removeShieldBooster (s)
        @shields.delete(s)
    end

    def removeWeapon (w)
        @weapons.delete(w)
    end

    def getUIversion
        HangarToUI.new(self)
    end
end
end
