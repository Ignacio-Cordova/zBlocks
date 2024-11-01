package juego.code.principales;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import juego.code.entidades.bloques.Bloque;
import juego.code.gestores.GestorTexturas;
import juego.code.entidades.Item;

/**
 * Clase que se encarga de renderizar los objetos del juego.
 */
public class RenderizadorJuego {
    private final OrthographicCamera camara;
    private final SpriteBatch batch;
    private final BitmapFont fuente;

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

        estado.obtenerBarra().draw(batch);

        for (Bloque bloque : estado.obtenerBloquesInmodificable()) {
            bloque.draw(batch);
        }

        for (Item item : estado.obtenerItemsInmodificable()) {
            if (item.estaActivo()) {
                item.draw(batch);
            }
        }

        estado.obtenerPelota().draw(batch);

        batch.end();

        dibujarHUD(estado.getHighScore(), estado.getPuntaje(), estado.getNivelActual(), estado.getVidas());
    }

    /**
     * Dibuja los textos de puntaje, nivel y vidas en la pantalla.
     * @param highScore High score del jugador.
     * @param puntaje Puntaje actual del jugador.
     * @param nivel Nivel actual del juego.
     * @param vidas Vidas restantes del jugador.
     */
    private void dibujarHUD(int highScore, int puntaje, int nivel, int vidas) {
        camara.update();
        batch.setProjectionMatrix(camara.combined);
        batch.begin();

        // Escala de la fuente y posición en Y (parte inferior de la pantalla)
        float escalaFuente = 1.5f;
        fuente.getData().setScale(escalaFuente);
        float yPos = 25;

        // Posiciones en X para cada elemento, calculadas de acuerdo al tamaño de la pantalla
        float espacio = 230; // Espacio entre cada elemento
        float xHighScore = 10; // Posición inicial para el high score
        float xPuntaje = xHighScore + espacio;
        float xNivel = xPuntaje + espacio;
        float xVidas = xNivel + espacio;

        // Dibujar cada elemento en su posición respectiva
        fuente.draw(batch, "Record: " + highScore, xHighScore, yPos);
        fuente.draw(batch, "Puntaje: " + puntaje, xPuntaje, yPos);
        fuente.draw(batch, "Nivel: " + nivel, xNivel, yPos);
        fuente.draw(batch, "Vidas: " + vidas, xVidas, yPos);

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
