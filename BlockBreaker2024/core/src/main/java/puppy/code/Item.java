package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    public void aparecer(float x, float y) {
        setPosicion(x, y);
        setDimensiones(50, 22);
        setActivo(true);
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void update() {
        float velocidad = 1f;
        posY -= velocidad;
        if (posY <= 0) {
            setActivo(false);
        }
    }

    public void activar(Paddle pad, PingBall ball) {
        strategy.aplicarEfecto(pad, ball);
        efectoActivo = true;
        tiempoActivo = 0;
    }

    public void desactivar(Paddle pad, PingBall ball) {
        if (efectoActivo) {
            strategy.revertirEfecto(pad, ball);
            efectoActivo = false;
        }
    }

    public void actualizarTemporizador(Paddle pad, PingBall ball) {
        if (efectoActivo) {
            tiempoActivo += Gdx.graphics.getDeltaTime();
            if (tiempoActivo >= strategy.getDuracion()) {
                desactivar(pad, ball);
            }
        }
    }

    public boolean isEfectoActivo() {
        return efectoActivo;
    }

    public boolean estaActivo() {
        return activo;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(textura, posX, posY, ancho, alto);
    }
}
