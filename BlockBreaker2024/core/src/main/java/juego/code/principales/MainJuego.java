package juego.code.principales;

import com.badlogic.gdx.ApplicationAdapter;
import juego.code.gestores.GestorAudio;
import juego.code.gestores.GestorTexturas;

/**
 * Clase principal del juego.
 */
public class MainJuego extends ApplicationAdapter {
    private RenderizadorJuego renderizador;
    private EstadoJuego estadoJuego;

    /**
     * Método que se ejecuta al iniciar el juego.
     */
    @Override
    public void create() {
        cargarAssets();
        renderizador = new RenderizadorJuego();
        estadoJuego = new EstadoJuego();
    }

    /**
     * Método que carga los assets del juego.
     */
    private void cargarAssets() {
        GestorTexturas gestorTexturas = GestorTexturas.getInstance();

        gestorTexturas.cargarTextura("pelota", "texturas/pelota.png");
        gestorTexturas.cargarTextura("barra", "texturas/barra.png");
        gestorTexturas.cargarTextura("barra-larga", "texturas/barra-larga.png");
        gestorTexturas.cargarTextura("bloque-1", "texturas/bloque-1.png");
        gestorTexturas.cargarTextura("bloque-1-item", "texturas/bloque-1-item.png");
        gestorTexturas.cargarTextura("bloque-2", "texturas/bloque-2.png");
        gestorTexturas.cargarTextura("bloque-2-item", "texturas/bloque-2-item.png");
        gestorTexturas.cargarTextura("bloque-3", "texturas/bloque-3.png");
        gestorTexturas.cargarTextura("bloque-3-item", "texturas/bloque-3-item.png");
        gestorTexturas.cargarTextura("obsidiana", "texturas/obsidiana.png");

        gestorTexturas.cargarTextura("angosto", "texturas/item-angosto.png");
        gestorTexturas.cargarTextura("amplio", "texturas/item-amplio.png");
        gestorTexturas.cargarTextura("lento", "texturas/item-lento.png");
        gestorTexturas.cargarTextura("veloz", "texturas/item-veloz.png");
        gestorTexturas.cargarTextura("background", "texturas/background.png");


        GestorAudio gestorAudio = GestorAudio.getInstance();

        gestorAudio.cargarCancion("bg", "musica/bg-music.mp3");

        gestorAudio.cargarSonido("level-up", "sfx/level-up.mp3");
        gestorAudio.cargarSonido("game-over", "sfx/game-over.mp3");
        gestorAudio.cargarSonido("indestructible", "sfx/indestructible.mp3");
        gestorAudio.cargarSonido("bloque-1", "sfx/bloque-1.mp3");
        gestorAudio.cargarSonido("bloque-1", "sfx/bloque-1.mp3");
        gestorAudio.cargarSonido("bloque-2", "sfx/bloque-2.mp3");
        gestorAudio.cargarSonido("bloque-2-item", "sfx/bloque-2-item.mp3");
        gestorAudio.cargarSonido("bloque-3", "sfx/bloque-3.mp3");
        gestorAudio.cargarSonido("bloque-3-item", "sfx/bloque-3-item.mp3");
        gestorAudio.cargarSonido("vida-perdida", "sfx/vida-perdida.mp3");
    }

    /**
     * Renderiza cada frame del juego.
     */
    @Override
    public void render() {
        estadoJuego.update();
        renderizador.render(estadoJuego);
    }

    /**
     * Método que se ejecuta al cerrar el juego.
     */
    @Override
    public void dispose() {
        GestorTexturas.getInstance().dispose();
        GestorAudio.getInstance().dispose();
        renderizador.dispose();
    }
}
