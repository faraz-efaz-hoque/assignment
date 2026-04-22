package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

// NEW: spawned by Lantern. Location.tick() calls ground.tick() every turn,
// so this automatically burns and counts down without any extra wiring.
public class FireGround extends Ground {
    private int turnsRemaining = 5;
    private final Ground previousGround; // restore this when fire burns out

    public FireGround(Ground previousGround) {
        super('^', "Fire");
        this.previousGround = previousGround;
    }

    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            actor.hurt(1);
            new Display().println(actor + " is burned by the fire! (-1 HP)");
        }
        turnsRemaining--;
        if (turnsRemaining <= 0) {
            location.setGround(previousGround); // revert tile to what was there before
        }
    }
}
