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
			}//this code is slightly modified from the original trackfire code, because I want it to spread out looking further. Also for some reason the original variables broke so I ended up needing to redo things. Also, since we're not trying to target one specific person, I can take out the identification system. 
			// If we still haven't seen our target for 5 turns, look right
			if (count > 5) {
				i = 30;
		    }}
    }
    
    public void onScannedRobot(ScannedRobotEvent e) {
       // This part is taken from Tracker just cause it's so frigging good.
       //absoluteBearing is just the heading that we're at and the heading that the enemy is at. 
		double absoluteBearing = getHeading() + e.getBearing();
        //normalRelativeAngleDegrees finds the angle that it's at and puts it on a scale from 0-1 (based on 0-360) and then it takes abosoluteBearing and subtracts where our gun is to normalize that angle here. 
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());

		// If it's close enough, fire!
        //absolute value of the bearing that we have, if it's less than or equal to three then we turn gun right by that bearing, and the rest just checks heat. 
		if (Math.abs(bearingFromGun) <= 3) {
			turnGunRight(bearingFromGun);
			if (getGunHeat() == 0) {
				fire(3);
			}
            //for this I kinda just looked up how gunheat works and I literally just don't understand a single thing of it so yeah
            else if(getGunHeat()>0 && getGunHeat()<5){
                fire(2);
            }
            else{
                fire(1);
            }
            
		} // otherwise just set the gun to turn.
		// Note:  This will have no effect until we call scan()
        //dude, advancedrobot is just so frigging broken, whenever I go against any of them it's like they're playing a frigging hitman speedrun while I'm struggling on checkers. It's just straight up unfair. 
		else {
			turnGunRight(bearingFromGun);
	}}

        
    
    
    public void onHitByBullet(HitByBulletEvent e) {
       turnLeft(90);
       ahead(50);
    //obviously just turning again and stuff
    }
    
    public void onHitWall() {
        //I want them to turn slightly further than 100 so that they can strafe a bit better. 
       turnLeft(200);
       ahead(50);
    }
    //also wow getting a zero for not being able to explain your code is kinda crazy lol
    // Method to turn the gun towards the last scanned enemy
    public void turnGunToEnemy(ScannedRobotEvent e) {
        double gunTurn = getHeading() + e.getBearing() - getGunHeading();
        //Basically, how this works is it finds our position, adds the enemy's position to that, then subtracts where the gun is to trangulate where the gun should be pointed...
        turnGunRight(normalizeBearing(gunTurn));
        //here is where it's implemented. 
    }
    //originally there was a method here to determine firepower but I don't actually wanna use it cause the math isn't actually my math. 
    public double normalizeBearing(double angle) {
        //again, just normalization again, it's a while loop that subtracts 360 to make the number smol. 
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}
    
    

          
	

