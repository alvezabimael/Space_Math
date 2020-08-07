package com.spw.Explosion;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ManejoExplosion {

	private static ArrayList<TipoDeExplosion> explosiones = new ArrayList<TipoDeExplosion>();
	
	public void draw (Graphics2D g) {
		for (int i = 0; i < explosiones.size(); i++) {
			explosiones.get(i).draw(g);
		}
	}
	
	public void update(double delta) {
		for (int i = 0; i < explosiones.size(); i++) {
			explosiones.get(i).update(delta);
			if(explosiones.get(i).destory()) {
				explosiones.remove(i);
			}
		}
	}
	
	public static void createPixelExplosion(double xPos, double yPos) {
		TipoDeExplosion et = new PixelExplosion(xPos, yPos);
		explosiones.add(et);
	}
}
