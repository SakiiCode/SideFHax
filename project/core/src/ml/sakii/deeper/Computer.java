package ml.sakii.deeper;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ml.sakii.deeper.levels.Level1;

public class Computer {
	
	public static final int size=64;
	public static final int offsetV=(720-size*5)/2;
	public static final int offsetH=(1280-size*7)/2;
	
	boolean target=false;
	public int xIndex,yIndex;
	public int xRender,yRender;
	private boolean blinking=false;
	private Level1 lvl;
	
	public Computer(boolean target,int x, int y,Level1 level1) {
		this.target=target;
		this.xIndex=x;
		this.yIndex=y;
		this.xRender=offsetH+x*size;
		this.yRender=offsetV+y*size;
		this.lvl=level1;
	}
	
	public void render(SpriteBatch batch) {
		if(blinking) {
			batch.draw(lvl.onTexture, xRender, 720-size-yRender ,size,size);
		}else {
			batch.draw(lvl.offTexture, xRender, 720-size-yRender ,size,size);
		}
	}
	
	public void blink() {
		if(!target) {
			if(Math.random()<0.9) {
				blinking=true;
			}
		}
	}
	
	public void turnOff() {
		this.blinking=false;
	}

}
