package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PingBall extends GameObject {
	private float xSpeed;
	private float ySpeed;
	private boolean estaQuieto;

	public PingBall(float x, float y, float size, float xSpeed, float ySpeed, boolean iniciaQuieto, String nombreTextura, String sfx) {
		setPosicion(x, y);
		setDimensiones(size, size);
		setTextura(nombreTextura);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
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

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(textura, posX - ancho, posY - alto, ancho * 2, alto * 2);
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
	}

	public void update() {
		if (estaQuieto) return;
		posX += xSpeed;
		posY += ySpeed;
		if (posX - ancho < 0 || posX + ancho > Gdx.graphics.getWidth()) {
			xSpeed = -xSpeed;
		}
		if (posY + alto > Gdx.graphics.getHeight()) {
			ySpeed = -ySpeed;
		}
	}
}
