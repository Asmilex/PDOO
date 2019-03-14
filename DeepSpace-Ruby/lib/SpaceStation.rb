# encoding:utf-8

class SpaceStation
   @@MAXFUEL = 100
   @@SHIELDLOSSPERUNITSHOT = 0.1
   
   def initialize
      @ammoPower
      @fuelUnits
      @name
      @nMedals
      @shieldPower
      @pendingDamage #array
      @weapons #array
      @shieldBoosters #array
      @hangar #array
   end

   def assignFuelValue (f)
      @fuelUnits = Math.max(@@MAXFUEL,f)
   end
   
   def cleanPendingDamage()
      if(@pendingDamage.hasNoEffect)
         @pendingDamage = null
   end

   def receiveWeapon(Weapon w)
   # ESTO FALTA POR HACERLOOOOOOOOOOOOOOOOOOOOOOOOOOOOO  
   end
      
end
