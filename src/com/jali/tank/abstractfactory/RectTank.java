package com.jali.tank.abstractfactory;

import com.jali.tank.*;

import java.awt.*;
import java.util.Random;

/**
 * 方块形状的坦克
 * @author lijiang
 * @create 2020-05-06 23:48
 */
public class RectTank extends BaseTank{
    private static final int SPEED = 2;
    public static int WIDTH = ResourceManager.goodTankU.getWidth();
    public static int HEIGHT = ResourceManager.goodTankU.getHeight();

    private Random random = new Random();

    int x,y;

    Dir dir = Dir.DOWN;

    private boolean moving = true;
    TankFrame tankFrame = null;
    private boolean living = true;
    Group group = Group.BAD;

    FireStrategy fireStrategy;

    public RectTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tf;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        // 根据坦克属性好坏决定开火策略
        if(group == Group.GOOD){
            String goodFSName = PropertyManager.get("goodFS");
            try {
                fireStrategy = (FireStrategy)Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            fireStrategy = new DefaultFireStrategy();
        }
    }

    @Override
    public void paint(Graphics g) {
        if(!living){
            // 死掉，移除，不再画
            tankFrame.tanks.remove(this);
        }
        Color color = g.getColor();
        g.setColor(group == Group.GOOD ? Color.RED: Color.BLUE);
        g.fillRect(x,y,40,40);
        g.setColor(color);
        move();
    }

    private void move() {
        if(!moving){
            return;
        }
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

        // 控制开火间隔
        if(this.group == Group.BAD && random.nextInt(100) > 95){
            this.fire();
        }
        // 随机改变方向
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }
        boundsCheck();
        rect.x = this.x;
        rect.y = this.y;
    }

    /**
     * 随机方向
     */
    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    /**
     * 开火
     */
    private void fire() {
        int bX = this.x + RectTank.WIDTH / 2 - RectBullet.WIDTH / 2;
        int bY = this.y + RectTank.HEIGHT / 2 - RectBullet.HEIGHT / 2;

        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            System.out.println(bX + " " + bY + " " + dir);
            tankFrame.gf.createBullet(bX, bY, dir, group, tankFrame);
        }

//        if (group == Group.GOOD)
//            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
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
        if(this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH){
            x = TankFrame.GAME_WIDTH - RectTank.WIDTH;
            dir = Dir.LEFT;
        }
        if(this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT){
            y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT;
            dir = Dir.UP;
        }
    }

    @Override
    public void die() {
        this.living = false;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
