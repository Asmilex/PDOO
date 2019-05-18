package deepspace;

//
// ──────────────────────────────────────────────────────────────── I ──────────
//   :::::: C L A S E   W E A P O N : :  :   :    :     :        :          :
// ──────────────────────────────────────────────────────────────────────────
//

class Weapon implements Copyable <Weapon>, CombatElement {
    private String name;
    private WeaponType type;
    private int uses;

    Weapon (String nombre, WeaponType tipo, int usos) {
        name = nombre;
        type = tipo;
        uses = usos;
    }

    Weapon (Weapon otro) {
        name = otro.name;
        type = otro.type;
        uses = otro.uses;
    }

    @Override
    public Weapon copy() {
        return new Weapon(this);
    }

    public WeaponType getType () {
        return type;
    }

    public int getUses () {
        return uses;
    }

    public float power() {
        return type.getPower();
    }

    public float useIt() {
        if (uses > 0) {
            uses--;
            return this.power();
        }
        else {
            return 1.0f;
        }
    }

    WeaponToUI getUIversion () {
        return new WeaponToUI(this);
    }

    @Override
    public String toString() {
        return getUIversion.toString;
    }
}
