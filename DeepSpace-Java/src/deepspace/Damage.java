package deepspace;

import java.util.ArrayList;

public abstract class Damage {
    protected int nShields;

//
// ────────────────────────────────────────────────────────────── CONSTRUCTOR ─────
//

    Damage (int s) {
        nShields = s;
    }

    Damage (Damage d) {
        nShields = d.nShields;
    }

//
// ─────────────────────────────────────────────────────────────────── UTILES ─────
//

    SpecificDamage copy (SpecificDamage dano) {
        return new SpecificDamage(dano);
    }

    NumericDamage copy (NumericDamage dano) {
        return new NumericDamage(dano);
    }

    private int arrayContainsType (ArrayList<Weapon> w, WeaponType t) {
        for (int i = 0; i < w.size(); i++) {
            if (w.get(i).getType() == t)
                return i;
        }

        return -1;
    }

    abstract public void discardWeapon(Weapon w);
    
    public void discardShieldBooster () {
        if (nShields > 0)
            nShields--;
    }

    abstract public Boolean hasNoEffect();
    
    abstract public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s);

//
// ─────────────────────────────────────────────────────────────── INTERFACES ─────
//

    public int getNShields () {
        return nShields;
    }

    abstract DamageToUI getUIversion ();

    @Override
    public String toString() {
        return getUIversion().toString();
    }
}