package puppy.code;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Bloque extends GameObject {
    protected int durabilidad;

    public final void actualizarBloque() {
        reducirDurabilidad();
        actualizarTextura();
    }

    abstract void reducirDurabilidad();
    abstract void actualizarTextura();

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(textura, posX, posY, ancho, alto);
    }

    @Override
    public void render(SpriteBatch batch) {
    }

    public abstract boolean isDestroyed();
}
