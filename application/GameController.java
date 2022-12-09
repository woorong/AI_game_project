package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import animation.GameLoopTimer;

import entities.AIEntity;
import entities.CharacterEntity;
import entities.Player;
import entities.StaticRectEntity;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import rendering.Renderer;

import userdata.KeyPolling;

public class GameController implements Initializable {
	public Canvas gameCanvas;
    public AnchorPane gameAnchor;
    
    KeyPolling keys = KeyPolling.getInstance();

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    	initialiseCanvas();
    	
    	Renderer renderer = new Renderer(this.gameCanvas);
    	
    	
    	renderer.addCharacterEntity(new Player(Main.getColoursOfPlayers()[0], 300, 500, 100, 100,
    			new Image(getClass().getResourceAsStream("/resources/LeftIdle.png")),
    			new Image(getClass().getResourceAsStream("/resources/RightIdle.png"))));
    	
    	renderer.addCharacterEntity(new AIEntity(Main.getColoursOfPlayers()[1], 600, 500, 100, 100,
    			new Image(getClass().getResourceAsStream("/resources/LeftIdle2.png")),
    			new Image(getClass().getResourceAsStream("/resources/RightIdle2.png"))));
    	
    	renderer.addCharacterEntity(new AIEntity(Main.getColoursOfPlayers()[2], 900, 500, 100, 100,
    			new Image(getClass().getResourceAsStream("/resources/LeftIdle2.png")),
    			new Image(getClass().getResourceAsStream("/resources/RightIdle2.png"))));
    	
    	renderer.addCharacterEntity(new AIEntity(Main.getColoursOfPlayers()[3], 1200, 500, 100, 100,
    			new Image(getClass().getResourceAsStream("/resources/LeftIdle2.png")),
    			new Image(getClass().getResourceAsStream("/resources/RightIdle2.png"))));
    	
    	renderer.addCharacterEntity(new AIEntity(Main.getColoursOfPlayers()[4], 1500, 500, 100, 100,
    			new Image(getClass().getResourceAsStream("/resources/LeftIdle2.png")),
    			new Image(getClass().getResourceAsStream("/resources/RightIdle2.png"))));

    	
    	renderer.addStaticRectEntity(new StaticRectEntity(0, 880, 1920, 20, new Color(0, 0, 0, 1.0)));
    	
    	List<CharacterEntity> characterList = renderer.getCharacterList();
    	
    	
    	
    	GameLoopTimer timer = new GameLoopTimer() {
    		@Override
    		public void tick(float secondsSinceLastFrame) {
    			
    			renderer.prepare();
    			
    			if (renderer.pauseState) {
    				if (secondsSinceLastFrame < 1) { 
    					renderer.counter.timer += secondsSinceLastFrame;
    				}
    				if (renderer.counter.getCount() == 0) {
    					renderer.pauseState = false;
    				}
    			}
    			else {
    			
    				for (CharacterEntity entity : characterList) {
    				
    					entity.updateNearestEnemy(characterList);
    				
    					//if (!entity.nearestEnemy.isDead) {
    				
    					if (entity.isHumanPlayer) { 
    						updatePlayerMovement(entity, secondsSinceLastFrame);
    					}
    					else {
    						updateAIMovement((AIEntity) entity, secondsSinceLastFrame, characterList);
    					}
    				
    			
    					updateCharacterMovement(entity, secondsSinceLastFrame);
    				
    					//}
    			
    				}
    			}

                renderer.render();
            }
        };
        
