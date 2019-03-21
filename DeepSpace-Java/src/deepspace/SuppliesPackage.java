package deepspace;

class SuppliesPackage implements Copyable <SuppliesPackage> {
   private float ammoPower;
   private float fuelUnits;
   private float shieldPower;

   SuppliesPackage(float a, float f, float s) {
      ammoPower   = a;
      fuelUnits   = f;
      shieldPower = s;
   }

   SuppliesPackage(SuppliesPackage s) {
      ammoPower   = s.getAmmoPower();
      fuelUnits   = s.getFuelUnits();
      shieldPower = s.getShieldPower();
   }

   @Override
   public SuppliesPackage copy() {
      return new SuppliesPackage(this);
   }

   public float getAmmoPower() {
      return ammoPower;
   }

   public float getFuelUnits() {
      return fuelUnits;
   }

   public float getShieldPower() {
      return shieldPower;
   }

   @Override
   public String toString() {
      return    "-> Gasolina: " + fuelUnits
                + "\n\t-> Potencia: " + ammoPower
                + "\n\t-> Escudos: " + shieldPower;

   }

}
