# encoding:utf-8
module Deepspace
    class Weapon
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

        def name
            @name
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
            else
                1.0
            end
        end
      
        def getUIversion
            l = WeapontoUI.new(self)
        end
            

    end
end
