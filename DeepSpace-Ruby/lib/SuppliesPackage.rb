#encoding: UTF-8

module DeepSpace
   class SuppliesPackage
      
      def initialize(a,f,s)
         @ammoPower = a
         @fuelUnits = f
         @shieldPower = s
      end
   
      def newCopy(s)
         @ammoPower = s.ammoPower()
         @fuelUnits = s.fuelUnits()
         @shieldPower = s.shieldPower()
      end

      def ammoPower
         return @ammoPower
      end

      def fuelUnits
         return @fuelUnits
      end

      def shieldPower
         return @shieldPower
      end

   end
end


