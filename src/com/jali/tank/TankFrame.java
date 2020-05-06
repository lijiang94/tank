package com.jali.tank;

import com.jali.tank.abstractfactory.BaseBullet;
import com.jali.tank.abstractfactory.BaseExplode;
import com.jali.tank.abstractfactory.BaseTank;
import com.jali.tank.abstractfactory.factory.DefaultFactory;
import com.jali.tank.abstractfactory.factory.GameFactory;
import com.jali.tank.abstractfactory.factory.RectFactory;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lijiang
 * @create 2020-04-19 14:50
 */
public class TankFrame extends Frame {

    Tank myTank = new Tank(250,400,Dir.UP, Group.GOOD, this);
    public List<BaseBullet> bullets = new ArrayList<>();
    public List<BaseTank> tanks = new ArrayList<>();
    public List<BaseExplode> explodes = new ArrayList<>();

    public GameFactory gf = new DefaultFactory();
//    public GameFactory gf = new RectFactory();

    public static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;

    public TankFrame(){
        this.setVisible(true);
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("沙雕大战");

        this.addKeyListener(new MyKeyListenner());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }


    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(),10,60);
        g.drawString("敌人的数量：" + tanks.size(),10,80);
        g.setColor(color);

        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
    }

    class MyKeyListenner extends KeyAdapter{

        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainDir();
        }

        private void setMainDir() {
            if(!bL && !bR && !bU && !bD){
                myTank.setMoving(false);
            }else{
                myTank.setMoving(true);
                if(bL) myTank.setDir(Dir.LEFT);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bU) myTank.setDir(Dir.UP);
                if(bD) myTank.setDir(Dir.DOWN);
            }
        }
    }
}
