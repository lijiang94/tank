package com.jali.tank.model;

import com.jali.tank.GameModel;

import java.awt.*;

/**
 * @author lijiang
 * @create 2020-05-14 17:26
 */
public class Wall extends GameObject {

    int w,h;

    public Rectangle rect;

    public Wall(int x,int y,int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rect = new Rectangle(x,y,w,h);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x,y,w,h);
        g.setColor(color);
    }
}