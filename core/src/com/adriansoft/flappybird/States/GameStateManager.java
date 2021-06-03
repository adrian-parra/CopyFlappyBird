package com.adriansoft.flappybird.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

//ENCARGADO DE ADMINISTRAR LOS ESTADOS
public class GameStateManager {
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    //DECLARANDO PILA...QUE CONTENDRA LOS ESTADOS
    private Stack<State> states;

    //CONSTRUCTOR
    public GameStateManager() {
        states = new Stack<State>();
    }

    //ENCARGADO DE INSERTAR EL ESTADO A LA PILA
    public void push(State state){
        //INSERTANDO ESTADO EN LA PILA
        states.push(state);
    }

    //ENCARGADO DE ELIMINAR EL ULTIMO ESTADO DE NUESTRA PILA
    public void pop(){
    //ELIMINANDO EL ULTIMOM ESTADO DE LA PILA
    states.pop().dispose();
    }

    public void set(State state){
    states.pop().dispose();
    states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);

    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }




}
