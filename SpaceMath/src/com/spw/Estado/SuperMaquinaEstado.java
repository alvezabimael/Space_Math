package com.spw.Estado;

import java.awt.Canvas;
import java.awt.Graphics2D;

public abstract class SuperMaquinaEstado {

private MaquinaEstado maquinaEstado;
	
	public SuperMaquinaEstado(MaquinaEstado maquinaEstado) {
		this.maquinaEstado = maquinaEstado;
	}
	
	public abstract void update(double delta);
	public abstract void draw(Graphics2D g);
	public abstract void init(Canvas canvas);
	
	public MaquinaEstado getMaquinaEstado() {
		return maquinaEstado;
	}
}
