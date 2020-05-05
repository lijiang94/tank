package com.jali.tank;

/**
 * @author lijiang
 * @create 2020-04-27 9:13
 */
public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            int bX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
            int bY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
            switch (dir){
                case UP:
                case LEFT:
                    bX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2 + 1;
                    bY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2 + 2;
                    break;
                case DOWN:
                case RIGHT:
                    bX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2 - 1;
                    bY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2 + 4;
                    break;
            }
            tank.tankFrame.bullets.add(new Bullet(bX,bY,dir,tank.group,tank.tankFrame));
        }
    }
}
