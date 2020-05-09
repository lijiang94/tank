package com.jali.tank;

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
    // 子弹
    public List<Bullet> bullets = new ArrayList<>();
    // 敌方坦克
    public List<Tank> tanks = new ArrayList<>();
    // 爆炸数量
    public List<Explode> explodes = new ArrayList<>();

    public GameModel() {
        // 初始化敌方坦克
        int initTankCount = Integer.parseInt(PropertyManager.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(200 + i * 120,50,Dir.DOWN, Group.BAD ,this));
        }
    }

    public void paint(Graphics g){
        // 画左上角的数量
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(),10,60);
        g.drawString("敌人的数量：" + tanks.size(),10,80);
        g.drawString("爆炸数量：" + explodes.size(),10,120);
        g.setColor(color);

        // 画我方坦克
        myTank.paint(g);
        // 画子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        // 画敌方坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        // 画爆炸
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
        // 子弹碰撞
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
    }

    public Tank getMyTank(){
        return myTank;
    }
}
