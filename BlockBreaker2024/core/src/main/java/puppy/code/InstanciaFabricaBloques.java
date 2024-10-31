package puppy.code;

public class InstanciaFabricaBloques implements FabricaBloques {
    @Override
    public BloqueDestructible crearBloqueDestructible(float posX, float posY, float ancho, float alto, int durabilidad, boolean tieneItem) {
        return new BloqueDestructible(posX, posY, ancho, alto, durabilidad, tieneItem);
    }

    @Override
    public BloqueIndestructible crearBloqueIndestructible(float posX, float posY, float ancho, float alto, String textura) {
        return new BloqueIndestructible(posX, posY, ancho, alto, textura, "indestructible");
    }
}
