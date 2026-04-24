package game;

import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

// NEW: placeholder for the 'o' map tiles. Full creature-spawning behaviour
// is added in REQ3.
public class Hole extends Ground {
    private int turnCount = 0; // MODIFIED: was empty placeholder; now has full spawn logic
    public Hole() {
        super('o', "Hole");
    }

    // MODIFIED: entire method added. Ground.tick(Location) is called by
    // Location.tick() every turn, so this auto-fires without extra wiring.
    @Override
    public void tick(Location location) {
        turnCount++;
        if (turnCount >= 20) {          // every 20 turns
            turnCount = 0;
            if (!location.containsAnActor()) {
                Actor creature = Math.random() < 0.5 ? new Undead() : new Slime(); // 50/50
                try {
                    location.addActor(creature);
                } catch (GameEngineException ignored) { }
            }
        }
    }
}
