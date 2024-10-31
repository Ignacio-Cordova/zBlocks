package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

/*** Clase que se encarga de gestionar la reproducción de audio en el juego. ***/
public class GestorAudio {
    private static GestorAudio instance; // Singleton

    private Music cancionActual; // Canción actual
    private final Map<String, Music> canciones; // Canciones cargadas
    private final Map<String, Sound> sonidos; // Sonidos cargados

    private GestorAudio() {
        canciones = new HashMap<>();
        sonidos = new HashMap<>();
    }

    /***
     * Devuelve la instancia única de la clase.
     * @return Instancia única de la clase.
     */
    public static GestorAudio getInstance() {
        if (instance == null) {
            instance = new GestorAudio();
        }
        return instance;
    }

    /***
     * Carga una canción.
     * @param key Identificador de la canción.
     * @param path Ruta del archivo de audio.
     */
    public void cargarCancion(String key, String path) {
        Music cancion = Gdx.audio.newMusic(Gdx.files.internal(path));
        canciones.put(key, cancion);
    }

    /***
     * Carga un sonido.
     * @param key Identificador del sonido.
     * @param path Ruta del archivo de audio.
     */
    public void cargarSonido(String key, String path) {
        Sound sonido = Gdx.audio.newSound(Gdx.files.internal(path));
        sonidos.put(key, sonido);
    }

    /***
     * Reproduce una canción.
     * @param key Identificador de la canción.
     * @param loop Indica si la canción debe reproducirse en loop.
     */
    public void reproducirCancion(String key, boolean loop) {
        // Si está sonando alguna canción, primero detenerla y liberar su recurso
        if (cancionActual != null) {
            cancionActual.stop();
            cancionActual.dispose();
        }

        // Ahora reproducimos la canción
        cancionActual = canciones.get(key);
        if (cancionActual != null) {
            cancionActual.setLooping(loop);
            cancionActual.play();
            cancionActual.setVolume(.08f);
        }
    }

    /***
     * Detiene la canción que se está reproduciendo.
     */
    public void detenerCancion() {
        if (cancionActual != null) {
            cancionActual.stop();
        }
    }

    /***
     * Reproduce un sonido.
     * @param key Identificador del sonido.
     */
    public void reproducirSonido(String key) {
        Sound sonido = sonidos.get(key);
        if (sonido != null) {
            sonido.play();
        }
    }

    /***
     * Libera los recursos de audio cargados.
     */
    public void dispose() {
        if (cancionActual != null) {
            cancionActual.dispose();
        }
        for (Music cancion: canciones.values()) {
            cancion.dispose();
        }
        for (Sound sonido: sonidos.values()) {
            sonido.dispose();
        }
        canciones.clear();
        sonidos.clear();
    }
}

