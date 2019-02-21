package deepspace;

class SuppliesPackage{
   private float ammoPower;
   private float fuelUnits;
   private float shieldPower;

   SuppliesPackage(float a,float f,float s){
      ammoPower = a;
      fuelUnits = f;
      shieldPower = s;
   }

   SuppliesPackage(SuppliesPackage s){
      ammoPower = s.getammoPower();
      fuelUnits = s.getfuelUnits();
      shieldPower = s.getshieldPower();
   }

   public float getammoPower(){
      return ammoPower;
   }

   public float getfuelUnits(){
      return fuelUnits;
   }

   public float getshieldPower(){
      return shieldPower;
   }

}
