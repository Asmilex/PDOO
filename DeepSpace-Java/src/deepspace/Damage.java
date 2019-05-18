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

    public Boolean hasNoEffect() {
        return nShields == 0;
    }

    protected int adjust_shields (ArrayList<ShieldBooster> s) {
        return Math.min(nShields, s.size());
    }

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
        return "El numero de escudos eliminados ha sido "+nShields;
    }
}
