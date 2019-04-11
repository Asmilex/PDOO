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
        state = gameState.getState()
        if (state == GameState.CANNOTPLAY
        //#FIXME 
    }

    public boolean nextTurn () {
        GameState state = gameState.getState();
        if (state == GameState.AFTERCOMBAT){
            stationState = currentStation.validState();
            if(stationState){
               int  currentStationIndex=(currentStationIndex+1)%spaceStations.size();
                currentStation = spaceStations.get(currentStationIndex);
                currentStation.cleanUpMountedItems;
                CardDealer dealer = CardDealer.getInstance();
                currentEnemy = currentEnemy.nextEnemy();
                gameState.next(turns.spaceStations.size());
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean haveAWinner(){
        return currentStation.getNMedals() == WIN;
    }

    public CombatResult combat () {
        state = gameState.getState();
        if ((state == GameState.BEFORECOMBAT) or (state == GameState.INIT)){
            return combat(currentStation, currentEnemy);
        }    
        return CombatResult.NOCOMBAT;
    }

    CombatResult combat (SpaceStation station, EnemyStarShip enemy) {
        GameCharacter ch = dice.firstShot();
        if (ch == GameCharacter.ENEMYSTARSHIP){
            float fire = enemy.fire();
            ShotResult result = station.receiveShot(fire);
            if (result == ShotResult.RESIST){
                fire = station.fire();
                result = enemy.receiveShot(fire);
                bool enemyWins = (result == ShotResult.RESIST);
            }
            else{
                enemyWins = true;
            }
        }
        else{
            float fire = station.fire();
            ShotResult result = enemy.receiveShot(fire);
            bool enemyWins = (result == ShotResult.RESIST);
        }

        if (enemyWins){
            float s = station.getSpeed();
            bool moves = dice.spaceStationMoves(s);
            if (!moves){
                Damage damage = enemy.getDamage();
                station.setPendingDamage(damage)
                combatResult = CombatResult.ENEMYWINS;
            }
            else{
                station.move();
                combatresult = CombatResult.STATIONSCAPES;
            }
        }
        else{
            Loot aLoot = enemy.getLoot();
            station.setLoot(aLoot);
            combatResult = CombatResult.STATIONWINS;
        }

        gameState.next(spaceStations.size());
        return combatResult;
    }

    public String toString () {
        return      "\t-> turnos: " + turns
                + "\n\t-> Dado: " + dice.toString()
                + "\n\t-> Estación actual: " + currentStation.toString()
                + "\n\t-> Enemigo actual: " + currentEnemy.toString()
                + "\n\t-> Estado:" + gameState.toString();
    }
}
