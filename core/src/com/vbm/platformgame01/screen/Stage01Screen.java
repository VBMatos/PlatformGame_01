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
import com.vbm.platformgame01.helper.TileMapHelper;

import static com.vbm.platformgame01.helper.Constants.PPM;

public class Stage01Screen extends ScreenAdapter {

    private OrthographicCamera orthCam;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    private OrthogonalTiledMapRenderer orthTiledMapRenderer;
    private TileMapHelper tileMapHelper;

    public Stage01Screen(OrthographicCamera orthCam){
        this.orthCam = orthCam;
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0, 0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();

        this.tileMapHelper = new TileMapHelper();
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

        box2DDebugRenderer.render(world, orthCam.combined.scl(PPM));
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
        orthCam.position.set(new Vector3(0, 0, 0));
        orthCam.update();
    }
}