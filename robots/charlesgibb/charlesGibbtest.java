package charlesgibb;

import robocode.*;

import java.awt.*;
import robocode.Robot;

public class charlesGibbtest extends Robot{
    public void run() {
        while (true) {
            turnGunRight(360); // Continuously scan for enemies
        }
    }
    
    public void onScannedRobot(ScannedRobotEvent e) {
        // Fire at the enemy with increased power as it gets closer
        fireAtEnemy(e.getDistance());
        double gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
        scan();
        fire(turnGunAmt);
    }
    
    public void onHitByBullet(HitByBulletEvent e) {
        // Dodge the bullet by moving randomly
        back(100);
        turnRight(90);
    }
    
    public void onHitWall(HitWallEvent e) {
        // Avoid the wall by moving away from it
        back(50);
        turnRight(90);
    }
    
    // Method to turn the gun towards the last scanned enemy
    public void turnGunToEnemy(ScannedRobotEvent e) {
        double gunTurn = getHeading() + e.getBearing() - getGunHeading();
        turnGunRight(normalizeBearing(gunTurn));
    }
    
    // Method to fire at the enemy with increased power as it gets closer
    public void fireAtEnemy(double distance) {
        // Calculate fire power based on distance
        double firePower = Math.min(3, 400 / distance);
    
        // Fire at the enemy
        fire(firePower);
    }
    
    // Method to normalize the bearing between -180 and 180 degrees
    public double normalizeBearing(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}
    
    

          
	

