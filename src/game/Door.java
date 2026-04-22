package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Its primary purpose in the universe is to halt the progress of underpaid
 * {@code ContractedWorker}s until they can produce the correct rectangular
 * piece of plastic.
 */
public class Door extends Ground {
    private boolean isUnlocked = false;

    public Door() {
        super('=', "Door");
    }

    /**
     * if the door is unlocked, any actor can step into the door
     * @return true if the door is unlocked, false otherwise.
     */
    public void unlock() {
        this.isUnlocked = true;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return isUnlocked;
    }
}
