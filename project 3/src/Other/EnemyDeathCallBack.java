package Other;

import Tile.Unit.Enemy.Enemy;

public interface EnemyDeathCallBack {
    void call(Enemy unit, boolean usedAbility);
}
