package com.mygdx.pongdemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		battons = new Array<Batton>();
		balls = new Array<Ball>();
		battons.add(new Batton(false));
		battons.add(new Batton(true));
		font = new BitmapFont();
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
		for (Batton batton: battons){
			batch.draw(batton.getSprite(), batton.getBattonRectX(), batton.getBattonRectY());
			batton.update();
		}
		if (balls != null) {
			for (Ball ball : balls) {
				batch.draw(ball.getSprite(), ball.getBallRectX(), ball.getBallRectY());
				//font.draw(batch, "Direction: " + String.valueOf(ball.getDirection()), ball.getBallRectX(), ball.getBallRectY() + 60);
				ball.update();
				for (Batton batton : battons) {
					if (ball.getBallRect().overlaps(batton.getBattonRect())) {
						//ball.setDirection(ball.getDirection() - 180);
						ball.bounce(false);
						ball.ballSound(false);
					}
				}
			}
		}
		batch.end();
		camera.update();
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			spawnBall();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.H)){
            /*for (Iterator<Ball> ball = balls.iterator(); ball.hasNext();){
				ball.remove();
			}*/
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
	}
}
