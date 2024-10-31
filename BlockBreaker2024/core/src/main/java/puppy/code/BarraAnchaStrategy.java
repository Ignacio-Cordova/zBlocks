package puppy.code;

public class BarraAnchaStrategy implements ItemStrategy {
    private final float multiplicador = 1.5f;
    private final int duracion = 6;

    @Override
    public void aplicarEfecto(Paddle pad, PingBall ball) {
        if(pad.getAncho() == 150){
            return;
        }
        pad.setDimensiones(pad.getAncho() * multiplicador, pad.getAlto());
        pad.setTextura("barra-larga");
    }

    @Override
    public void revertirEfecto(Paddle pad, PingBall ball) {
        pad.setDimensiones(pad.getAncho() * 1 / multiplicador, pad.getAlto());
        pad.setTextura("barra");
    }

    @Override
    public int getDuracion() {
        return duracion;
    }
}
