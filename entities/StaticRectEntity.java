package entities;

import javafx.scene.paint.Color;

public class StaticRectEntity extends Entity{
	
	public Color color;
	
	public StaticRectEntity(float initXpos, float initYpos, int initXsize, int initYsize, Color initColor) {
		super(initXpos, initYpos, initXsize, initYsize);
		color = initColor;
	}
}
