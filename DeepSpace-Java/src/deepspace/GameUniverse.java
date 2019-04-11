package deepspace;
import java.util.ArrayList;

class GameUniverse {
    private static int WIN = 10;

    private int turns;
    private Dice dice;
    private int currentStationIndex;
    private EnemyStarShip currentEnemy;
    private SpaceStation currentStation;
    private GameStateController gameState;
    private ArrayList<SpaceStation> spaceStations = new ArrayList<>();


    GameUniverse() {
        gameState = new GameStateController();
        dice      = new Dice();
        turns     = 0;
    }


//
// ──────────────────────────────────────────────────────────────────── MOUNTS ─────
//

    public void mountShieldBooster(int i){
        if (gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
            currentStation.mountShieldBooster(i);
    }

    public void mountWeapon(int i){
        if (gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
            currentStation.mountWeapon(i);
    }

//
// ───────────────────────────────────────────────────────────────── DISCARDS ─────
//

    public void discardHangar () {
        if (gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
            currentStation.discardHangar();
    }

    public void discardShieldBooster(int i) {
        if (gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
            currentStation.discardShieldBooster(i);
    }

    public void discardShieldBoosterInHangar(int i) {
        if (gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
            currentStation.discardShieldBoosterInHangar(i);
    }


    public void discardWeapon (int i) {
        if (gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
            currentStation.discardWeapon(i);
    }

    public void discardWeaponInHangar(int i) {
        if (gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
            currentStation.discardWeaponInHangar(i);
    }

//
// ─────────────────────────────────────────────────────────────── INTERFACES ─────
//

    public GameState getState() {
        return gameState.getState();
    }

    public GameUniverseToUI getUIversion() {
        return new GameUniverseToUI(currentStation, currentEnemy);
    }

//
// ──────────────────────────────────────────────────────────── OTROS METODOS ─────
//

    public void init (ArrayList<String> names) {
        throw new UnsupportedOperationException();
    }

    public boolean nextTurn () {
        throw new UnsupportedOperationException();
    }

    public boolean haveAWinner(){
        return currentStation.getNMedals() == WIN;
    }

    public CombatResult combat () {
        throw new UnsupportedOperationException();
    }

    CombatResult combat (SpaceStation station, EnemyStarShip enemy) {
        throw new UnsupportedOperationException();
    }

    public String toString () {
        return      "\t-> turnos: " + turns
                + "\n\t-> Dado: " + dice.toString()
                + "\n\t-> Estación actual: " + currentStation.toString()
                + "\n\t-> Enemigo actual: " + currentEnemy.toString()
                + "\n\t-> Estado:" + gameState.toString();
    }
}
