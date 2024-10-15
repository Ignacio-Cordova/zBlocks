package puppy.code;

public interface ItemStrategy {
    void aplicarEfecto(Paddle pad, PingBall ball);
    void revertirEfecto(Paddle pad, PingBall ball);
    int getDuracion();
}
