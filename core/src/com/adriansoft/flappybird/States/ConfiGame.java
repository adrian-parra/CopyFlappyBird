package com.adriansoft.flappybird.States;

import com.adriansoft.flappybird.FlappyBird;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ConfiGame extends State{

    //ATRIBUTOS PARA CALAR BUTTON
    private Stage stage;
    private Skin skin;

    private Texture background;

    Table table;

    private Preferences preferences;

    final TextButton btnGuardar;
    final TextField txtNombre;
    public ConfiGame(GameStateManager gameStateManager) {
        super(gameStateManager);

        preferences = Gdx.app.getPreferences("records");
        //SE OCUPA PARA ANDROID
        camera.setToOrtho(false , FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);

        background = new Texture("bg.png");
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        skin.getFont("default-font").getData().setScale(3.5f);

       table= new Table();
        table.setFillParent(true);

        table.pad(30);




        Label labelTitle = new Label("Ingrese su nombre",skin);
        Label labelNombre = new Label("Nombre",skin);
       // Label labelPuntuacion = new Label("Puntuacion",skin);
         txtNombre = new TextField("",skin);






        //textFieldStyle.font.scale(1.6f);


        //TextField txtPuntuacion = new TextField("",skin);

        btnGuardar = new TextButton("GUARDAR",skin);

        btnGuardar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(!txtNombre.getText().equals("")){
                    preferences.putString("user", txtNombre.getText());
                    preferences.flush();
                    gsm.set(new MenuState(gsm));

                }
            }
        });

        //txtPuntuacion.setScale(1);

        table.add(labelTitle).colspan(2).fill().center().space(20);
        table.row();
        //table.add(labelNombre);
        table.add(txtNombre).colspan(2).fill().center().space(20);

        txtNombre.setMaxLength(8);

        /*
        table.row();
        table.add(labelPuntuacion);
        table.add(txtPuntuacion).width(300).height(100);*/
        table.row();
        table.add(btnGuardar).colspan(2).fill();

        //table.debug();

        stage.addActor(table);


        //button.setPosition(100,100);
        // button.setSize(300 ,300);
        //button.setStyle(button.getStyle());

        //button.sizeBy(50);
        //button.sizeBy(150,150);
       // stage.addActor(button);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        spriteBatch.draw(background,0,0);
        spriteBatch.end();
        stage.draw();




    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();

    }
}
