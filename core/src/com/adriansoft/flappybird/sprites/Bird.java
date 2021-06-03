package com.adriansoft.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;



public class Bird {
    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Animation birdAnimation;
    private Texture texture;


    private Vector3 position ,velocity;

    private Sound flap;


    private Rectangle bounds;
    public Bird(int x ,int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
    texture = new Texture("birdanimation.png");
    birdAnimation = new Animation(new TextureRegion(texture) ,3,0.5f);
    flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    bounds = new Rectangle(x,y ,texture.getWidth() / 3,texture.getHeight());
    }

    public void update(float dt) {
        birdAnimation.update(dt);
        if (position.y > 0){
            //SIMULACION DE GRABEDAD DEL BIRD
            velocity.add(0,GRAVITY ,0);
        }

        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);
        if (position.y < 0){
            position.y = 0;
        }

        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);
    }

    public Rectangle getBounds() {
    return bounds;
    }
    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump() {
        velocity.y = 250;
        flap.play(0.5f);
       // velocity.add(0,250,0);
    }

    public void dispose(){
        texture.dispose();
        flap.dispose();
    }

}
