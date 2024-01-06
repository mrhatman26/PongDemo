package com.mygdx.pongdemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import org.w3c.dom.css.Rect;

public class Ball {
    private Texture sprite;
    private Rectangle ballRect;
    private Array<Sound> sounds;
    private float direction;
    //private int speed = 100;//600;
    private int speedX = 600;
    private int speedY = speedX;
    private int startX = 616;
    private int startY = 336;

    public Ball(){
        this.sprite = new Texture(Gdx.files.internal("spr_ball.png"));
        this.ballRect = new Rectangle();
        this.ballRect.width = 24;
        this.ballRect.height = 24;
        this.ballRect.x = startX;
        this.ballRect.y = startY;
        if (MathUtils.random(0, 1) == 1) {
            this.direction = MathUtils.random(-45, 45);
        }
        else{
            this.direction = MathUtils.random(135, 225);
        }
        this.speedX = MathUtils.random(400, 1400);
        this.speedY = speedX;
        this.sounds = new Array<Sound>();
        //This is going to be dirty code, but whatever...
        this.sounds.add(Gdx.audio.newSound(Gdx.files.internal("bounceA.wav")));
        this.sounds.add(Gdx.audio.newSound(Gdx.files.internal("bounceB.wav")));
        this.sounds.add(Gdx.audio.newSound(Gdx.files.internal("bounceC.wav")));
        this.sounds.add(Gdx.audio.newSound(Gdx.files.internal("bounceD.wav")));
        this.sounds.add(Gdx.audio.newSound(Gdx.files.internal("bounceE.wav")));
        this.sounds.add(Gdx.audio.newSound(Gdx.files.internal("bounceF.wav")));
        this.sounds.add(Gdx.audio.newSound(Gdx.files.internal("bounceG.wav")));
        this.sounds.add(Gdx.audio.newSound(Gdx.files.internal("bounceH.wav")));
        this.sounds.add(Gdx.audio.newSound(Gdx.files.internal("bounceI.wav")));
        this.sounds.add(Gdx.audio.newSound(Gdx.files.internal("bounceJ.wav")));
        this.sounds.add(Gdx.audio.newSound(Gdx.files.internal("fallA.wav")));
        this.sounds.add(Gdx.audio.newSound(Gdx.files.internal("fallB.wav")));
        //Eww
    }

    public Texture getSprite(){
        return sprite;
    }

    public Rectangle getBallRect(){
        return this.ballRect;
    }

    public float getBallRectX(){
        return ballRect.x;
    }

    public float getBallRectY(){
        return ballRect.y;
    }

    public float getDirection(){
        return this.direction;
    }

    public void setDirection(float newDir){
        this.direction = newDir;
    }

    public void bounce(boolean isY){
        if (isY){
            speedY *= -1;
        }
        else {
            speedX *= -1;
        }
        correctDirection();
    }

    public void correctDirection(){
        if (direction > 360){
            direction -= 360;
        }
        if (direction < 0){
            direction += 360;
        }
    }

    public void reset(){
        ballRect.x = startX;
        ballRect.y = startY;
        if (MathUtils.random(0, 1) == 1) {
            direction = MathUtils.random(-45, 45);
        }
        else{
            direction = MathUtils.random(135, 225);
        }
        speedX = MathUtils.random(400, 1400);
        speedY = speedX;
    }

    public void ballSound(boolean fallenOff){
        if (!fallenOff){
            sounds.get(MathUtils.random(0, 9)).play();
        }
        else{
            sounds.get(MathUtils.random(10, 11)).play();
        }
    }

    public void update(){
        PlayerControls.temp(this);
        if (ballRect.y < 0 || ballRect.y > 696) {
            bounce(true);
            ballSound(false);
        }
        if (ballRect.x < 0 || ballRect.x > 1280){
            reset();
            ballSound(true);
        }
        correctDirection();
        ballRect.x += (speedX * (float) Math.sin(direction)) * Gdx.graphics.getDeltaTime();
        ballRect.y += (speedY * (float) Math.cos(direction)) * Gdx.graphics.getDeltaTime();
    }

    public void dispose(){
        sprite.dispose();
    }
}
