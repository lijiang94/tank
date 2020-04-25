package com.jali.tank;

import java.awt.*;

/**
 * @author lijiang
 * @create 2020-04-24 0:26
 */
public class Explode {

    public static int WIDTH = ResourceManager.explodes[0].getWidth();
    public static int HEIGHT = ResourceManager.explodes[0].getHeight();

    private int x, y;
    TankFrame tankFrame = null;
    private int step = 0;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceManager.explodes[step++],x,y,null);
        if(step >= ResourceManager.explodes.length){
            step = 0;
            tankFrame.explodes.remove(this);
        }
    }

}
