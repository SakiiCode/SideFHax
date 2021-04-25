package ml.sakii.deeper;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Label {
	
	public Rectangle hitbox;
	public String text;
	public float x,y,w,h;
	public BitmapFont f;
	private int align;
	
	public Label(String text,float x, float y, float w,int align,BitmapFont f) {
		this.text=text;
		this.x=x;
		this.y=y;
		this.w=w;
		this.align=align;
		this.f=f;
		
		GlyphLayout gl = new GlyphLayout();
		gl.setText(f, text);
		this.h=gl.height;
		
	}
	
	public void render(SpriteBatch batch) {
		f.draw(batch, text, x, 720-y-h/2,w,align,false);
	}

}
