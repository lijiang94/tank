package com.jali.tank.model;

import java.awt.*;

/**
 * 所有物体的父类
 * @author lijiang
 * @create 2020-05-10 16:05
 */
public abstract class GameObject {
    public int x,y;
    public int beforeX,beforeY;

    public abstract void paint(Graphics g);
}
