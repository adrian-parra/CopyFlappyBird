package com.adriansoft.flappybird.States;

import com.adriansoft.flappybird.FlappyBird;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuState extends State{
private Texture background,playButton ,infoMenuPrincipal;
    private BitmapFont recordsuperar;
    private String recordsup = "";
    private int newrecord = 0;
    private Preferences preferences;

    //ATRIBUTOS PARA CALAR BUTTON
    private Stage stage;
    private Skin skin;

    private String userActual ,user;
    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        preferences = Gdx.app.getPreferences("records");

        newrecord = preferences.getInteger("newrecord",0);
        userActual =preferences.getString("userActual" ,"anonimo");
        user = preferences.getString("user","");

        recordsuperar = new BitmapFont();

        //SE OCUPA PARA ANDROID
        camera.setToOrtho(false , FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        //GENERA LA TEXTURA DEL BACKGROUND
        background = new Texture("bg.png");
        //TEXTURA PARA EL BOTTON
        playButton = new Texture("playbtn.png");

        //TEXTTURA PARA LA INFORMACION DE INICIO
        //NOMBRE DE JUEGO, E INICIAR JUEGO
        infoMenuPrincipal = new Texture("messageMenuPrincipal.png");



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
        //spriteBatch.draw(playButton ,camera.position.x - playButton.getWidth() / 2,camera.position.y);
        spriteBatch.draw(infoMenuPrincipal ,camera.position.x - infoMenuPrincipal.getWidth() / 2,camera.position.y - 100);
        recordsuperar.setColor(Color.WHITE);
        recordsuperar.getData().setScale(1,1);

        if (userActual.equals("anonimo")){
            recordsuperar.draw(spriteBatch,"sin record registrado " ,20 ,20);

        }else{

            if(user.equals(userActual)){
                recordsuperar.draw(spriteBatch,"tienes un record de " + newrecord ,20 ,20);

            }else{
                recordsuperar.draw(spriteBatch,""+userActual + " tiene un record de " + newrecord ,20 ,20);

            }

        }

        spriteBatch.end();



    }



    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        System.out.println("MENU STATE DISPOSE");
    }
}
