package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.behaviours.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.TreeMap;

// NEW: hostile NPC. TreeMap keeps behaviours ordered by integer key;
// lower key = higher priority. Attack is tried first; if no worker is
// adjacent, falls back to wander.
public class Undead extends Actor {
    private final TreeMap<Integer, Behaviour<Actor, ? extends Action>> behaviours = new TreeMap<>();

    public Undead() {
        super("Undead", 'Ѫ', 15, new BasicInventory());
        this.setIntrinsicWeapon(new BareHand()); // 1 damage, 10% hit rate
        behaviours.put(1, new AttackBehaviour()); // priority 1 = highest
        behaviours.put(2, new WanderBehaviour()); // priority 2 = fallback
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) { // handles death by poison/fire
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