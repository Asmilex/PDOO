package deepspace;

import java.util.ArrayList;

class NumericDamage extends Damage {
    private int nWeapons;

//
// ──────────────────────────────────────────────────────────── CONSTRUCTORES ─────
//

    NumericDamage (int w, int s) {
        super(s);
        nWeapons = w;
    }

    NumericDamage (NumericDamage dano) {
        super(dano.getNShields());
        nWeapons = dano.getWeapons();
    }

//
// ─────────────────────────────────────────────────────────────── UTILIDADES ─────
//

    public void discardWeapon () {
        if (nWeapons > 0)
            nWeapons--;
    }

    public boolean hasNoEffect () {
        return super.getNShields() == 0 && nWeapons == 0;
    }

    public NumericDamage adjust (ArrayList<Weapon> w, ArrayList<ShieldBooster> s) {
        int min_escudos = Math.min(super.getNShields(), s.size());
        int min_dano    = Math.min(nWeapons, w.size());

        return new NumericDamage(min_dano, min_escudos);
    }

//
// ─────────────────────────────────────────────────────────────── INTERFACES ─────
//

    public int getNWeapons () {
        return nWeapons;
    }

    public String toString() {
        return super.toString() + "\n\t-> Daño a armas: " + nWeapons;
    }
}