package com.adriansoft.flappybird.States;

import com.adriansoft.flappybird.FlappyBird;
import com.adriansoft.flappybird.sprites.Bird;

import com.adriansoft.flappybird.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30;


    private Sound PerderVida,countTube;
    private Array<Tube> tubes;

    private Bird bird;


    private String Yourscorename = "0";
    private BitmapFont yourbitmapfontname;
    private Texture background ,score1;


    private int score = 0;

    private Texture ground;
    private Vector2 groundpos1 , groundpos2;

    private Preferences preferences;
    private int newrecord;

    private String user ,userRecordActual;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        preferences = Gdx.app.getPreferences("records");
        newrecord = preferences.getInteger("newrecord",0);

        //OBTENER EL NOMBRE DE USUARIO QUE ESTA JUGANDO
        user = preferences.getString("user" ,"");

        //USUARIO QUE TIENE EL RECORD ACTUAL
        userRecordActual = preferences.getString("userActual", "anonimo");

        countTube = Gdx.audio.newSound(Gdx.files.internal("moneda.mp3"));
        PerderVida = Gdx.audio.newSound(Gdx.files.internal("perderVida.mp3"));
        ground = new Texture("ground.png");
        groundpos1 = new Vector2(camera.position.x - camera.viewportWidth /2,GROUND_Y_OFFSET);
        groundpos2 = new Vector2((camera.position.x - camera.viewportWidth /2) + ground.getWidth() , GROUND_Y_OFFSET);
        background = new Texture   ("bg.png");
        yourbitmapfontname = new BitmapFont();


        //TEXTURE PARA SCORE MEJORADO
        score1 = new Texture("0.png");

        bird = new Bird(50 ,320);
        camera.setToOrtho(false , FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        tubes = new Array<Tube>();

        for (int i = 1; i<= TUBE_COUNT; i++){
            tubes.add(new Tube(i*(TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
    handleInput();
    updateGround();
    bird.update(dt);
        camera.position.x = bird.getPosition().x + 80;
    //FORD MEJORADO
        for (int i=0; i< tubes.size; i++){

            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposisition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
               // System.out.println("play state " + score++);
                countTube.play(0.5f);
                score++;
                gsm.setScore(score);
                Yourscorename = "" + score;
                score1 = new Texture("1.png");
            }

            if (tube.collides(bird.getBounds())){
                PerderVida.play(0.5f);
                if(score > newrecord){
                    preferences.putInteger("newrecord",score);
                    preferences.putInteger("oldrecord", newrecord);

                    //COMO EL RECORD ES MAYOR AL ACTUAL NUEVO USUARIO CON NUEVO RECORD
                    preferences.putString("userActual" ,user);
                    preferences.flush();
                }else if(score == newrecord){
                    preferences.putInteger("newrecord",score);
                    preferences.putInteger("oldrecord", newrecord);
                    preferences.flush();
                }

                gsm.set(new GameOver(gsm));
            }




        }

        //Colision cuando toca el piso

        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            PerderVida.play(0.5f);
            if(score > newrecord){
                preferences.putInteger("newrecord",score);
                preferences.putInteger("oldrecord", newrecord);

                //COMO EL RECORD ES MAYOR AL ACTUAL NUEVO USUARIO CON NUEVO RECORD
                preferences.putString("userActual" ,user);
                preferences.flush();
            }else if(score == newrecord){
                preferences.putInteger("newrecord",score);
                preferences.putInteger("oldrecord", newrecord);
                preferences.flush();
            }

            gsm.set(new GameOver(gsm));
        }


        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        spriteBatch.draw(background ,camera.position.x - (camera.viewportWidth / 2), 0 );
        spriteBatch.draw(bird.getTexture() ,bird.getPosition().x , bird.getPosition().y);

        for (Tube tube: tubes)
        {
            spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }

        spriteBatch.draw(ground ,groundpos1.x ,groundpos1.y);
        spriteBatch.draw(ground ,groundpos2.x ,groundpos2.y);

        yourbitmapfontname.setColor(1.0f,1.0f,1.0f,1.0f);
        yourbitmapfontname.getData().setScale(2,2);
        yourbitmapfontname.draw(spriteBatch,Yourscorename,camera.position.x - (camera.viewportWidth/2) ,camera.position.y + (FlappyBird.HEIGHT / 4));

        //spriteBatch.draw(score1,camera.position.x - (camera.viewportWidth/2) ,camera.position.y + (FlappyBird.HEIGHT / 4));
        spriteBatch.end();
    }

    private void updateGround(){
        if (camera.position.x - (camera.viewportWidth /2) > groundpos1.x + ground.getWidth())
            groundpos1.add(ground.getWidth() * 2,0);
        if (camera.position.x - (camera.viewportWidth /2) > groundpos2.x + ground.getWidth())
            groundpos2.add(ground.getWidth() * 2,0);
    }


    @Override
    public void dispose() {


        ground.dispose();
    background.dispose();
    bird.dispose();
    PerderVida.dispose();
    countTube.dispose();
    for (Tube tube: tubes){
    tube.dispose();
    yourbitmapfontname.dispose();
    }
        System.out.println("PLAY STATE DISPOSE");
    }
}
