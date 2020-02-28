package tutorial;
import bugwars.*;


// Class for storing information of a location, you can compare different micro infos to see
// where is the best place to move
public class MicroInfo {

    int numEnemies;
    int minDistEnemy;
    Location loc;
    UnitController uc;

    public MicroInfo(Location loc, UnitController uc) {
        this.loc = loc;
        this.uc = uc;
        numEnemies = 0;
        minDistEnemy = Integer.MAX_VALUE;
    }

    void update(UnitInfo unit) {
        Location enemyLocation = unit.getLocation();
        int d = enemyLocation.distanceSquared(loc);
        if (d <= unit.getType().getAttackRangeSquared() && !uc.isObstructed(loc, enemyLocation)) numEnemies++;
        if (d < minDistEnemy) minDistEnemy = d;
    }

    boolean imBetterThan(MicroInfo other) {
        if(numEnemies < other.numEnemies) return true;
        if(numEnemies > other.numEnemies) return false;

        return minDistEnemy > other.minDistEnemy;
    }
}
