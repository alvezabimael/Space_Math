package com.spw.Pantalla_Menu;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.spw.Display.Display;
import com.spw.Estado.MaquinaEstado;
import com.spw.Estado.SuperMaquinaEstado;

public class PantallaMenu extends SuperMaquinaEstado implements KeyListener {

	private Font tittleFont = new Font("Arial", Font.PLAIN, 64);
	private Font startFont = new Font("Arial", Font.PLAIN, 32);
	private Font nameFont = new Font("Arial", Font.PLAIN, 16);
	private String tittle = "Space Math";
	private String start = "Presione Enter";
	private String abimael = "Abimael Madrigal";
	private String sergio = "Sergio A. Martinez";
	
	public PantallaMenu(MaquinaEstado maquinaEstado) {
		super(maquinaEstado);
	}

	@Override
	public void update(double delta) {

	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(tittleFont);
		int tittleWidth = g.getFontMetrics().stringWidth(tittle);
		g.setColor(Color.yellow);
		g.drawString(tittle, ((Display.WIDTH/2)-(tittleWidth/2))-2, (Display.HEIGHT/2)-123);
		g.setColor(Color.green);
		g.drawString(tittle, (Display.WIDTH/2)-(tittleWidth/2), (Display.HEIGHT/2)-125);
		
		g.setFont(startFont);
		g.setColor(Color.white);
		int startWidth = g.getFontMetrics().stringWidth(start);
		g.drawString(start, (Display.WIDTH/2)-(startWidth/2), (Display.HEIGHT/2)+75);
		g.setFont(nameFont);
		g.drawString(abimael,(Display.WIDTH/2)-(g.getFontMetrics().stringWidth(abimael)/2), (Display.HEIGHT/2)+155);
		g.drawString(sergio,(Display.WIDTH/2)-(g.getFontMetrics().stringWidth(sergio)/2), (Display.HEIGHT/2)+185);
	}

	@Override
	public void init(Canvas canvas) {
		canvas.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			getMaquinaEstado().setEstado((byte) 1);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

