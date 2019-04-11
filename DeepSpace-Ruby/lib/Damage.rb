# encoding:utf-8

require_relative 'WeaponType'
require_relative 'Weapon'
require_relative 'DamageToUI'

module Deepspace
class Damage
    attr_reader :nShields, :nWeapons, :weapons

    def initialize (w, s, wl)
        @nShields = s
        @nWeapons = w

        @weapons = Array.new
        @weapons = wl
    end

    def self.newNumericWeapons(w, s)
        new(w, s, nil)
    end

    def self.newSpecificWeapons(wl, s)
        new(0, s, wl)
    end

    def getUIversion
        DamageToUI.new(self)
    end

    def arrayContainsType (w, t)
        for i in 0...w.size
            if w[i].type == t
                return i
            end
        end
    end

    def adjust (w, s)
        nuevo_escudo = [s.length, @nShields].min

        if @weapons == nil or @weapons.length == 0
            nuevo_dano = [w.length, @nWeapons].min
            Damage.newNumericWeapons(nuevo_dano, nuevo_escudo)
        else
            nuevos_tipos = Array.new

            w.each { |arma|
                if @weapons.include?(arma.type)
                    nuevos_tipos.push(arma.type)
                end
            }

            Damage.newSpecificWeapons(nuevos_tipos, nuevo_escudo)
        end
    end

=begin
    def discardWeapon (w)
        if @weapons != nil
            @weapons.delete(w.type)
        elsif @nWeapons > 0
            @nWeapons -= 1
        end
    end

    def discardShieldBooster
        if @nShields > 0
            @nShields-=1
        end
    end
=end

        def discardWeapon(w) # discardWeapon (w: Weapon) : void
            if @weapons != nil and @nWeapons == 0
        		@weapons.delete_if {|x| x == w.type}
        	elsif @weapons == nil and @nWeapons != 0
        		@nWeapons = @nWeapons > 0 ? @nWeapons -= 1 : 0
        	end
        end

        def discardShieldBooster
            if(@nShields > 0)
                @nShields -= 1
            end
        end

    def hasNoEffect
        @nShields == 0 and @nWeapons == 0 and (@weapons == nil or @weapons.size == 0)
    end

    def to_s
        ret = "Daño a escudos: #{@nShields}"

        if @weapons == nil or @weapons.length == 0
            ret + "\n\t-> Daño a armas: #{@nWeapons}"
        else
            ret + "\n\t-> Tamaño del array de armas: #{@weapons.length}"
        end
    end

    private_class_method :new
end
end
