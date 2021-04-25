package ml.sakii.deeper;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;

public abstract class Button {
	public Rectangle hitbox;
	public String text;
	public float x,y,w,h,fontHeight;
	public BitmapFont f;
	private Texture t;
	private ShapeRenderer shapeRenderer;
	
	public Button(String text,Texture t,float x, float y, float w, float h,BitmapFont f) {
		this.text=text;
		this.x=x;
		this.y=y;
		this.hitbox=new Rectangle(x,y,w,h);
		this.f=f;
		this.t=t;
		this.w=w;
		this.h=h;
		
		
		GlyphLayout gl = new GlyphLayout();
		gl.setText(f, text);
		this.fontHeight=gl.height;
		
		if(t==null) {
			shapeRenderer=new ShapeRenderer();
		}
	}
	
	public void render(SpriteBatch batch) {
		if(t!=null) {
			batch.draw(t, x, 720-y-h, w, h);
		}else {
			/*batch.end();
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.rect(x, 720-y-h, w, h);
			shapeRenderer.end();
			batch.begin();*/
		}
		f.draw(batch, text, x, 720-y-(h-fontHeight)/2,hitbox.getWidth(),Align.center,false);
	}
	
	public void onTap(float x, float y, int button) {
		if(hitbox.contains(x, y)) {
			action();
		}else {
			System.out.println("Not in hitbox");
		}
	}
	
	public abstract void action();
}

