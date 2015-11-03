package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mugamedev.game.Pong;

public class MenuState extends State
{
	private Texture background;
	private Texture playbtn;
	
	public MenuState(GameStateManager gsm)
	{
		super(gsm);
		background = new Texture("main.png");
		playbtn = new Texture("startB.png");
	}

	@Override
	public void handleInput(float dt) 
	{
		if(Gdx.input.justTouched())
		{
			gsm.pop();
			gsm.push(new PlayState(gsm));
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
		sb.draw(playbtn, (Pong.WIDTH / 2) - (playbtn.getWidth() /2), (Pong.HEIGHT / 2) - (playbtn.getHeight() / 2));
		sb.end();
	}
	
	@Override
	public void dispose() 
	{
		background.dispose();
		playbtn.dispose();
	}
}
