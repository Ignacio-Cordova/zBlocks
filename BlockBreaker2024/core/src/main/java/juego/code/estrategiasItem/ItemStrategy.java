package juego.code.estrategiasItem;

import juego.code.entidades.Paddle;
import juego.code.entidades.PingBall;

public interface ItemStrategy {
    void aplicarEfecto(Paddle pad, PingBall ball);
    void revertirEfecto(Paddle pad, PingBall ball);
    int getDuracion();
}
