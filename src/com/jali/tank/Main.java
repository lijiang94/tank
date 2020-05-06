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
//            tankFrame.tanks.add(new Tank(200 + i * 120,50,Dir.DOWN, Group.BAD ,tankFrame));
            // 使用工厂创建坦克
            tankFrame.tanks.add(tankFrame.gf.createTank(200 + i * 120,50,Dir.DOWN, Group.BAD ,tankFrame));
        }
        // 当坦克都打死了，再出现五辆坦克
//        new Thread(()->{
//            while(true){
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                if(tankFrame.tanks.size()<=0){
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    for (int i = 0; i < 5; i++) {
//                        tankFrame.tanks.add(new Tank(200 + i * 120,50,Dir.DOWN, Group.BAD ,tankFrame));
//                    }
//                }
//            }
//        }).start();

        while(true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
