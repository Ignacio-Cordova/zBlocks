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
        float x2 = posX; //= Gdx.input.getX();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x2 = posX - 15;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x2 = posX + 15;
        // y = Gdx.graphics.getHeight() - Gdx.input.getY();
        if (x2 > 0 && x2 + ancho < Gdx.graphics.getWidth()) {
            previaPosX = posX;
            posX = x2;
        }
        batch.draw(textura, posX, posY, ancho, alto);
    }

    @Override
    public void render(SpriteBatch batch) {
        // TODO Auto-generated method stub
    }
}
