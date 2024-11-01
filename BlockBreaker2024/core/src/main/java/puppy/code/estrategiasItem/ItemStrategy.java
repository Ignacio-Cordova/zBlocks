package puppy.code.estrategiasItem;

import puppy.code.entidades.Paddle;
import puppy.code.entidades.PingBall;

public interface ItemStrategy {
    void aplicarEfecto(Paddle pad, PingBall ball);
    void revertirEfecto(Paddle pad, PingBall ball);
    int getDuracion();
}
