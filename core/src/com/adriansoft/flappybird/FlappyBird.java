package com.adriansoft.flappybird;

import com.adriansoft.flappybird.States.ConfiGame;
import com.adriansoft.flappybird.States.GameStateManager;
import com.adriansoft.flappybird.States.MenuState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class FlappyBird extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;
	public static final String TITLE = "FlappyBird para android";
	private static  Music music;

	private GameStateManager gsm;
	private SpriteBatch batch;





	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new ConfiGame(gsm));
		setUpMusic();




	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);






	}
	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();

	}

	public static void setUpMusic(){
	    music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
	    music.setLooping(true);
	    //100% volumen musica
	    //music.setVolume(1.0f);

        music.setVolume(0.1f);

        music.play();
    }

    public static void stopMusica(){
		music.stop();
	}
}
