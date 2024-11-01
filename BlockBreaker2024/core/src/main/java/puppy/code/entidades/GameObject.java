package puppy.code.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import puppy.code.gestores.GestorTexturas;

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

    public void draw(SpriteBatch batch) { batch.draw(textura, posX, posY, ancho, alto); }
    public void update() {}

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }

    public String getSfx() {
        return sfx;
    }
}
