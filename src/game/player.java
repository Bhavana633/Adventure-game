package game;

public class player {
	
	
//	Player HAS-A Weapon
	
	private String name; //name of player
	private int health; //Represents the player's life.Goes down when enemy attacks.Starts at 100(in game.java).
	private int baseAttackPower; //The damage that player deals without any weapon.
	private Weapon weapon;
	
	public player(String name,int health,int baseAttackPower) {
		
		this.name = name;
		this.health = health;
		this.baseAttackPower = baseAttackPower;
		this.weapon = null;
		
	}
	
	public String getName() {
		return name;
	}

	public int getHealth() { 
		return health;
	}

	public boolean isAlive() { //Returns false when player is dead
		return health > 0;
	}

	public void takeDamage(int damage) { //When enemy hits player
		
		health = health - damage;
		if(health < 0) {
			health = 0;
		}
		System.out.println(name + " took " + damage + " damage.");
		
	}
	
	public void attack(Enemy enemy) {
		
		int totalAttack = baseAttackPower;
		
		if(weapon != null) {
			totalAttack += weapon.getBonusDamage();
		}
		
		System.out.println(name + " attacks "+enemy.getType() + " for "+totalAttack+" damage. ");
		enemy.takeDamage(totalAttack);
	}

	
	public void pickWeapon(Weapon weapon) {
		
		this.weapon = weapon;
		
	}
	
	public void showStats() {
	    System.out.println("Name: " + name);
	    System.out.println("Health: " + health);
	    System.out.println("Base Attack: " + baseAttackPower);
	    if (weapon != null) {
	        System.out.println("Weapon: " + weapon.getName() + " (+" + weapon.getBonusDamage() + " dmg)");
	    } else {
	        System.out.println("Weapon: None");
	    }
	}

}
