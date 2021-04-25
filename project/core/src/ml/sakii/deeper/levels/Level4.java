package ml.sakii.deeper.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Align;

import ml.sakii.deeper.Button;
import ml.sakii.deeper.Main;

public class Level4 implements Level{

	private static final String guide = "Overload the CPU";
	private Main main;

	private Button workButton;
	
	private float temp=0;
	ShapeRenderer shapeRenderer;
	private static final float TRESHOLD = 20;
	private Texture workTexture;
	
	private int x=400,y=350,w=150,h=80;
	
	public Level4(Main main) {
		this.main=main;
		shapeRenderer = new ShapeRenderer();
		this.workTexture=main.manager.get("work.png",Texture.class);
		
	}
	
	
	@Override
	public void create() {

		workButton = new Button("",workTexture, x, 720-y-h, w, h,main.guideFont) {
			
			@Override
			public void action() {
				work();
				
			}
		};
		
		
	}

	@Override
	public void render(SpriteBatch batch) {
		temp = Math.max(0, temp-Gdx.graphics.getDeltaTime()*1.5f);
		batch.begin();
		workButton.render(batch);
		main.guideFont.draw(batch, guide, 0, 720-100,1280,Align.center,false);
		//batch.draw(workTexture, x, y,w,h);
		//main.guideFont.draw(batch, temp+"", 400, 500);
		batch.end();
		shapeRenderer.begin(ShapeType.Filled);
		/*shapeRenderer.setColor(Color.DARK_GRAY);
		shapeRenderer.rect(x+w+50, y+h/4, TRESHOLD*20, h/2);*/
		if(temp<TRESHOLD/3f) {
			shapeRenderer.setColor(Color.WHITE);
		}else if(temp<TRESHOLD*2f/3f) {
			shapeRenderer.setColor(Color.ORANGE);
		}else {
			shapeRenderer.setColor(Color.RED);
		}
		shapeRenderer.rect(x+w+50, y+h/4, temp*20, h/2);
		shapeRenderer.end();
		
	}

	@Override
	public void onTap(float x, float y, int button) {
		workButton.onTap(x, y, button);
		
	}
	
	private void work() {
		temp+=0.5;
		if(temp>TRESHOLD) {
			main.nextLevel();
		}
	}
	
	@Override
	public void dispose() {
		shapeRenderer.dispose();
	}

}
