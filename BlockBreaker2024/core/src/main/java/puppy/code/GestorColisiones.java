package puppy.code;

import java.util.List;

/*** Clase que se encarga de gestionar las colisiones en el juego. ***/
public class GestorColisiones {
    private static GestorColisiones instance; // Singleton

    private GestorColisiones() {}

    public static GestorColisiones getInstance() {
        if (instance == null) {
            instance = new GestorColisiones();
        }
        return instance;
    }

    /***
     * Comprueba las colisiones entre la bola y los bloques.
     * @param ball Bola.
     * @param bloques Bloques.
     */
    public void checkBallBlockCollisions(PingBall ball, List<Bloque> bloques) {
        for (Bloque bloque : bloques) {
            if (collidesWith(ball, bloque)){
                reboteEstatico(ball, bloque);
                bloque.actualizarBloque();
                return;
            }
        }
    }

    /***
     * Comprueba la colisión entre la bola y la barra.
     * @param ball Bola.
     * @param paddle Barra.
     */
    public void checkBallPaddleCollision(PingBall ball, Paddle paddle) {
        if (collidesWith(ball, paddle)) {
            if (paddle.estaQuieto())
                reboteEstatico(ball, paddle);
            else
                reboteDinamico(ball, paddle);
        }
    }

    /***
     * Comprueba las colisiones entre los items y la barra.
     * @param items Items.
     * @param paddle Barra.
     * @param ball Bola.
     */
    public void checkItemPaddleCollision(List<Item> items, Paddle paddle, PingBall ball) {
        for (Item item : items) {
            if (item.estaActivo() && collidesWith(paddle, item)) {
                item.activar(paddle, ball);
                item.setActivo(false);
            }
        }
    }

    private boolean collidesWith(GameObject go1, GameObject go2) {
        boolean intersectaX = go1.getPosX() - go1.getAncho() <= go2.getPosX() + go2.getAncho()
                              && go1.getPosX() + go1.getAncho() >= go2.getPosX();
        boolean intersectaY = go1.getPosY() - go1.getAlto() < go2.getPosY() + go2.getAlto()
                              && go1.getPosY() + go1.getAlto() > go2.getPosY();
        return intersectaX && intersectaY;
    }

    private void reboteEstatico(PingBall ball, GameObject go) {
        if (ball.getPosY() <= go.getPosY() || ball.getPosY() >= go.getPosY() + go.getAlto())
            ball.setySpeed(-ball.getySpeed());
        else if (ball.getPosX() <= go.getPosX() || ball.getPosX() >= go.getPosX() + go.getAncho())
            ball.setxSpeed(-ball.getxSpeed());
    }

    private void reboteDinamico(PingBall ball, Paddle paddle) {
        if (paddle.isMovingLeft()) {
            ball.setxSpeed(ball.getxSpeed() - 1);
        } else if (paddle.isMovingRight()) {
            ball.setxSpeed(ball.getxSpeed() + 1);
        }

        ball.setySpeed(-ball.getySpeed());
    }
}
