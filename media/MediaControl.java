package media;

import javafx.stage.Stage;

public class MediaControl extends MediaPlay {
	
	public static void setVolume(double d) {
		MediaPlay.mediaplayer.setVolume(d);
	}
	
	public static double getVolume() {
		double x = MediaPlay.mediaplayer.getVolume();
		return x;
	}


	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	
		
	}

}
