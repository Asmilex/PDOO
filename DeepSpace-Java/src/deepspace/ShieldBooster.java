package deepspace;

//
// ────────────────────────────────────────────────────────────────────────────── I ──────────
//   :::::: C L A S E   S H I E L D B O O S T E R : :  :   :    :     :        :          :
// ────────────────────────────────────────────────────────────────────────────────────────
//

    class ShieldBooster{
        private String name;
        private float boost;
        private int uses;

        ShieldBooster(String n, float b, int u) {
            name  = n;
            boost = b;
            uses  = u;
        }

        ShieldBooster(ShieldBooster s) {
            name  = s.name;
            boost = s.boost;
            uses  = s.uses;
        }

        public float getBoost() {
            return boost;
        }

        public int getUses() {
            return uses;
        }

        public float useIt() {
            if (uses > 0) {
                uses--;
                return boost;
            }
            else
                return 1.0f;
        }

        ShieldToUI getUIversion() { // FIXME
            return new ShieldToUI(this);
        }
    }
