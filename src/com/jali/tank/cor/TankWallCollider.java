package com.jali.tank.cor;

import com.jali.tank.model.GameObject;
import com.jali.tank.model.Tank;
import com.jali.tank.model.Wall;

/**
 * @author lijiang
 * @create 2020-05-14 22:37
 */
public class TankWallCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall){
            Tank t = (Tank)o1;
            Wall w = (Wall)o2;
            if(t.rect.intersects(w.rect)){
                t.rebound();
            }
        }else if(o1 instanceof Wall && o2 instanceof Tank){
            return collide(o2,o1);
        }
        return true;
    }
}
