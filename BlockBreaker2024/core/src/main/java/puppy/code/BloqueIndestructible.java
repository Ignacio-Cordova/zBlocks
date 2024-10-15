package puppy.code;

public class BloqueIndestructible extends Bloque {
    public BloqueIndestructible(float x, float y, float ancho, float alto, String textura, String sfx) {
        setPosicion(x, y);
        setDimensiones(ancho, alto);
        setTextura(textura);
        setSfx(sfx);
    }

    @Override
    public void reducirDurabilidad() {
        GestorAudio.getInstance().reproducirSonido("indesctructible");
    }

    @Override
    public void actualizarTextura() {
        // No tiene efecto
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
