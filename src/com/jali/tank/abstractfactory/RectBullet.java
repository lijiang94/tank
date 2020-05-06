package com.jali.tank.abstractfactory;

import com.jali.tank.*;

import java.awt.*;

/**
 * 方块的子弹
 * @author lijiang
 * @create 2020-05-06 22:55
 */
public class RectBullet extends BaseBullet {
    private static final int SPEED = 6;
    public static int WIDTH = ResourceManager.bulletD.getWidth();
    public static int HEIGHT = ResourceManager.bulletD.getHeight();

    Rectangle rect = new Rectangle();

    private int x,y;
    private Dir dir;

    private boolean living = true;
    TankFrame tf = null;
    private Group group = Group.BAD;

    public RectBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        tf.bullets.add(this);
    }

    @Override
    public void paint(Graphics g) {
        if(!living){
            tf.bullets.remove(this);
        }
        Color color = g.getColor();
        g.setColor(group == Group.GOOD ? Color.YELLOW : Color.GRAY);
        g.fillRect(x, y, 20, 20);
        g.setColor(color);

        move();
    }

    private void move() {
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        rect.x = this.x;
        rect.y = this.y;
        // 子弹出了框就死掉了
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living = false;
        }
    }

    @Override
    public void collideWith(BaseTank tank) {
        // 同伙的子弹，没事
        if(this.group == tank.group) return;

        // 判断是否相交，相交就死亡
        if(rect.intersects(tank.rect)){
            // 坦克死亡
            tank.die();
            // 方块子弹死亡，不再画
            this.die();
            // 添加爆炸
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add(tf.gf.createExplode(eX, eY, tf));
        }

    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private void die(){
        this.living = false;
    }
}
