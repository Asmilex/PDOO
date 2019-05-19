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

    public BetaPowerSpaceStationToUI getUIversion () {
        return new BetaPowerSpaceStationToUI(this);
    }

    public String toString() {
        return "Beta "+super.toString
    }
}
