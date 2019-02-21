package deepspace;

public enum WeaponType {
    LASER(2.0),
    MISSILE(3.0),
    PLASMA(4.0);

    private float power;

    WeaponType(float potencia) {
        this.power = potencia;
    }

    float getPower() {
        return power;
    }
}