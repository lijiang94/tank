package com.jali.tank.cor;

import com.jali.tank.model.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * 责任一
 * @author lijiang
 * @create 2020-05-11 23:56
 */
public class ColliderChain implements Collider{
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollide());
        add(new TankWallCollider());
        add(new BulletWallCollider());
    }

    public void add(Collider c){
        colliders.add(c);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if(!colliders.get(i).collide(o1,o2)){
                return false;
            }
        }
        return false;
    }
}