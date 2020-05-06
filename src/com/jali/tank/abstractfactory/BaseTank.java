package com.jali.tank.abstractfactory;

import com.jali.tank.Group;

import java.awt.*;

/**
 * @author lijiang
 * @create 2020-05-06 22:23
 */
public abstract class BaseTank {
    /** 默认 bad 坦克*/
    public Group group = Group.BAD;
    public Rectangle rect = new Rectangle();

    public abstract void paint(Graphics g);

    public Group getGroup(){
        return this.group;
    }

    public abstract void die();

    public abstract int getX();

    public abstract int getY();

}
