package puppy.code;

public class BarraAnchaStrategy implements ItemStrategy {
    private final float multiplicador = 1.5f;
    private final int duracion = 6;

    @Override
    public void aplicarEfecto(Paddle pad, PingBall ball) {
        pad.setDimensiones(pad.getAncho() * multiplicador, pad.getAlto());
    }

    @Override
    public void revertirEfecto(Paddle pad, PingBall ball) {
        pad.setDimensiones(pad.getAncho() * 1 / multiplicador, pad.getAlto());
    }

    @Override
    public int getDuracion() {
        return duracion;
    }
}
