package juego.code.entidades.bloques;

import juego.code.entidades.GameObject;

/** Clase abstracta que representa un bloque del juego.***/
public abstract class Bloque extends GameObject {
    /**
     * Actualiza el estado del bloque tras ser golpeado por la pelota.
     */
    public final void actualizarBloque() {
        reducirDurabilidad();
        actualizarTextura();
    }

    protected abstract void reducirDurabilidad();
    protected abstract void actualizarTextura();
    public abstract boolean esDestructible();
    public abstract boolean isDestroyed();
}
