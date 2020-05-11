package com.jali.tank.cor;

import com.jali.tank.model.GameObject;

/**
 * 碰撞器
 * @author lijiang
 * @create 2020-05-10 17:45
 */
public interface Collider {

    void collide(GameObject o1, GameObject o2);
}
