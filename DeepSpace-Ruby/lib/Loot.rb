# enconding:utf-8
require_relative 'LootToUI'

module Deepspace
class Loot
    attr_reader :nSupplies, :nWeapons, :nShields, :nHangars, :nMedals

    def initialize (suppliesValue, weaponsValue, shieldsValue, hangarsValue, medalsValue)
        @nSupplies = suppliesValue
        @nWeapons  = weaponsValue
        @nShields  = shieldsValue
        @nHangars  = hangarsValue
        @nMedals   = medalsValue
    end

    def getUIversion
        t = LootToUI.new(self)
    end

    def to_s
        "-> Supplies: #{@nSupplies} \n\t-> nWeapons: #{@nWeapons} \n\t-> nShields: #{@nShields} \n\t-> nHangars: #{@nHangars} \n\t-> nMedals: #{nMedals}"
    end

end
end
