package deepspace;

import java.util.ArrayList;

class Hangar implements Copyable <Hangar> {
    private int maxElements;
    private ArrayList<Weapon> weapons        = new ArrayList<>();
    private ArrayList<ShieldBooster> shields = new ArrayList<>();


    Hangar (int capacity) {
        maxElements = capacity;
    }

    Hangar (Hangar h) {
        maxElements = h.maxElements;
        weapons     = h.weapons;
        shields     = h.shields;
    }

    @Override
    public Hangar copy() {
        return new Hangar(this);
    }

//
// ──────────────────────────────────────────────────────────── MODIFICADORES ─────
//


    public boolean addWeapon (Weapon w) {
        if (SpaceAvailable()) {
            weapons.add(w);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean addShieldBooster (ShieldBooster s) {
        if (SpaceAvailable()) {
            shields.add(s);
            return true;
        }
        else {
            return false;
        }
    }

//
// ────────────────────────────────────────────────────────────── CONSULTORES ─────
//

    public int getMaxElements () {
        return maxElements;
    }

    public ArrayList<ShieldBooster> getShieldBoosters () {
        return shields;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    private boolean SpaceAvailable () {
        return shields.size() + weapons.size() < maxElements;
    }

//
// ─────────────────────────────────────────────────────────────────── REMOVE ─────
//

    public ShieldBooster removeShieldBooster (int s) {
        if (s < shields.size())
            return shields.remove(s);
        else
            return null;
    }

    public Weapon removeWeapon (int w) {
        if (w < weapons.size())
            return weapons.remove(w);
        else
            return null;
    }

    HangarToUI getUIversion () {
        return new HangarToUI(this);
    }

    public String toString() {
        return getUIversion.toString;;
    }
}
