# enconding:utf-8
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
            return @nSupplies
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

    end
end