        timer.start();
    }
    
    private void initialiseCanvas() {
    	gameCanvas.widthProperty().bind(gameAnchor.widthProperty());
        gameCanvas.heightProperty().bind(gameAnchor.heightProperty());
    }
    
    
    
    private void updateAIMovement(AIEntity entity, float frameDuration, List<CharacterEntity> characterList) {
    	entity.moveUp = false;
    	entity.moveRight = false;
    	entity.moveLeft = false;
    	entity.attacking = false;
    	if (entity.nearestEnemy.isDead) {
    		if (entity.xpos < 300) {
    			entity.moveRight = true;
    		}
    		else if (entity.xpos > 1600) {
    			entity.moveLeft = true;
    		}
    	}
    	else {
    		if (entity.vectorToNearestEnemy.x < -35) {
    			entity.moveLeft = true;
    			entity.isLookingRight = false;
    		}
    		else if (entity.vectorToNearestEnemy.x > 35) {
    			entity.moveRight = true;
    			entity.isLookingRight = true;
    		}
    		if (entity.vectorToNearestEnemy.y < -100) {
    			if (Math.random() > 0.99) {
    				entity.moveUp = true;
    			}
    		}
    		
    		if (entity.distanceSquaredToNearestEnemy < 2500 && Math.random() > 0.9) {
    			entity.attacking = true;
    		}
    	}
    }

    private void updatePlayerMovement(CharacterEntity entity, float frameDuration) {
        if (keys.isDown(KeyCode.UP)) {
        	entity.moveUp = true;
        }
        else {
        	entity.moveUp = false;
        }

        if (keys.isDown(KeyCode.RIGHT)) {
            entity.moveRight = true;
            entity.isLookingRight = true;
        } 
        else {
        	entity.moveRight = false;
        }
        
        if (keys.isDown(KeyCode.LEFT)) {
            entity.moveLeft = true;
            entity.isLookingRight = false;
        }
        else {
        	entity.moveLeft = false;
        }
        
        if (keys.isDown(KeyCode.SPACE)) {
            entity.attacking = true;
        }
        else {
        	entity.attacking = false;
        }
    }
    
    private void updateCharacterMovement(CharacterEntity entity, float frameDuration) {
    	if(entity.isDead) {
    		entity.deathAnimationTimer += frameDuration;
    		if (entity.deathAnimationTimer > 2 & entity.lives > 0) {
    			entity.deathAnimationTimer = 0;
    			entity.xpos = (int)(Math.random()*1000+200);
    			entity.xvelocity = 0;
    			entity.yvelocity = 0;
    			entity.hit = 0;
    			entity.ypos = -100;
    			entity.isDead = false;
    		}
    		return;
    	}
    	if(entity.dazedCooldown != 0) {
    		entity.dazedCooldown = Math.max(entity.dazedCooldown-frameDuration, 0);
    	}
    	if(entity.jumpCooldown != 0) {
    		entity.jumpCooldown = Math.max(entity.jumpCooldown-frameDuration, 0);
    	}
    	if(entity.attackCooldown != 0) {
    		entity.attackCooldown = Math.max(entity.attackCooldown-frameDuration, 0);
    	}
    	
    	entity.xacceleration = 0;
    	
    	if (entity.xvelocity > 0) {
			entity.xacceleration -= 2;
		}
		if (entity.xvelocity < 0) {
			entity.xacceleration += 2;
		}
		
    	if (entity.dazedCooldown == 0) {
    	
    		if (entity.moveUp & entity.jumpCooldown == 0) {
    			entity.yvelocity = -1000;
    			entity.jumpCooldown = 0.5f;
    		}
    		
    		if (entity.moveRight & entity.xvelocity < 400) {
    			entity.xacceleration += 10;
    		}
    		
    		if (entity.moveLeft & entity.xvelocity > -400) {
    			entity.xacceleration -= 10;
    		}
    		
    		if (entity.xvelocity < 2 & entity.xvelocity > -2) {
    			entity.xvelocity = 0;
    		}
    		
    		if (entity.attacking && entity.attackCooldown == 0) {
    			entity.attackCooldown = 0.1f;
    			if (entity.distanceSquaredToNearestEnemy < 10000) {
    				entity.attack(entity.nearestEnemy);
    			}
    		}
    	
    	}
    	
    	entity.xvelocity += entity.xacceleration;
    	entity.yvelocity += entity.yacceleration;
    	
    	if (frameDuration < 1) {
    		entity.xpos += entity.xvelocity * frameDuration;
    	
    		entity.ypos += entity.yvelocity * frameDuration;
    	}
    	
    	if (entity.ypos > 785) {
    		entity.yvelocity = 0;
			entity.ypos = 785;
		}
    	
    	if (entity.xpos < -100 | entity.xpos > 1900) {
    		if (!entity.isDead) {
    			entity.isDead = true;
    			entity.lives -= 1;
    			if (entity.ypos < -50) {
    				entity.ypos = -50;
    			}
    		}
    	}
    
    }
}