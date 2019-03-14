package deepspace;

import java.util.ArrayList;

// NOTE Convendría volver a repasar el código una vez sepamos qué tiene que hacer exactamente
// Ahora mismo voy un poco a ciegas

class Damage {
    private int nShields;
    private int nWeapons;
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
        //TODO
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
        // TODO
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