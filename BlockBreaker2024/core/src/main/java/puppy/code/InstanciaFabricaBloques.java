package puppy.code;

public class InstanciaFabricaBloques implements FabricaBloques {
    @Override
    public BloqueDestructible crearBloqueDestructible(float posX, float posY, float ancho, float alto, String texture,
                                                      int durabilidad, boolean tieneItem, String sfx) {
        BloqueDestructible BloqueDestructible;
        BloqueDestructible = new BloqueDestructible(posX, posY, ancho, alto, texture, durabilidad, tieneItem, sfx);
        return BloqueDestructible;
    }

    @Override
    public BloqueIndestructible crearBloqueIndestructible(float posX, float posY, float ancho, float alto, String textura) {
        return new BloqueIndestructible(posX, posY, ancho, alto, textura, "indestructible");
    }
}
