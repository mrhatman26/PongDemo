package com.mygdx.pongdemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Batton {
    private Texture sprite;
    private Rectangle battonRect;
    private int score;
    private boolean isSecondPlayer;

    public Batton(boolean isSecondPlayer){
        this.isSecondPlayer = isSecondPlayer;
        this.sprite = new Texture(Gdx.files.internal("spr_batton.png"));
        this.battonRect = new Rectangle();
        this.battonRect.width = 52;//48;
        this.battonRect.height = 260;//256;
        if (this.isSecondPlayer){
            this.battonRect.x = 1192;
        }
        else{
            this.battonRect.x = 40;
        }
        this.battonRect.y = 232;
        this.score = 0;
    }

    public Texture getSprite(){
        return this.sprite;
    }

    public Rectangle getBattonRect(){
        return this.battonRect;
    }

    public float getBattonRectX(){
        return this.battonRect.x;
    }

    public float getBattonRectY(){
        return this.battonRect.y;
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int newScore){
        this.score = newScore;
    }

    public void update(){
        PlayerControls.checkControls(battonRect, isSecondPlayer);
        if (battonRect.y > 464){
            battonRect.y = 464;
        }
        if (battonRect.y < 0){
            battonRect.y = 0;
        }
    }

    public void dispose(){
        sprite.dispose();
    }
}
