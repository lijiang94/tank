package com.jali.tank.model;

import com.jali.tank.*;
import com.jali.tank.strategy.DefaultFireStrategy;
import com.jali.tank.strategy.FireStrategy;
import com.jali.tank.strategy.FourDirFireStrategy;

import java.awt.*;
import java.util.Random;

/**
 * @author lijiang
 * @create 2020-04-20 22:31
 */
public class Tank extends GameObject{

    Rectangle tankRectangle;
    private boolean living = true;
    public Dir dir;
    public static final int SPEED = 3;
    boolean moving = true;
    public GameModel gameModel;
    private Random random = new Random();
    public Group group;
    FireStrategy fireStrategy;

    public static final int WIDTH = ResourceManager.goodTankU.getWidth();
    public static final int HEIGHT = ResourceManager.goodTankU.getHeight();

    public Tank(int x, int y, Dir dir, Group group, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gameModel = gameModel;
        // 坦克的长方形
        tankRectangle = new Rectangle(x, y ,WIDTH, HEIGHT);
        if(group==Group.BAD){
            fireStrategy = new DefaultFireStrategy();
        }else{
            fireStrategy = new FourDirFireStrategy();
        }
    }

    @Override
    public void paint(Graphics g) {
        if(!this.living){
            gameModel.remove(this);
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
        if(this.group == Group.BAD && random.nextInt(100) > 95){
            this.fire();
        }
        if(!moving){
            return;
        }
        beforeX = x;
        beforeY = y;
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

        tankRectangle.x = x;
        tankRectangle.y = y;
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
        fireStrategy.fire(this);
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

    public Rectangle getTankRectangle() {
        return tankRectangle;
    }

    public void setTankRectangle(Rectangle tankRectangle) {
        this.tankRectangle = tankRectangle;
    }

    public void rebound(){
        this.x = beforeX;
        this.y = beforeY;
//        switch (dir){
//            case UP:
//                this.dir = Dir.DOWN;
//                break;
//            case DOWN:
//                this.dir = Dir.DOWN;
//                break;
//            case LEFT:
//                this.dir = Dir.RIGHT;
//                break;
//            case RIGHT:
//                this.dir = Dir.LEFT;
//                break;
//        }
    }

}
