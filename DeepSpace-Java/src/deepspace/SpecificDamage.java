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

    @Override
    public void discardWeapon (Weapon w) {
        weapons.remove( w.getType() );
    }


    @Override
    public Boolean hasNoEffect() {
        return nShields == 0 && weapons.isEmpty();
    }

    @Override
    public SpecificDamage adjust (ArrayList<Weapon> w, ArrayList<ShieldBooster> s) {
        // FIXME esto hay que comprobar que funciona

        int min_escudos = Math.min(nShields, s.size());
        ArrayList<WeaponType> armas_ajustadas = new ArrayList<>();

        for (Weapon arma: w)
            if (weapons.contains(arma.getType()))
                armas_ajustadas.add(arma.getType());

        return new SpecificDamage(armas_ajustadas, min_escudos);
    }

//
// ─────────────────────────────────────────────────────────────── INTERFACES ─────
//

    public ArrayList<WeaponType> getWeapons () {
        return weapons;
    }

    @Override
    public String toString () {
        return SpecificDamageToUI(this);
    }

    @Override
    DamageToUI getUIversion() {
        return new SpecificDamageToUI(this);
    }
}