#encoding: UTF-8

module Deepspace
class SuppliesPackage

#
# ──────────────────────────────────────────────────────────── CONSTRUCTORES ─────
#

   def initialize(a, f, s)
      @ammoPower   = a
      @fuelUnits   = f
      @shieldPower = s
   end

   def self.newCopy(s)
      t = SuppliesPackage.new(s.ammoPower(), s.fuelUnits(), s.shieldPower())
      return t
   end

#
# ─────────────────────────────────────────────────────────────── INTERFACES ─────
#

   attr_reader :ammoPower, :fuelUnits, :shieldPower

   def to_s
      "\t-> Potencia: #{@ammoPower} \n\t-> Fuel: #{@fuelUnits} \n\t-> Potencia de escudo: #{@shieldPower}"
   end
end
end
