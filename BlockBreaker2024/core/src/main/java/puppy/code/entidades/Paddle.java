package puppy.code.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/** Clase que representa la barra del juego.***/
public class Paddle extends GameObject {
    private float previaPosX;

    public Paddle(int x, int y, int ancho, int alto, String nombreTextura) {
        setPosicion(x, y);
        setDimensiones(ancho, alto);
        setTextura(nombreTextura);
    }

    /**
     * Indica si la barra está quieta.
     * @return Verdadero si la barra está quieta, falso en caso contrario.
     */
    public boolean estaQuieto() {
        return previaPosX == posX;
    }

    /**
     * Indica si la barra se está moviendo hacia la derecha.
     * @return Verdadero si la barra se está moviendo hacia la derecha, falso en caso contrario.
     */
    public boolean isMovingRight() {
        return posX > previaPosX;
    }

    /**
     * Indica si la barra se está moviendo hacia la izquierda.
     * @return Verdadero si la barra se está moviendo hacia la izquierda, falso en caso contrario.
     */
    public boolean isMovingLeft() {
        return posX < previaPosX;
    }

    /***
     * Actualiza la posición de la barra.
     */
    @Override
    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        float velocidad = 1000f;

        previaPosX = posX;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            posX -= velocidad * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            posX += velocidad * delta;
        }

        // Mantener la barra dentro de la pantalla
        if (posX < 0) {
            setPosicion(0, posY);
        }
        if (posX + ancho > Gdx.graphics.getWidth()) {
            setPosicion(Gdx.graphics.getWidth() - ancho, posY);
        }
    }

    public float getPreviaPosX() {
        return previaPosX;
    }

    public void setPreviaPosX(float previaPosX) {
        this.previaPosX = previaPosX;
    }
}
