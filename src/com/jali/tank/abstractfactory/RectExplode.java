package com.jali.tank.abstractfactory;

import com.jali.tank.Audio;
import com.jali.tank.ResourceManager;
import com.jali.tank.TankFrame;

import java.awt.*;

/**
 * 方块形状的爆炸
 * @author lijiang
 * @create 2020-05-06 23:39
 */
public class RectExplode extends BaseExplode {
    public static int WIDTH = ResourceManager.explodes[0].getWidth();
    public static int HEIGHT = ResourceManager.explodes[0].getHeight();

    private int x,y;

    TankFrame tf = null;

    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        // 爆炸声音
//        new Thread(()->
//                new Audio("audio.explode.wav").play()
//        ).start();
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10*step, 10*step);
        step++;

        if(step >= 15){
            tf.explodes.remove(this);
        }
        g.setColor(color);
    }
}
