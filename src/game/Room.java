package game;


// This class represents one room in the adventure game.
public class Room {

	private String description;
	private Enemy enemy;
	private Weapon weapon;
	
	public Room(String description,Enemy enemy,Weapon weapon) {
		this.description = description;
		this.enemy = enemy;
		this.weapon = weapon;
	}
	
	public String getDescription() {
		return description;
	}
	
	
	public boolean hasEnemy() {
		
		return enemy != null && enemy.isAlive();
		
	}
	
	public Enemy getEnemy() {
		return enemy;
	}
	
	
	public boolean hasWeapon() {
		
		return weapon != null;
		
	}

	public Weapon getWeapon() {
		return weapon;
	}


	
}
