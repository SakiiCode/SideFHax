package ml.sakii.deeper.levels;

import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;

import ml.sakii.deeper.Computer;
import ml.sakii.deeper.Main;
import ml.sakii.deeper.Point;

public class Level1 implements Level{
	
	Timer timer;
	Timer.Task blinkTask;
	
	public Texture onTexture;
	public Texture offTexture;
	BitmapFont font;
	
	HashMap<Point, Computer> Computers = new HashMap<>();

	private static final String guide = "Left click to test nearby computers with 90% chance.\r\n"
			+ "Double click on the one that never turns on.";
	
	private Main main;
	
	Computer targetComputer;
	
	public Level1(Main main) {
		this.main=main;
		this.onTexture=main.manager.get("computer_on.png",Texture.class);
		this.offTexture=main.manager.get("computer_off.png",Texture.class);
		this.font = main.guideFont;
		
	}
	
	boolean clickable=true;
	
	
	@Override
	public void create() {
		Computers.clear();
		int targetX=(int)(Math.random()*7);
		int targetY=(int)(Math.random()*5);
		System.out.println("Selected:"+targetX+","+targetY);
		for(int x=0;x<7;x++) {
			for(int y=0;y<5;y++) {
				boolean selected = targetX==x && targetY==y;
				Computer c = new Computer(selected,x,y,this);
				Computers.put(new Point(x,y),c);
				if(selected) {
					targetComputer = c;
				}
			}
		}
		
		
		
		timer = new Timer();
		
		blinkTask = new Timer.Task() {

			@Override
			public void run() {
				for(Computer c : Computers.values()) {
					c.turnOff();
					clickable=true;
				}
			}
			
			
		};
		
		
		
	
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		font.draw(batch, guide, Computer.offsetH+7*Computer.size+20, 720-Computer.offsetV,300,Align.left,true);
		for(Computer c : Computers.values()) {
			c.render(batch);
		}
		
		batch.end();
	}




	public Computer getComputer(float screenX, float screenY) {
		for(Computer c : Computers.values()) {
			if(screenX>c.xRender && screenX<c.xRender+Computer.size) {
				if(screenY>c.yRender && screenY<c.yRender+Computer.size) {
					return c;
				}
			}
		}
		return null;
	}
	

	@Override
	public void onTap(float x, float y,int button) {
		System.out.println("x:"+x+",y:"+y);
		if(button == Buttons.LEFT) {
			if(!clickable) return;
			
			
			//y=720-y;
			/*Computer c1 = Computers.get(new Point(0,0));
			Computer c2 = Computers.get(new Point(1,0));
			System.out.println("c1:"+c1.xRender+","+c1.yRender);
			System.out.println("c2:"+c2.xRender+","+c2.yRender);*/
			for(Entry<Point,Computer> entry : Computers.entrySet()) {
				Point p = entry.getKey();
				Computer c = entry.getValue();
				
				
				if(x>c.xRender && x<c.xRender+Computer.size) {
					if(y>c.yRender && y<c.yRender+Computer.size) {
						Computer nearby;
						nearby = Computers.get(new Point(p.x+1,p.y));
						if(nearby != null) {
							nearby.blink();
						}
						
						nearby = Computers.get(new Point(p.x-1,p.y));
						if(nearby != null) {
							nearby.blink();
						}
						
						nearby = Computers.get(new Point(p.x,p.y+1));
						if(nearby != null) {
							nearby.blink();
						}
						
						nearby = Computers.get(new Point(p.x,p.y-1));
						if(nearby != null) {
							nearby.blink();
						}
						
						
						
						clickable=false;
						timer.scheduleTask(blinkTask, 0.5f);
						break;
					}
				}
			}		
		}
	}
	
	@Override
	public void onDoubleTap(float x, float y) {
		Computer clicked =getComputer(x,y); 
		if(clicked != targetComputer) {
			System.out.println("Clicked at: "+clicked.xIndex+","+clicked.yIndex);
			System.out.println("Target: "+targetComputer.xIndex+","+targetComputer.yIndex);
			main.GameOver();
		}else {
			main.nextLevel();
		}
	}

	


}
