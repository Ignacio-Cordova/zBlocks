package puppy.code.estrategiasItem;

import puppy.code.entidades.Paddle;
import puppy.code.entidades.PingBall;

/** Item que hace que la pelota aumente su velocidad. */
public class PelotaRapidaStrategy implements ItemStrategy {
    private final float multiplicador = 1.3f;
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
