package ml.sakii.deeper.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ml.sakii.deeper.Button;
import ml.sakii.deeper.Main;

public class Level3 implements Level{

	
	private static final String guide = "Set all bits to 1 in the memory";
	private static final int LENGTH = 6;
	
	
	private int[] bitSequence;
	private Main main;
	
	private Button[] buttons;
	
	private Texture memoryTexture;
	
	int offsetH=400;
	int offsetV=(720-11)/2;
	int size=64;

	
	public Level3(Main main) {
		this.main=main;
		this.memoryTexture=main.manager.get("ram.png",Texture.class);
	}
	
	@Override
	public void create() {
		bitSequence = randomBits(LENGTH);
		setupButtons();
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		main.guideFont.draw(batch, guide, 400, 600);
		for(Button b : buttons) {
			b.render(batch);
		}
		batch.draw(memoryTexture,offsetH,offsetV-87,memoryTexture.getWidth()*4,memoryTexture.getHeight()*4);
		batch.end();
	}

	@Override
	public void onTap(float x, float y, int button) {
		for(Button b : buttons) {
			b.onTap(x, y, button);
		}
		
	}
	
	private int randomBit() {
		return (int)(Math.random()*2);
	}
	
	private int[] randomBits(int amount) {
		int[] result = new int[amount];
		
		for(int i=0;i<amount;i++) {
			result[i]=randomBit();
		}
		return result;
	}
	
	private void setupButtons() {
		buttons=new Button[LENGTH];
		
		
		
		for(int i=0;i<LENGTH;i++) {
			int bit=bitSequence[i];
			
			
			final int index = i;
			buttons[i]=new Button(bit+"",null, offsetH+size*i,offsetV,size,size,main.fileFont) {
				
				@Override
				public void action() {
					swapNeighbors(index);
					int sum=0;
					for(int i=0;i<LENGTH;i++) {
						sum += bitSequence[i];
					}
					if(sum==LENGTH) {
						main.nextLevel();
					}
					
				}
			};
			
			
		}
	}
	
	private void swapNeighbors(int index){
		if(index==0) {
			swapBit(1);
		}else if(index==LENGTH-1) {
			swapBit(LENGTH-2);
		}else {
			swapBit(index+1);
			swapBit(index-1);
		}
	}
	
	private void swapBit(int index) {
		bitSequence[index]=Math.abs(1-bitSequence[index]);
		buttons[index].text=bitSequence[index]+"";
	}

}
