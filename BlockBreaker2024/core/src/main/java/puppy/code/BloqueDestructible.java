package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public class BloqueDestructible extends Bloque {
    private Item item;

    public BloqueDestructible(float x, float y, float ancho, float alto, String textura, int durabilidad, boolean tieneItem, String sfx) {
        setPosicion(x, y);
        setDimensiones(ancho, alto);
        setTextura(textura);
        setSfx(sfx);
        this.durabilidad = durabilidad;
        if (tieneItem)
            item = generarItem();
    }

    @Override
    public void reducirDurabilidad() {
        if (durabilidad > 0) {
            durabilidad--;
            GestorAudio.getInstance().reproducirSonido(sfx);
        }
    }

    @Override
    public void actualizarTextura() {
        if (durabilidad > 0)
            if (getTexture().toString().contains("piedra-poder")) {
                setTextura("tierra-poder");
                setSfx("tierra");
            }
            else if(getTexture().toString().contains("piedra")){
                setTextura("tierra");
                setSfx("tierra");
            }
            else if (getTexture().toString().contains("rubi-poder")) {
                setTextura("piedra-poder");
                setSfx("piedra");
            }
            else if (getTexture().toString().contains("rubi")) {
                setTextura("piedra");
                setSfx("piedra");
            }
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

    public Texture getTexture() {
        return textura;
    }
}
