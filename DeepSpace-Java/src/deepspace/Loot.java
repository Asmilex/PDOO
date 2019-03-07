package deepspace;

//
// ──────────────────────────────────────────────────────────── I ──────────
//   :::::: C L A S E   L O O T : :  :   :    :     :        :          :
// ──────────────────────────────────────────────────────────────────────
//

    class Loot {
        private int nSupplies;
        private int nWeapons;
        private int nShields;
        private int nHangars;
        private int nMedals;

        Loot (int SuppliesValue, int WeaponsValue, int ShieldsValue, int HangarsValue, int MedalsValue) {

            nSupplies = SuppliesValue;
            nWeapons  = WeaponsValue;
            nShields  = ShieldsValue;
            nHangars  = HangarsValue;
            nMedals   = MedalsValue;
        }

        public int getNHangars() {
            return nHangars;
        }

        public int getNMedals() {
            return nMedals;
        }

        public int getNShields() {
            return nShields;
        }

        public int getNSupplies() {
            return nSupplies;
        }

        public int getNWeapons() {
            return nWeapons;
        }

        LootToUI getUIversion() { // FIXME public o private?
            return new LootToUI(this);
        }
    }