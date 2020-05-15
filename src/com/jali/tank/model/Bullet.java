package com.jali.tank.model;

import com.jali.tank.*;

import java.awt.*;

/**
 * @author lijiang
 * @create 2020-04-20 23:18
 */
public class Bullet extends GameObject{

    private static final int SPEED = 6;
    public static final int WIDTH = ResourceManager.bulletD.getWidth(), HEIGHT = ResourceManager.bulletD.getHeight();
    private boolean living = true;

    // 子弹的长方形
    public Rectangle rect;

    private Dir dir;
    private Group group;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rect = new Rectangle(this.x, this.y ,WIDTH, HEIGHT);
        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        if(!this.living){
            GameModel.getInstance().remove(this);
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
        // 改成图片
//        Color color = g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x,y,WIDTH,HEIGHT);
//        g.setColor(color);
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

    public boolean collideWith(Tank tank) {
        if(this.group == tank.getGroup()) return false;

        // 如果两个长方形相交，说明子弹打中了
        if(rect.intersects(tank.rect)){
            this.die();
            tank.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            new Explode(eX,eY);
            return true;
        }
        return false;
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
