package puppy.code;

public class PelotaRapidaStrategy implements ItemStrategy {
    private final float multiplicador = 1.3f;
    private final int duracion = 8;

    @Override
    public void aplicarEfecto(Paddle pad, PingBall pelota) {
        pelota.multiplicarVelocidad(multiplicador);
    }

    @Override
    public void revertirEfecto(Paddle pad, PingBall pelota) {
        pelota.velocidadInicial();
    }

    @Override
    public int getDuracion() {
        return duracion;
    }
}
