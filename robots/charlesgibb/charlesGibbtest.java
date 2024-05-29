package charlesgibb;

import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.*;


public class charlesGibbtest extends Robot{
    int i = 0; //this is taken from Tracker.java because it has really good tracking
    int count = 0;
    
    public void run() {
        // Prepare gun
		setAdjustGunForRobotTurn(true); // Keep the gun still when we turn
		i = 10; // Initialize i to 10

		// Loop forever
		while (true) {
			// turn the Gun (looks for enemy)
			turnGunRight(i);
			// Keep track of how long we've been looking
			count++;
			// If we've haven't seen our target for 2 turns, look left
			if (count > 2) {
				i = -30;
			}
			// If we still haven't seen our target for 5 turns, look right
			if (count > 5) {
				i = 30;
		    }}
    }
    
    public void onScannedRobot(ScannedRobotEvent e) {
       // This part is taken from Tracker just cause it's so frigging good.
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());

		// If it's close enough, fire!
		if (Math.abs(bearingFromGun) <= 3) {
			turnGunRight(bearingFromGun);
			if (getGunHeat() == 0) {
				fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
			}
		} // otherwise just set the gun to turn.
		// Note:  This will have no effect until we call scan()
		else {
			turnGunRight(bearingFromGun);
	}}

        
    
    
    public void onHitByBullet(HitByBulletEvent e) {
       
        turnGunRight(90);
    }
    
    public void onHitWall() {
        // Avoid the wall by moving away from it
        back(70);
        turnRight(50);
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
    
    

          
	

