package puppy.code.niveles;

import com.badlogic.gdx.Gdx;
import puppy.code.entidades.bloques.Bloque;

import java.util.ArrayList;
import java.util.List;

/** Clase que representa el segundo tipo de nivel. */
public class Nivel2 extends NivelBase {
    /***
     * Crea los bloques del nivel.
     * @return Lista de bloques del nivel.
     */
    @Override
    public List<Bloque> crearBloques() {
        List<Bloque> bloques = new ArrayList<>();

        for (int fila = 0; fila < 5; fila++) {
            int y = calcularPosicionY(fila);

            for (int x = 5; x < Gdx.graphics.getWidth(); x += ANCHO_BLOQUE + 10) {
                if (fila == 0) {
                    if (x == 345 || x == 515 || x == 685 || x == 90 || x == 175 ||
                            x == 260 || x == 430 || x == 600 || x == 770) {
                        bloques.add(crearBloqueAleatorio(x, y, false));
                    } else {
                        bloques.add(crearBloqueAleatorio(x, y, true));
                    }
                } else if (fila == 1 || fila == 3) {
                    if (x == 5 || x == 770) {
                        bloques.add(crearBloqueAleatorio(x, y, false));
                    }
                } else if (fila == 2) {
                    if (x == 600 || x == 770) {
                        bloques.add(crearBloqueAleatorio(x, y, true));
                    } else if (x != 685) {
                        bloques.add(crearBloqueAleatorio(x, y, false));
                    }
                } else if (x != 90) {
                    bloques.add(crearBloqueAleatorio(x, y, false));
                }
            }
        }
        return bloques;
    }
}
