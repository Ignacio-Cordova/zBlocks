package juego.code.principales;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import juego.code.entidades.Item;
import juego.code.entidades.Paddle;
import juego.code.entidades.PingBall;
import juego.code.entidades.bloques.Bloque;
import juego.code.entidades.bloques.BloqueDestructible;
import juego.code.gestores.GestorAudio;
import juego.code.gestores.GestorColisiones;
import juego.code.gestores.GestorNiveles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstadoJuego {
    private static final int VIDAS_INICIALES = 3;

    private Estado estado;
    private int vidas;
    private int puntaje;
    private int nivelActual;
    private int highScore;

    private PingBall pelota;
    private Paddle barra;
    private List<Bloque> bloques;
    private List<Item> items;

    public EstadoJuego() {
        estado = Estado.LISTO;
        vidas = VIDAS_INICIALES;
        puntaje = 0;
        nivelActual = 1;
        highScore = 0;
        bloques = new ArrayList<>();
        items = new ArrayList<>();
        inicializarObjetosJuego();
    }

    private void inicializarObjetosJuego() {
        barra = new Paddle(Gdx.graphics.getWidth() / 2 - 50,40,100,10, "barra");

        reiniciarPelota();

        bloques = GestorNiveles.getInstance().crearNivel();

        GestorAudio.getInstance().reproducirCancion("bg", true);
    }

    public void update() {
        switch (estado) {
            case LISTO:
                manejarEstadoListo();
                break;
            case JUGANDO:
                manejarEstadoJugando();
                break;
            case PAUSADO:
                manejarEstadoPausado();
                break;
            case NIVEL_COMPLETADO:
                manejarNivelCompletado();
                break;
            case GAME_OVER:
                manejarGameOver();
                break;
        }
    }

    private void manejarEstadoListo() {
        // Mantiene la pelota sobre la barra hasta que se le dé al espacio y comience el juego
        actualizarPosicionBarra();
        pelota.setPosicion(barra.getPosX() + barra.getAncho()/2 - 5,
                barra.getPosY() + barra.getAlto() + 11);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            estado = Estado.JUGANDO;
            pelota.setEstaQuieto(false);
        }
    }

    private void manejarEstadoJugando() {
        // Actualizamos las distintas entidades y verificamos colisiones
        actualizarPosicionBarra();
        if (!pelota.estaQuieto()) {
            pelota.update();
        }
        actualizarItems();
        procesarColisiones();
        actualizarBloques();

        // Luego verificamos condiciones como haber perdido vida, pasado de nivel o pausa
        verificarCondiciones();
    }

    private void manejarEstadoPausado() {
        // TODO menú pausa
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            estado = Estado.JUGANDO;
        }
    }

    private void manejarNivelCompletado() {
        // Aumentamos el nivel y damos vida si es que tiene menos de las iniciales
        nivelActual++;
        if (vidas < VIDAS_INICIALES) {
            vidas++;
        }

        for (Item item : items) {
            item.desactivar(barra, pelota);
        }

        // Reproducimos el sonido
        GestorAudio.getInstance().reproducirSonido("level-up");

        // Iniciamos el nuevo nivel y cambiamos el estado actual del juego
        iniciarNivel();
        estado = Estado.LISTO;
    }

    private void manejarGameOver() {
        // TODO: Agregar pantalla de game over con puntaje obtenido, nivel máximo, etc
        if (puntaje > highScore) {
            highScore = puntaje;
        }
        reiniciarJuego();
    }

    private void reiniciarPelota() {
        // Inicia la pelota o la reinicia a los valores default dependiendo del caso
        pelota = new PingBall(
                barra.getPosX() + barra.getAncho() / 2 - 5,
                barra.getPosY() + barra.getAlto() + 11,
                10, true,
                "pelota");
    }

    private void actualizarPosicionBarra() {
        barra.update();
    }

    private void actualizarItems() {
        List<Item> itemsAEliminar = new ArrayList<>();

        for (Item item : items) {
            if (item.estaActivo()) {
                // El item está cayendo
                item.update();
                if (item.getPosY() <= 0) {
                    // El item se salió de la pantalla
                    itemsAEliminar.add(item);
                }
            } else {
                // El item fue recogido o no está activo
                if (item.isEfectoActivo()) {
                    // El efecto está activo
                    item.actualizarTemporizador(barra, pelota);
                }
                // Solo eliminar si no está activo Y no tiene efecto activo
                if (!item.estaActivo() && !item.isEfectoActivo()) {
                    itemsAEliminar.add(item);
                }
            }
        }

        items.removeAll(itemsAEliminar);
    }

    private void procesarColisiones() {
        // Procesa las distintas posibles colisiones entre entidades usando el gestor para esto mismo
        GestorColisiones.getInstance().checkBallBlockCollisions(pelota, bloques);
        GestorColisiones.getInstance().checkBallPaddleCollision(pelota, barra);
        GestorColisiones.getInstance().checkItemPaddleCollision(items, barra, pelota);
    }

    private void actualizarBloques() {
        List<Bloque> bloquesAEliminar = new ArrayList<>();

        for (Bloque bloque : bloques) {
            if (bloque.isDestroyed()) {
                Item item = ((BloqueDestructible) bloque).obtenerItem();
                if (item != null)
                    items.add(item);
                puntaje++;
                bloquesAEliminar.add(bloque);
            }
        }

        bloques.removeAll(bloquesAEliminar);
    }

    private void verificarCondiciones() {
        // Verificamos las condiciones especiales de un nivel como perder la pelota, nivel completado o pausa
        verificarPelotaPerdida();
        verificarNivelCompletado();
        verificarPausa();
    }

    private void verificarPelotaPerdida() {
        // Verificamos si el jugador perdió la pelota
        if (pelota.getPosY() <= 0) {
            // Si es así restamos vida y vemos si es game over para cambiar el estado del juego
            vidas--;

            // Desactivamos los efectos de los ítems
            for (Item item : items) {
                item.desactivar(barra, pelota);
            }

            if (vidas <= 0) {
                GestorAudio.getInstance().reproducirSonido("game-over");
                estado = Estado.GAME_OVER;
            } else {
                GestorAudio.getInstance().reproducirSonido("vida-perdida");
                reiniciarPelota();
                estado = Estado.LISTO;
            }
        }
    }

    private void verificarNivelCompletado() {
        // Si se cumple que el nivel está completado, se actualiza el estado
        if (bloques.isEmpty() || soloQuedanBloquesIndestructibles()) {
            estado = Estado.NIVEL_COMPLETADO;
        }
    }

    private void verificarPausa() {
        // Si se presiona la tecla 'P' el estado pasa a estado pausado
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            estado = Estado.PAUSADO;
        }
    }

    private boolean soloQuedanBloquesIndestructibles() {
        for (Bloque bloque : bloques) {
            if (bloque.esDestructible()) {
                return false;
            }
        }
        return true;
    }

    private void iniciarNivel() {
        // Inicia un nivel limpiando los items que hayan quedado y creando el nivel para los bloques
        items.clear();
        bloques = GestorNiveles.getInstance().crearNivel();
        reiniciarPelota();
    }

    private void reiniciarJuego() {
        // Reinicia el juego con los valores iniciales
        vidas = VIDAS_INICIALES;
        puntaje = 0;
        nivelActual = 1;
        items.clear();
        iniciarNivel();
        estado = Estado.LISTO;
    }

    /**
     * Obtiene los bloques del juego en una lista inmutable.
     * @return Lista de bloques.
     */
    public List<Bloque> obtenerBloquesInmodificable() {
        return Collections.unmodifiableList(bloques);
    }

    /**
     * Obtiene los items del juego en una lista inmutable.
     * @return Lista de items.
     */
    public List<Item> obtenerItemsInmodificable() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Obtiene la barra del juego.
     * @return Barra del juego.
     */
    public Paddle obtenerBarra() {
        return barra;
    }

    /**
     * Obtiene la pelota del juego.
     * @return Pelota del juego.
     */
    public PingBall obtenerPelota() {
        return pelota;
    }

    /**
     * Obtiene el puntaje actual del juego.
     * @return Puntaje actual.
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * Obtiene el nivel actual del juego.
     * @return Nivel actual.
     */
    public int getVidas() {
        return vidas;
    }

    /**
     * Obtiene el puntaje más alto del juego.
     * @return Puntaje más alto.
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Obtiene el nivel actual del juego.
     * @return Nivel actual.
     */
    public int getNivelActual() {
        return nivelActual;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
