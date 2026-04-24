package game;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

// NEW: Undead's natural weapon. Constructor args: damage, verb, hitRate, name.
public class BareHand extends IntrinsicWeapon {
    public BareHand() {
        super(1, "punches", 10, "Bare Fist"); // 1 damage, 10% hit rate
    }
}