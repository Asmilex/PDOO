package deepspace;

import java.util.ArrayList;

public class NumericDamage extends Damage {
    private int nWeapons;

//
// ──────────────────────────────────────────────────────────── CONSTRUCTORES ─────
//

    @Override
    NumericDamage (int w, int s) {
        super(s);
        nWeapons = w;
    }

    @Override
    NumericDamage (NumericDamage dano) {
        super( dano.getNShields() );
        nWeapons = dano.getNWeapons();
    }

//
// ─────────────────────────────────────────────────────────────── UTILIDADES ─────
//

    @Override
    NumericDamage copy (NumericDamage dano) {
        return new NumericDamage(dano);
    }

    @Override
    public void discardWeapon () {
        if (nWeapons > 0)
            nWeapons--;
    }

    @Override
    public Boolean hasNoEffect () {
        return super.hasNoEffect() && nWeapons == 0;
    }

    @Override
    public NumericDamage adjust (ArrayList<Weapon> w, ArrayList<ShieldBooster> s) {
        int min_dano = Math.min(nWeapons, w.size());

        return new NumericDamage(min_dano, super.adjust_shields(s));
    }

//
// ─────────────────────────────────────────────────────────────── INTERFACES ─────
//

    @Override
    public int getNWeapons () {
        return nWeapons;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\t-> Daño a armas: " + nWeapons;
    }

    @Override
    DamageToUI getUIversion() {
        return new NumericDamageToUI(this);
    }

    @Override
    public void discardWeapon(Weapon w) {
        if(nWeapons>0)
            nWeapons--;
    }
}
