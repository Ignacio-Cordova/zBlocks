package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Clase que se encarga de renderizar los objetos del juego.
 */
public class RenderizadorJuego {
    private OrthographicCamera camara;
    private SpriteBatch batch;
    private BitmapFont fuente;

    /**
     * Constructor de la clase.
     */
    public RenderizadorJuego() {
        camara = new OrthographicCamera();
        camara.setToOrtho(false, 850, 480);

        batch = new SpriteBatch();

        fuente = new BitmapFont();
        fuente.getData().setScale(3, 2);
    }

    /**
     * Renderiza los objetos de juego.
     * @param estado Estado del juego.
     */
    public void render(EstadoJuego estado) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        Texture fondo = GestorTexturas.getInstance().getTextura("background");
        batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        estado.getBarra().draw(batch);

        for (Bloque bloque : estado.getBloques()) {
            bloque.draw(batch);
        }

        for (Item item : estado.getItems()) {
            if (item.estaActivo()) {
                item.draw(batch);
            }
        }

        estado.getPelota().draw(batch);

        batch.end();

        dibujarTextos(estado.getPuntaje(), estado.getVidas());
    }

    /**
     * Dibuja los textos de puntaje y vidas en la pantalla.
     * @param puntaje Puntaje del jugador.
     * @param vidas Vidas del jugador.
     */
    public void dibujarTextos(int puntaje, int vidas) {
        camara.update();
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        fuente.getData().setScale(0.5f);
        fuente.draw(batch, "Puntos: " + puntaje, 10, 25);
        fuente.draw(batch, "Vidas : " + vidas, Gdx.graphics.getWidth() - 180, 25);
        batch.end();
    }

    /**
     * Libera los recursos utilizados por el renderizador.
     */
    public void dispose() {
        batch.dispose();
        fuente.dispose();
    }
}
