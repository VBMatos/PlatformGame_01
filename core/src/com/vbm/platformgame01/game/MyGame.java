package com.vbm.platformgame01.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.vbm.platformgame01.screen.Stage01Screen;

public class MyGame extends Game {

	public static MyGame INSTANCE;

	private int widthScreen;
	private int heightScreen;

	private OrthographicCamera orthCam;

	public MyGame(){
		INSTANCE = this;
	}

	@Override
	public void create () {
		this.widthScreen = Gdx.graphics.getWidth();
		this.heightScreen = Gdx.graphics.getHeight();

		this.orthCam = new OrthographicCamera();
		this.orthCam.setToOrtho(false, widthScreen, heightScreen);
		setScreen(new Stage01Screen(orthCam));
	}
}