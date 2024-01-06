package com.mygdx.pongdemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input;

public class PlayerControls {
    private static final int MOVE_SPEED = 400;
    public static void checkControls(Rectangle battonRect, boolean isSecondPlayer){
        if (isSecondPlayer){
            if (Gdx.input.isKeyPressed(Input.Keys.UP)){
                battonRect.y += MOVE_SPEED * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                battonRect.y -= MOVE_SPEED * Gdx.graphics.getDeltaTime();
            }
        }
        else {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                battonRect.y += MOVE_SPEED * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                battonRect.y -= MOVE_SPEED * Gdx.graphics.getDeltaTime();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
            System.exit(0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.R) && (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT))){
            Gdx.app.exit();
        }
    }

    public static void temp(Ball ball){
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            ball.setDirection(ball.getDirection() + 0.1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            ball.setDirection(ball.getDirection() - 0.1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.R)){
            ball.reset();
        }
    }
}
