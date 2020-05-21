
package gameCommons;

import java.awt.Color;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import util.Case;
import util.Direction;
import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	public Instant time;
	private boolean over;
	// Lien aux objets utilisés
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant déplacement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.over=false;
	}

	/**
	 * Lie l'objet frog à la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un écran de fin approprié si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		int ord = this.frog.getPosition().ord+this.frog.retourneHauteur();
		Case c = new Case(this.frog.getPosition().absc, ord);
		if(!this.environment.isSafe(c)){	//si la grenouille n'est pas en sécurité
			if(this.over==false) {
				Instant a;
				a=Instant.now();
				long t=Duration.between(this.time, a).getSeconds();
				this.over=true;
				graphic.endGameScreen("Game Over! Time: "+t+"s"+" Score : "+ this.frog.retourneScore());
			
			}
			return true;
		}
		return false;
	}

	/**
	 * Teste si la partie est gagnee et lance un écran de fin approprié si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagnée
	 */
	public boolean testWin() {
		if (this.environment.isWinningPosition(this.frog.getPosition())){
			if(this.over==false) {
				Instant a;
				a=Instant.now();
				long t=Duration.between(this.time, a).getSeconds();
				this.over=true;

			graphic.endGameScreen("Bien Joué. Time: "+t+"s");
			}
			return true;
		}
		return false;
	}

	/**
	 * defile l'environnement selon la derniere direction de frog
	 */
	public void testDefilement(){
		environment.defile(frog.getDirection(), frog.retourneHauteur());
		frog.modifieLastD(Direction.left);
	}
	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		testDefilement();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
		testLose();
		testWin();
	}

}
