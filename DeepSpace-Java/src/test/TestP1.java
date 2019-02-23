package deepspace;

public class TestP1 {
    public static void main(String[] args) {
        Loot            botin       = new Loot(1,1,1,1,1);
        ShieldBooster   pEscudo     = new ShieldBooster("Ligero", 3.5f, 0);
        Weapon          arma        = new Weapon("Rayo", WeaponType.LASER, 0);
        //Dice            dado        = new Dice();
        SuppliesPackage suministros = new SuppliesPackage(3.0f, 50f, 3.5f);

        System.out.println("WE LIVING");
    }
}