package deepspace;

class EnemyStarShip {

    private Loot loot;
    private Damage damage;
    private String name;
    private float ammoPower;
    private float shieldPower;

//
// ──────────────────────────────────────────────────────────── CONSTRUCTORES ─────
//

    EnemyStarShip (String n, float a, float s, Loot l, Damage d) {
        name        = n;
        loot        = l;
        damage      = d;
        ammoPower   = a;
        shieldPower = s;
    }

    EnemyStarShip (EnemyStarShip s) {
        name        = s.name;
        loot        = s.loot;
        damage      = s.damage;
        ammoPower   = s.ammoPower;
        shieldPower = s.shieldPower;
    }


    EnemyToUI getUIversion() {
        return new EnemyToUI(this);
    }

//
// ────────────────────────────────────────────────────────────── CONSULTORES ─────
//

    public float protection () {
        return shieldPower;
    }

    public float fire () {
        return ammoPower;
    }

    public float getAmmoPower() {
        return ammoPower;
    }

    public Damage getDamage() {
        return damage;
    }

    public Loot getLoot() {
        return loot;
    }

    public String getName() {
        return name;
    }

    public float getShieldPower() {
        return shieldPower;
    }

    public ShotResult receiveShot (float shot) {
        // FIXME hace falta reducir los escudos?

        if (shot < shieldPower)
            return ShotResult.DONOTRESIST;
        else
            return ShotResult.RESIST;
    }
}