package puppy.code;

/** Clase abstracta que representa un bloque del juego.***/
public abstract class Bloque extends GameObject {
    /**
     * Actualiza el estado del bloque tras ser golpeado por la pelota.
     */
    public final void actualizarBloque() {
        reducirDurabilidad();
        actualizarTextura();
    }

    abstract void reducirDurabilidad();
    abstract void actualizarTextura();
    abstract boolean esDestructible();
    public abstract boolean isDestroyed();
}
