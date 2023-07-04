package com.vbm.platformgame01.helper;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TileMapHelper {

    private TiledMap tiledMap;

    public OrthogonalTiledMapRenderer setupMap(){
        tiledMap = new TmxMapLoader().load("maps/platform01/platform01.tmx");

        return new OrthogonalTiledMapRenderer(tiledMap);
    }
}