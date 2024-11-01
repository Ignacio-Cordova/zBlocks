package puppy.code.estrategiasItem;

import puppy.code.entidades.Paddle;
import puppy.code.entidades.PingBall;

/**
 * Item que hace que la barra del jugador se vuelva más delgada.
 */
public class BarraDelgadaStrategy implements ItemStrategy {
    private final float multiplicador = 0.5f;
    private final int duracion = 6;

    @Override
    public void aplicarEfecto(Paddle pad, PingBall pelota) {
        pad.setDimensiones(pad.getAncho() * multiplicador, pad.getAlto());
    }

    @Override
    public void revertirEfecto(Paddle pad, PingBall pelota) {
        pad.setDimensiones(pad.getAncho() * 1 / multiplicador, pad.getAlto());
    }

    @Override
    public int getDuracion() {
        return duracion;
    }

    public float getMultiplicador() {
        return multiplicador;
    }
}
