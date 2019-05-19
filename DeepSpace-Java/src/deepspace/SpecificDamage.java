package deepspace;

import java.util.ArrayList;

public class SpecificDamage extends Damage {
    private ArrayList<WeaponType> weapons = new ArrayList<>();

//
// ──────────────────────────────────────────────────────────── CONSTRUCTORES ─────
//

    @Override
    SpecificDamage (ArrayList<WeaponType> wl, int s) {
        super(s);
        weapons = wl;
    }

    @Override
    SpecificDamage (SpecificDamage dano) {
        super (dano.getNShields());
        weapons = dano.weapons;
    }

//
// ─────────────────────────────────────────────────────────────── UTILIDADES ─────
//

    @Override
    private int arrayContainsType (ArrayList<Weapon> w, WeaponType t) {
        for (int i = 0; i < w.size(); i++) {
            if (w.get(i).getType() == t)
                return i;
        }

        return -1;
    }

    @Override
    SpecificDamage copy (SpecificDamage dano) {
        return new SpecificDamage(dano);
    }

    @Override
    public void discardWeapon (Weapon w) {
        weapons.remove( w.getType() );
    }


    @Override
    public Boolean hasNoEffect() {
        return super.hasNoEffect() && weapons.isEmpty();
    }

    @Override
    public SpecificDamage adjust (ArrayList<Weapon> w, ArrayList<ShieldBooster> s) {
        ArrayList<WeaponType> armas_ajustadas = new ArrayList<>();

        for (Weapon arma: w)
            if (weapons.contains(arma.getType()))
                armas_ajustadas.add(arma.getType());

        return new SpecificDamage(armas_ajustadas, super.adjust_shields(s));
    }

//
// ─────────────────────────────────────────────────────────────── INTERFACES ─────
//

    @Override
    public ArrayList<WeaponType> getWeapons () {
        return weapons;
    }

    @Override
    public String toString () {
        return super.toString() + "weapons" + weapons + "\n";
    }

    DamageToUI getUIversion() {
        return new SpecificDamageToUI(this);
    }
}
