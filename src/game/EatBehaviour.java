package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.behaviours.Behaviour;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

// NEW: used by Slime (priority 1). location.getItemsAs(Consumable.class)
// uses GameEntity.asCapability() which returns items that implement the
// Consumable interface — so only Apple, Cookies, Flask qualify.
public class EatBehaviour implements Behaviour<Actor, EatAction> {

    @Override
    public EatAction operate(Actor actor, Location location) {
        List<Consumable> consumables = location.getItemsAs(Consumable.class);
        if (!consumables.isEmpty()) {
            return new EatAction(consumables.get(0));
        }
        return null;
    }
}