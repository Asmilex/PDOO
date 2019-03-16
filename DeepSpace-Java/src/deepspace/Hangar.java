package deepspace;

import java.util.ArrayList;

class Hangar implements Copyable <> {
    private int maxElements;
    private ArrayList<Weapon> weapons        = new ArrayList<>();
    private ArrayList<ShieldBooster> shields = new ArrayList<>();


    Hangar (int capacity) {
        maxElements = capacity;
    }

    Hangar (Hangar h) {
        maxElements = h;
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

    public boolean addShieldBoosters (ShieldBooster s) {
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
        return shields.remove(s);
    }

    public Weapon removeWeapon (int w) {
        return weapons.remove(w);
    }

    HangarToUI getUIversion () {
        return new HangarToUI(this);
    }
}