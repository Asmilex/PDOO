package deepspace;

import java.util.ArrayList;

public class SpaceCity extends SpaceStation {
    private SpaceStation base;
    private ArrayList<SpaceStation> collaborators;

    public SpaceCity (SpaceStation basico, ArrayList<SpaceStation> resto) {
        super(basico);
        this.base = basico;

        for (SpaceStation estacion : resto)
            collaborators.add(estacion);
    }

    public ArrayList<SpaceStation> getCollaborators () {
        return collaborators;
    }

    @Override
    public float fire () {
        float disparo = base.fire();

        for (SpaceStation colaboradores : collaborators)
            disparo += colaboradores.fire();

        return disparo;
    }

    @Override
    public float protection () {
        float muralla = base.protection();

        for (SpaceStation colaboradores : collaborators)
            muralla += colaboradores.protection();

        return muralla;
    }

    public Trasnformation setLoot (Loot loot) {
        base.setLoot(loot);
        return Transformation.NOTRANSFORM;
    }

    @Override
    public String toString() {
        return SpaceCityToUI(this);
    }
}