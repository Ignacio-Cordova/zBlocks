package puppy.code;

public interface FabricaBloques {
    BloqueDestructible crearBloqueDestructible(float posX, float posY, float ancho, float alto, String texture,
                                               String sfx, int durabilidad, boolean tieneItem);
    BloqueIndestructible crearBloqueIndestructible(float posX, float posY, float ancho, float alto, String textura, String sfx);
}
