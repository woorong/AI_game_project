package rendering;

import java.util.ArrayList;
import java.util.List;

import entities.CharacterEntity;
import entities.CountDown;
import entities.StaticRectEntity;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Renderer {
	Canvas canvas;
	GraphicsContext context;
	
	List<StaticRectEntity> staticRectEntities = new ArrayList<>();
	List<CharacterEntity> characterEntities = new ArrayList<>();
	Image[] deathAnimation = {
			new Image(getClass().getResourceAsStream("/resources/Explosion1.png")),
			new Image(getClass().getResourceAsStream("/resources/Explosion2.png")),
			new Image(getClass().getResourceAsStream("/resources/Explosion3.png")),
			new Image(getClass().getResourceAsStream("/resources/Explosion4.png")),
			new Image(getClass().getResourceAsStream("/resources/Explosion5.png")),
			new Image(getClass().getResourceAsStream("/resources/Explosion6.png")),
			new Image(getClass().getResourceAsStream("/resources/Explosion7.png")),
			new Image(getClass().getResourceAsStream("/resources/Explosion8.png")),
			new Image(getClass().getResourceAsStream("/resources/Explosion9.png")),
			new Image(getClass().getResourceAsStream("/resources/Explosion10.png")),
			new Image(getClass().getResourceAsStream("/resources/Explosion11.png")),
			new Image(getClass().getResourceAsStream("/resources/Explosion12.png")),
			new Image(getClass().getResourceAsStream("/resources/Explosion13.png")),
			new Image(getClass().getResourceAsStream("/resources/Explosion14.png")),
			new Image(getClass().getResourceAsStream("/resources/Explosion15.png")),
	};
	
	//Text text = new Text();
	Font font = new Font("Bauhaus 93", 60);
	Font font2 = new Font("Bauhaus 93", 200);
	
	Color black = new Color(0, 0, 0, 1);
	Color[] teams = {new Color(0.6, 0.2, 0.7, 1.0), new Color(0.2, 0.4, 0.7, 1.0), new Color(0.5, 0.7, 0.5, 1.0), new Color(0.9, 0.4, 0.8, 1.0), new Color(0.8, 0.8, 0.1, 1.0)};
	
	float timer = 0;
	
	public boolean pauseState = true;
	public CountDown counter = new CountDown();
	
	public void addStaticRectEntity(StaticRectEntity entity) {
		staticRectEntities.add(entity);
	}
	
	public void addCharacterEntity(CharacterEntity entity) {
		characterEntities.add(entity);
	}
	
	public List<CharacterEntity> getCharacterList() {
		return characterEntities;
	}
	
	public Renderer(Canvas canvas) {
		this.canvas = canvas;
		this.context = canvas.getGraphicsContext2D();
	}
	
	public void render() {
		context.save();
		
		for (StaticRectEntity entity : staticRectEntities) {
			context.setFill(entity.color);
			context.fillRect(entity.xpos, entity.ypos, entity.xsize, entity.ysize);
		}
		
		
		
		int j = 0;
		for (CharacterEntity entity : characterEntities) {
			context.setFill(teams[entity.team]);
			if (!entity.isDead) {
				if (entity.isLookingRight) {
					context.drawImage(entity.rightImage, entity.xpos+2, entity.ypos, entity.xsize, entity.ysize);
					context.fillPolygon(new double[]{entity.xpos+35, entity.xpos+55, entity.xpos+45}, new double[]{entity.ypos-30, entity.ypos-30, entity.ypos-10}, 3);
				}
				else {
					context.drawImage(entity.leftImage, entity.xpos-2, entity.ypos, entity.xsize, entity.ysize);
					context.fillPolygon(new double[]{entity.xpos+45, entity.xpos+65, entity.xpos+55}, new double[]{entity.ypos-30, entity.ypos-30, entity.ypos-10}, 3);
				}
				
			}
			else {
				int i = (int) (entity.deathAnimationTimer * 30);
				if (i < 15) {
					//if (entity.ypos < -90) {
					//	context.drawImage(deathAnimation[i], entity.xpos, entity.ypos, 200, 200);
					//}
					if (entity.xpos > 1900) {
						context.drawImage(deathAnimation[i], 1800, entity.ypos-90, 200, 200);
					}
					else {
						context.drawImage(deathAnimation[i], -100, entity.ypos-90, 200, 200);
					}
				}
			}
			
			if (entity.isHumanPlayer) {
				context.setFont(font);
				
				context.fillText(entity.lives + "", 100, 70);
				context.drawImage(entity.rightImage, 40, 10, 75, 75);
			}
			else {
				context.setFont(font);
				context.fillText(entity.lives + "", 1800 - j * 150, 70);
				context.drawImage(entity.rightImage, 1740 - j * 150, 10, 75, 75);
				j++;
			}
			
			
			
		}
		
		if (pauseState) {
			context.setFont(font2);
			context.setFill(black);
			context.fillText(counter.getCount() + "", 900, 410);
		}
		
		context.restore();
	}
	
	public void prepare() {
        //context.setFill(new Color(0.2, 0.4, 0.7, 1.0) );
        //context.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
		context.drawImage(new Image(getClass().getResourceAsStream("/resources/Background.png")), 0, 0, canvas.getWidth(), canvas.getHeight());
    }
	
	
}
