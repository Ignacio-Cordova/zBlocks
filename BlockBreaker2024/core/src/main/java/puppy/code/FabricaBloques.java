package puppy.code;

public interface FabricaBloques {
    BloqueDestructible crearBloqueDestructible(float posX, float posY, float ancho, float alto, String texture,
                                               int durabilidad, boolean tieneItem, String sfx);
    BloqueIndestructible crearBloqueIndestructible(float posX, float posY, float ancho, float alto, String textura);
}
