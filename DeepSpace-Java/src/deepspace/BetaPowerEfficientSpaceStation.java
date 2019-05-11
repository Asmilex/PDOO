package deepspace;

public class BetaPowerEfficientSpaceStation extends PowerEfficientSpaceStation {
    private float EXTRAEFFICIENCY = 1.2f;

    private Dice dice = new Dice();


    BetaPowerEfficientSpaceStation(SpaceStation estacion) {
        super(estacion);
    }

    @Override
    public float fire() {
        return dice.extraEfficiency()
            ?   super.fire() * EXTRAEFFICIENCY
            :   super.fire();
    }

    public String toString() {
        return "Beta :\n" + super.toString();
    }
}