package com.jali.tank.model;

import com.jali.tank.GameModel;
import com.jali.tank.ResourceManager;

import java.awt.*;

/**
 * @author lijiang
 * @create 2020-04-24 0:26
 */
public class Explode extends GameObject{

    public static int WIDTH = ResourceManager.explodes[0].getWidth();
    public static int HEIGHT = ResourceManager.explodes[0].getHeight();

    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceManager.explodes[step++],x,y,null);
        if(step >= ResourceManager.explodes.length){
            step = 0;
            GameModel.getInstance().remove(this);
        }
    }

}
