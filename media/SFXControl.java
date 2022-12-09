package media;

//import entities.CharacterEntity;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
//import media.MediaPlay;

public class SFXControl extends MediaPlay {
	
	public static void setVolume(double d) {
		MediaPlay.mediaplayerjump.setVolume(d);
		MediaPlay.mediaplayerhit.setVolume(d);
		MediaPlay.mediaplayerdeath.setVolume(d);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	
		
	}

}
