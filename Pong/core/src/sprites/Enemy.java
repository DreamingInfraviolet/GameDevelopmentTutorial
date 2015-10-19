package sprites;

public class Enemy extends Paddle
{	
	public Enemy(int x, int y)
	{
		super(x,y);
		
	}
	
	int updateFrameCounter = 0;
	float moveDirection = 0;
	
	public void update(float dt, Ball ball)
	{
		//if(++updateFrameCounter%3==0)
		//{
			updateFrameCounter = 0;
			if(position.y < ball.position.y)
				moveDirection = 1;
			else if (position.y > ball.position.y)
				moveDirection = -1;
			else
				moveDirection = 0;
		//}
		setVVelocity(moveDirection, dt);

		position.add(velocity.x, velocity.y);
		bounds.setPosition(position.x, position.y);
	}
}