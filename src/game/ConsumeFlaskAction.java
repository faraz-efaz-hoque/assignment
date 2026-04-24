package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Inventory;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action representing the desperate, mid-combat decision to chug whatever
 * liquid is sloshing around inside a flask.
 * Because nothing cures catastrophic injuries quite like aggressive hydration.
 *
 * @see Action
 */
public class ConsumeFlaskAction extends Action {

    private final Flask flask;

    public ConsumeFlaskAction(Flask flask) {
        this.flask = flask;
    }
    /**
     * When executed, it will check for the actor's inventory whether they are carrying the flask.
     * If so, it will decrease the flask content and heal the actor.
     *
     * @param actor The actor consuming the flask.
     * @param map The map the actor is on.
     * @return the description of the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return flask.consumedBy(actor, map); // MODIFIED: was flask.consume() + actor.heal(1) + return string
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from Flask (" + flask.getRemainingUses() + " uses remaining)";
    }
}
