package deepspace;

class ShieldBooster{
   private String name;
   private float boost;
   private int uses;

   ShieldBooster(String n, float b, int u) {
      name = n;
      boost = b;
      uses = u;
   }

   ShieldBooster(ShieldBooster s) {
      name = s.name;
      boost = s.boost;
      uses = s.uses;
   }

   public float getBoost() {
      return boost;
   }

   public int getUses() {
      return uses;
   }

   public float uselt() {
      if (uses > 0){
         uses--;
         return boost;
      }
      else
      return 1.0f;
   }
}
