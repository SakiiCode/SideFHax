package ml.sakii.deeper.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ml.sakii.deeper.Button;
import ml.sakii.deeper.Main;

public class IntroLevel implements Level{

	private Main main;
	private Texture background;
	private Button startButton;
	
	public IntroLevel(Main main) {
		this.main=main;	
		this.background=main.manager.get("intro.png",Texture.class);
	}
	
	@Override
	public void create() {
		startButton = new Button("",null,1280f*(142f/384f),720f*(152f/216f),1280f*(120f/384f),720f*(47f/216f),main.guideFont) {

			@Override
			public void action() {
				main.jumpToLevel(1);
				
			}
			
		};
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(background, 0, 0,1280,720);
		batch.end();
		
	}



	@Override
	public void onTap(float x, float y, int button) {
		startButton.onTap(x, y, button);
		
	}




}
