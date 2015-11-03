package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mugamedev.game.Pong;

public class EndState extends State
{
	private Texture background, win, lose;
	private boolean victory;
	
	protected EndState(GameStateManager gsm, boolean victory) 
	{
		super(gsm);
		background = new Texture("bg.png");
		win = new Texture("win.png");
		lose = new Texture("lose.png");
		this.victory = victory;
	}

	@Override
	protected void handleInput(float dt) 
	{
		if(Gdx.input.justTouched())
		{
			gsm.pop();
			gsm.push(new MenuState(gsm));
			dispose();
		}
	}

	@Override
	public void update(float dt) 
	{
		handleInput(dt);
	}

	@Override
	public void render(SpriteBatch sb) 
	{
		sb.begin();
		sb.draw(background, 0, 0, Pong.WIDTH, Pong.HEIGHT);
		if(victory)
		{
			sb.draw(win, (Pong.WIDTH/2) - (win.getWidth()/2) , (Pong.HEIGHT/2) - (win.getHeight()/2));
		}
		else
		{
			sb.draw(lose, (Pong.WIDTH/2) - (lose.getWidth()/2) , (Pong.HEIGHT/2) - (lose.getHeight()/2));
		}
		sb.end();
		
	}

	@Override
	public void dispose() 
	{
		background.dispose();
		if(victory)
		{
			win.dispose();
		}
		else
		{
			lose.dispose();
		}
	}

}
