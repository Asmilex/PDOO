package deepspace;

public class PowerEfficientSpaceStation extends SpaceStation {
    private float EFFICIENCYFACTOR = 1.10f;

    PowerEfficientSpaceStation (SpaceStation estacion) {
        super(estacion);
    }

    @Override
    public float fire () {
        return super.fire() * EFFICIENCYFACTOR;
    }

    @Override
    public float protection () {
        return super.protection() * EFFICIENCYFACTOR;
    }

    public Transformation setLoot (Loot loot) {
        super.setLoot(loot);

        return loot.getEfficient()
            ?   Transformation.GETEFFICIENT
            :   Transformation.NOTRANSFORM;
    }

    @Override
    public String toString() {
        return "Power efficient Space Station:\n" + super.toString();
    }
}