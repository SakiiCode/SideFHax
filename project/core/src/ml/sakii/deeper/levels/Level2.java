package ml.sakii.deeper.levels;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

import ml.sakii.deeper.Button;
import ml.sakii.deeper.Label;
import ml.sakii.deeper.Main;

public class Level2 implements Level{
	
	ArrayList<Button> filenames= new ArrayList<>();
	ArrayList<Label> contents= new ArrayList<>();

	private Main main;
	private float fontHeight;
	private int padding=720/20;
	private float offsetV;
	
	private static final String guide = "Infect the file which has \"i.n.f.e.c.t.a.b.l.e\" hidden in it\r\n"
			+ ". stands for a single random character\r\n"
			+ "Left Click: Open\r\n";
	
	private int currentFile;
	
	private Texture infectTexture,backTexture;
	private Button infectButton,backButton;
	
	private Label guideLabel;

	
	
	public Level2(Main main) {
		this.main=main;
		infectTexture = main.manager.get("infect.png",Texture.class);
		backTexture = main.manager.get("back.png",Texture.class);
		guideLabel = new Label(guide,0,400,1280,Align.center,main.guideFont);
	}
	
	private char randomChar() {
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		return c;
				
	}

	@Override
	public void create() {
		currentFile=-1;
		filenames.clear();
		contents.clear();
		
		
		GlyphLayout gl = new GlyphLayout();
		gl.setText(main.fileFont, "abcdefghijklmnopqrstuvwxyz");
		this.fontHeight=gl.height;
		
		float buttonHeight = fontHeight+padding;
		
		this.offsetV=(720-8*buttonHeight)/2+100;
		
		int infectableIndex=(int)(Math.random()*8);
		
		System.out.println(infectableIndex);
		
		for(int i=0;i<8;i++) {
			StringBuilder sb = new StringBuilder();
			
			for(int j=0;j<12;j++) {
				sb.append(randomChar());
			}
			
			final int tmpFileIndex=i;
			
			filenames.add(new Button(sb.toString(),null,500f,offsetV+fontHeight*i+padding*(i-1),280f,buttonHeight,main.fileFont) {
				
				@Override
				public void action() {
					showFile(tmpFileIndex);
					
				}
				
			});
			
			boolean infectable = infectableIndex==i;
			StringBuilder sb2 = new StringBuilder();

			if(infectable) {
				int insertIndex=(int)(Math.random()*64);
				
				
				for(int j=0;j<64;j++) {
					
						if(j!=insertIndex) {
							sb2.append(randomChar());
						}else {
							sb2.append("i"+randomChar()+"n"+randomChar()+"f"+randomChar()+"e"+randomChar()
							+"c"+randomChar()+"t"+randomChar()+"a"+randomChar()+"b"+randomChar()+"l"+randomChar()+"e");
						}
					
				}
				
				
			}else {
				if(infectableIndex!=i) {
					for(int j=0;j<83;j++) {
						sb2.append(randomChar());
					}
				}
				
				
			}
			
			contents.add(new Label(sb2.toString(),0,offsetV,1280,Align.center,main.fileFont) );

		}
		
		infectButton=new Button("",infectTexture,(1280-infectTexture.getWidth()*4)/2,400,infectTexture.getWidth()*4,infectTexture.getHeight()*4,main.guideFont) {
			@Override
			public void action() {
				if(currentFile != infectableIndex) {
					main.GameOver();
				}else {
					main.nextLevel();
				}
				
			}
		};
		
		backButton=new Button("",backTexture,(1280-backTexture.getWidth()*4)/2,550,backTexture.getWidth()*4,backTexture.getHeight()*4,main.guideFont) {
			@Override
			public void action() {
				showFile(-1);
				
			}
		};
		
		guideLabel=new Label(guide,0,50,1280,Align.center,main.guideFont);
		
		
		
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		guideLabel.render(batch);
		if(currentFile==-1) {
			for(Button b : filenames) {
				b.render(batch);
			}
		}else {
			contents.get(currentFile).render(batch);
			infectButton.render(batch);
			backButton.render(batch);
		}
		batch.end();
		
	}

	private void showFile(int index) {
		currentFile=index;
		System.out.println("Opening file "+index);
	}
	


	@Override
	public void onTap(float x, float y,int button) {
		if(button == Buttons.LEFT) {
			if(currentFile == -1) {
				for(Button b : filenames) {
					b.onTap(x, y, button);
				}
			}else {
				infectButton.onTap(x, y, button);
				backButton.onTap(x,y,button);
			}
		}
		
		
		
		
	}


}
