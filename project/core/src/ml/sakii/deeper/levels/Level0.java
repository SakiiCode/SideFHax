package ml.sakii.deeper.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ml.sakii.deeper.Button;
import ml.sakii.deeper.Main;

public class Level0 implements Level{

	private Main main;
	private Texture gameoverTexture;
	private Button restartButton;
	
	public Level0(Main main) {
		this.main=main;	
		this.gameoverTexture=main.manager.get("gameover.png",Texture.class);
	}
	
	@Override
	public void create() {
		restartButton = new Button("",null,430,450,420,130,main.guideFont) {

			@Override
			public void action() {
				main.jumpToLevel(1);
			}
			
		};
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(gameoverTexture,0, 0,1280,720);
		batch.end();
		
	}



	@Override
	public void onTap(float x, float y, int button) {
		restartButton.onTap(x, y, button);
		
	}




}
