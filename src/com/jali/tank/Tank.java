package com.jali.tank;

import java.awt.*;
import java.util.Random;

/**
 * @author lijiang
 * @create 2020-04-20 22:31
 */
public class Tank {

    public Rectangle tankRectangle;
    private int x;
    private int y;
    private boolean living = true;
    Dir dir;
    public static final int SPEED = 5;
    boolean moving = true;
    TankFrame tankFrame;
    private Random random = new Random();
    private Group group;
    Explode explode = new Explode(100,100,tankFrame);

    public static final int WIDTH = ResourceManager.tankD.getWidth();
    public static final int HEIGHT = ResourceManager.tankD.getHeight();

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
        // 坦克的长方形
        tankRectangle = new Rectangle(x, y ,WIDTH, HEIGHT);
    }


    public void paint(Graphics g) {
//        Color color = g.getColor();
//        g.setColor(Color.GREEN);
//        g.fillRect(x,y,50,50);
//        g.setColor(color);
        if(!this.living){
            tankFrame.tanks.remove(this);
            return;
        }

        // 改成画图片
        switch (dir){
            case LEFT:
                g.drawImage(ResourceManager.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.tankR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceManager.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.tankD,x,y,null);
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

        tankRectangle.x = x;
        tankRectangle.y = y;
    }

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
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        switch (dir){
            case UP:
            case LEFT:
                bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2 + 1;
                bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2 + 2;
            break;
            case DOWN:
            case RIGHT:
                bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2 - 1;
                bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2 + 4;
            break;
        }
        tankFrame.bullets.add(new Bullet(bX,bY,dir,this.group,this.tankFrame));
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
