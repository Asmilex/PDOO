#encoding: UTF-8

module DeepSpace
   class SuppliesPackage

      def initialize(a, f, s)
         @ammoPower   = a
         @fuelUnits   = f
         @shieldPower = s
      end

      def self.newCopy(s)
         t = SuppliesPackage.new(s.ammoPower(), s.fuelUnits(), s.shieldPower())
         return t
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
