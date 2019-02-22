package deepspace;

class Weapon {
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
}