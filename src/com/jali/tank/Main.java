package com.jali.tank;

/**
 * @author lijiang
 * @create 2020-04-19 14:31
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        while(true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
