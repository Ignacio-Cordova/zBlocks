package puppy.code;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BlockBreakerGame extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;
	private PingBall ball;
	private Paddle pad;
	private ArrayList<Bloque> bloques = new ArrayList<>();
	private ArrayList<Item> items = new ArrayList<>();
	private int vidas;
	private int puntaje;
	private int nivel;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 850, 480);

		batch = new SpriteBatch();

		font = new BitmapFont();
		font.getData().setScale(3, 2);

		GestorTexturas gestorTexturas = GestorTexturas.getInstance();

		gestorTexturas.cargarTextura("pelota", "texturas/pelota.png");
		gestorTexturas.cargarTextura("barra", "texturas/barra.png");
		gestorTexturas.cargarTextura("tierra", "texturas/tierra.png");
		gestorTexturas.cargarTextura("tierra-poder", "texturas/tierra-poder.png");
		gestorTexturas.cargarTextura("piedra", "texturas/piedra.png");
		gestorTexturas.cargarTextura("piedra-poder", "texturas/piedra-poder.png");
		gestorTexturas.cargarTextura("rubi", "texturas/rubi.png");
		gestorTexturas.cargarTextura("rubi-poder", "texturas/rubi-poder.png");

		gestorTexturas.cargarTextura("angosto", "texturas/angosto.png");
		gestorTexturas.cargarTextura("amplio", "texturas/amplio.png");
		gestorTexturas.cargarTextura("lento", "texturas/lento.png");
		gestorTexturas.cargarTextura("veloz", "texturas/veloz.png");


		GestorAudio gestorAudio = GestorAudio.getInstance();

		gestorAudio.cargarCancion("bg", "musica/bg-music.mp3");
		gestorAudio.reproducirCancion("bg", true);

		gestorAudio.cargarSonido("level-up", "sfx/level-up.mp3");
		gestorAudio.cargarSonido("game-over", "sfx/game-over.mp3");
		gestorAudio.cargarSonido("indestructible", "sfx/indestructible.mp3");
		gestorAudio.cargarSonido("tierra", "sfx/tierra.mp3");
		gestorAudio.cargarSonido("tierra-poder", "sfx/tierra-poder.mp3");
		gestorAudio.cargarSonido("piedra", "sfx/piedra.mp3");
		gestorAudio.cargarSonido("piedra-poder", "sfx/piedra-poder.mp3");
		gestorAudio.cargarSonido("rubi", "sfx/rubi.mp3");
		gestorAudio.cargarSonido("rubi-poder", "sfx/rubi-poder.mp3");
		gestorAudio.cargarSonido("pelota", "sfx/pelota.mp3");

		ball = new PingBall((float) Gdx.graphics.getWidth() /2-10, 41, 10, 2.5f, 3.7f, true, "pelota", "pelota");
		pad = new Paddle(Gdx.graphics.getWidth()/2 - 50,40,100,10, "barra");

		vidas = 3;
		puntaje = 0;
		nivel = 1;
		crearBloques(nivel);
	}

	/*public void crearBloques(int filas) {
		bloques.clear();
		FabricaBloques fabricaBloques = new InstanciaFabricaBloques();
		int blockWidth = 75;
		int blockHeight = 25;
		int y = Gdx.graphics.getHeight();
		for (int cont = 0; cont < filas; cont++) {
			y -= blockHeight+10;
			for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
//				bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "tierra",
//																1, false));
				bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "tierra-poder",
																1, true));
			}
		}
	}
	*/
	public void crearBloques(int nivel) {
		bloques.clear();
		FabricaBloques fabricaBloques = new InstanciaFabricaBloques();
		int blockWidth = 75;
		int blockHeight = 25;
		int y = Gdx.graphics.getHeight();
		if (nivel == 1) {
			crearNivel1(fabricaBloques, blockHeight, blockWidth, y);
		} else if (nivel == 2) {
			crearNivel2(fabricaBloques, blockHeight, blockWidth, y);
		}
		else if (nivel == 3){
			crearNivel3(fabricaBloques, blockHeight, blockWidth, y);
		}
	}

	public void crearNivel1(FabricaBloques fabricaBloques ,int blockHeight, int blockWidth, int y) {
		for (int cont = 0; cont < 3; cont++) {
			y -= blockHeight + 10;

			for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
				if (cont == 2) {
					bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "tierra",
							1, false));
				} else if (x == 430 && cont == 1 || x == 90 && cont == 1) {
					bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "piedra-poder",
							2, true));
				} else if (x == 175 && cont == 0) {
					bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "rubi-poder",
							3, true));
				} else {
					bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "piedra",
							2, false));
				}
			}
		}
	}

	public void crearNivel2(FabricaBloques fabricaBloques, int blockHeight, int blockWidth, int y) {
		for (int cont = 0; cont < 5; cont++) {
			y -= blockHeight + 10;

			for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
				if (cont == 0){
					if (x == 345 || x == 515 || x == 685){
						crearBloqueAleatorio(fabricaBloques, blockWidth, blockHeight, x, y);
					}
					else if (x == 90 || x == 175 || x == 260 || x == 430 || x == 600){
						crearBloqueAleatorio(fabricaBloques, blockWidth, blockHeight, x, y);
					}
					else if (x == 770){
						crearBloqueAleatorio(fabricaBloques, blockWidth, blockHeight, x, y);
					}
					else {
						bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "rubi-poder",
								3, true));
					}
				}
				else if (cont == 1){
					if (x == 5 || x == 770){
						crearBloqueAleatorio(fabricaBloques, blockWidth, blockHeight, x, y);
					}
				}
				else if (cont == 2){
					if (x == 600 || x == 770){
						bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "piedra-poder", 2, true));
					}
					else if (x != 685){
						crearBloqueAleatorio(fabricaBloques, blockWidth, blockHeight, x, y);
					}
				}
				else if (cont == 3){
					if (x == 5 || x == 770){
						crearBloqueAleatorio(fabricaBloques, blockWidth, blockHeight, x, y);
					}
				}
				else if (cont == 4){
					if (x != 90){
						crearBloqueAleatorio(fabricaBloques, blockWidth, blockHeight, x, y);
					}
				}
			}
		}
	}

	public void crearNivel3(FabricaBloques fabricaBloques, int blockHeight, int blockWidth, int y){
		for (int cont = 0; cont < 7; cont++) {
			y -= blockHeight + 10;

			for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
				if (cont == 0 || cont == 6){
					if (x == 345 || x == 430){
						bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "tierra", 1, false));
					}
				}
				else if (cont == 1 || cont == 5){
					if (x == 260 || x == 515){
						bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "tierra", 1, false));
					}
					else if (x == 345 || x == 430){
						bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "piedra", 2, false));
					}
				}
				else if (cont == 2 || cont == 4){
					if (x == 175 || x == 600){
						bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "tierra", 1, false));
					}
					else if (x == 260 || x == 515){
						bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "piedra", 2, false));
					}
					else if(x == 345 || x == 430){
						bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "rubi", 3, false));
					}
				}
				else{
					if (x == 90 || x == 685){
						bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "tierra", 1, false));
					}
					else if (x == 175 || x == 600){
						bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "piedra", 2, false));
					}
					else if (x == 260 || x == 515){
						bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "rubi", 3, false));
					}
					else if (x == 345 || x == 430){
						// bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "obsidiana", 3, true));
					}
				}
			}
		}
	}

	public void crearBloqueAleatorio(FabricaBloques fabricaBloques, int blockWidth, int blockHeight, int x, int y) {
		int random = (int) (Math.random() * 100); // Genera un número entre 0 y 99
		if (random < 70) {
			// 70% de probabilidad para piedra
			bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "piedra", 2, false));
		} else if (random < 95) {
			// 25% de probabilidad para tierra (70 a 94)
			bloques.add(fabricaBloques.crearBloqueDestructible(x, y, blockWidth, blockHeight, "tierra", 1, false));
		} else {
			// 5% de probabilidad para rubí (95 a 99)
			bloques.add(fabricaBloques.crearBloqueIndestructible(x, y, blockWidth, blockHeight, "rubi"));
		}
	}

	public void dibujaTextos() {
		//actualizar matrices de la cámara
		camera.update();
		//actualizar
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//dibujar textos
		font.getData().setScale(0.5f);
		font.draw(batch, "Puntos: " + puntaje, 10, 25);
		font.draw(batch, "Vidas : " + vidas, Gdx.graphics.getWidth() - 180, 25);
		batch.end();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		GestorColisiones gestorColisiones = GestorColisiones.getInstance();

		batch.begin();

		pad.draw(batch);

		// monitorear inicio del juego
		if (ball.estaQuieto()) {
			ball.setPosicion(pad.getPosX() + pad.getAncho()/2 - 5, pad.getPosY() + pad.getAlto() + 11);
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
		} else ball.update();

		//verificar si se fue la bola x abajo
		if (ball.getPosY() <= 0) {
			vidas--;
			//nivel = 1;
			ball = new PingBall(pad.getPosX()+pad.getAncho()/2 - 5, pad.getPosY() + pad.getAlto() + 11, 10,
					2.5f, 3.7f, true, "pelota", "pelota");
		}

		gestorColisiones.checkBallBlockCollisions(ball, bloques);
		gestorColisiones.checkBallPaddleCollision(ball, pad);
		gestorColisiones.checkItemPaddleCollision(items, pad, ball);

		// verificar game over
		if (vidas <= 0) {
			items.clear();
			vidas = 3;
			nivel = 1;
			crearBloques(nivel);
			//ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
		}

		// verificar si el nivel se terminó
		if (bloques.size() == 0) {
			nivel++;
			items.clear();
			if (vidas < 3) vidas++;
			crearBloques(nivel);
			ball = new PingBall(pad.getPosX() + pad.getAncho()/2 - 5, pad.getPosY() + pad.getAlto() + 11,
					10, 2.5f, 3.7f, true, "pelota", "pelota");
		}

		//dibujar bloques
		for (Bloque b : bloques) {
			b.draw(batch);
		}

		// actualizar estado de los bloques
		for (int i = 0; i < bloques.size(); i++) {
			Bloque b = bloques.get(i);
			if (b.isDestroyed()) {
				Item item = ((BloqueDestructible) b).getItem();
				if (item != null)
					items.add(item);
				puntaje++;
				bloques.remove(b);
				i--; //para no saltarse 1 tras eliminar del arraylist
			}
		}

		//dibujar items
		Iterator<Item> itemIterator = items.iterator();
		while (itemIterator.hasNext()) {
			Item item = itemIterator.next();
			if (item.estaActivo()) {
				item.draw(batch);
				item.caer();
			} else {
				itemIterator.remove();
			}
		}

		ball.draw(batch);

		batch.end();

		dibujaTextos();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
