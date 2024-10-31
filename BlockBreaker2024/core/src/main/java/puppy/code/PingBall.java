package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PingBall extends GameObject {
	private float xSpeed;
	private float ySpeed;
	private boolean estaQuieto;
	private static final float VELOCIDAD_INICIAL = 250f;

	public PingBall(float x, float y, float size, boolean iniciaQuieto, String nombreTextura, String sfx) {
		setPosicion(x, y);
		setDimensiones(size, size);
		setTextura(nombreTextura);
		this.xSpeed = VELOCIDAD_INICIAL;
		this.ySpeed = VELOCIDAD_INICIAL;
		estaQuieto = iniciaQuieto;
	}

	public boolean estaQuieto() {
		return estaQuieto;
	}

	public void setEstaQuieto(boolean bb) {
		estaQuieto=bb;
	}

	public void setxSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}

	public float getxSpeed() {
		return xSpeed;
	}

	public void setySpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}

	public float getySpeed() {
		return ySpeed;
	}

	public void multiplicarVelocidad(float factor) {
		xSpeed *= factor;
		ySpeed *= factor;
	}

	public void velocidadInicial() {
		xSpeed = VELOCIDAD_INICIAL;
		ySpeed = VELOCIDAD_INICIAL;
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(textura, posX - ancho, posY - alto, ancho * 2, alto * 2);
	}

	public void update() {
		if (estaQuieto) return;

		float delta = Gdx.graphics.getDeltaTime();

		posX += xSpeed * delta;
		posY += ySpeed * delta;
		if (posX - ancho < 0 || posX + ancho > Gdx.graphics.getWidth()) {
			xSpeed = -xSpeed;
			if (posX - ancho < 0) {
				posX = ancho;
			} else {
				posX = Gdx.graphics.getWidth() - ancho;
			}
		}
		if (posY + alto > Gdx.graphics.getHeight()) {
			ySpeed = -ySpeed;
			posY = Gdx.graphics.getHeight() - alto;
		}
	}
}
