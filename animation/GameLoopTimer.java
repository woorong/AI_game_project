package animation;

import javafx.animation.AnimationTimer;

public abstract class GameLoopTimer extends AnimationTimer {
	
	boolean isActive;
	long lastFrameTimeNanos;
	
	@Override
    public void start() {
        super.start();
        isActive = true;
    }
	
	@Override
    public void stop() {
        super.stop();
        isActive = false;
    }
	
	@Override
	public void handle(long now) {
		if (isActive) {
			float secondsSinceLastFrame = (float) ((now - lastFrameTimeNanos) / 1e9);
			//if (secondsSinceLastFrame * 400 > 1) { frame rate limiter
			//System.out.println(secondsSinceLastFrame);
			lastFrameTimeNanos = now;
	        tick(secondsSinceLastFrame);
			
			
		}
	}
	
	public abstract void tick(float secondsSinceLastFrame);

}
