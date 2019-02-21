# encoding:utf-8
module DeepSpace
    class Weapon
        def initialize (nombre, tipo, usos)
            @name = nombre
            @type = tipo
            @uses = usos
        end

        def newCopy(s)
            @name = s.name()
            @type = s.type()
            @uses = s.uses()
        end

        def type
            @type
        end

        def uses
            @uses
        end

        def power
            @type.power
        end

        def useIt
            if @uses > 0
                @uses -= 1
                return @uses
            else
                1.0
            end
        end

    end
end