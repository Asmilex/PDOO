# encoding:utf-8

require '../lib/CombatResult.rb'
require '../lib/Dice.rb'
require '../lib/GameCharacter.rb'
require '../lib/Loot.rb'
require '../lib/ShieldBooster.rb'
require '../lib/ShotResult.rb'
require '../lib/SuppliesPackage.rb'
require '../lib/Weapon.rb'
require '../lib/WeaponType.rb'

module Deepspace
    class TestP1
        def initialize

        end

        def self.main

            botin = Loot.new(1,2,3,4,5)
            puts "Prueba de interfaces de Loot:"
            puts "-> Supplies: #{botin.nSupplies}\n-> NWeapons: #{botin.nWeapons}\n-> NShields: #{botin.nShields}\n-> Hangars: #{botin.nHangars}\n-> Medals: #{botin.nMedals}\n\n"

            arma = Weapon.new("Rayo", WeaponType::LASER, 1)
            puts "Prueba de métodos de Weapon:"
            puts "-> Tipo: #{arma.type}\n-> Usos: #{arma.uses}\n->Potencia: #{arma.power}"
            arma.useIt
            puts "Tras useIt tiene estos usos: #{arma.uses}\n\n"

            escudo = ShieldBooster.new("Ligero", 3.5, 0)
            puts "Prueba de métodos de ShieldBooster:"
            puts "-> Boost: #{escudo.boost}\n-> Usos: #{escudo.uses}\n\n"

            suministros = SuppliesPackage.new(3.0,50,3.5)
            puts "Prueba de métodos de SuppliesPackage"
            puts "-> Ammo: #{suministros.ammoPower}\n-> Fuel: #{suministros.fuelUnits}\n-> Potencia: #{suministros.shieldPower}\n\n"

            dado = Dice.new()
            jugadores = 5
            velocidad = 0.5

            puts "Prueba de métodos de Dice"
            puts "Jugadores: #{jugadores}"
            puts "Hacemos 100 iteraciones para ver la generación de números aleatorios:"

            cuenta_armas         = [0, 0, 0]
            cuenta_hangares      = [0, 0]
            cuenta_escudos       = [0, 0]
            cuenta_quien_dispara = [0, 0]
            ha_movido            = [0, 0]
            cuenta_quien_empieza = Array.new(jugadores, 0)

            for i in 1..100
                cuenta_hangares[dado.initWithNHangars]   += 1
                cuenta_escudos[dado.initWithNShields]    += 1
                cuenta_armas[dado.initWithNWeapons - 1]  += 1
                cuenta_quien_empieza[dado.whoStarts(jugadores) - 1] += 1

                if dado.spaceStationMoves(velocidad)
                    ha_movido[1] += 1
                else
                    ha_movido[0] += 1
                end

                if dado.firstShot() == GameCharacter::ENEMYSTARSHIP
                    cuenta_quien_dispara[0] += 1
                else
                    cuenta_quien_dispara[1] += 1
                end
            end

            puts "Hangares: 0 -> #{cuenta_hangares[0]}, 1 -> #{cuenta_hangares[1]}"
            puts "Escudos: 0 -> #{cuenta_escudos[0]}, 1 -> #{cuenta_escudos[1]}"
            puts "Armas: 1 -> #{cuenta_armas[0]}, 2 -> #{cuenta_armas[1]}, 3 -> #{cuenta_armas[2]}"
            puts "Quién dispara: ENEMYSTARSHIP #{cuenta_quien_dispara[0]}, SPACESTATION #{cuenta_quien_dispara[1]}"
            puts "Ha conseguido mover: #{ha_movido[1]}. No lo ha conseguido: #{ha_movido[0]}"
            puts "Qué jugador empieza:"

            for i in 0...jugadores
                puts "#{i + 1}: #{cuenta_quien_empieza[i]} veces"
            end

        end
   end

   TestP1.main
end
