package com.vbm.platformgame01.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.vbm.platformgame01.helper.Constants;
import com.vbm.platformgame01.helper.TileMapHelper;
import com.vbm.platformgame01.objects.player.Player;

public class Stage01Screen extends ScreenAdapter {

    private OrthographicCamera orthCam;
    private SpriteBatch batch;

    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    private OrthogonalTiledMapRenderer orthTiledMapRenderer;
    private TileMapHelper tileMapHelper;

    //Game objects
    private Player player;

    public Stage01Screen(OrthographicCamera orthCam){
        this.orthCam = orthCam;
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0, -9.81F), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();

        this.tileMapHelper = new TileMapHelper(this);
        this.orthTiledMapRenderer = tileMapHelper.setupMap();
    }

    @Override
    public void render(float delta) {
        update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthTiledMapRenderer.render();

        batch.begin();
        //Render objects
        batch.end();

        box2DDebugRenderer.render(world, orthCam.combined.scl(Constants.PPM));
    }

    private void update(){
        world.step(1/60F, 6, 2);
        cameraUpdate();

        batch.setProjectionMatrix(orthCam.combined);
        orthTiledMapRenderer.setView(orthCam);

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
    }

    private void cameraUpdate(){
        Vector3 position = orthCam.position;
        position.x = Math.round(player.getBody().getPosition().x * Constants.PPM * 10) / 10F;
        position.y = Math.round(player.getBody().getPosition().y * Constants.PPM * 10) / 10F;

        orthCam.position.set(position);
        orthCam.update();
    }

    public World getWorld() {
        return world;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}