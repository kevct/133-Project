package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The GameWorld class initializes and manipulates 
 * game objects and stores game state data
 */

public class GameWorld extends Observable{
	private Random rand = new Random();
	
	private boolean sound = false; //Tracks whether sound is off or on
	private int runningTime = 0;
	private int clock = 0; //Keeps track of clock
	private int lives = 3; //Keeps track of lives
	private int numBases; //Number of Bases to initialize
	private int numEnergyStations; //Initial number of energy stations to initialize
	private int numNPCs; //Initial number of NPCs to initialize
	private int numDrones; //Initial number of drones to initialize
	private GameObjectCollection objects; //The collection of game objects
	private MapView mv = MapView.getMapView(this); 
	private BGSound bgsound;
	private Sound colSound;
	private Sound esColSound;
	private Sound destroySound;
	private boolean paused;
	
	/**
	* This method initializes the clock to 0
	* and sets the initial lives at 3
	* @param nothing
	* @return nothing
	*/
	public void init() {
		
		numBases = 4; //Setting number of bases to 4
		numNPCs = 4; //Setting number of NPCs to 4
		numDrones = 2;//Setting number of drones to 2
		numEnergyStations = 2; //Setting number of energy stations to 2
		objects = new GameObjectCollection();
		
		objects.add(new Base(80, new Point(100, 100), 1));
		objects.add(new Base(80, new Point(1000, 300), 2));
		objects.add(new Base(80, new Point(50, 1100), 3));
		objects.add(new Base(80, new Point(1100, 1050), 4));
		
		IIterator iterator = objects.getIterator();
		Base initBase = (Base)iterator.getNext(); //Base # 1
		Base secBase = (Base)iterator.getNext(); //Base # 2
		
		for(int i = 0; i < numNPCs; i++) {
			/**
			 * Set NPCs to have a size of 40, an initial point
			 * set around the location of base 1, a max speed of 
			 * 1, an initial energy of 20, an energy 
			 * consumption rate of 0, and a random strategy of either 
			 * attack (0) or reach next base (1)*/
			objects.add(new NPCyborg(40, new Point(initBase.getLocation().getX() + ((i+1)*90), initBase.getLocation().getY() ), 2, 20, 0, rand.nextInt(2), secBase));
		}
		for(int i = 0; i < numDrones; i++) {
			/** 
			 * Randomly set drone to have a size between 65 to 85,
			 *  have its initial point set at a random point
			 *  have its heading set to a direction between 0 and 359 degrees
			 *  and have its speed set between 5 and 15 */
			objects.add(new Drone(rand.nextInt(21)+65, new Point(rand.nextInt(mv.getWidth()), rand.nextInt(mv.getHeight())), rand.nextInt(360), rand.nextInt(11) + 5));
		}
		for(int i = 0; i < numEnergyStations; i++) {
			/**
			 * Set energy station to have a size between 50 and 70 and a 
			 * initial point set between (0,0) and (1000, 1000)*/
			objects.add(new EnergyStation(rand.nextInt(21)+50, new Point(rand.nextInt(mv.getWidth()), rand.nextInt(mv.getHeight()))));
		}
		
		/**
		 * Set player cyborg to have a size of 40, an initial point
		 * set to the location of base 1, a max speed of between 
		 * 2 and 4, an initial energy of 20, and an energy 
		 * consumption rate of 2 */
		PlayerCyborg c;
		objects.add(c = PlayerCyborg.getPlayerCyborg(40, initBase.getLocation(), rand.nextInt(1) + 2, 20, 2));
		c.setDamageLevel(0);
		c.setSpeed(0);
		c.setLastBaseReached(1);
		c.setLocation(initBase.getLocation());
		c.setEnergyLevel(20);
		c.setHeading(0);
		c.setColor(ColorUtil.CYAN);
		
		if(lives == 3) {
			createSounds();
		}
		
		this.setChanged();
		this.notifyObservers(c);
	}
	
