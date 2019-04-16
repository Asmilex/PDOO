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
      @nMedals     = 0
      @ammoPower   = supplies.ammoPower
      @fuelUnits   = assignFuelValue(supplies.fuelUnits)
      @shieldPower = supplies.shieldPower

      @hangar
      @pendingDamage

      @weapons        = Array.new
      @shieldBoosters = Array.new
   end


################### Metodos privados

   private def assignFuelValue (f)
      @fuelUnits = [@@MAXFUEL, f].min
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
        size = @weapons.size
        #indice = 0
        if i >= 0 and i < size
            w = @weapons.delete_at(i)
            if (@pendingDamage != nil)
                @pendingDamage.discardWeapon(w)
                cleanPendingDamage
            end
        end
   end

   def discardWeaponInHangar(i)
      if (@hangar != nil)
         @hangar.removeWeapon(i)
      end
   end

   def discardShieldBooster (i)
        size = @shieldBoosters.size
        #indice = 0
        if i >= 0 and i < size
            sh = @shieldBoosters.delete_at(i)
            if (@pendingDamage != nil)
                @pendingDamage.discardShieldBooster(sh)
                cleanPendingDamage
            end
        end
   end


   def discardShieldBoosterInHangar(i)
      if(@hangar != nil)
         @hangar.removeShieldBooster(i)
      end
   end

################### Interfaces

   attr_reader :nMedals, :name, :fuelUnits, :ammoPower, :hangar, :shieldBoosters, :weapons, :shieldPower, :pendingDamage

   def getSpeed
      @fuelUnits/@@MAXFUEL
   end

   def getUIversion
      SpaceStationToUI.new(self)
   end


################### Receive

   def receiveWeapon(w)
      if @hangar != nil
         @hangar.addWeapon(w)
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
      @ammoPower   += s.ammoPower
      @shieldPower += s.shieldPower
      assignFuelValue(s.fuelUnits + @fuelUnits)
   end

   def receiveShot (shot)
        myprotection = protection
        @shieldPower -= @@SHIELDLOSSPERUNITSHOT*shot
        @shieldPower = [@shieldPower, 0.0].max
        if (shot <= myprotection)
            ShotResult::RESIST
        else
            ShotResult::DONOTRESIST
        end
   end

#################### Set

   def setPendingDamage(d)
      d.adjust(@weapons,@shieldBoosters)
   end

   def setLoot (s)
        dealer = CardDealer.instance

        h = s.nHangars
        @hangar = dealer.nextHangar
        if (h > 0)
            receiveHangar(h)
        end

        indice = 0
        elements = s.nSupplies
        while indice < elements
           sup = dealer.nextSuppliesPackage
           receiveSupplies(sup)
           indice += 1
        end

        indice = 0
        elements = s.nWeapons
        while indice < elements
           w = dealer.nextWeapon
           receiveWeapon(w)
           indice += 1
        end

        indice = 0
        elements = s.nShields

        while indice < elements
            sh = dealer.nextShieldBooster
            receiveShieldBooster(sh)
           indice += 1
        end

        @nMedals += s.nMedals
   end

   def mountWeapon(i)
    if (i >= 0)
          if (@hangar != nil)
             arma = @hangar.removeWeapon(i)

             if (arma != nil)
                @weapons.push(arma)
             end
          end
    end
   end

   def mountShieldBooster(i)
        if (i >= 0)      
            if (@hangar != nil)
             escudo = @hangar.removeShieldBooster(i)

             if (escudo != nil)
                @shieldBoosters.push(escudo)
             end
          end
        end
   end

#################### Otros

   def move
      @fuelUnits -= getSpeed
   end

   def fire
        factor = 1
        for w in @weapons
            factor *=w.useIt
        end

        @ammoPower*factor
   end

   def protection
        factor = 1
        for s in @shieldBoosters
            factor *=s.useIt
        end

        @shieldPower*factor
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

   def to_s
      "-> Nombre:#{@name} \n\t-> Potencia: #{@ammoPower} \n\t-> Escudos: #{@shieldPower} \n\t-> nMedals: #{@nMedals} \n\t-> Daño pendiente: #{@pendingDamage.to_s} \n\t-> Gasolina: #{@fuelUnits} \n\t-> Hangar: #{@hangar.to_s}"
   end
end
end
