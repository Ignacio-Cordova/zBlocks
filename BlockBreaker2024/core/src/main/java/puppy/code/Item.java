package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Item extends GameObject {
    private final ItemStrategy strategy;
    private float velocidadCaida = 1f;
    private float tiempoActivo;
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

    public void caer() {
        posY -= velocidadCaida;
        if (posY <= 0) {
            setActivo(false);
        }
    }

    public void activar(Paddle pad, PingBall ball) {
        strategy.aplicarEfecto(pad, ball);
        temporizador(pad, ball);
    }

    public void temporizador(Paddle pad, PingBall ball) {
        tiempoActivo += Gdx.graphics.getDeltaTime();
        if (tiempoActivo >= strategy.getDuracion()) {
            strategy.revertirEfecto(pad, ball);
        }
    }

    public boolean estaActivo() {
        return activo;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(textura, posX, posY, ancho, alto);
    }

    @Override
    public void render(SpriteBatch batch) {
        // TODO Auto-generated method stub
    }
}