	/**
	* The accelerate() method increases the speed of the cyborg
	* acceleration is based on the energy level, the damage level,
	* and the maximum speed
	* @param nothing
	* @return nothing
	*/
	public void accelerate() {
		int accAmount = 2; //Amount of speed to accelerate by
		Cyborg c = null;
		IIterator iterator = objects.getIterator();
		
		//Checks to see if object in ArrayList is a player cyborg and sets c equal to the object
		while(iterator.hasNext()) {
			GameObject g = iterator.getNext();
			if(g instanceof PlayerCyborg) {
				c = (PlayerCyborg)g;
			}
		}
		int maxSpeedDmg = (int)(((100-c.getDamageLevel())/100.0)*c.getMaxSpeed()); //Current possible max speed after damage is considered
		
		//If the current speed is equal to the max speed multiplied by 1-damage level
		if(c.getSpeed() == maxSpeedDmg) {
			System.out.print("Max speed already reached.\n");
		//If the energy level is greater than 0 and the damage level is less than 100
		} else if(c.getEnergyLevel() > 0 && c.getDamageLevel() < 100) {
			c.setSpeed(c.getSpeed()+accAmount);
			//If the speed is greater than or equal to the max speed multiplied by 1-damage level the set the speed back to the max speed
			if(c.getSpeed() > maxSpeedDmg) {
				accAmount = c.getSpeed() - maxSpeedDmg;
				c.setSpeed(maxSpeedDmg);
			}
			//Print the acceleration amount
			System.out.print("Player Cyborg has accelerated by " + accAmount + ". Current speed: " + c.getSpeed() + "\n");
		}
	}
	
	/**
	* The brake() method decreases the speed of the cyborg
	* @param nothing
	* @return nothing
	*/
	public void brake() {
		int brakeAmount = 2; //Amount of speed to decelerate by
		Cyborg c = null;
		IIterator iterator = objects.getIterator();
		
		//Checks to see if object in ArrayList is a player cyborg and sets c equal to the object
		while(iterator.hasNext()) {
			GameObject g = iterator.getNext();
			if(g instanceof PlayerCyborg) {
				c = (PlayerCyborg)g;
			}
		}
		
		if(c.getSpeed() == 0) {
			System.out.print("Speed is already 0.\n");
		}else {
			c.setSpeed(c.getSpeed()-brakeAmount);
			if(c.getSpeed() < 0) {
				brakeAmount += c.getSpeed();
				c.setSpeed(0);
			}
			System.out.print("Cyborg has decelerated by " + brakeAmount + ". Current speed: " + c.getSpeed() + "\n");
		}
	}
	
	/**
	* The steerRight() method changes the steering 
	* direction of the cyborg 10 degrees to the Right
	* @param nothing
	* @return nothing
	*/
	public void steerRight() {
		Cyborg c = null;
		IIterator iterator = objects.getIterator();
		
		//Checks to see if object in ArrayList is a player cyborg and sets c equal to the object
		while(iterator.hasNext()) {
			GameObject g = iterator.getNext();
			if(g instanceof PlayerCyborg) {
				c = (PlayerCyborg)g;
			}
		}
		
		//Checks if the steering direction is greater than 40 degrees
		if(c.getSteeringDirection()+10 <= 40) {
			//If the steering direction + 10 <= 40 degrees then increment the steering direction by 5 degrees
			c.setSteeringDirection(c.getSteeringDirection()+10); 
			System.out.print("Cyborg has turned 10 degrees to the right.\nSteering direction is now " + c.getSteeringDirection() + " degrees.\n");
		}else {
			//If the steering direction is greater than 40 degrees then print an error message
			System.out.print("Error: Cannot steer cyborg more than 40 degrees.\n");
		}
	}
	
