package deepspace;

public enum WeaponType {
    LASER(2.0f),
    MISSILE(3.0f),
    PLASMA(4.0f);

    private float power;

    WeaponType(float potencia) {
        this.power = potencia;
    }

    float getPower() {
        return power;
    }
}