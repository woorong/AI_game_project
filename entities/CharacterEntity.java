package entities;


import java.util.List;

import javafx.scene.image.Image;
import math.Vector;


public class CharacterEntity extends Entity{
	
	public boolean moveLeft;
	public boolean moveRight;
	public boolean moveUp;
	public boolean attacking;
	public float xvelocity = 0;
	public float yvelocity = 0;
	public float xacceleration = 0;
	public float yacceleration = 8;
	public int hit = 0;
	public int lives = 3;
	public float attackCooldown = 0;
	public float dazedCooldown = 0;
	public float jumpCooldown = 0;
	public float deathAnimationTimer = 0;
	public boolean isHumanPlayer;
	public boolean isLookingRight;
	public boolean isDead = false;
	public int deathAnimationImage = 0;
	public Image leftImage;
	public Image rightImage;
	public int team;
	
	public float distanceSquaredToNearestEnemy = 1000000000;
	public CharacterEntity nearestEnemy;
	public Vector vectorToNearestEnemy = new Vector();
	
	public CharacterEntity(
			int initTeam,
			float initXpos, 
			float initYpos, 
			int initXsize, 
			int initYsize,
			boolean initIsHumanPlayer,
			Image initLeftImage,
			Image initRightImage) {
		
		super(initXpos, initYpos, initXsize, initYsize);
		
		team = initTeam;
		isHumanPlayer = initIsHumanPlayer;
		leftImage = initLeftImage;
		rightImage = initRightImage;
	}
	
	
	//public void setHealth(int newHealth) {
	//	health = newHealth;
	//}
	
	public void attack(CharacterEntity entity) {
		entity.dazedCooldown = 0.7f;
		entity.hit += 1;
		
		entity.xvelocity = (1 / distanceSquaredToNearestEnemy * (entity.xpos - xpos) * 2000 * (entity.hit+1) * (entity.hit+1)) + xvelocity;
		entity.yvelocity = (1 / distanceSquaredToNearestEnemy * (entity.ypos - ypos) * 2000 * (entity.hit+1) * (entity.hit+1)) + yvelocity;	
	}
	
	public void updateNearestEnemy(List<CharacterEntity> characterList) {
    	
		float distance = 1000000000;
		CharacterEntity closest = this;
				
    	for (CharacterEntity entity : characterList) {
			
    		//if entity is an enemy 
			if (team != entity.team && entity.isDead == false) { 
				//cheap distance**2 checker
				float distanceSquared = (entity.xpos - xpos) * (entity.xpos - xpos) + (entity.ypos - ypos) * (entity.ypos - ypos);
				if ( distanceSquared < distance) {
					closest = entity;
					distanceSquaredToNearestEnemy = distanceSquared;
					distance = distanceSquared;
				}
			}
    	}
    	
    	vectorToNearestEnemy.x = closest.xpos - xpos;
    	vectorToNearestEnemy.y = closest.ypos - ypos;
    	
    	nearestEnemy = closest;
    	
    }
	
	
	
	//TO DO
	//public boolean isTouching(Entity entity) {
	//	
	//}
}
