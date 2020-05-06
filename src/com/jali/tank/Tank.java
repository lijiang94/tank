package com.jali.tank;

import com.jali.tank.abstractfactory.BaseTank;

import java.awt.*;
import java.util.Random;

/**
 * @author lijiang
 * @create 2020-04-20 22:31
 */
public class Tank extends BaseTank {

    int x;
    int y;
    private boolean living = true;
    Dir dir = Dir.DOWN;
    public static final int SPEED = 5;
    boolean moving = true;
    TankFrame tankFrame;
    private Random random = new Random();
    FireStrategy fireStrategy;

    public static final int WIDTH = ResourceManager.goodTankU.getWidth();
    public static final int HEIGHT = ResourceManager.goodTankU.getHeight();

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height =HEIGHT;
        // 坦克的长方形
        if(group==Group.GOOD){
            String goodFSName = (String)PropertyManager.get("goodFS");

            try {
                fireStrategy = (FireStrategy)Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            fireStrategy = new DefaultFireStrategy();
        }
    }


    public void paint(Graphics g) {
        if(!this.living){
            tankFrame.tanks.remove(this);
            return;
        }

        // 改成画图片
        switch (dir){
            case LEFT:
                g.drawImage(this.group == Group.GOOD?ResourceManager.goodTankL:ResourceManager.badTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD?ResourceManager.goodTankR:ResourceManager.badTankR,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD?ResourceManager.goodTankU:ResourceManager.badTankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD?ResourceManager.goodTankD:ResourceManager.badTankD,x,y,null);
                break;
        }
        move();
    }

    private void move() {
        if(this.group == Group.BAD && random.nextInt(10) > 8){
            this.fire();
        }
        if(!moving){
            return;
        }
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        if(group==Group.BAD && random.nextInt(100) > 95){
            randomDir();
        }
        boundsCheck();

        rect.x = x;
        rect.y = y;
    }

    /**
     * 边界检测
     */
    private void boundsCheck() {
        if(this.x < 2) {
            x = 2;
            dir = Dir.RIGHT;
        }
        if(this.y < 30){
            y = 30;
            dir = Dir.DOWN;
        }
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH){
            x = TankFrame.GAME_WIDTH - Tank.WIDTH;
            dir = Dir.LEFT;
        }
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT){
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
            dir = Dir.UP;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
//        fireStrategy.fire(this);
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for(Dir dir : dirs){
            tankFrame.gf.createBullet(bX,bY,dir,group,tankFrame);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
