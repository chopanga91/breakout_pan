package com.pan.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.pan.breakout.screens.StartScreen;

public class BreakoutGame extends Game {
	public SpriteBatch batch;
	public static Array<Texture> textures;
	public static ObjectMap<String, Sound> sonidos;
	public static Music musica;
	public static OrthographicCamera camera;
	public static BitmapFont fontL;
	public static BitmapFont fontM;

	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
		textures = new Array<>();
		sonidos = new ObjectMap<>();
		agregarSonidos();
		agregarTexturas();
		Texture fondo = textures.get(1);
		musica = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.wav"));
		fontL = new BitmapFont(Gdx.files.internal("fonts/fuente.fnt"));
		fontM = new BitmapFont(Gdx.files.internal("fonts/fuente.fnt"));
		this.setScreen(new StartScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		fontL.dispose();
		fontM.dispose();
		for (Texture texture : textures) {
			texture.dispose();
		}
		for (Sound sonido : sonidos.values()) {
			sonido.dispose();
		}
		musica.dispose();
	}

	public void escribirTextoL(String texto, int x, int y, float tamano) {
		fontL.getData().setScale(tamano);
		fontL.draw(batch, texto, x, y);
	}

	public void escribirTextoM(String texto, int x, int y, float tamano) {
		fontM.getData().setScale(tamano);
		fontM.draw(batch, texto, x, y);
	}

	private void agregarTexturas() {
		textures.add(new Texture("textures/arrows.png"));
		textures.add(new Texture("textures/background.png"));
		textures.add(new Texture("textures/blocks.png"));
		textures.add(new Texture("textures/breakout.png"));
		textures.add(new Texture("textures/breakout_big.png"));
		textures.add(new Texture("textures/hearts.png"));
		textures.add(new Texture("textures/particle.png"));
		textures.add(new Texture("textures/ui.png"));
	}

	private void agregarSonidos() {
		sonidos.put("brick-hit-1", Gdx.audio.newSound(Gdx.files.internal("sounds/brick-hit-1.wav")));
		sonidos.put("brick-hit-2", Gdx.audio.newSound(Gdx.files.internal("sounds/brick-hit-2.wav")));
		sonidos.put("confirm", Gdx.audio.newSound(Gdx.files.internal("sounds/confirm.wav")));
		sonidos.put("high_score", Gdx.audio.newSound(Gdx.files.internal("sounds/high_score.wav")));
		sonidos.put("hurt", Gdx.audio.newSound(Gdx.files.internal("sounds/hurt.wav")));
		sonidos.put("no-select", Gdx.audio.newSound(Gdx.files.internal("sounds/no-select.wav")));
		sonidos.put("paddle_hit", Gdx.audio.newSound(Gdx.files.internal("sounds/paddle_hit.wav")));
		sonidos.put("pause", Gdx.audio.newSound(Gdx.files.internal("sounds/pause.wav")));
		sonidos.put("recover", Gdx.audio.newSound(Gdx.files.internal("sounds/recover.wav")));
		sonidos.put("score", Gdx.audio.newSound(Gdx.files.internal("sounds/score.wav")));
		sonidos.put("select", Gdx.audio.newSound(Gdx.files.internal("sounds/select.wav")));
		sonidos.put("victory", Gdx.audio.newSound(Gdx.files.internal("sounds/victory.wav")));
		sonidos.put("wall_hit", Gdx.audio.newSound(Gdx.files.internal("sounds/wall_hit.wav")));
	}
}
