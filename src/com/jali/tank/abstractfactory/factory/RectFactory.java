package com.jali.tank.abstractfactory.factory;

import com.jali.tank.Dir;
import com.jali.tank.Group;
import com.jali.tank.TankFrame;
import com.jali.tank.abstractfactory.*;
import com.jali.tank.abstractfactory.factory.GameFactory;

/**
 * @author lijiang
 * @create 2020-05-06 23:46
 */
public class RectFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new RectTank(x,y,dir,tf,group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x,y,tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new RectBullet(x,y,dir,tf,group);
    }
}
