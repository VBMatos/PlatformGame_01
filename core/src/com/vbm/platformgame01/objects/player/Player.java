package com.vbm.platformgame01.objects.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.vbm.platformgame01.helper.Constants;

public class Player extends Entity{

    public Player(float width, float height, Body body) {
        super(width, height, body);
    }

    @Override
    public void update() {
        x = body.getPosition().x * Constants.PPM;
        y = body.getPosition().y * Constants.PPM;
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
