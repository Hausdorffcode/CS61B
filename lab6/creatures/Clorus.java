package creatures;
import huglife.*;
import java.awt.Color;
import java.util.Map;
import java.util.List;

/**
 * Created by huangqiming on 2017/1/22.
 */
public class Clorus extends Creature {

    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;

    /** creates clorus with energy equal to E. */
    public Clorus(double e){
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /** creates a clorus with energy equal to 1. */
    public Clorus(){
        this(1);
    }

    public Color color(){
        return color(r,g,b);
    }

    public void move(){
        energy -= 0.03;
    }

    public void stay(){
        energy -= 0.01;
    }

    public void attack(Creature c){
        energy += c.energy();
    }

    public Clorus replicate(){
        energy = energy / 2;
        return new Clorus(energy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors){
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if(empties.size() == 0){
            return new Action(Action.ActionType.STAY);
        }
        else if(plips.size() > 0){
            Direction attackDir = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, attackDir);
        }
        else if(energy >= 1){
            Direction repDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, repDir);
        }
        Direction movDir = HugLifeUtils.randomEntry(empties);
        return new Action(Action.ActionType.MOVE, movDir);
    }

}
