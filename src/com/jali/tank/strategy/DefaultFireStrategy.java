package com.jali.tank.strategy;

import com.jali.tank.GameModel;
import com.jali.tank.model.Bullet;
import com.jali.tank.model.Tank;

/**
 * @author lijiang
 * @create 2020-04-27 9:11
 */
public class DefaultFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        int bX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        switch (tank.dir){
            case UP:
            case LEFT:
                bX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2 + 1;
                bY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2 + 2;
                break;
            case DOWN:
            case RIGHT:
                bX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2 - 1;
                bY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2 + 4;
                break;
        }
        new Bullet(bX,bY,tank.dir,tank.group);
    }
}
