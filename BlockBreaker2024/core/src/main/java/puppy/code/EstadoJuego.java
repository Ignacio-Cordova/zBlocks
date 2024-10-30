package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstadoJuego {
    private static final int VIDAS_INICIALES = 3;

    private Estado estado;
    private int vidas;
    private int puntaje;
    private int nivelActual;

    private PingBall pelota;
    private Paddle barra;
    private List<Bloque> bloques;
    private List<Item> items;

    public EstadoJuego() {
        estado = Estado.LISTO;
        vidas = VIDAS_INICIALES;
        puntaje = 0;
        nivelActual = 1;
        bloques = new ArrayList<>();
        items = new ArrayList<>();
        inicializarObjetosJuego();
    }

    private void inicializarObjetosJuego() {
        barra = new Paddle(Gdx.graphics.getWidth() / 2 - 50,40,100,10, "barra");

        reiniciarPelota();

        bloques = GestorNiveles.getInstance().crearNivel();
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
        // TODO
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            estado = Estado.JUGANDO;
        }
    }

    private void manejarNivelCompletado() {
        // Aumentamos el nivel y damos vida si es que tiene menos de las iniciales
        nivelActual++;
        if (vidas < VIDAS_INICIALES) {
            vidas++;
        }

        // Reproducimos el sonido
        GestorAudio.getInstance().reproducirSonido("level-up");

        // Iniciamos el nuevo nivel y cambiamos el estado actual del juego
        iniciarNivel();
        estado = Estado.LISTO;
    }

    private void manejarGameOver() {
        // TODO: Agregar pantalla de game over con puntaje obtenido, nivel máximo, etc
        reiniciarJuego();
    }

    private void reiniciarPelota() {
        // Inicia la pelota o la reinicia a los valores default dependiendo del caso
        pelota = new PingBall(
                barra.getPosX() + barra.getAncho() / 2 - 5,
                barra.getPosY() + barra.getAlto() + 11,
                10, 2.5f, 3.7f, true,
                "pelota", "pelota");
    }

    private void actualizarPosicionBarra() {
        //TODO: barra.update();

        // Mantener la barra dentro de la pantalla
        if (barra.getPosX() < 0) {
            barra.setPosicion(0, barra.getPosY());
        }
        if (barra.getPosX() + barra.getAncho() > Gdx.graphics.getWidth()) {
            barra.setPosicion(Gdx.graphics.getWidth() - barra.getAncho(), barra.getPosY());
        }
    }

    private void actualizarItems() {
        for (Item item : items) {
            if (item.estaActivo()) {
                item.caer();
                if (item.getPosY() < 0) {
                    items.remove(item);
                }
            } else {
                items.remove(item);
            }
        }

        // Actualiza los items usando iterador que funciona mejor con concurrencia
//        Iterator<Item> iterador = items.iterator();
//        while (iterador.hasNext()) {
//            Item item = iterador.next();
//            if (item.estaActivo()) {
//                item.caer();
//                if (item.getPosY() < 0) {
//                    iterador.remove();
//                }
//            } else {
//                iterador.remove();
//            }
//        }
    }

    private void procesarColisiones() {
        // Procesa las distintas posibles colisiones entre entidades usando el gestor para esto mismo
        GestorColisiones.getInstance().checkBallBlockCollisions(pelota, bloques);
        GestorColisiones.getInstance().checkBallPaddleCollision(pelota, barra);
        GestorColisiones.getInstance().checkItemPaddleCollision(items, barra, pelota);
    }

    private void actualizarBloques() {
        for (Bloque bloque : bloques) {
            if (bloque.isDestroyed()) {
                Item item = ((BloqueDestructible) bloque).getItem();
                if (item != null)
                    items.add(item);
                puntaje++;
                bloques.remove(bloque);
            }
        }
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
        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            estado = Estado.PAUSADO;
        }
    }

    private boolean soloQuedanBloquesIndestructibles() {
        for (Bloque bloque : bloques) {
            if (bloque instanceof BloqueDestructible) {
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
     * Obtiene la barra del juego.
     * @return Barra del juego.
     */
    public Paddle getBarra() {
        return barra;
    }

    /**
     * Obtiene la pelota del juego.
     * @return Pelota del juego.
     */
    public PingBall getPelota() {
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
     * Obtiene los bloques del juego en una lista inmutable.
     * @return Lista de bloques.
     */
    public List<Bloque> getBloques() {
        return Collections.unmodifiableList(bloques);
    }

    /**
     * Obtiene los items del juego en una lista inmutable.
     * @return Lista de items.
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }
}
