package com.jali.tank;

import com.jali.tank.cor.BulletTankCollider;
import com.jali.tank.cor.TankTankCollide;
import com.jali.tank.model.GameObject;
import com.jali.tank.model.Tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 抽象出GameModel，将Model和View分离
 * GameModel作为Facade，负责与Frame打交道，同时负责内部事务
 * @author lijiang
 * @create 2020-05-09 22:56
 */
public class GameModel {

    // 我方坦克
    Tank myTank = new Tank(250,400,Dir.UP, Group.GOOD, this);

    List<GameObject> objects = new ArrayList<>();
    BulletTankCollider collider = new BulletTankCollider();
    TankTankCollide tankTankCollide = new TankTankCollide();

    public GameModel() {
        // 初始化敌方坦克
        int initTankCount = Integer.parseInt(PropertyManager.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            objects.add(new Tank(i * 80,50,Dir.DOWN, Group.BAD ,this));
        }

        objects.add(myTank);
    }

    /**
     * 添加
     * @param gameObject
     */
    public void add(GameObject gameObject){
        this.objects.add(gameObject);
    }

    /**
     * 移除
     * @param gameObject
     */
    public void remove(GameObject gameObject){
        this.objects.remove(gameObject);
    }

    public void paint(Graphics g){
        // 画左上角的数量
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.setColor(color);

        // 画我方坦克
        myTank.paint(g);

        // 画去其他物体
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        // 互相碰撞
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                collider.collide(o1,o2);
                tankTankCollide.collide(o1,o2);
            }
        }

    }

    public Tank getMyTank(){
        return myTank;
    }
}
