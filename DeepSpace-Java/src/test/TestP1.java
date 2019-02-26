package deepspace;

//
// ─── PRUEBAS DE LAS CLASES ──────────────────────────────────────────────────────
//

public class TestP1 {
    public static void main(String[] args) {
    //
    // ───────────────────────────────────────────────────────────────────── LOOT ─────
    //

        Loot botin = new Loot(1,2,3,4,5);

        System.out.print("Prueba de interfaces de Loot:\n");
        System.out.printf( "-> Supplies: " + botin.getNSupplies() +
                         "\n-> Weapons: "  + botin.getNWeapons()  +
                         "\n-> Shields: "  + botin.getNShields()  +
                         "\n-> Hangars: "  + botin.getNHangars()  +
                         "\n-> Medals: "   + botin.getNMedals()   + "\n\n" );

    //
    // ─────────────────────────────────────────────────────────────────── WEAPON ─────
    //

        Weapon arma = new Weapon("Rayo", WeaponType.LASER, 1);

        System.out.print("Prueba de métodos de Weapon\n");
        System.out.printf("-> Tipo: "     + arma.getType() +
                        "\n-> Usos: "     + arma.getUses() +
                        "\n-> Potencia: " + arma.power()   + "\n");

        arma.useIt();
        System.out.printf("Tras useIt(), tiene estos usos: " + arma.getUses() + "\n\n");


    //
    // ──────────────────────────────────────────────────────────── SHIELDBOOSTER ─────
    //

        ShieldBooster escudo = new ShieldBooster("Ligero", 3.5f, 0);

        System.out.print("Prueba de métodos de ShieldBooster\n");
        System.out.printf("-> Boosteo: " + escudo.getBoost() +
                        "\n-> Usos: "    + escudo.getUses());

        escudo.useIt();
        System.out.printf("Tras useIt(), tiene estos usos: " + escudo.getUses() + "\n\n");

    //
    // ────────────────────────────────────────────────────────── SUPPLIESPACKAGE ─────
    //

        SuppliesPackage suministros = new SuppliesPackage(3.0f, 50f, 3.5f);

        System.out.print("Prueba de métodos de SuppliesPackage\n");
        System.out.printf("-> Ammo: "     + suministros.getAmmoPower() +
                        "\n-> Fuel: "     + suministros.getFuelUnits() +
                        "\n-> Potencia: " + suministros.getShieldPower() + "\n\n");

    //
    // ───────────────────────────────────────────────────────────────────── DICE ─────
    //

        Dice dado = new Dice();
        int jugadores = 5;

        System.out.print("Prueba de métodos de Dice\n");
        System.out.print("Jugadores: " + jugadores + "\n");
        System.out.print("Primer disparo: " + dado.firstShot() + "\n");

        System.out.print("Haremos 5 iteraciones para ver la generación de números\n");

        for (int i=0; i < 10; i++) {
            System.out.printf("-> Hangares: " + dado.initWithNHangars()      +
                            "\n-> Armas: "    + dado.initWithNWeapons()      +
                            "\n-> Escudos: "  + dado.initWithNShields()      +
                            "\n-> Mueve: "    + dado.spaceStationMoves(0.5f) +
                            "\n-> Empieza "   + dado.whoStarts(jugadores) + "\n\n");
        }
    }
}