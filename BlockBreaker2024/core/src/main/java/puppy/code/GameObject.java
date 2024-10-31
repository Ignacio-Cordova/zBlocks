package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObject {
    protected float posX;
    protected float posY;
    protected float ancho;
    protected float alto;
    protected Texture textura;
    protected String sfx;

    public void setPosicion(float x, float y) {
        posX = x;
        posY = y;
    }

    public void setDimensiones(float ancho, float alto) {
        this.ancho = ancho;
        this.alto = alto;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getAncho() {
        return ancho;
    }

    public float getAlto() {
        return alto;
    }

    public void setTextura(String nombreTextura) {
        GestorTexturas gestorTexturas = GestorTexturas.getInstance();
        textura = gestorTexturas.getTextura(nombreTextura);
    }

    public void setSfx(String nombreSfx) {
        sfx = nombreSfx;
    }

    public abstract void draw(SpriteBatch batch);
}
