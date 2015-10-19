package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mugamedev.game.Pong;

public class Sprite
{
	protected final Vector2 startPosition;
	protected Vector2 position;
	protected Vector2 velocity = new Vector2();
	protected Texture texture;
	protected Rectangle bounds;
	
	public enum Corner {RIGHT, LEFT, TOP, BOTTOM, NONE};

	public Sprite(int posX, int posY, Texture texture)
	{
		startPosition = new Vector2(posX, posY);
		position = new Vector2(posX, posY);
		this.texture = texture;
		bounds = new Rectangle(posX, posY, this.texture.getWidth(), this.texture.getHeight());
	}

	public Corner getCollisionCorner()
	{
		if (position.x < 0)
			return Corner.LEFT;
		else if (position.y < 0)
			return Corner.BOTTOM;
		else if (position.x+texture.getWidth() > Pong.WIDTH)
			return Corner.RIGHT;
		else if (position.y+texture.getHeight() > Pong.HEIGHT)
			return Corner.TOP;
		else
			return Corner.NONE;
	}

	void  update(float dt)
	{
		
	}
	
	void resetPosition()
	{
		position.x = startPosition.x;
		position.y = startPosition.y;
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	public Vector2 getPosition() 
	{
		return position;
	}
	
	public Texture getTexture()
	{
		return texture;
	}
	
	public Vector2 getVelocity()
	{
		return position;
	}
	
	public void dispose()
	{
		texture.dispose();
	}
}
