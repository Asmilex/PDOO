# encoding:utf-8
require_relative "SuppliesPackage"
require_relative "Hangar"
require_relative "Damage"
require_relative "Weapon"
require_relative "WeaponType"
require_relative "Loot"
require_relative "ShieldBooster"
require_relative "CardDealer"
require_relative "SpaceStationToUI"


module Deepspace
class SpaceStation
   @@MAXFUEL = 100
   @@SHIELDLOSSPERUNITSHOT = 0.1

##################### Constructor

   def initialize (n, supplies)
      @name        = n
      @ammoPower   = supplies.ammoPower
      @fuelUnits   = supplies.fuelUnits
      @shieldPower = supplies.shieldPower

      @nMedals
      @hangar
      @pendingDamage

      @weapons        = Array.new
      @shieldBoosters = Array.new
   end


################### Metodos privados

   private def assignFuelValue (f)
      @fuelUnits = [@@MAXFUEL, f].max
   end

   private def cleanPendingDamage()
      if (@pendingDamage.hasNoEffect)
         @pendingDamage = nil
      end
   end

################### discard

   def discardHangar()
         @hangar = nil
   end

   def discardWeapon (i)
      puts "Siguiente práctica ¯\\_(ツ)_/¯"
   end

   def discardWeaponInHangar(i)
      if (@hangar != nil)
         @hangar.removeWeapon(i)
      end
   end

   def discardShieldBooster (i)
      puts "Siguiente práctica ¯\\_(ツ)_/¯"
   end

   def discardShieldBoosterInHangar(i)
      if(@hangar != nil)
         @hangar.removeShieldBooster(i)
      end
   end

################### Interfaces

   attr_reader :nMedals, :name, :fuelUnits, :ammoPower, :hangar, :shieldBoosters, :weapons, :shieldPower

   def getSpeed
      @fuelUnits/@@MAXFUEL
   end

   public def getUIversion
      s = SpaceStationToUI.new(self)
   end


################### Receive

   def receiveWeapon(w)
      if (@hangar != nil)
         @hangar.addWeapon(w)
         true
      else
         false
      end
   end

   def receiveShieldBooster(s)
      if (@hangar != nil)
         @hangar.addShieldBooster(s)
         true
      else
         false
      end
   end

   def receiveHangar(h)
      if (@hangar == nil)
         @hangar = h
      end
   end

   def receiveSupplies(s)
      @ammoPower += s.ammoPower
      @shieldPower += s.shieldPower
      assignFuelValue(s.fuelUnits + @fuelUnits)
   end

   def receiveShot (shot)
      puts "Siguiente práctica ¯\\_(ツ)_/¯"
   end

#################### Set

   def setPendingDamage(d)
      d.adjust(@weapons,@shieldBoosters)
   end

   def setLoot (s)
      puts "Siguiente práctica ¯\\_(ツ)_/¯"
   end

   def mountWeapon(i)
      if (@hangar != nil)
         arma = @hangar.removeWeapon(i)

         if (arma != nil)
            @weapons.push(arma)
         end
      end
   end

   def mountShieldBooster(i)
      if (@hangar != nil)
         escudo = @hangar.removeShieldBooster(i)

         if (escudo != nil)
            @shieldBoosters.push(escudo)
         end
      end
   end

#################### Otros

   def move
      @fuelUnits -= self.getSpeed
   end

   def fire
      puts "Siguiente práctica ¯\\_(ツ)_/¯"
   end

   def protection
      puts "Siguiente práctica ¯\\_(ツ)_/¯"
   end

   def validState
      @pendingDamage == nil or @pendingDamage.hasNoEffect
   end

   def cleanUpMountedItems
      for arma in @weapons
         if(arma.uses == 0)
            @weapons.delete(arma)
          end
      end
      for escudo in @shieldBoosters
         if(escudo.uses == 0)
            @shieldBoosters.delete(escudo)
         end
      end
   end
end
end
