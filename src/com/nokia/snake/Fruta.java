package com.nokia.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Fruta {

	private Random random;
	private Point fruta;

	public Fruta() {
		random = new Random();
		fruta = new Point();
	}

	public void nuevaFrutita() {
		fruta.x = random.nextInt(39);
		fruta.y = random.nextInt(28) + 1;
	}

	public void dibujoFrutita(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(fruta.x * 20, fruta.y * 20, 20, 20);
	}

	public Point getFrutita() {
		return fruta;
	}

}