package deepspace;

class Damage {
    private int nShields;
    private int nWeapons;

    Damage (int w, int s) {
        nShields = s;
        nWeapons = w;
    }

    Damage (WeaponType[] wl, int s) {

    }

    Damage (Damage d) {
        nShields = d.nShields;
        nWeapons = d.nWeapons;
    }

    DamageToUI getUIversion () {
        return new DamageToUI(this);
    }

    private int arrayContainsType (Weapon[] w, WeaponType t) {

    }

    
    public Damage adjust (Weapon[] w, ShieldBooster[] s) {

    }

    public void discardWeapon (Weapon w) {

    }

    public void discardShieldBooster () {

    }

    public boolean hasNoEffect () {

    }

    public int getNShields () {
        return nShields;
    }

    public int getNWeapons () {
        return nWeapons;
    }

    public WeaponType[] getWeapons () {

    }

}