package com.adriansoft.flappybird.States;

import com.adriansoft.flappybird.FlappyBird;
import com.adriansoft.flappybird.States.GameStateManager;
import com.adriansoft.flappybird.States.PlayState;
import com.adriansoft.flappybird.States.State;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOver extends State {
    private Texture background,gameover,playbutton,ground,muerte;
    private String scoreName;
    private String recordName;
    private BitmapFont scorefontname,recordfontname;
    private int score,record;
    private int newrecord,oldreco;




    public GameOver(GameStateManager gameStateManager) {
        super(gameStateManager);
        Preferences preferences = Gdx.app.getPreferences("records");
        newrecord = preferences.getInteger("newrecord", 0);
        oldreco = preferences.getInteger("oldrecord", 0);

        background = new Texture("bg.png");
        playbutton = new Texture("playbtn.png");
        gameover = new Texture("gameover.png");
        ground = new Texture("ground.png");
        muerte =  new Texture("m3.png");
        camera.setToOrtho(false , FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        scorefontname = new BitmapFont();
        recordfontname = new BitmapFont();

       score = gsm.getScore();



    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            gsm.setScore(0);
            FlappyBird.setUpMusic();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        /*
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,0,0);
        spriteBatch.end();
*/


        scoreName = "Tu puntage es de: " + score;
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        FlappyBird.stopMusica();
        spriteBatch.draw(background,0,0,FlappyBird.WIDTH , FlappyBird.HEIGHT);
        spriteBatch.draw(playbutton ,camera.position.x - playbutton.getWidth() / 2,camera.position.y);
        spriteBatch.draw(gameover,camera.position.x - gameover.getWidth() /2,camera.position.y + (gameover.getHeight() * 2) );
        scorefontname.setColor(Color.BLUE);
        scorefontname.getData().setScale(1,1);
        scorefontname.draw(spriteBatch ,scoreName,camera.position.x /2  , camera.position.y + (gameover.getHeight() * 4));
        spriteBatch.draw(ground,0,0);

        if (score > oldreco){
            recordfontname.setColor(Color.BLUE);
            recordfontname.getData().setScale(1,1);
            recordfontname.draw(spriteBatch ,"conseguiste nuevo record: " + newrecord ,20,20);

        } else{
            recordfontname.setColor(Color.BLUE);
            recordfontname.getData().setScale(1,1);
            recordfontname.draw(spriteBatch ,"record a superar es de: " + newrecord ,20,20);
        }

        //spriteBatch.draw(muerte ,78,0);

        spriteBatch.end();

    }


    @Override
    public void dispose() {
        background.dispose();
       playbutton.dispose();
        gameover.dispose();
        muerte.dispose();
        scorefontname.dispose();

       // scorefontname.dispose();

    }
}
