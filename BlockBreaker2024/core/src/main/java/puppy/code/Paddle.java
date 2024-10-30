package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Paddle extends GameObject {
    private float previaPosX;

    public Paddle(int x, int y, int ancho, int alto, String nombreTextura) {
        setPosicion(x, y);
        setDimensiones(ancho, alto);
        setTextura(nombreTextura);
    }

    public boolean estaQuieto() {
        return previaPosX == posX;
    }

    public boolean isMovingRight() {
        return posX > previaPosX;
    }

    public boolean isMovingLeft() {
        return posX < previaPosX;
    }

    @Override
    public void draw(SpriteBatch batch){
        batch.draw(textura, posX, posY, ancho, alto);
    }

    @Override
    public void render(SpriteBatch batch) {
        // TODO Auto-generated method stub
    }

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
}
