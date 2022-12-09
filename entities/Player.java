package entities;

import javafx.scene.image.Image;
import userdata.ButtonLayout;

//import userdata.ButtonLayout;

public class Player extends CharacterEntity {
	
	ButtonLayout buttonLayout = new ButtonLayout();
	
	public Player(
			int team,
			float initXpos, 
			float initYpos, 
			int initXsize, 
			int initYsize,
			Image initLeftImage,
			Image initRightImage) {
		
		super(
			team,
			initXpos, 
			initYpos, 
			initXsize, 
			initYsize,
			true,
			initLeftImage,
			initRightImage);
	}
}

