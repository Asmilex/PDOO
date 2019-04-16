# encoding:utf-8

require_relative "WeaponToUI"
require_relative "WeaponType"

module Deepspace
class Weapon
    attr_reader :name, :type, :uses

    def initialize (nombre, tipo, usos)
        @name = nombre
        @type = tipo
        @uses = usos
    end

    def self.newCopy(s)
        new(s.name, s.type, s.uses)
    end

    def power
        @type.power
    end

    def useIt
        if @uses > 0
            @uses -= 1
        else
            1.0
        end
    end

    def getUIversion
        WeaponToUI.new(self)
    end

    def to_s
        "-> Nombre: #{@name} \n\t-> Tipo: #{@type} \n\t-> Usos: #{@uses}"
    end
end
end
