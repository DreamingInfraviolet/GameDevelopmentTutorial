package sprites;

import com.badlogic.gdx.graphics.Texture;

public class Paddle extends Sprite
{
	protected static final int speed = 250;
	protected int score;

	
	public Paddle(int x, int y)
	{
		super(x, y, new Texture("paddle.png"));
	}
	

	public void setVVelocity(float direction, float dt) 
	{
		velocity.y = direction*speed*dt;
	}
	
	public void setVPosition(int y)
	{
		position.y = y;
	}
	
	public void scored()
	{
		score++;
	}
	
	public void resetScore()
	{
		score = 0;
	}
	
	public int getScore()
	{
		return score;
	}
}
