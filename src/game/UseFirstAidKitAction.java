package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorStatistics;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.statistics.StatisticOperations;

public class UseFirstAidKitAction extends Action {
    private final FirstAidKit firstAidKit;

    public UseFirstAidKitAction(FirstAidKit firstAidKit) {
        this.firstAidKit = firstAidKit;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // BaseStatistic.increaseMaximum() also sets current HP = new max, so full restore is free
        actor.modifyStatisticMaximum(ActorStatistics.HEALTH, StatisticOperations.INCREASE, 1);
        firstAidKit.startCooldown();
        return actor + " uses First Aid Kit. Max HP +1, health restored to full. (20-turn cooldown)";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses First Aid Kit (+1 max HP, restore to full)";
    }
}
