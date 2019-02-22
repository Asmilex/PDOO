package deepspace;
import java.util.Random;

class Dice {
    private final float NHANGARPROB;
    private final float NSHIELDPROB;
    private final float NWEAPONPROB;
    private final float FIRSTSHOTPROB;

    private Random generator;

    Dice() {
        NHANGARPROB   = 0.25f;
        NSHIELDPROB   = 0.25f;
        NWEAPONPROB   = 0.33f;
        FIRSTSHOTPROB = 0.5f;

        generator = new Random();
    }
    /*
        @return 0 con probabilidad NHANGARSPROB. 1 de lo contrario
    */
    public int initWithNHangars() {
        float randomNumber = generator.nextFloat();

        if (randomNumber <= NHANGARPROB)
            return 0;
        else
            return 1;
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
        float randomNumber = generator.nextFloat();

        if (randomNumber <= NSHIELDPROB)
            return 0;
        else
            return 1;
    }

    public int whoStarts(int nPlayers) {
        return generator.nextInt(nPlayers);
    }

    public GameCharacter firstShot() {
        float randomNumber = generator.nextFloat();

        if (randomNumber < FIRSTSHOTPROB)
            return GameCharacter.SPACESTATION;
        else
            return GameCharacter.ENEMYSTARSHIP;
    }

    public boolean spaceStationMoves(float speed) {
        float randomNumber = generator.nextFloat();

        return randomNumber < speed;
    }
}