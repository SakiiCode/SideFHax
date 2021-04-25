package ml.sakii.deeper;

import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import ml.sakii.deeper.levels.IntroLevel;
import ml.sakii.deeper.levels.Level;
import ml.sakii.deeper.levels.Level0;
import ml.sakii.deeper.levels.Level1;
import ml.sakii.deeper.levels.Level2;
import ml.sakii.deeper.levels.Level3;
import ml.sakii.deeper.levels.Level4;
import ml.sakii.deeper.levels.Level5;

public class Main extends ApplicationAdapter implements GestureListener {
	public AssetManager manager;
	

	
	
	long tick=0;
	private int currentLevel=6;
	ArrayList<Level> Levels = new ArrayList<>();
	public BitmapFont fileFont,guideFont;
	SpriteBatch batch;


	@Override
	public void create () {
		

		manager=new AssetManager();
		manager.load("computer_on.png",Texture.class);
		manager.load("computer_off.png",Texture.class);
		manager.load("ram.png",Texture.class);
		manager.load("work.png",Texture.class);
		manager.load("gameover.png",Texture.class);
		manager.load("intro.png",Texture.class);
		manager.load("finish.png",Texture.class);
		manager.load("infect.png",Texture.class);
		manager.load("back.png",Texture.class);
		manager.load("pixel.fnt",BitmapFont.class);
		manager.load("mono.fnt",BitmapFont.class);
		
		batch=new SpriteBatch();
		Gdx.input.setInputProcessor(new GestureDetector(this));
		
		manager.finishLoading();
		
		guideFont = manager.get("pixel.fnt",BitmapFont.class);
		fileFont = manager.get("mono.fnt",BitmapFont.class);
		
		
		Levels.add(new Level0(this));
		Levels.add(new Level1(this));
		Levels.add(new Level2(this));
		Levels.add(new Level3(this));
		Levels.add(new Level4(this));
		Levels.add(new Level5(this));
		Levels.add(new IntroLevel(this));
		
		

		
		
		Levels.get(currentLevel).create();
		

	}

	@Override
	public void render () {
		ScreenUtils.clear(0.15f, 0.15f, 0.15f, 1);
		Levels.get(currentLevel).render(batch);
		
	}
	
	
	public void GameOver() {
		jumpToLevel(0);
	}
	
	@Override
	public void dispose () {
		int i=0;
		for(Level lvl : Levels) {
			System.out.println("Disposing Level "+i);
			lvl.dispose();
			i++;
		}
		batch.dispose();
		manager.dispose();
		fileFont.dispose();
		guideFont.dispose();
	}
	
	public void nextLevel() {
		System.out.println("Going to level "+(currentLevel+1));
		Levels.get(currentLevel+1).create();
		currentLevel++;
	}
	
	public void jumpToLevel(int nextLevel) {
		System.out.println("Going to level "+nextLevel);
		Levels.get(nextLevel).create();
		currentLevel=nextLevel;
	}
	
	
	

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if(count == 1 || currentLevel == 4) {
			Levels.get(currentLevel).onTap(x,y,button);
		}else if(count == 2 && button == Buttons.LEFT){
			Levels.get(currentLevel).onDoubleTap(x,y);
		}

		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pinchStop() {
		// TODO Auto-generated method stub
		
	}
}

