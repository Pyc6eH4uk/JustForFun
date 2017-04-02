package tppo.fileloadandshow;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import filehandling.MyFileHandle;

public class FileLoadAndShow extends Game{
	SpriteBatch batch;
	Texture img;
	Stage stage, stage1;
	BitmapFont font, bitmapFont;
	public FileHandle fileHandle;
	String text;
	float width, height;
	private static final int FRAME_COLS = 6;
	private static final int FRAME_ROWS = 5;
	Animation walkAnimation;
	Texture walkSheet;
	TextureRegion[] walkFrames;
	SpriteBatch spriteBatch;
	TextureRegion currentFrame;
	float stateTime;
//	private Button button, button1;
	private TextButton button, button1;
	private Skin skin;
	TextureAtlas buttonAtlas;
	private TextButton.TextButtonStyle textButtonStyle;
	private Button.ButtonStyle buttonStyle;
	private boolean GAME_PAUSED = false;
	private Label outputLabel, title;
	private Rectangle rectangle;
	private Window window;

	@Override
	public void create () {
		//fileHandle = new MyFileHandle();
//		stage = new Stage();
//		font = new BitmapFont();
//		fileHandle = Gdx.files.internal("test.txt");
//		System.out.println(fileHandle);
//		text = fileHandle.readString();
//		System.out.println(text);
//		batch = new SpriteBatch();
		Skin mySkin = new Skin(Gdx.files.internal("glassy/glassy-ui.json"));
//		int row_height = Gdx.graphics.getWidth() / 12;
//		int col_width = Gdx.graphics.getWidth() / 12;
		stage = new Stage(new ScreenViewport());
		stage1 = new Stage(new ScreenViewport());
		button = new TextButton("Pause", mySkin, "small");
		button1 = new TextButton("Resume", mySkin, "small");
//		button.setSize(col_width * 4, row_height);
		button.pack();
		button.setPosition(0, stage.getHeight() - button.getHeight());
		button.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				GAME_PAUSED = !GAME_PAUSED;
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		stage.addActor(button);
		title = new Label("Pause Screen", mySkin, "default");
		title.setColor(0, 1, 0, 1f);
//		title.setSize(Gdx.graphics.getWidth(), row_height * 2);
//		title.setPosition(0, Gdx.graphics.getHeight() - row_height * 2);
		title.setAlignment(Align.center);

//		stage1.addActor(title);
		walkSheet = new Texture(Gdx.files.internal("animation_sheet.png"));
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		window = new Window("Pause screen", mySkin);
		float windowWidth = stage.getWidth() - stage.getWidth() / 4;
		float windowHeight = stage.getHeight() - stage.getHeight() / 4;
		Gdx.input.setInputProcessor(stage);
		window.setSize(windowWidth, windowHeight);
		button1.setColor(0, 0, 1, 1f);
		window.setColor(1, 0, 0, 0.6f);
		window.setPosition(stage.getWidth() / 2, stage.getHeight() / 2, Align.center);
		button1.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				stage1.clear();
				GAME_PAUSED = !GAME_PAUSED;
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		button1.setPosition(0, windowHeight - button1.getHeight() - button1.getHeight() / 2);
		window.addActor(button1);
		Gdx.input.setInputProcessor(stage1);
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		Gdx.input.setInputProcessor(stage);
		walkAnimation = new Animation(0.033f, walkFrames);
		spriteBatch = new SpriteBatch();
		stateTime = 0f;
	}

	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		//batch.draw(img, 0, 0);
//		font.draw(batch, text, 0, stage.getHeight());
//		batch.end();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		if (!GAME_PAUSED) {
			Gdx.input.setInputProcessor(stage);
			stateTime += Gdx.graphics.getDeltaTime();
//			button.setTouchable(Touchable.enabled);
//			stage1.clear();
		}
		currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, 50, 50);
		spriteBatch.end();

		stage.act();
		stage.draw();
		if (GAME_PAUSED) {
//			button.setTouchable(Touchable.disabled);
			Gdx.input.setInputProcessor(stage1);
			stage1.addActor(window);
//			button1.setColor(0, 0, 0, 1f);
//			stage1.addActor(title);
			stage1.act();
//			Gdx.gl.glClearColor(1, 0, 0, 1);
			stage1.draw();
		}
	}

	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();
		spriteBatch.dispose();
		stage.dispose();
		stage1.dispose();
	}
}
