package puppy.code;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;

/** Clase que representa el tercer tipo de nivel. */
public class Nivel3 extends NivelBase {
    /**
     * Crea los bloques del nivel.
     * @return Lista de bloques del nivel.
     */
    @Override
    public List<Bloque> crearBloques() {
        List<Bloque> bloques = new ArrayList<>();

        for (int fila = 0; fila < 7; fila++) {
            int y = calcularPosicionY(fila);

            for (int x = 5; x < Gdx.graphics.getWidth(); x += ANCHO_BLOQUE + 10) {
                if (fila == 0 || fila == 6) {
                    if (x == 345 || x == 430) {
                        bloques.add(fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE, 
                                    1, false));
                    }
                }
                else if (fila == 1 || fila == 5) {
                    if (x == 260 || x == 515) {
                        bloques.add(fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE, 
                                    1, false));
                    }
                    else if (x == 345 || x == 430) {
                        bloques.add(fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE,
                                 2, false));
                    }
                }
                else if (fila == 2 || fila == 4) {
                    if (x == 175 || x == 600) {
                        bloques.add(fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE,
                                    1, false));
                    }
                    else if (x == 260 || x == 515) {
                        bloques.add(fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE,
                                    2, false));
                    }
                    else if (x == 345 || x == 430) {
                        bloques.add(fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE,
                                    3, false));
                    }
                }
                else {
                    if (x == 90 || x == 685) {
                        bloques.add(fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE,
                                    1, false));
                    }
                    else if (x == 175 || x == 600) {
                        bloques.add(fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE,
                                    2, false));
                    }
                    else if (x == 260 || x == 515) {
                        bloques.add(fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE,
                                    3, false));
                    }
                    else if (x == 345 || x == 430) {
                        bloques.add(fabricaBloques.crearBloqueIndestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE,
                                "obsidiana"));
                    }
                }
            }
        }
        return bloques;
    }
}