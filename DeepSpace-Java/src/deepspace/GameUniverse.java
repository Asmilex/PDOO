package deepspace;

class GameUniverse {
   private static int WIN = 10;
   private int currentStationIndex;
   private int turns;
   private GameStateController gameState;
   private Dice dice;
   private EnemyStarShip currentEnemy;
   private SpaceStation currentStation;
   private ArrayList<SpaceStation> spaceStations = new ArrayList<>();

   GameUniverse(){ // FIXME no se si es asi
      gameState = new GameStateController;
      dice = new Dice;
      turns = 0;
   }

   public boolean haveAWinner(){
      if (currentStation.getNMedals() == WIN)
         return true;
      else
         return false;
   }
   
   public void mountShieldBooster(int i){
      if (gameState.state == GameState.INIT or gameState.state == GameState.AFTERCOMBAT) 
         currentStation.mountShieldBooster(i);
   }

   public void mountWeapon(int i){
      if (gameState.state == GameState.INIT or gameState.state == GameState.AFTERCOMBAT)  
         currentStation.mountWeapon(i);
   }

   public void discardShieldBooster(int i){
      if (gameState.state == GameState.INIT or gameState.state == GameState.AFTERCOMBAT)
         currentStation.discardShieldBooster(i);
   }

   public void discardWeapon(int i){
      if (gameState.state == GameState.INIT or gameState.state == GameState.AFTERCOMBAT)
         currentStation.discardWeapon(i);
   }

   public void discardShieldBoosterInHangar(int i){
      if (gameState.state == GameState.INIT or gameState.state == GameState.AFTERCOMBAT) 
         currentStation.discardShieldBoosterInHangar(i);
   }

   public void discardWeaponInHangar(int i){
      if (gameState.state == GameState.INIT or gameState.state == GameState.AFTERCOMBAT)
         currentStation.discardWeaponInHangar(i);
   }
}
