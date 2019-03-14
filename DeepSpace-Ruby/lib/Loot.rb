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


end
end
