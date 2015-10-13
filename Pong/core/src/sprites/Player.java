package sprites;

public class Player extends Paddle
{
	public Player(int x, int y)
	{
		super(x, y);
	}
	
	@Override
	public void update(float dt)
	{
		position.add(velocity.x, velocity.y);
		bounds.setPosition(position.x, position.y);
	}

}
