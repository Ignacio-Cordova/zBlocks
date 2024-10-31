package puppy.code;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/** Clase base para los niveles del juego. */
public abstract class NivelBase implements PlantillaNivel {
    protected static final int ANCHO_BLOQUE = 75;
    protected static final int ALTO_BLOQUE = 25;
    protected final FabricaBloques fabricaBloques;
    private final Random random;

    /***
     * Constructor de la clase.
     */
    protected NivelBase() {
        fabricaBloques = new InstanciaFabricaBloques();
        random = new Random();
    }

    /***
     * Calcula la posición Y de un bloque en función de la fila.
     * @param fila Fila del bloque.
     * @return Posición Y del bloque.
     */
    protected int calcularPosicionY(int fila) {
        return Gdx.graphics.getHeight() - ((ALTO_BLOQUE + 10) * (fila + 1));
    }

    /***
     * Crea un bloque destructible aleatorio.
     * @param x Posición X del bloque.
     * @param y Posición Y del bloque.
     * @param tieneItem Indica si el bloque tiene un item.
     * @return Bloque creado.
     */
    protected Bloque crearBloqueAleatorio(int x, int y, boolean tieneItem) {
        int numero = random.nextInt(100);
        if (numero < 70) {
            return fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE, 1, tieneItem);
        } else if (numero < 90) {
            return fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE, 2, tieneItem);
        } else {
            return fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE, 3, tieneItem);
        }
    }
}
