package deepspace;

import java.util.ArrayList;

public class SpecificDamage extends Damage {
    private ArrayList<WeaponType> weapons = new ArrayList<>();

//
// ──────────────────────────────────────────────────────────── CONSTRUCTORES ─────
//

    SpecificDamage (ArrayList<WeaponType> wl, int s) {
        super(s);
        weapons = wl;
    }


    SpecificDamage (SpecificDamage dano) {
        super (dano.getNShields());
        weapons = dano.weapons;
    }

//
// ─────────────────────────────────────────────────────────────── UTILIDADES ─────
//

    public void discardWeapon (Weapon w) {
        weapons.remove( w.getType() );
    }


    @Override
    public Boolean hasNoEffect() {
        return super.hasNoEffect() && weapons.isEmpty();
    }

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

    public ArrayList<WeaponType> getWeapons () {
        return weapons;
    }

    @Override
    public String toString () {
        return super.toString() + "Tamaño del array de armas: " + weapons.size() + "\n";
    }

    DamageToUI getUIversion() {
        return new SpecificDamageToUI(this);
    }
}