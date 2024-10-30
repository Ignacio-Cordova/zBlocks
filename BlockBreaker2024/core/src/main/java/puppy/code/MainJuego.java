package puppy.code;

import com.badlogic.gdx.ApplicationAdapter;

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
    public void cargarAssets() {
        GestorTexturas gestorTexturas = GestorTexturas.getInstance();

        gestorTexturas.cargarTextura("pelota", "texturas/pelota.png");
        gestorTexturas.cargarTextura("barra", "texturas/barra.png");
        gestorTexturas.cargarTextura("barraLarga", "texturas/LargePaddle.png");
        gestorTexturas.cargarTextura("tierra", "texturas/tierra.png");
        gestorTexturas.cargarTextura("tierra-poder", "texturas/tierra-poder.png");
        gestorTexturas.cargarTextura("piedra", "texturas/piedra.png");
        gestorTexturas.cargarTextura("piedra-poder", "texturas/piedra-poder.png");
        gestorTexturas.cargarTextura("rubi", "texturas/rubi.png");
        gestorTexturas.cargarTextura("rubi-poder", "texturas/rubi-poder.png");
        gestorTexturas.cargarTextura("obsidiana", "texturas/obsidiana.png");

        gestorTexturas.cargarTextura("angosto", "texturas/angosto.png");
        gestorTexturas.cargarTextura("amplio", "texturas/amplio.png");
        gestorTexturas.cargarTextura("lento", "texturas/lento.png");
        gestorTexturas.cargarTextura("veloz", "texturas/veloz.png");
        gestorTexturas.cargarTextura("background", "texturas/background.png");


        GestorAudio gestorAudio = GestorAudio.getInstance();

        gestorAudio.cargarCancion("bg", "musica/bg-music.mp3");

        gestorAudio.cargarSonido("level-up", "sfx/level-up.mp3");
        gestorAudio.cargarSonido("game-over", "sfx/game-over.mp3");
        gestorAudio.cargarSonido("indestructible", "sfx/indestructible.mp3");
        gestorAudio.cargarSonido("tierra", "sfx/tierra.mp3");
        gestorAudio.cargarSonido("tierra-poder", "sfx/tierra-poder.mp3");
        gestorAudio.cargarSonido("piedra", "sfx/piedra.mp3");
        gestorAudio.cargarSonido("piedra-poder", "sfx/piedra-poder.mp3");
        gestorAudio.cargarSonido("rubi", "sfx/rubi.mp3");
        gestorAudio.cargarSonido("rubi-poder", "sfx/rubi-poder.mp3");
        gestorAudio.cargarSonido("pelota", "sfx/pelota.mp3");
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
        renderizador.dispose();
    }
}
