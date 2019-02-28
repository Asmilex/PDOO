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
        float velocidad = 0.5f;

        System.out.print("Prueba de métodos de Dice\n");
        System.out.print("Jugadores: " + jugadores + "\n");
        System.out.print("Velocidad: " + velocidad + "\n");

        System.out.print("Haremos 100 iteraciones para ver la distribución de números aleatorios\n");

        int[] cuentaArmas        = {0, 0, 0};
        int[] cuentaHangares     = {0, 0};
        int[] cuentaEscudos      = {0, 0};
        int[] cuentaQuienDispara = {0, 0};
        int[] haMovido           = {0, 0};
        int[] cuentaQuienEmpieza = new int [jugadores];

        for (int i = 0; i < jugadores; i++) {
            cuentaQuienEmpieza[i] = 0;
        }

        for (int i=0; i < 100; i++) {
            cuentaHangares[dado.initWithNHangars()]++;
            cuentaEscudos[dado.initWithNShields()]++;
            cuentaArmas[dado.initWithNWeapons() - 1]++;
            cuentaQuienEmpieza[dado.whoStarts(jugadores)]++;

            if (dado.spaceStationMoves(velocidad))
                haMovido[1]++;
            else
                haMovido[0]++;

            if (dado.firstShot() == GameCharacter.ENEMYSTARSHIP)
                cuentaQuienDispara[0]++;
            else
                cuentaQuienDispara[1]++;
        }

        System.out.print("Hangares: 0 -> " + cuentaHangares[0] + ", 1 -> " + cuentaHangares[1] + "\n");
        System.out.print("Escudos: 0 -> " + cuentaEscudos[0] + ", 1 -> " + cuentaEscudos[1] + "\n");
        System.out.print("Armas: 1 -> " + cuentaArmas[0] + ", 2 -> " + cuentaArmas[1] + ", 3 -> " + cuentaArmas[2] + "\n");
        System.out.print("Quién dispara: EnemyStarship -> " + cuentaQuienDispara[0] + ", SpaceStation -> " + cuentaQuienDispara[1] + "\n");
        System.out.print("Ha conseguido mover: " + haMovido[1] + ". No lo ha conseguido: " + haMovido[0] + "\n");
        System.out.print("Qué jugador empieza: \n");

        for (int i = 0; i < jugadores; i++) {
            System.out.print("Jugador " + (i+1) +": " + cuentaQuienEmpieza[i] + "\n");
        }

    }
}