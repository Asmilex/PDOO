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
        private boolean efficient;
        private boolean spaceCity;

        Loot (int SuppliesValue, int WeaponsValue, int ShieldsValue, int HangarsValue, int MedalsValue) {

            nSupplies = SuppliesValue;
            nWeapons  = WeaponsValue;
            nShields  = ShieldsValue;
            nHangars  = HangarsValue;
            nMedals   = MedalsValue;
            getEfficient = false;
            spaceCity = false;
        }
        
        Loot (int SuppliesValue, int WeaponsValue, int ShieldsValue, int HangarsValue, int MedalsValue, boolean efficient, boolean spcity){
            nSupplies = SuppliesValue;
            nWeapons  = WeaponsValue;
            nShields  = ShieldsValue;
            nHangars  = HangarsValue;
            nMedals   = MedalsValue;
            getEfficient = efficient;
            spaceCity = spcity;
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
        
        public boolean getEfficient() {
            return getEfficient;
        }
        
        public boolean spaceCity() {
            return spaceCity;
        }

        LootToUI getUIversion() {
            return new LootToUI(this);
        }
        

        @Override
        public String toString() {
            return    "-> Supplies: " + nSupplies
                + "\n\t-> nWeapons: " + nWeapons
                + "\n\t-> nShields: " + nShields
                + "\n\t-> nHangars: " + nHangars
                + "\n\t-> nMedals:" + nMedals;
        }
    }
