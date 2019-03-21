package deepspace;

import java.util.ArrayList;

class SpaceStation {
    private static int MAXFUEL = 100;
    private static float SHIELDOSSPERUNITSHOT = 0.1f;

    private int nMedals;
    private String name;
    private float fuelUnits;
    private float ammoPower;
    private float shieldPower;

    private Hangar hangar;
    private Damage pendingDamage = new Damage(0, 0);
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<ShieldBooster> shieldBoosters = new ArrayList<>();


//
// ───────────────────────────────────────────────────────── METODOS PRIVADOS ─────
//

    private void assignFuelValue (float f) {
        fuelUnits = Math.min(f, MAXFUEL);
    }

    private cleanPendingDamage () {
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

//
// ───────────────────────────────────────────────────────────────── DISCARDS ─────
//

    public void discardShieldBooster (int i) {
        throw new UnsupportedOperationException();
    }

    public void discardShieldBoosterInHangar (int i) {
        if (hangar != null)
            hangar.removeShieldBooster(i);
    }

    public void discardWeapon (int i) {
        throw new UnsupportedOperationException();
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
        return new SpaceStationUI(this);
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
            return hangar.addShieldBoosters(s);

        return false;
    }

    public ShotResult receiveShot (float shot) {
        throw new UnsupportedOperationException();
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

    public void setLoot (Loot l) {
        throw new UnsupportedOperationException();
    }

    public void setPendingDamage (Damage d) {
        pendingDamage = d.adjust(weapons, shieldBoosters);
    }

    public void mountShieldBooster (int i) {
        if (hangar != null) {
            ShieldBooster aMontar = hangar.removeShieldBooster(i);

            if (aMontar != null)
                shieldBoosters.add(aMontar);
        }
    }

    public void mountWeapon (int i) {
        if (hangar != null) {
            Weapon aMontar = hangar.removeWeapon(i);

            if (aMontar != null)
                weapons.add(aMontar);
        }
    }

//
// ──────────────────────────────────────────────────────────────────── OTROS ─────
//

    public boolean validState () {
        return (pendingDamage == null || pendingDamage.hasNoEffect());
    }

    public void move () {
        if (fuelUnits > 0)
            fuelUnits -= getSpeed();
    }

    public float protection () {
        throw new UnsupportedOperationException();
    }

    public float fire () {
        throw new UnsupportedOperationException();
    }

    public void cleanUpMountedItems () {
        for (Weapon arma: weapons)
            if (arma.getUses() == 0)
                weapons.remove(arma);
        for (ShieldBooster escudo: shieldBoosters)
            if(escudo.getUses() == 0)
                shieldBoosters.remove(escudo);
    }
}
