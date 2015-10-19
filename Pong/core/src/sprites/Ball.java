package sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Ball extends Sprite
{
	private static final int speed = 600;

	public Ball(int x, int y)
	{
		super(x, y, new Texture("ball.png"));
		setRandomVelocity();
	}

	public void update(float dt)
	{
		position.add(new Vector2(velocity.x*dt, velocity.y*dt));
		bounds.setPosition(position.x, position.y);
	}

	public void bounceTop()
	{
		velocity.y = -Math.abs(velocity.y);
	}
	public void bounceBot()
	{
		velocity.y = Math.abs(velocity.y);
	}
	public void bounceRight()
	{
		velocity.x = -Math.abs(velocity.x);
	}
	public void bounceLeft()
	{
		velocity.x = Math.abs(velocity.x);
	}

	public void setRandomVelocity()
	{
		velocity.x = (float)Math.random()+1;
		if(Math.random()<0.5)
			velocity.x = -velocity.x;
		velocity.y = (float)Math.random();
		if(Math.random()<0.5)
			velocity.y = -velocity.y;
		velocity = velocity.nor();
		velocity.x = velocity.x*speed;
		velocity.y =  velocity.y*speed;
	}
}
