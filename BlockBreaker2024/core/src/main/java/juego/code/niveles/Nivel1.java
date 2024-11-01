package juego.code.niveles;

import com.badlogic.gdx.Gdx;
import juego.code.entidades.bloques.Bloque;

import java.util.ArrayList;
import java.util.List;

/** Clase que representa el primer tipo de nivel. */
public class Nivel1 extends NivelBase {
    /***
     * Crea los bloques del nivel.
     * @return Lista de bloques del nivel.
     */
    @Override
    public List<Bloque> crearBloques() {
        List<Bloque> bloques = new ArrayList<>();

        for (int fila = 0; fila < 3; fila++) {
            int y = calcularPosicionY(fila);

            for (int x = 5; x < Gdx.graphics.getWidth(); x += ANCHO_BLOQUE + 10) {
                if (fila == 2) {
                    bloques.add(fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE,
                                1, false));
                } else if ((x == 430 || x == 90) && fila == 1) {
                    bloques.add(fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE,
                                2, true));
                } else if (x == 175 && fila == 0) {
                    bloques.add(fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE,
                                3, true));
                } else {
                    bloques.add(fabricaBloques.crearBloqueDestructible(x, y, ANCHO_BLOQUE, ALTO_BLOQUE,
                                2, false));
                }
            }
        }
        return bloques;
    }
}
