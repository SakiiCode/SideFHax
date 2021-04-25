package ml.sakii.deeper.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ml.sakii.deeper.Main;

public class Level5 implements Level{

	private Texture finishTexture;
	
	public Level5(Main main) {
		this.finishTexture=main.manager.get("finish.png",Texture.class);
	}
	
	@Override
	public void create() {
		
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(finishTexture, 0, 0,1280,720);
		batch.end();
		
	}



	@Override
	public void onTap(float x, float y, int button) {
		
	}

}
