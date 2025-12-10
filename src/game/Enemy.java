package game;


public class Enemy {

//	private is not given here because Goblin and Dragon need access to these variables.
//	so protected is the correct choice.
	
	protected String type;
	protected int health;
	protected int attackPower;
//	protected Random rand = new Random();
	
	
	public Enemy(String type,int health,int attackPower) {
		
		this.type = type;
        this.health = health;
        this.attackPower = attackPower;
		
	}
	
	public String getType() {
		return type;
	}

	public int getHealth() {
		return health;
	}
	
	public boolean isAlive() {
		return health > 0;
	}

	public void takeDamage(int damage) {
		
		health =- health - damage;
		if(health < 0) health = 0;
		System.out.println(type + " took " +damage+" damage. ");
		
	}
	
	public void attack(player player) {
		
		int damage = attackPower;
		System.out.println(type + " attacks " + player.getName() +
                " for " + damage + " damage.");
		player.takeDamage(damage);
		
	}
	

	
}
