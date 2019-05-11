package deepspace;

import java.util.ArrayList;

class SpecificDamage extends Damage {
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
        weapons = dano.getWeapons();
    }

//
// ─────────────────────────────────────────────────────────────── UTILIDADES ─────
//

    public void discardWeapon (Weapon w) {
        weapons.remove( w.getType() );
    }


    public boolean hasNoEffect() {
        return  == 0 && weapons.size() == 0;
    }


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


    public String toString () {
        return super.toString() + "\n\t-> Tamaño del array de armas: " + weapons.size();
    }
    
    @Override
    DamageToUI getUIversion() {
        return new SpecificDamageToUI(this);  
    }
}