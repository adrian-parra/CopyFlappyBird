package com.adriansoft.flappybird.States;

import com.adriansoft.flappybird.FlappyBird;
import com.adriansoft.flappybird.States.GameStateManager;
import com.adriansoft.flappybird.States.PlayState;
import com.adriansoft.flappybird.States.State;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameOver extends State {
    private Texture background,gameover,playbutton,ground,muerte,puntaje;
    private String scoreName;
    private String recordName;
    private BitmapFont scorefontname,recordfontname;
    private int score,record;
    private int newrecord,oldreco;

    private Stage stage;

    private String user ,userActual;




    public GameOver(GameStateManager gameStateManager) {
        super(gameStateManager);
        Preferences preferences = Gdx.app.getPreferences("records");
        newrecord = preferences.getInteger("newrecord", 0);
        oldreco = preferences.getInteger("oldrecord", 0);

        user = preferences.getString("user","");
        userActual = preferences.getString("userActual","anonimo");

        background = new Texture("bg.png");
        playbutton = new Texture("playbtn.png");
        puntaje = new Texture("puntaje.png");



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


        scoreName = ""+user +" su puntaje fue de "+ score;
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        FlappyBird.stopMusica();
        spriteBatch.draw(background,0,0,FlappyBird.WIDTH , FlappyBird.HEIGHT);


        spriteBatch.draw(playbutton ,camera.position.x - playbutton.getWidth() / 2,camera.position.y);

        spriteBatch.draw(gameover,camera.position.x - gameover.getWidth() /2,camera.position.y + (gameover.getHeight() * 2) );
        //scorefontname.setColor(Color.BLUE);
        //scorefontname.getData().setScale(1,1);
       //spriteBatch.draw(puntaje,camera.position.x /2  , camera.position.y + (gameover.getHeight() * 4));
        //scorefontname.draw(spriteBatch ,scoreName,camera.position.x /2  , (camera.position.y - camera.position.y) +30);
       // scorefontname.draw(spriteBatch ,scoreName,30 , 30);


        spriteBatch.draw(ground,0,0);

        scorefontname.draw(spriteBatch ,scoreName,20,40);

        if (score >= oldreco && score == newrecord){
            //recordfontname.setColor(Color.BLUE);
            //recordfontname.getData().setScale(1,1);
            //recordfontname.draw(spriteBatch ,"Conseguiste nuevo record: " + newrecord ,20,20);
            /*
            if(score == newrecord){

            }else {
                recordfontname.draw(spriteBatch ,"conseguiste nuevo record" ,20,20);
            }*/
            if(oldreco == newrecord){
                if(user.equals(userActual)){
                    recordfontname.draw(spriteBatch , "tienes un record de " + newrecord ,20,20);

                }else{
                    recordfontname.draw(spriteBatch ,""+userActual +" tiene un record de " + newrecord ,20,20);

                }
            }else{
                recordfontname.draw(spriteBatch ,"conseguiste nuevo record" ,20,20);
            }


        } else{
            //recordfontname.setColor(Color.BLUE);
            //recordfontname.getData().setScale(1,1);

            if(userActual.equals("anonimo")){
                recordfontname.draw(spriteBatch ,"sin record registrado" ,20,20);
            }else{

                if(user.equals(userActual)){
                    recordfontname.draw(spriteBatch , "tienes un record de " + newrecord ,20,20);

                }else{
                    recordfontname.draw(spriteBatch ,""+userActual +" tiene un record de " + newrecord ,20,20);

                }

            }
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
