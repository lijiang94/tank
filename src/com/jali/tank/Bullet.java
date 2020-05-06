package com.jali.tank;

import com.jali.tank.abstractfactory.BaseBullet;
import com.jali.tank.abstractfactory.BaseTank;

import java.awt.*;

/**
 * @author lijiang
 * @create 2020-04-20 23:18
 */
public class Bullet extends BaseBullet {

    private static final int SPEED = 10;
    public static final int WIDTH = ResourceManager.bulletD.getWidth(), HEIGHT = ResourceManager.bulletD.getHeight();
    private boolean living = true;
    private TankFrame tankFrame;

    // 子弹的长方形
    Rectangle rect = new Rectangle();

    private int x,y;
    private Dir dir;
    private Group group;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
//        bulletRectangle = new Rectangle(this.x, this.y ,WIDTH, HEIGHT);
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        tankFrame.bullets.add(this);
    }

    public void paint(Graphics g) {
        if(!this.living){
            tankFrame.bullets.remove(this);
            return;
        }

        switch (dir){
            case LEFT:
                g.drawImage(ResourceManager.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceManager.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.bulletD,x,y,null);
                break;
        }
        move();
    }

    private void move() {
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
        if(x < 0 || y <0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            this.living = false;
        }
        rect.x = x;
        rect.y = y;
    }

    public void collideWith(BaseTank tank) {
        if(this.group == tank.getGroup()) return;

        // 如果两个长方形相交，说明子弹打中了
        if(rect.intersects(tank.rect)){
            this.die();
            tank.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tankFrame.explodes.add(tankFrame.gf.createExplode(eX, eY, tankFrame));
        }
    }

    private void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
