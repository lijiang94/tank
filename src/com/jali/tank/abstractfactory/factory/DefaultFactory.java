package com.jali.tank.abstractfactory.factory;

import com.jali.tank.*;
import com.jali.tank.abstractfactory.BaseBullet;
import com.jali.tank.abstractfactory.BaseExplode;
import com.jali.tank.abstractfactory.BaseTank;
import com.jali.tank.abstractfactory.factory.GameFactory;

/**
 * @author lijiang
 * @create 2020-05-06 22:45
 */
public class DefaultFactory extends GameFactory {

    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Tank(x, y, dir, group, tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x, y, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Bullet(x, y, dir, group, tf);
    }
}
