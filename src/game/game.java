package game;

import java.util.Scanner;
import java.util.Random;

public class game {

	private player player; //This will store the player's object.Contains health, attack power, weapon, etc.
	private Room[] rooms; //array of 5 rooms.Each room may contain goblin,dragon,weapon or be empty. 
	private int currRoomIndex; // Tracks which room the player is currently in.Starts at 0(room 1).
	private Scanner sc; //To read inputs from the user.
	private Random rand;//Used to generate random enemies/weapons.
	
	public game(Scanner sc) { //constructor
		
		this.sc = sc;
		this.rand = new Random();
		this.currRoomIndex = 0;
		
	}
	
	public void start() {
		
		System.out.println("======= Welcome to the Adventure Game! ========");
		System.out.print("Enter your name : ");
		
		String name = sc.nextLine();
		
		player = new player(name,100,10); //health = 100, baseAttack = 10
		System.out.println("\nHello "+ player.getName() + " ! Your adventure begins...\n");
		
		createRooms(); //Generates 5 random rooms
		
		while(player.isAlive() && currRoomIndex < rooms.length) {
			
			Room currentRoom = rooms[currRoomIndex];
			System.out.println("You enter Room " + (currRoomIndex + 1));
			System.out.println(currentRoom.getDescription());
			
			handleWeapon(currentRoom); //If there is a weapon, the player can pick it up.
			handleEnemy(currentRoom); //If enemy present → fighting happens here.
			
			if(!player.isAlive()) {
				break;
			}
			
			currRoomIndex++; //move to next room
			
			
//			Ask player if they want to continue:
			if(currRoomIndex < rooms.length) {
				System.out.println("\nDo you want to go to the next room? (1 = Yes, 2 = No)");
				int choice = sc.nextInt();
				if(choice != 1) {
					System.out.println("You decided to end your adventure early.");
					break;
				}
			}
			
			
			
		}
		
		System.out.println("\n===== GAME OVER =====");
		if(player.isAlive()) {
			System.out.println("Congratulations " + player.getName() + " You have survived!");
		}
		else {
			System.out.println("You died during the adventure...");
		}
		
		
		System.out.println("\nYour final stats:");
		player.showStats();
	}
	
	
//	CREATING ROOMS - ROOM GENERATOR
	public void createRooms() {
		
		rooms = new Room[5];
		for(int i=0;i<rooms.length;i++) {
			String desc = "A dark and mysterious room "+ (i+1) + ".";
		
	//		initialize empty room
			Enemy enemy = null;
			Weapon weapon = null;
			
			int random = rand.nextInt(3); // only 0,1,2
			
	//		If 0
			if(random == 0) {
				enemy = new Goblin();
				desc += "You sense a aweak presence...a Goblin is here!";
			}
			
			
			
	//		If 1
			else if(random == 1) {
				enemy = new Dragon();
				desc += " A fearsome dragon awaits!";
			}
			
			
	//		If 2
			else {
				weapon = createRandomWeapon();
				desc += " You see something shining...a weapon lies on the floor.";
			}
			
			
	//		Finally create the room
			rooms[i] = new Room(desc,enemy,weapon);
		}
	}
	
	
	
	public Weapon createRandomWeapon() {
		int random = rand.nextInt(3);
		switch(random) {
		
		
//			Here return already exits the method → break is unnecessary.
			case 0: return new Weapon("Sword",10);
			case 1: return new Weapon("Knife",5);
			case 2: return new Weapon("Magic wand",20);
			
			default: return new Weapon("Stick",1);
		}
	}
	
	
	public void handleWeapon(Room room) {
		
		if(room.hasWeapon()) {
			Weapon w = room.getWeapon();
			System.out.println("\nYou found a weapon: " + w.getName() +
                    " (Bonus Damage: " + w.getBonusDamage() + ")");
			System.out.println("Do you want to pick it up? (1 Yes, 2 No)");
			
			int choice = sc.nextInt();
			
			if(choice == 1) {
				player.pickWeapon(w);
				System.out.println("You equipped the " + w.getName() + "!");
			}
			else {
				System.out.println("You ignored the weapon.");
			}
		}
		else {
			System.out.println("\nThere are no weapons in this room.");
		}
	}
	
	
	
	public void handleEnemy(Room room) {
		
		if(!room.hasEnemy()) return;
		
		Enemy enemy = room.getEnemy();
		System.out.println("Your enemy is "+ enemy.getType() );
		System.out.println(enemy+ " has health " + enemy.getHealth());
	
		while(player.isAlive() && enemy.isAlive()) {
			
			System.out.println("\nWhat do you want to do?");
            System.out.println("1. Attack");
            System.out.println("2. Run to next room (risky!)");
			
            int choice = sc.nextInt();
			
			if(choice == 1) {
				
				player.attack(enemy);
				
				if(!enemy.isAlive()) {
					System.out.println("You defeated the "+enemy.getType() + "!");
					break;
				}
				
				enemy.attack(player);
				
				if(!player.isAlive()) {
					System.out.println("You were killed by the "+enemy.getType() + "!");
					break;
				}
				
				System.out.println("\nYour health : "+player.getHealth());
				System.out.println(enemy.getType()+ " health : "+enemy.getHealth());
			}
			
			else if(choice == 2) {
				
				int chance = rand.nextInt(100);
				if(chance < 50) {
					System.out.println("Success!!!You have been escaped.");
				}
				else {
					System.out.println("OOPS!!You have been caught.");
					enemy.attack(player);
					
					if (!player.isAlive()) {
                        System.out.println("You were killed while trying to escape...");
                    }
				}
			}
			
			else {
				System.out.println("Invalid choice. Try again.");
			}
		}
	
	
	
	}
	

}
