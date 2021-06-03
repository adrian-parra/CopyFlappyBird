package com.adriansoft.flappybird.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {


    //MUESTRA SOLO UN PEDASO DEL VIDEOJUEGO...
    //MUESTRA SOLO LO QUE ESTE EN EL MOMENTO
    protected OrthographicCamera camera;

    //FUNCIONA PARA SABER EN QUE PARTE DE LA PANTALLA SE DA CLICK
    protected Vector3 mouse;

    //ES EL ENCARGADO DE ADMINISTRAR TODOS LOS ESTADOS DEL JUEGO...CONTROLARLOS CUANDO APRACEN
    protected GameStateManager gsm;

    //CONSTRUCTOR
    protected State(GameStateManager gameStateManager){
        this.gsm = gameStateManager;
        camera = new OrthographicCamera();
        mouse = new Vector3();

    }

    //METODOS

    //FUNCIONA PARA SABER CUALQUIER TIPO DE ENTRADAS EN EL JUEGO
    protected abstract void handleInput();

    //LOOP GAME

    //ENCARGADO DE ESTAR ACTUALIZANDO EL JUEGO EN TIME REAL
    public abstract void update(float dt);

    //ENCARGADO DE CARGAR TODOS LOS ELEMENTOS NECESARIOS PARA EL JUEGO .ESTADOS ETC
    public abstract void render(SpriteBatch spriteBatch);

    //ENCARGADO DE CERRAR RECURSOS QUE YO NO SE ESTEN USANDO
    public abstract  void dispose();



}
