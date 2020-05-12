package com.jali.tank.cor;

import com.jali.tank.model.GameObject;
import com.jali.tank.model.Tank;

/**
 * @author lijiang
 * @create 2020-05-10 23:45
 */
public class TankTankCollide implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank) {
            Tank tank1 = (Tank) o1;
            Tank tank2 = (Tank) o2;
            if (tank1.getTankRectangle().intersects(tank2.getTankRectangle())){
                tank1.rebound();
                tank2.rebound();
            }
        }
        return true;
    }
}
