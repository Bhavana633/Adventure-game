package game;

public class Weapon {

	private String name;
	private int bonusDamage;

	public Weapon(String name,int bonusDamage) {
		this.name = name;
		this.bonusDamage = bonusDamage;
	}
	
	public int getBonusDamage() {
		return bonusDamage;
	}
	
	public String getName() {
		return name;
	}
	
}
