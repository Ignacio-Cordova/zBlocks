package puppy.code;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Bloque extends GameObject {
    public final void actualizarBloque() {
        reducirDurabilidad();
        actualizarTextura();
    }

    abstract void reducirDurabilidad();
    abstract void actualizarTextura();
    abstract boolean esDestructible();

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(textura, posX, posY, ancho, alto);
    }

    public abstract boolean isDestroyed();
}
