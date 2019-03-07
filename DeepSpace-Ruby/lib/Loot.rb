# enconding:utf-8
require_relative 'LootToUI.rb'

module DeepSpace
    class Loot
        def initialize (suppliesValue, weaponsValue, shieldsValue, hangarsValue, medalsValue)
            @nSupplies = suppliesValue
            @nWeapons  = weaponsValue
            @nShields  = shieldsValue
            @nHangars  = hangarsValue
            @nMedals   = medalsValue
        end

        def nSupplies
            @nSupplies
        end

        def nWeapons
            @nWeapons
        end

        def nShields
            @nShields
        end

        def nHangars
            @nHangars
        end

        def nMedals
            @nMedals
        end

        def getUIversion
            t = LootToUI.new(self)
        end


    end
end
