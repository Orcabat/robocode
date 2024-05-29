package charlesgibb
import robocode.robot;
public class charlesgibb extends Robot{
    public void run(){
       
}



public void run(){
    /*while(getHeading()!=180 || getHeading()!=90){
        turnRight(3);
        fire(1);
    }
    if(getHeading() == 180){
        turnRight(90);
    }/* */
    
    int counter = 0;
    while (true) {
        counter = counter+3;
        if(counter%1 == 0){
            turnLeft(30);
            
        }
        if(counter%2 == 0){
            turnLeft(10);
           
        }

        
        
        
        
    }
    
}
public void onScannedRobot(ScannedRobotEvent e) {
double angleToFireRobot = getHeading() + e.getBearing();
turnLeft(e.getBearing());
ahead(50);
scan();
fire(2);
}
public void onHitRobot(HitRobotEvent e) {
    fire(3);
    
}
public void onHitByBullet(HitByBulletEvent e) {
    back(100);

    
}























public void run() {
    while (true) {
        turnGunRight(360); // Continuously scan for enemies
    }
}

public void onScannedRobot(ScannedRobotEvent e) {
    // Update the enemyBearing with the bearing of the scanned enemy
    enemyBearing = e.getBearing();

    // Rotate the gun to target the enemy
    turnGunToEnemy();

    // Fire at the enemy with increased power as it gets closer
    fireAtEnemy(e.getDistance());
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
public void turnGunToEnemy() {
    double gunTurn = getHeading() + enemyBearing - getGunHeading();
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

