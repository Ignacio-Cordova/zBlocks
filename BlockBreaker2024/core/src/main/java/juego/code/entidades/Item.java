package juego.code.entidades;

import com.badlogic.gdx.Gdx;
import juego.code.estrategiasItem.ItemStrategy;

/** Clase que representa un item del juego. */
public class Item extends GameObject {
    private final ItemStrategy strategy;
    private float tiempoActivo;
    private boolean efectoActivo = false;
    private boolean activo = false;

    public Item(ItemStrategy strategy, String textura) {
        this.strategy = strategy;
        setTextura(textura);
        this.tiempoActivo = 0;
    }

    /**
     * Hace que el item aparezca en la posición indicada.
     * @param x Posición en el eje x.
     * @param y Posición en el eje y.
     */
    public void aparecer(float x, float y) {
        setPosicion(x, y);
        setDimensiones(50, 22);
        setActivo(true);
    }

    /**
     * Actualiza el estado del item.
     * @param activo Si el item está activo.
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Actualiza la posición del item.
     */
    @Override
    public void update() {
        float velocidad = 1f;
        posY -= velocidad;
        if (posY <= 0) {
            setActivo(false);
        }
    }

    /**
     * Aplica el efecto del item a la barra o la bola.
     * @param pad Barra.
     * @param ball Bola.
     */
    public void activar(Paddle pad, PingBall ball) {
        strategy.aplicarEfecto(pad, ball);
        efectoActivo = true;
        tiempoActivo = 0;
    }

    /**
     * Desactiva el efecto del item.
     * @param pad Barra.
     * @param ball Bola.
     */
    public void desactivar(Paddle pad, PingBall ball) {
        if (efectoActivo) {
            strategy.revertirEfecto(pad, ball);
            efectoActivo = false;
        }
    }

    /**
     * Actualiza el temporizador del efecto.
     * @param pad Barra.
     * @param ball Bola.
     */
    public void actualizarTemporizador(Paddle pad, PingBall ball) {
        if (efectoActivo) {
            tiempoActivo += Gdx.graphics.getDeltaTime();
            if (tiempoActivo >= strategy.getDuracion()) {
                desactivar(pad, ball);
            }
        }
    }

    /**
     * Verifica si el efecto del item está activo.
     * @return Si el efecto del item está activo.
     */
    public boolean isEfectoActivo() {
        return efectoActivo;
    }

    /**
     * Verifica si el item está activo.
     * @return Si el item está activo.
     */
    public boolean estaActivo() {
        return activo;
    }

    public float getTiempoActivo() {
        return tiempoActivo;
    }

    public void setTiempoActivo(float tiempoActivo) {
        this.tiempoActivo = tiempoActivo;
    }

    public void setEfectoActivo(boolean efectoActivo) {
        this.efectoActivo = efectoActivo;
    }
}