	/**
	* The steerLeft() method changes the steering 
	* direction of the cyborg 10 degrees to the left
	* @param nothing
	* @return nothing
	*/
	public void steerLeft() {
		Cyborg c = null;
		IIterator iterator = objects.getIterator();
		
		//Checks to see if object in ArrayList is a player cyborg and sets c equal to the object
		while(iterator.hasNext()) {
			GameObject g = iterator.getNext();
			if(g instanceof PlayerCyborg) {
				c = (PlayerCyborg)g;
			}
		}
		
		//Checks if the steering direction is greater than 40 degrees
		if(c.getSteeringDirection()-10 >= -40) {
			//If the steering direction - 10 >= -40 degrees then decrement the steering direction by 5 degrees
			c.setSteeringDirection(c.getSteeringDirection()-10); 
			System.out.print("Cyborg has turned 10 degrees to the left.\nSteering direction is now " + c.getSteeringDirection() + " degrees.\n");
		}else {
			//If the steering direction is greater than 40 degrees then print an error message
			System.out.print("Error: Cannot steer cyborg more than 40 degrees.\n");
		}
	}
	
	/**
	* The collideCyborg() method increases the damage level
	* of the cyborg to simulate a collision with another cyborg
	* @param nothing
	* @return nothing
	*/
	public void collideCyborg(Cyborg c1, Cyborg c2) {
		//Adds damage to both cyborgs
		if(c1 instanceof PlayerCyborg) {
			c1.setDamageLevel(c1.getDamageLevel()+25);
		}else {
			c1.setDamageLevel(c1.getDamageLevel()+10);
		}
		
		if(c2 instanceof PlayerCyborg) {
			c2.setDamageLevel(c2.getDamageLevel()+25);
		}else {
			c2.setDamageLevel(c2.getDamageLevel()+10);
		}
		//Fades the color of the cyborgs by its damage percentage
		if(c1.getDamageLevel() > 100) {
			c1.setDamageLevel(100);
		}
		if(c2.getDamageLevel() > 100) {
			c2.setDamageLevel(100);
		}
		c1.setColor(ColorUtil.rgb((int)(((100-c1.getDamageLevel())/100.0)*(ColorUtil.red(c1.getColor()))), (int)(((100-c1.getDamageLevel())/100.0)*(ColorUtil.green(c1.getColor()))), (int)(((100-c1.getDamageLevel())/100.0)*(ColorUtil.blue(c1.getColor())))));
		c2.setColor(ColorUtil.rgb((int)(((100-c2.getDamageLevel())/100.0)*(ColorUtil.red(c2.getColor()))), (int)(((100-c2.getDamageLevel())/100.0)*(ColorUtil.green(c2.getColor()))), (int)(((100-c2.getDamageLevel())/100.0)*(ColorUtil.blue(c2.getColor())))));
		//Decrease the max speed of the cyborg by 25% and decreases speed if necessary
		//c.setMaxSpeed((int)(((100-c.getDamageLevel())/100.0)*c.getMaxSpeed()));
		//If the current speed is greater than the max speed multiplied by 1-damage percentage, then set the current speed to the max speed
		if(c1.getSpeed() > (int)(((100-c1.getDamageLevel())/100.0)*c1.getMaxSpeed())) {
			c1.setSpeed((int)(((100-c1.getDamageLevel())/100.0)*c1.getMaxSpeed()));
		}
		if(c2.getSpeed() > (int)(((100-c2.getDamageLevel())/100.0)*c2.getMaxSpeed())) {
			c2.setSpeed((int)(((100-c2.getDamageLevel())/100.0)*c2.getMaxSpeed()));
		}
		//If the cyborg has 100 damage or greater or if the cyborg's max speed after damage is 0, they lose a life and gets reset
		if(c1 instanceof PlayerCyborg) {
			if(c1.getDamageLevel() >= 100 || (int)(((100-c1.getDamageLevel())/100.0)*c1.getMaxSpeed()) <= 0) {
				destroySound.play();
				removeLife();
				System.out.print("\n-----------------------------\n");
				System.out.print("Cyborg has sustained too much damage. World has been reset.\n");
			}
		}else if(c2 instanceof PlayerCyborg) {
			if(c2.getDamageLevel() >= 100 || (int)(((100-c2.getDamageLevel())/100.0)*c2.getMaxSpeed()) <= 0) {
				destroySound.play();
				removeLife();
				System.out.print("\n-----------------------------\n");
				System.out.print("Cyborg has sustained too much damage. World has been reset.\n");
			}
		}
		
		if(sound) {
			colSound.play();
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	* The collideBase() method checks whether the collided base
	* is the next sequential base on the track
	* @param int This is the base's sequence number
	* @return nothing
	*/
	public void collideBase(Cyborg c, Base b) {
		
		//If the base reached is 1 greater than the cyborg's last base reached, update the last base reached
		if(c.getLastBaseReached()+1 == b.getSeqNumber()) {
			c.setLastBaseReached(b.getSeqNumber());
			System.out.print("Collided with next base. Last base reached: " + c.getLastBaseReached() + "\n");
			//If the cyborg's last base reached is equal to or greater than the total number of bases, they win
			if(c instanceof PlayerCyborg && c.getLastBaseReached() >= numBases) {
				System.out.print("Game over, you win! Total time: " + clock + "\n");
				Display.getInstance().exitApplication();
			}else if(c instanceof NPCyborg) {
				if(c.getLastBaseReached() >= numBases) {
					System.out.print("NPC has reached the last base! Game Over!\nTotal time: " + clock + "\n");
					Display.getInstance().exitApplication();
				}else {
					IIterator i = objects.getIterator();
					Base nextBase = null;
					while(i.hasNext()) {
						GameObject g = i.getNext();
						if(g instanceof Base && ((Base)g).getSeqNumber() == ((NPCyborg)c).getLastBaseReached()+1) {
							nextBase = (Base)g;
						}
					}
					
					((NPCyborg)c).setStrategy(new BaseStrategy((NPCyborg)c, nextBase));
				}
			}
		}
		
		this.setChanged();
		this.notifyObservers(c);
	}
	
	/**
	* The collideEnergy() simulates a collision with 
	* an energy station
	* @param nothing
	* @return nothing
	*/
	public void collideEnergy(Cyborg c, EnergyStation e) {	
		if(e.getCapacity() != 0) {
			//Increase energy level of cyborg by the energy station capacity
			c.setEnergyLevel(c.getEnergyLevel() + e.getCapacity());
			//Set chosen energy station capacity to 0
			e.setCapacity(0);
			//Fade color of energy station to dark green
			e.setColor(ColorUtil.rgb(0, 128, 0));
			//Add a new energy station
			objects.add(new EnergyStation(rand.nextInt(21)+50, new Point(rand.nextInt(mv.getWidth()), rand.nextInt(mv.getHeight()))));
			System.out.print("Collided with energy station.\n");
			
			if(sound) {
				esColSound.play();
			}
		
			this.setChanged();
			this.notifyObservers(c);
		}
	}
	
	/**
	* The collideDrone() simulates a collision with a drone
	* @param nothing
	* @return nothing
	*/
	public void collideDrone(Cyborg c, Drone d) {
		//Adds damage to the cyborg
		if(c instanceof PlayerCyborg) {
			c.setDamageLevel(c.getDamageLevel()+13);
		}else {
			c.setDamageLevel(c.getDamageLevel()+5);
		}
		if(c.getDamageLevel() > 100) {
			c.setDamageLevel(100);
		}
		//Fades the color of the cyborg by its damage percentage
		c.setColor(ColorUtil.rgb((int)(((100-c.getDamageLevel())/100.0)*(ColorUtil.red(c.getColor()))), (int)(((100-c.getDamageLevel())/100.0)*(ColorUtil.green(c.getColor()))), (int)(((100-c.getDamageLevel())/100.0)*(ColorUtil.blue(c.getColor())))));
		//Decrease the max speed of the cyborg by 13% and decreases speed if necessary
		//c.setMaxSpeed((int)(((100-c.getDamageLevel())/100.0)*c.getMaxSpeed()));
		//If the current speed is greater than the max speed, then set the current speed to the max speed
		if(c.getSpeed() > (int)(((100-c.getDamageLevel())/100.0)*c.getMaxSpeed())) {
			c.setSpeed((int)(((100-c.getDamageLevel())/100.0)*c.getMaxSpeed()));
		}
		//If the cyborg has 100 damage or greater or if the max speed after damage is 0 or less, they lose a life and gets reset
		if(c instanceof PlayerCyborg) {
			if(c.getDamageLevel() >= 100 || (int)(((100-c.getDamageLevel())/100.0)*c.getMaxSpeed()) <= 0) {
				destroySound.play();
				removeLife();
				System.out.print("\n-----------------------------\n");
				System.out.print("Cyborg has sustained too much damage. World has been reset.\n");
			}
		}
		
		if(sound) {
			colSound.play();
		}

		this.setChanged();
		this.notifyObservers(c);
	}
	
	/**
	* The tick() method increments the time by 1 and updates the game objects
	* @param nothing
	* @return nothing
	*/
	public void tick(int tickrate) {
		runningTime += tickrate;
		if(runningTime % 1000 == 0) { //If a second passes
			clock++; //Increment time by 1 tick
		}
		IIterator iterator = objects.getIterator();
		
		while(iterator.hasNext()) {
			GameObject g = iterator.getNext();
			if(g instanceof Drone) {
				Drone d = (Drone)g;
				//Modifies heading of drone by a random number between -10 to 10 degrees
				d.setHeading(d.getHeading() + 10 - rand.nextInt(21));
				//Moves the drone once the heading is set
				d.move(tickrate);
			}else if(g instanceof PlayerCyborg) {
				PlayerCyborg c = (PlayerCyborg)g;
				//Steers the cyborg by the steering direction
				c.steer();
				//Moves the cyborg once the heading is set
				c.move(tickrate);
				if(runningTime%1000 == 0) {
					//Removes the energy level by the energy consumption rate
					removeEnergy(c);
				}
				//If the cyborg has ran out of energy then reinitialize the world and remove a life
				if(c.getEnergyLevel() <= 0) {
					removeLife();
					System.out.print("\n-----------------------------\n");
					System.out.print("Cyborg has ran out of energy. World has been reset.\n");
				}
			}else if(g instanceof NPCyborg) {
				NPCyborg npc = (NPCyborg)g;
				npc.invokeStrategy();
				npc.move(tickrate);
			}
		}
		System.out.print("Game time has increased by one tick.\n");
		
		//Checks collisions
		iterator = objects.getIterator();
		while(iterator.hasNext()) {
			GameObject thisObject = iterator.getNext();
			if(thisObject instanceof Cyborg) {
				IIterator i = objects.getIterator();
				while(i.hasNext()) {
				GameObject otherObject = i.getNext();
					if(thisObject != otherObject && thisObject.collidesWith(otherObject)) { //If the other object is not itself and it collides with the other object
						if(!thisObject.hasAlreadyCollided(otherObject)) {
							thisObject.addCollision(otherObject);
							otherObject.addCollision(thisObject);
							thisObject.handleCollision(otherObject, this);
						}
					}else {
						thisObject.removeCollision(otherObject);
						otherObject.removeCollision(thisObject);
					}
				}
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	* The display() method displays the number of lives,
	* the clock time, the highest base number reached, the
	* cyborg's current energy level, and the cyborg's current
	* damage level
	* @param nothing
	* @return nothing
	*/
	public void display() {
		Cyborg c = null;
		IIterator iterator = objects.getIterator();
		
		//Checks to see if object in ArrayList is a player cyborg and sets c equal to the object
		while(iterator.hasNext()) {
			GameObject g = iterator.getNext();
			if(g instanceof PlayerCyborg) {
				c = (PlayerCyborg)g;
			}
		}
		
		System.out.print("-----------------------------\n");
		System.out.print("Lives: " + lives + "\nTime: " + clock + "\nHighest Base Reached: " + c.getLastBaseReached() + "\nEnergy Level: " + c.getEnergyLevel() + "\nDamage Level: " + c.getDamageLevel() + "\n");
		System.out.print("-----------------------------\n");
	}
	
	/**
	* The showMap() method outputs the current world
	* @param nothing
	* @return nothing
	*/
	public void showMap() {
		IIterator iterator = objects.getIterator();
		
		System.out.print("-----------------------------\n");
		while(iterator.hasNext()) {
			GameObject g = iterator.getNext();
			System.out.print(g);
		}
		System.out.print("-----------------------------\n");
	}	
	
	/**
	* The getTime() method returns the current time
	* @param nothing
	* @return int this is the time
	*/
	public int getTime() {
		return clock;
	}
	
	/**
	* The getLives() method returns the current lives
	* @param nothing
	* @return int this is the # of lives
	*/
	public int getLives() {
		return lives;
	}
	
	/**
	* The getSound() method returns a string that says whether
	* sound is on or off
	* @param nothing
	* @return String this is the status of the sound
	*/
	public String getSound() {
		return sound ? "ON" : "OFF";
	}
	
	/**
	* The toggleSound() method toggles the sound
	* @param nothing
	* @return nothing
	*/
	public void toggleSound() {
		sound = sound ? false : true;
		if(sound) {
			bgsound.play();
		}else {
			bgsound.pause();
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void pauseSound() {
		if(sound) {
			bgsound.pause();
		}
		paused = true;
		System.out.println("Paused");
	}
	public void playSound() {
		if(sound) {
			bgsound.play();
		}
		paused = false;
		System.out.println("Play");
	}
	public boolean isPaused() {
		return paused;
	}
	
	public void createSounds() {
		bgsound = new BGSound("bgsound.wav");
		colSound = new Sound("crash.wav");
		destroySound = new Sound("explosion.wav");
		esColSound = new Sound("powerup.wav");
	}
	
	/**
	* This method unselects all objects
	* @param nothing
	* @return nothing
	*/
	public void unselectAll() {
		IIterator iterator = objects.getIterator();
		while(iterator.hasNext()) {
			GameObject o = iterator.getNext();
			if(o instanceof Fixed) {
				((Fixed)o).setSelected(false);
			}
		}
	}
	
	public void changeStrategy() {
		GameObject g;
		IIterator iterator = objects.getIterator();
		while(iterator.hasNext()) {
			if((g = iterator.getNext()) instanceof NPCyborg) {
				NPCyborg npc = (NPCyborg)g;
				//If the current npc's strategy is to attack the change the strategy 
				//to reach next base and vice versa
				if(npc.getStrategy() instanceof AttackStrategy) {
					//Finds the next base to be reached by the NPC
					GameObject o;
					Base b = null;
					IIterator baseIterator = objects.getIterator();
					while(baseIterator.hasNext()) {
						if((o = baseIterator.getNext()) instanceof Base) {
							if(((Base)o).getSeqNumber() == npc.getLastBaseReached()+1) {
								b = (Base)o;
							}
						}
					}
					npc.setStrategy(new BaseStrategy(npc, b));
				}else {
					npc.setStrategy(new AttackStrategy(npc));
				}
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	* Gets an iterable list of objects
	* @param nothing
	* @return IIterator
	*/
	public IIterator getObjects() {
		return objects.getIterator();
	}
	
	
	/**
	* The removeEnergy() method decreases the energy of Cyborg
	* @param nothing
	* @return nothing
	*/
	private void removeEnergy(Cyborg c) {
		c.setEnergyLevel(c.getEnergyLevel() - c.getEnergyConsumptionRate());
		this.setChanged();
		this.notifyObservers(c);
	}
	
	/**
	* The removeLife() method removes a life and if there are 
	* no more lives then quit, otherwise reinitialize the world
	* @param nothing
	* @return nothing
	*/
	private void removeLife() {
		lives--;
		if(lives <= 0) { //If there are 0 lives then exit
			System.out.print("\nGame over, you failed!\n\n");
			Display.getInstance().exitApplication();
		}
		init(); //Reinitialize the world
		
		Cyborg c = null;
		IIterator iterator = objects.getIterator();
		
		//Checks to see if object in ArrayList is a player cyborg and sets c equal to the object
		while(iterator.hasNext()) {
			GameObject g = iterator.getNext();
			if(g instanceof PlayerCyborg) {
				c = (PlayerCyborg)g;
			}
		}
		c.setColor(ColorUtil.CYAN);
		this.setChanged();
		this.notifyObservers(c);
	}
}
