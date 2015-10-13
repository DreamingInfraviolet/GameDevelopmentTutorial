package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mugamedev.game.Pong;
import sprites.Ball;
import sprites.Enemy;
import sprites.Paddle;
import sprites.Player;
import sprites.Sprite;
import sprites.Sprite.Corner;

public class PlayState extends State
{
	private Ball ball;
	private Player player;
	private Enemy enemy;
	private Texture background, win, lose;
	private static boolean victory = false;
	private static boolean inPlay = true;

	protected PlayState(GameStateManager gsm) 
	{
		super(gsm);
		spawnBall();
		player = new Player(Pong.WIDTH-50, (Pong.HEIGHT/2)-38);
		enemy = new Enemy(50, (Pong.HEIGHT/2)-38);
		background = new Texture("bg.png");
		win = new Texture("win.png");
		lose = new Texture("lose.png");
	}
	
	private void spawnBall()
	{
		if(ball!=null)
			ball.dispose();
		ball = new Ball((Pong.WIDTH/2)-15,(Pong.HEIGHT/2)-15);
	}

	@Override
	protected void handleInput(float dt) 
	{
		if(Gdx.input.isKeyPressed(Keys.UP))
			player.setVVelocity(1, dt);
		else if(Gdx.input.isKeyPressed(Keys.DOWN))
			player.setVVelocity(-1, dt);
		else
			player.setVVelocity(0, dt);

		if(!inPlay && Gdx.input.justTouched())
		{
			gsm.push(new MenuState(gsm));
			dispose();
			inPlay = true;
			victory = false;
			player.resetScore();
			enemy.resetScore();
		}
	}

	private void handleBall()
	{
		Sprite.Corner corner = ball.getCollisionCorner();
		
		if(corner!=Sprite.Corner.NONE)
		{
			if(corner == Sprite.Corner.LEFT)
			{
				enemy.scored();
				spawnBall();
			}
			else if (corner == Sprite.Corner.RIGHT)
			{
				player.scored();
				spawnBall();
			}
			else if (corner == Sprite.Corner.TOP)
				ball.bounceTop();
			else if (corner == Sprite.Corner.BOTTOM)
				ball.bounceBot();
		}
		
		if(ball.getBounds().overlaps(player.getBounds()))
		{
			ball.bounceRight();
		}
		else if(ball.getBounds().overlaps(enemy.getBounds()))
		{
			ball.bounceLeft();
		}

	}
	
	public void handlePaddle(Paddle paddle)
	{
		Sprite.Corner corner = paddle.getCollisionCorner();
		if(corner!=Sprite.Corner.NONE)
		{
			if(corner == Corner.BOTTOM)
				paddle.setVPosition(1);
			else if(corner == Corner.TOP)
				paddle.setVPosition(Pong.HEIGHT-paddle.getTexture().getHeight()-1);
			else
				assert(false);	
		}
	}
	
	@Override
	public void update(float dt) 
	{
		handleInput(dt);
		handleBall();



		if(player.getScore() == 5 || enemy.getScore() == 5)
		{
			if(player.getScore() == 5)
			{
				victory = true;
				inPlay = false;
			}
			else
			{
				inPlay = false;
			}
		}
		player.update(dt);
		enemy.update(dt, ball);//, ball);
		ball.update(dt);
		
		handlePaddle(player);
		handlePaddle(enemy);
	}

	@Override
	public void render(SpriteBatch sb) 
	{
		sb.begin();
		sb.draw(background, 0, 0, Pong.WIDTH, Pong.HEIGHT);
		if(inPlay)
		{
			sb.draw(ball.getTexture(), ball.getPosition().x, ball.getPosition().y);
			sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
			sb.draw(enemy.getTexture(), enemy.getPosition().x, enemy.getPosition().y);
		}
		else
		{
			if(victory)
			{
				sb.draw(win, (Pong.WIDTH/2) - (win.getWidth()/2) , (Pong.HEIGHT/2) - (win.getHeight()/2));
			}
			else
			{
				sb.draw(lose, (Pong.WIDTH/2) - (lose.getWidth()/2) , (Pong.HEIGHT/2) - (lose.getHeight()/2));
			}
		}
		sb.end();
	}

	@Override
	public void dispose() 
	{
		background.dispose();
	}
}
