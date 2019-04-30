package deepspace;

import java.util.ArrayList;

// FIXME hace falta declararla como abstracta?
class Damage {
    private int nShields;

//
// ────────────────────────────────────────────────────────────── CONSTRUCTOR ─────
//

    Damage (int s) {
        nShields = s;
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

    public void discardShieldBooster () {
        if (nShields > 0)
            nShields--;
    }

//
// ─────────────────────────────────────────────────────────────── INTERFACES ─────
//

    public int getNShields () {
        return nShields;
    }

    DamageToUI getUIversion () {
        return new DamageToUI(this);
    }

    public String toString() {
        return getUIversion().toString();
    }
}