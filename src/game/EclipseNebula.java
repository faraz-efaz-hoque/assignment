package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Inventory;
import edu.monash.fit2099.engine.positions.DefaultGroundCreator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

import java.util.Arrays;
import java.util.List;

/**
 * This class handles the miracle of creation, translating a bunch of periods
 * and hashtags into a sprawling, functional sci-fi facility.
 */
public class EclipseNebula extends World {
    public EclipseNebula(Display display) {
        super(display);
    }

    /**
     * Initialise maps, actors, items, and grounds of the game world.
     * @throws Exception in case if anything goes wrong...
     */
    public void initialise() throws Exception {
        DefaultGroundCreator groundCreator = new DefaultGroundCreator();
        groundCreator.registerGround('.', Dirt::new);
        groundCreator.registerGround('#', Wall::new);
        groundCreator.registerGround('~', Puddle::new);
        groundCreator.registerGround('_', Floor::new);
        groundCreator.registerGround('=', Door::new);

        List<String> moon99Deprecated = Arrays.asList(
                "....................########################################",
                "...#######..........#__________________#___________________#",
                "...#_____#..........=__________________=___________________#",
                "...#_____=...~......#__________________#___________________#",
                "...#_____#..~~~.....########=#####=#####___#############___#",
                "...#######.~~~~.....#______#_#_________#___#___________#___#",
                ".........~~~~.......#______#_#_________#####___________#####",
                "....................#______=_#_________#___________________#",
                "......~.............#______#_#_________#___________________#",
                ".....~~~............#______#_###########___#############___#",
                ".....~..............#______#___________#___#___________#___#",
                "....................=______#___________=___=___________=___#",
                "....................#______#############___#############___#",
                ".........~~~~.......#______#___________#####################",
                "........~~~~~~......#______#___________=___________________#",
                ".........~~~~.......#______#___________#___________________#",
                "....................#______#############___#############___#",
                "....................#______#___________#___#___________#___#",
                "..~.................#______=___________=___=___________=___#",
                "....................########################################"
        );

        GameMap moon99DeprecatedMap = new GameMap("99-Deprecated", groundCreator, moon99Deprecated);
        this.addGameMap(moon99DeprecatedMap);

        //REQ 1 ship items
        //one instance each, placed inside the armoured ship
        moon99DeprecatedMap.at(5, 3).addItem(new AccessCard());
        moon99DeprecatedMap.at(6, 3).addItem(new FirstAidKit());
        moon99DeprecatedMap.at(7, 3).addItem(new SterilisationBox());

        // Each worker gets their own WeightLimitedInventory(50) and their own Flask
        ContractedWorker contractedWorker1 = new ContractedWorker("#1 Bob",   'ඞ', 10, new WeightLimitedInventory(50));
        ContractedWorker contractedWorker2 = new ContractedWorker("#2 Tom",   'ඞ', 10, new WeightLimitedInventory(50));
        ContractedWorker contractedWorker3 = new ContractedWorker("#3 Sarah", 'ඞ', 10, new WeightLimitedInventory(50));
        ContractedWorker contractedWorker4 = new ContractedWorker("#4 Julie", 'ඞ', 10, new WeightLimitedInventory(50));
        ContractedWorker contractedWorker5 = new ContractedWorker("#5 Rick",  'ඞ', 10, new WeightLimitedInventory(50));

        contractedWorker1.getInventory().add(new Flask());
        contractedWorker2.getInventory().add(new Flask());
        contractedWorker3.getInventory().add(new Flask());
        contractedWorker4.getInventory().add(new Flask());
        contractedWorker5.getInventory().add(new Flask());

        this.addPlayer(contractedWorker1, moon99DeprecatedMap.at(6, 2));
        this.addPlayer(contractedWorker2, moon99DeprecatedMap.at(7, 2));
        this.addPlayer(contractedWorker3, moon99DeprecatedMap.at(8, 2));
        this.addPlayer(contractedWorker4, moon99DeprecatedMap.at(6, 4));
        this.addPlayer(contractedWorker5, moon99DeprecatedMap.at(8, 4));
    }
}
