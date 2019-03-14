package deepspace;

import java.util.ArrayList;

// NOTE Convendría volver a repasar el código una vez sepamos qué tiene que hacer exactamente
// Ahora mismo voy un poco a ciegas

class Damage {
    private int nShields;

    private int nWeapons;
    // Complementarios
    private ArrayList<WeaponType> weapons = new ArrayList<>();

    Damage (int w, int s) {
        nWeapons = w;
        nShields = s;
    }

    Damage (ArrayList<WeaponType> wl, int s) {
        weapons = wl;
        nShields = s;
    }

    Damage (Damage d) {
        nShields = d.nShields;
        nWeapons = d.nWeapons;
    }

    DamageToUI getUIversion () {
        return new DamageToUI(this);
    }

    private int arrayContainsType (ArrayList<Weapon> w, WeaponType t) {
        for (int i = 0; i < w.size(); i++) {
            if (w.get(i).getType() == t)
                return i;
        }

        return -1;
    }

    public Damage adjust (ArrayList<Weapon> w, ArrayList<ShieldBooster> s) {
        // Descartar escudos
        int nuevo_escudo = Math.min(s.size(), nShields);

        // Descartar armas
        if (weapons.size() == 0) {
            int nuevo_dano = Math.min(w.size(), nWeapons);

            return new Damage(nuevo_dano, nuevo_escudo);
        }
        else {
            ArrayList<WeaponType> nuevos_tipos = new ArrayList<>();

            for (Weapon arma: w)
                if ( weapons.contains(arma.getType()) )
                    nuevos_tipos.add(arma.getType());

            return new Damage(nuevos_tipos, nuevo_escudo);
        }
    }

    public void discardWeapon (Weapon w) {
        if (weapons.size() != 0) {
            weapons.remove(w.getType());
        }
        else if (nWeapons > 0){
            nWeapons--;
        }
    }

    public void discardShieldBooster () {
        if (nShields > 0)
            nShields--;
    }

    public boolean hasNoEffect () {
        return nShields == 0 && nWeapons == 0 && weapons.size() == 0;
    }

//
// ─────────────────────────────────────────────────────────────── INTERFACES ─────
//

    public int getNShields () {
        return nShields;
    }

    public int getNWeapons () {
        return nWeapons;
    }

    public ArrayList<Weapon> getWeapons () {
        return weapons;
    }

}