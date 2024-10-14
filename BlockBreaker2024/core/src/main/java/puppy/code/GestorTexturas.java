package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

/*** Clase que se encarga de gestionar las texturas en el juego. ***/
public class GestorTexturas {
    private static GestorTexturas instance; // Singleton

    private final Map<String, Texture> texturas; // Texturas cargadas

    private GestorTexturas() {
        texturas = new HashMap<>();
    }

    /***
     * Devuelve la instancia única de la clase.
     * @return Instancia única de la clase.
     */
    public static GestorTexturas getInstance() {
        if (instance == null) {
            instance = new GestorTexturas();
        }
        return instance;
    }

    /***
     * Carga una textura.
     * @param key Identificador de la textura.
     * @param path Ruta del archivo de imagen.
     */
    public void cargarTextura(String key, String path) {
        Texture textura = new Texture(Gdx.files.internal(path));
        texturas.put(key, textura);
    }

    /***
     * Devuelve una textura.
     * @param key Identificador de la textura.
     * @return Textura.
     */
    public Texture getTextura(String key) {
        return texturas.get(key);
    }

    /***
     * Libera los recursos de las texturas cargadas.
     */
    public void dispose() {
        for (Texture textura: texturas.values()) {
            textura.dispose();
        }
        texturas.clear();
    }
}
