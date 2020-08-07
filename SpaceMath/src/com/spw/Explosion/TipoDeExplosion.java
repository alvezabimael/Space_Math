package com.spw.Explosion;

import java.awt.Graphics2D;

public interface TipoDeExplosion {
	
	public void draw(Graphics2D g);
	public void update(double delta);
	
	public boolean destory();

}
