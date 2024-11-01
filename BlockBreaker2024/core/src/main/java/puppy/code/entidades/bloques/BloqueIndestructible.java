package puppy.code.entidades.bloques;

import puppy.code.gestores.GestorAudio;

/**
 * Clase que representa un bloque indestructible del juego.
 */
public class BloqueIndestructible extends Bloque {
    public BloqueIndestructible(float x, float y, float ancho, float alto, String textura, String sfx) {
        setPosicion(x, y);
        setDimensiones(ancho, alto);
        setTextura(textura);
        setSfx(sfx);
    }

    @Override
    protected void reducirDurabilidad() {
        GestorAudio.getInstance().reproducirSonido("indestructible");
    }

    @Override
    protected void actualizarTextura() {
        // No tiene efecto
    }

    @Override
    public boolean esDestructible() {
        return false;
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
