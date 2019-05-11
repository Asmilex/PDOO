package deepspace;
import java.util.Random;

//
// ──────────────────────────────────────────────────────────── I ──────────
//   :::::: C L A S E   D I C E : :  :   :    :     :        :          :
// ──────────────────────────────────────────────────────────────────────
//

    class Dice {

        private final float NHANGARPROB         = 0.25f;
        private final float NSHIELDPROB         = 0.25f;
        private final float NWEAPONPROB         = 0.33f;
        private final float FIRSTSHOTPROB       = 0.5f;
        private final float EXTRAEFFICIENCYPROB = 0.8f;

        private Random generator;

    //
    // ────────────────────────────────────────────────────────────────── METODOS ─────
    //

        Dice() {
            generator = new Random();
        }


        /*
            @return 0 con probabilidad NHANGARSPROB. 1 de lo contrario
        */
        public int initWithNHangars() {
            return generator.nextFloat() <= NHANGARPROB
                ?   0
                :   1;
        }

        /*
            @return 1 con probabilidad NWEAPONSPROB, 2 con la misma probabilidad
            y 3 con una probabilidad de (1-2 * NWEAPONSPROB)
        */
        public int initWithNWeapons() {
            float randomNumber = generator.nextFloat();

            if (randomNumber <= NWEAPONPROB)
                return 1;
            else if (randomNumber <= 2*NWEAPONPROB)
                return 2;
            else
                return 3;
        }

        /*
            @return 0 con probabilidad NSHIELDPROB. 1 de lo contrario
        */
        public int initWithNShields() {
            return generator.nextFloat() <= NSHIELDPROB
                ?   0
                :   1;
        }

        public int whoStarts(int nPlayers) {
            return generator.nextInt(nPlayers);
        }

        public GameCharacter firstShot() {
            return generator.nextFloat() < FIRSTSHOTPROB
                ?   GameCharacter.SPACESTATION
                :   GameCharacter.ENEMYSTARSHIP;
        }

        boolean spaceStationMoves(float speed) {
            return generator.nextFloat() < speed;
        }

        public Boolean extraEfficiency () {
            return generator.nextFloat() <= EXTRAEFFICIENCYPROB
                ?   true
                :   false;
        }
    }