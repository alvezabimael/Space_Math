package com.spw.game_screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.spw.Display.Display;
import com.spw.Estado.MaquinaEstado;
import com.spw.Estado.SuperMaquinaEstado;
import com.spw.MathGenerator.multiplicacionRan;
import com.spw.Niveles.Nivel1;
import com.spw.Timer.TickTimer;

public class GameScreen extends SuperMaquinaEstado {
	
	private Jugador jugador;
	private Nivel1 level;
	public multiplicacionRan operacion = new multiplicacionRan();
	
	public static int INTENTO = 0;
	public static int SCORE = 0;
	
	private Font gameScreen = new Font("Arial", Font.PLAIN, 48);
	private TickTimer gameOverTimer = new TickTimer(180);
	private TickTimer completeTimer = new TickTimer(180);
	
	public GameScreen(MaquinaEstado maquinaEstado){
		super(maquinaEstado);
		jugador = new Jugador(Display.WIDTH/2-50, Display.HEIGHT-75, 50, 50);
		level = new Nivel1(jugador);
	}
	
	@Override
	public void update(double delta) {
		jugador.update(delta);
		level.update(delta);
		
		if (level.esGameOver()) {
			gameOverTimer.tick(delta);
			if (gameOverTimer.isEventReady()) {
				level.reset();
				getMaquinaEstado().setEstado((byte) 0);
				INTENTO = 0;
			}
		}

		if (level.esCompletado()) {
			completeTimer.tick(delta);
			if (completeTimer.isEventReady()) {
				level.reset();
			}
		}
	}

	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.drawString(operacion.getNumA() + "x" + operacion.getNumB() + " = "+INTENTO, 5, 15);
		
		g.setColor(Color.red);
		g.drawString("Vida: " + jugador.getVida(), 5, 35);
		g.setColor(Color.green);
		g.drawString("Score: " + SCORE, 5, 55);
		
		g.setColor(Color.white);
		g.drawString("Presione ENTER para introducir cantidad estimada!", 250, 780);
		
		jugador.draw(g);
		level.draw(g);
		
		if (level.esGameOver()) {
			g.setColor(Color.red);
			g.setFont(gameScreen);
			String gameOver = "GAME OVER!";
			int gameOverWidth = g.getFontMetrics().stringWidth(gameOver);
			g.drawString(gameOver, (Display.WIDTH/2)-(gameOverWidth/2), Display.HEIGHT/2);
		}
		
		if (level.esCompletado()) {
			g.setColor(Color.green);
			g.setFont(gameScreen);
			String complete = "LEVEL COMPLETE!";
			int completeWidth = g.getFontMetrics().stringWidth(complete);
			g.drawString(complete, (Display.WIDTH/2)-(completeWidth/2), Display.HEIGHT/2);
		}
	}

	@Override
	public void init(Canvas canvas) {
		canvas.addKeyListener(jugador);
	}

}
	

