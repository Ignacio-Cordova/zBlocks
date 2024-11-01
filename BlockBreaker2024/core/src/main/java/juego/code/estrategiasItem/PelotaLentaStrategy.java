package juego.code.estrategiasItem;

import juego.code.entidades.Paddle;
import juego.code.entidades.PingBall;

/** Item que hace que la pelota disminuya su velocidad. */
public class PelotaLentaStrategy implements ItemStrategy {
    private final float multiplicador = 0.7f;
    private final int duracion = 8;

    @Override
    public void aplicarEfecto(Paddle pad, PingBall pelota) {
        pelota.multiplicarVelocidad(multiplicador);
    }

    @Override
    public void revertirEfecto(Paddle pad, PingBall pelota) {
        pelota.multiplicarVelocidad(1 / multiplicador);
    }

    @Override
    public int getDuracion() {
        return duracion;
    }

    public float getMultiplicador() {
        return multiplicador;
    }
}
