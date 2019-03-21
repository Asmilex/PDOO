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
require_relative "Transformation"

module Deepspace
class SpaceStation
   @@MAXFUEL = 100
   @@SHIELDLOSSPERUNITSHOT = 0.1

##################### Constructor

   def initialize
      @ammoPower
      @fuelUnits
      @name
      @nMedals
      @shieldPower
      @pendingDamage #array
      @weapons #array
      @shieldBoosters #array
      @hangar
   end


################### Metodos privados

   private def assignFuelValue (f)
      @fuelUnits = Math.max(@@MAXFUEL,f)
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

   def discardWeaponInHangar(i)
      if (@hangar != nil)
         @hangar.removeWeapon(i)
      end
   end

   def discardShieldBoosterInHangar(i)
      if(@hangar != nil)
         @hangar.removeShieldBooster(i)
      end
   end

################### Interfaces

   attr :nMedals, :name, :fuelUnits, :ammoPower, :hangar, :shieldBoosters, :weapons, :shieldPower

   def getSpeed
      @fuelUnits/@@MAXFUEL
   end

   public SpaceStationToUI getUIversion
      SpaceStationUI.new(self)
   end
   

################### Receive

   def receiveWeapon(w)
      if (@hangar != nil)
         @hangar.addWeapon(w)
      else
         false
      end
   end

   def receiveShieldBooster(s)
      if (@hangar != nil)
         @hangar.addShieldBooster(s)
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
      @fuelUnits.assignFuelValue(s.fuelUnits)
   end

#################### Set

   def setPendingDamage(d)
      d.adjust(@weapons,@shieldBoosters)
   end
   
   def mountWeapon(i)
      if (@hangar != nil)
         arma = @hangar.removeWeapon(i)
         if (arma != nil)
            @hangar.addWeapon(arma)
         end
      end
   end

   def mountShieldBooster(i)
      if (@hangar != nil)
         escudo = @hangar.removeShieldBooster(i)
         if (escudo != nil)
            @hangar.addShieldBoosters(escudo)
         end
      end
   end



#################### Otros

   def move
      @fuelUnits -= self.getSpeed
   end

   def validState
      self.cleanPendingDamage
      @pendingDamage == nil
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
