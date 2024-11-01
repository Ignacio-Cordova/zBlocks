package puppy.code.entidades.bloques;

public interface FabricaBloques {
    BloqueDestructible crearBloqueDestructible(float posX, float posY, float ancho, float alto, int durabilidad, boolean tieneItem);
    BloqueIndestructible crearBloqueIndestructible(float posX, float posY, float ancho, float alto, String textura);
}
