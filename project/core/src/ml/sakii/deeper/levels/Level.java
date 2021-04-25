package ml.sakii.deeper.levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Level {
	
	public void create();
	public void render(SpriteBatch batch);
	
	public default void dispose() {
	}
	
	public void onTap(float x, float y,int button);
	public default void onDoubleTap(float x, float y) {
		
	}
}
