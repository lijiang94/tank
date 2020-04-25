package com.jali.tank;

/**
 * @author lijiang
 * @create 2020-04-19 14:31
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        int initTankCount = Integer.parseInt(PropertyManager.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            tankFrame.tanks.add(new Tank(200 + i * 120,50,Dir.DOWN, Group.BAD ,tankFrame));
        }

        while(true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
