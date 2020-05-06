package com.jali.tank.abstractfactory.factory;

import com.jali.tank.Dir;
import com.jali.tank.Group;
import com.jali.tank.TankFrame;
import com.jali.tank.abstractfactory.BaseBullet;
import com.jali.tank.abstractfactory.BaseExplode;
import com.jali.tank.abstractfactory.BaseTank;

/**
 * @author lijiang
 * @create 2020-05-06 22:41
 */
public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);
}
