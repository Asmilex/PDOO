package deepspace;

class SpaceStation {
    private static int MAXFUEL                = 100;
    private static float SHIELDOSSPERUNITSHOT = 0.1;

    private int nMedals;
    private String name;
    private float ammoPower;
    private float shieldPower;

//
// ───────────────────────────────────────────────────────── METODOS PRIVADOS ─────
//

    private void assignFuelValue (float f) {

    }

    private cleanPendingDamage () {

    }

//
// ────────────────────────────────────────────────────────────── CONSTRUCTOR ─────
//

    SpaceStation (String n, SuppliesPackage supplies) {

    }

//
// ───────────────────────────────────────────────────────────────── DISCARDS ─────
//

    public void discardShieldBooster (int i) {

    }

    public void discardShieldBoosterInHangar (int i) {

    }

    public void discardWeapon (int i) {

    }

    public void discardWeaponInHangar (int i) {

    }

    public void discardHangar () {

    }


//
// ──────────────────────────────────────────────────────────────────── OTROS ─────
//

    public void cleanUpMountedItems () {

    }

//
// ─────────────────────────────────────────────────────────────── INTERFACES ─────
//

    public float getAmmoPower() {
        return ammoPower;
    }

    public float getFuelUnits() {

    }

    public Hangar getHangar () {

    }

    public String getName () {

    }

    public int getNMedals() {
        return nMedals;
    }
}