package com.adriansoft.flappybird.States;

import com.adriansoft.flappybird.FlappyBird;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State{
private Texture background,playButton;
    private BitmapFont recordsuperar;
    private String recordsup = "";
    private int newrecord = 0;
    private Preferences preferences;
    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        preferences = Gdx.app.getPreferences("records");
        newrecord = preferences.getInteger("newrecord",0);
        recordsuperar = new BitmapFont();

        //SE OCUPA PARA ANDROID
        camera.setToOrtho(false , FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        //GENERA LA TEXTURA DEL BACKGROUND
        background = new Texture("bg.png");
        //TEXTURA PARA EL BOTTON
        playButton = new Texture("playbtn.png");

    }

    @Override
    protected void handleInput() {
    if (Gdx.input.justTouched()) {
        gsm.set(new PlayState(gsm));
    }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        //SE OCUPA PARA ANDROID
        spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    //DIBUJA EN PANTALLA EL FONDO DEL JUEGO
    //spriteBatch.draw(background,0,0, FlappyBird.WIDTH , FlappyBird.HEIGHT);
        //se oocupa para android
        spriteBatch.draw(background,0,0);
    //DIBUJA EL PLAY BUTTON...DE INISIAR JUEGO
     //spriteBatch.draw(playButton ,FlappyBird.WIDTH / 2 - (playButton.getWidth() /2),FlappyBird.HEIGHT /2 - (playButton.getHeight() /2));
            //se ocupa para android
        spriteBatch.draw(playButton ,camera.position.x - playButton.getWidth() / 2,camera.position.y);
        recordsuperar.setColor(Color.BLUE);
        recordsuperar.getData().setScale(1,1);
        recordsuperar.draw(spriteBatch,"el record a superar es de: " + newrecord ,20 ,20);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        System.out.println("MENU STATE DISPOSE");
    }
}
