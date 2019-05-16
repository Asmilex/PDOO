package deepspace;

import java.util.ArrayList;

// import sun.security.jca.GetInstance;

class SpaceStation implements SpaceFighter{
    private static int MAXFUEL = 100;
    private static float SHIELDOSSPERUNITSHOT = 0.1f;

    private int nMedals;
    private String name;
    private float fuelUnits;
    private float ammoPower;
    private float shieldPower;

    private Hangar hangar;
    private Damage pendingDamage = new NumericDamage(0, 0);
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<ShieldBooster> shieldBoosters = new ArrayList<>();


//
// ───────────────────────────────────────────────────────── METODOS PRIVADOS ─────
//

    private void assignFuelValue (float f) {
        fuelUnits = Math.min(f, MAXFUEL);
    }

    private void cleanPendingDamage () {
        if (pendingDamage.hasNoEffect())
            pendingDamage = null;
    }

//
// ────────────────────────────────────────────────────────────── CONSTRUCTOR ─────
//

    SpaceStation (String n, SuppliesPackage supplies) {
        name        = n;
        ammoPower   = supplies.getAmmoPower();
        fuelUnits   = supplies.getFuelUnits();
        shieldPower = supplies.getShieldPower();
    }

    SpaceStation (SpaceStation estacion) {
        name           = estacion.name;
        ammoPower      = estacion.ammoPower;
        fuelUnits      = estacion.fuelUnits;
        shieldPower    = estacion.shieldPower;
        nMedals        = estacion.nMedals;
        pendingDamage  = estacion.pendingDamage;
        weapons        = estacion.weapons;
        shieldBoosters = estacion.shieldBoosters;
        hangar         = estacion.hangar;
    }

//
// ───────────────────────────────────────────────────────────────── DISCARDS ─────
//

    public void discardShieldBooster (int i) {
        if (i >= 0 && i < shieldBoosters.size()) {
            shieldBoosters.remove(i);

            if (pendingDamage != null) {
                pendingDamage.discardShieldBooster();
                cleanPendingDamage();
            }
        }
    }

    public void discardShieldBoosterInHangar (int i) {
        if (hangar != null)
            hangar.removeShieldBooster(i);
    }

    public void discardWeapon (int i) {
        if (i >= 0 && i < weapons.size()) {
            Weapon w = weapons.remove(i);

            if (pendingDamage != null) {
                pendingDamage.discardWeapon(w); // FIXME
                cleanPendingDamage();
            }
        }
    }

    public void discardWeaponInHangar (int i) {
        if (hangar != null)
            hangar.removeWeapon(i);
    }

    public void discardHangar () {
        hangar = null;
    }

//
// ─────────────────────────────────────────────────────────────── INTERFACES ─────
//

    public float getAmmoPower() {
        return ammoPower;
    }

    public float getFuelUnits() {
        return fuelUnits;
    }

    public Hangar getHangar () {
        return hangar;
    }

    public String getName () {
        return name;
    }

    public int getNMedals() {
        return nMedals;
    }

    public ArrayList<ShieldBooster> getShieldBoosters() {
        return shieldBoosters;
    }

    public ArrayList<Weapon> getWeapons () {
        return weapons;
    }

    public float getShieldPower () {
        return shieldPower;
    }

    public float getSpeed() {
        return fuelUnits/MAXFUEL;
    }

    public Damage getPendingDamage () {
        return pendingDamage;
    }

    public SpaceStationToUI getUIversion () {
        return new SpaceStationToUI(this);
    }


//
// ─────────────────────────────────────────────────────────────── RECEPTORES ─────
//

    public void receiveHangar (Hangar h) {
        if (hangar == null)
            hangar = h;
    }

    public boolean receiveShieldBooster (ShieldBooster s) {
        if (hangar != null)
            return hangar.addShieldBooster(s);

        return false;
    }

