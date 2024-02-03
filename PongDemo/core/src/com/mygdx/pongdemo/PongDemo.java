package com.mygdx.pongdemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Iterator;

public class PongDemo extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Array<Batton> battons;
	private Array<Ball> balls;
	private BitmapFont font;
	private Texture centreTexture;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		battons = new Array<Batton>();
		balls = new Array<Ball>();
		battons.add(new Batton(false));
		battons.add(new Batton(true));
		FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("pong_score.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameters.size = 240;
		font = fontGenerator.generateFont(fontParameters);
		fontGenerator.dispose();
		centreTexture = new Texture(Gdx.files.internal("centre.png"));
	}

	public void spawnBall(){
		if (balls == null){
			balls = new Array<Ball>();
		}
		balls.add(new Ball());
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(centreTexture, 624, 0);
		for (Batton batton: battons){
			batch.draw(batton.getSprite(), batton.getBattonRectX(), batton.getBattonRectY());
			batton.drawScore(batch, font);
			batton.update();
		}
		if (balls != null) {
			for (Ball ball : balls) {
				batch.setColor(ball.spriteColour[0], ball.spriteColour[1], ball.spriteColour[2], 1f);
				batch.draw(ball.getSprite(), ball.getBallRectX(), ball.getBallRectY());
				batch.setColor(Color.WHITE);
				ball.update(battons);
				/*for (Batton batton : battons) {
					if (ball.getBallRect().overlaps(batton.getBattonRect())) {
						if (ball.getBallRectY() >= batton.getBattonRectY() + 260 || ball.getBallRectY() <= batton.getBattonRectY()){
							ball.bounce(true);
						}
						else {
							ball.bounce(false);
						}
						ball.ballSound(false);
					}
				}*/
			}
		}
		batch.end();
		camera.update();
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			spawnBall();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.H)){
			balls = null;
		}
	}

	@Override
	public void dispose () {
		for (Batton batton: battons){
			batton.dispose();
		}
		for (Ball ball: balls){
			ball.dispose();
		}
		font.dispose();
		batch.dispose();
		battons = null;
		balls = null;
		centreTexture.dispose();
	}
}
