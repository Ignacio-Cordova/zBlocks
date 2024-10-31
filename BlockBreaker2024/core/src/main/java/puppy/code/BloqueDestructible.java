package puppy.code;

/**
 * Clase que representa un bloque destructible del juego.
 */
public class BloqueDestructible extends Bloque {
    private Item item;
    private int durabilidad;

    public BloqueDestructible(float x, float y, float ancho, float alto, int durabilidad, boolean tieneItem) {
        setPosicion(x, y);
        setDimensiones(ancho, alto);
        this.durabilidad = durabilidad;
        String key = "bloque-" + durabilidad;
        if (tieneItem) {
            item = generarItem();
            key += "-item";
        }
        setTextura(key);
        setSfx(key);
    }

    @Override
    protected void reducirDurabilidad() {
        if (durabilidad > 0) {
            durabilidad--;
            GestorAudio.getInstance().reproducirSonido(sfx);
        }
    }

    @Override
    protected void actualizarTextura() {
        if (durabilidad > 0) {
            String key = "bloque-" + durabilidad;
            if (item != null)
                key += "-item";
            setTextura(key);
            setSfx(key);
        }
    }

    @Override
    public boolean esDestructible() {
        return true;
    }

    private Item generarItem() {
        int random = (int) (Math.random() * 4);
        switch (random) {
            case 0: return new Item(new PelotaLentaStrategy(), "lento");
            case 1: return new Item(new PelotaRapidaStrategy(), "veloz");
            case 2: return new Item(new BarraAnchaStrategy(), "amplio");
            case 3: return new Item(new BarraDelgadaStrategy(), "angosto");
            default: return null;
        }
    }

    @Override
    public boolean isDestroyed() {
        if (durabilidad > 0)
            return false;
        soltarItem();
        return true;
    }

    public void soltarItem() {
        if (item != null)
            item.aparecer(posX + getAncho() / 2, posY + getAlto() / 2);
    }

    public Item getItem() {
        return item;
    }
}
