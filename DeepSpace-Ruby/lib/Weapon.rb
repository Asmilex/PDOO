# encoding:utf-8
module Deepspace
class Weapon
    attr_reader :name, :type, :uses

    def initialize (nombre, tipo, usos)
        @name = nombre
        @type = tipo
        @uses = usos
    end

    def newCopy(s)
        @name = s.name
        @type = s.type
        @uses = s.uses
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
        l = WeapontoUI.new(self)
    end

    def to_s
        "-> Nombre: #{@name} \n\t-> Tipo: #{@type} \n\t-> Usos: #{@uses}"
    end
end
end
