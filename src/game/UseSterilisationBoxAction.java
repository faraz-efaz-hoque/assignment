package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class UseSterilisationBoxAction extends Action {
    private final SterilisationBox sterilisationBox;

    public UseSterilisationBoxAction(SterilisationBox sterilisationBox) {
        this.sterilisationBox = sterilisationBox;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + " uses the Sterilisation Box.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses Sterilisation Box";
    }
}
