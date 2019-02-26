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

module DeepSpace
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
         puts "-> Boost : #{escudo.boost}\n-> Usos: #{escudo.uses}\n\n"

         suministros = SuppliesPackage.new(3.0,50,3.5)
         puts "Prueba de métodos de SuppliesPackage"
         puts "-> Ammo: #{suministros.ammoPower}\n-> Fuel: #{suministros.fuelUnits}\n-> Potencia: #{suministros.shieldPower}\n\n"

         dado = Dice.new()
         jugadores = 5

         puts "Prueba de métodos de Dice"
         puts "Jugadores: #{jugadores}"
         puts "Primer disparo: #{dado.firstShot}"
         puts "Hacemos 5 iteraciones para ver la generación de números"
         
         i = 0
         while i < 10
            puts "-> Hangares: #{dado.initWithNHangars}\n-> Armas: #{dado.initWithNWeapons}\n-> Escudos: #{dado.initWithNShields}\n-> Mueve: #dado.spaceStationMoves(0.5)}\n-> Empieza: #{dado.whoStarts(jugadores)}\n\n"
         i += 1
         end
      
      end
   end

   TestP1.main
end
