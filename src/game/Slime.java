package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.behaviours.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.TreeMap;

// NEW: non-hostile NPC. Eats Consumable ground items first; wanders if none
// are present. Gets poisoned/burned just like a worker would.
public class Slime extends Actor {
    private final TreeMap<Integer, Behaviour<Actor, ? extends Action>> behaviours = new TreeMap<>();

    public Slime() {
        super("Slime", '⍾', 25, new BasicInventory());
        behaviours.put(1, new EatBehaviour());    // priority 1 = eat if food present
        behaviours.put(2, new WanderBehaviour()); // priority 2 = fallback
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            this.unconscious(map);
            return new DoNothingAction();
        }
        for (Behaviour<Actor, ? extends Action> behaviour : behaviours.values()) {
            Action action = behaviour.operate(this, map.locationOf(this));
            if (action != null) return action;
        }
        return new DoNothingAction();
    }
}