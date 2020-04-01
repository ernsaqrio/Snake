package com.nokia.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class JuegoSnake extends JFrame implements KeyListener {

	private int windowWidth = 800;
	private int windowHeight = 600;
	private Snake snake;
	private Fruta fruta;
	private int Score;
	private long goal;
	private int tiempoDemora = 40;

	public static void main(String[] args) {
		new JuegoSnake();
	}

	public JuegoSnake() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(windowWidth, windowHeight);
		this.setResizable(false);
		this.setLocation(100, 100);
		this.setVisible(true);

		this.createBufferStrategy(2);
		this.addKeyListener(this);

		inicializoObjetos();

		while (true) {
			juego();
			sleep();
		}

	}

	private void inicializoObjetos() {
		snake = new Snake();
		snake.crecerColaSnake();
		fruta = new Fruta();
		fruta.nuevaFrutita();
		Score = 0;

	}

	private void juego() {

		snake.muevoSnake();
		chequearColision();
		dibujoPantalla();

	}

	private void dibujoPantalla() {

		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;

		try {
			g = bf.getDrawGraphics();

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, windowWidth, windowHeight);

			fruta.dibujoFrutita(g);
			snake.dibujoSnake(g);
			muestroPuntos(g);

		} finally {
			g.dispose();
		}

		bf.show();

		Toolkit.getDefaultToolkit().sync();
	}

	private void chequearColision() {
		if (snake.getLargo().get(0).equals(fruta.getFrutita())) {
			fruta.nuevaFrutita();
			snake.crecerColaSnake();
			Score += 10;
		}

		if (snake.getLargo().get(0).x < 0 || snake.getLargo().get(0).x > 39 || snake.getLargo().get(0).y < 1
				|| snake.getLargo().get(0).y > 29) {
			inicializoObjetos();
		}

		for (int n = 1; n < snake.getLargo().size(); n++) {
			if (snake.getLargo().get(0).equals(snake.getLargo().get(n)) && snake.getLargo().size() > 2) {
				inicializoObjetos();
			}
		}
	}

	private void muestroPuntos(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString("Score: " + Score, 20, 50);

	}

	private void sleep() {
		goal = (System.currentTimeMillis() + tiempoDemora);
		while (System.currentTimeMillis() < goal) {

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int tecla = e.getKeyCode();

		switch (tecla) {
		case KeyEvent.VK_UP:
			snake.direccion("ARR");
			break;
		case KeyEvent.VK_DOWN:
			snake.direccion("ABA");
			break;
		case KeyEvent.VK_LEFT:
			snake.direccion("IZQ");
			break;
		case KeyEvent.VK_RIGHT:
			snake.direccion("DER");
			break;
		case KeyEvent.VK_E:
			System.exit(0);

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}