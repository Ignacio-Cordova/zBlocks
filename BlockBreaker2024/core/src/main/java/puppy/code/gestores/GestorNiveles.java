package puppy.code.gestores;

import puppy.code.niveles.Nivel1;
import puppy.code.niveles.Nivel2;
import puppy.code.niveles.Nivel3;
import puppy.code.niveles.PlantillaNivel;
import puppy.code.entidades.bloques.Bloque;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Clase que se encarga de gestionar los niveles del juego. */
public class GestorNiveles {
    private static GestorNiveles instance; // Singleton

    private final List<PlantillaNivel> plantillasNiveles; // Plantillas de niveles
    private final Random random;

    private GestorNiveles() {
        // Instanciamos las variables
        plantillasNiveles = new ArrayList<>();
        random = new Random();

        // Agregamos los niveles
        plantillasNiveles.add(new Nivel1());
        plantillasNiveles.add(new Nivel2());
        plantillasNiveles.add(new Nivel3());
    }

    /***
     * Devuelve la instancia única de la clase.
     * @return Instancia única de la clase.
     */
    public static GestorNiveles getInstance() {
        if (instance == null) {
            instance = new GestorNiveles();
        }

        return instance;
    }

    /***
     * Crea un nivel específico.
     * @param numeroNivel Número del nivel a crear.
     * @return Lista de bloques del nivel.
     */
    public List<Bloque> crearNivel(int numeroNivel) {
        int indice = (numeroNivel - 1) % 3;
        return plantillasNiveles.get(indice).crearBloques();
    }

    /***
     * Crea un nivel aleatorio.
     * @return Lista de bloques del nivel.
     */
    public List<Bloque> crearNivel() {
        int indice = random.nextInt(plantillasNiveles.size());
        return plantillasNiveles.get(indice).crearBloques();
    }
}