    public ShotResult receiveShot (float shot) {
        float myProtection = protection();

        if (myProtection >= shot) {
            shieldPower -= SHIELDOSSPERUNITSHOT * shot;
            shieldPower = Math.max(0, shieldPower);

            return ShotResult.RESIST;
        }
        else {
            shieldPower = 0;

            return ShotResult.DONOTRESIST;
        }
    }

    public void receiveSupplies (SuppliesPackage s) {
        ammoPower   += s.getAmmoPower();
        shieldPower += s.getShieldPower();

        assignFuelValue(fuelUnits + s.getFuelUnits());
    }

    public boolean receiveWeapon (Weapon w) {
        if (hangar != null)
            return hangar.addWeapon(w);

        return false;
    }

//
// ────────────────────────────────────────────────────────────────── SETTERS ─────
//

    public Transformation setLoot (Loot l) {
        CardDealer dealer = CardDealer.getInstance();

        int h = l.getNHangars();

        if (h > 0) {
            Hangar hangar = dealer.nextHangar();
            receiveHangar(hangar);
        }

        int elements = l.getNSupplies();

        for (int i = 0; i < elements; i++) {
            SuppliesPackage sup = dealer.nextSuppliesPackage();
            receiveSupplies(sup);
        }

        elements = l.getNWeapons();

        for (int i = 0; i < elements; i++) {
            Weapon weap = dealer.nextWeapon();
            receiveWeapon(weap);
        }

        elements = l.getNShields();

        for (int i = 0; i < elements; i++) {
            ShieldBooster sh = dealer.nextShieldBooster();
            receiveShieldBooster(sh);
        }

        nMedals += l.getNMedals();


        if ( l.getEfficient() ) {
            return Transformation.GETEFFICIENT;
        }
        else if (l.spaceCity()) {
            return Transformation.SPACECITY;
        }
        else {
            return Transformation.NOTRANSFORM;
        }
    }

    public void setPendingDamage (Damage d) {
        pendingDamage = d.adjust(weapons, shieldBoosters);
    }

    public void mountShieldBooster (int i) {
        if (i >= 0) {
            if (hangar != null) {
                ShieldBooster aMontar = hangar.removeShieldBooster(i);

                if (aMontar != null)
                    shieldBoosters.add(aMontar);
            }
        }
    }

    public void mountWeapon (int i) {
        if (i >= 0 ) {
            if (hangar != null) {
                Weapon aMontar = hangar.removeWeapon(i);

                if (aMontar != null)
                    weapons.add(aMontar);
            }
        }
    }

//
// ──────────────────────────────────────────────────────────────────── OTROS ─────
//

    public boolean validState () {
        return ( pendingDamage == null || pendingDamage.hasNoEffect() );
    }

    public void move () {
        if (fuelUnits > 0)
            fuelUnits -= getSpeed();
    }

    public float protection () {
        int factor = 1;

        for (ShieldBooster s : shieldBoosters) {
            factor *= s.useIt();
        }

        return shieldPower * factor;
    }

    public float fire () {
        int factor = 1;

        for (Weapon w : weapons) {
            factor *= w.useIt();
        }

        return ammoPower * factor;
    }

    public void cleanUpMountedItems () {
        for (Weapon arma: weapons)
            if (arma.getUses() == 0)
                weapons.remove(arma);

        for (ShieldBooster escudo: shieldBoosters)
            if (escudo.getUses() == 0)
                shieldBoosters.remove(escudo);
    }

    @Override
    public String toString() {
        return    "-> Nombre: " + name
                + "\n\t-> Potencia: " + ammoPower
                + "\n\t-> Escudos: " + shieldPower
                + "\n\t-> nMedals: " + nMedals
                + "\n\t-> Daño pendiente:" + pendingDamage.toString()
                + "\n\t-> Gasolina: " + fuelUnits
                + "\n\t-> Hangar: " + hangar.toString();
    }
}