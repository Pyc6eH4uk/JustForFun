package tppo.fileloadandshow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by pyc6eh4uk on 02.04.17.
 */

public class PauseScreen implements Screen {
    private Label title;
    private Stage stage;
    private Skin mySkin;
    int row_height = Gdx.graphics.getWidth() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;
    public PauseScreen() {
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
        mySkin = new Skin(Gdx.files.internal("glassy/glassy-ui.json"));
        System.out.println("HUS");
        title = new Label("Pause Game", mySkin, "default");
        title.setSize(Gdx.graphics.getWidth(), row_height * 2);
        title.setPosition(0, Gdx.graphics.getHeight() - row_height * 2);
        title.setAlignment(Align.center);
        stage.addActor(title);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
