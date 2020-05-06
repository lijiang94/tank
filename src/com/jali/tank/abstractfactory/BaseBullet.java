package com.jali.tank.abstractfactory;

import java.awt.*;

/**
 * @author lijiang
 * @create 2020-05-06 22:35
 */
public abstract class BaseBullet {

    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank tank);
}
