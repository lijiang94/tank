package com.jali.tank.cor;

import com.jali.tank.model.Bullet;
import com.jali.tank.model.GameObject;
import com.jali.tank.model.Tank;

/**
 * @author lijiang
 * @create 2020-05-10 17:49
 */
public class BulletTankCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bulletObject = (Bullet) o1;
            Tank tankObject = (Tank) o2;
            // TODO 把 collideWith 方法的代码拷贝过来
            if(bulletObject.collideWith(tankObject)){
                return false;
            };
        }else if(o1 instanceof Tank && o2 instanceof Bullet){
            return collide(o2, o1);
        }
        return true;
    }
}
