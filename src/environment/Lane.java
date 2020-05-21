package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;

	public Lane(Game game,  boolean leftToRight, int ord, int speed){
		this.game=game;
		this.leftToRight=leftToRight;
		this.speed=speed;
		this.density =game.defaultDensity;
		this.ord=ord;
	
	}
	
	public int getOrd(){
		return this.ord;
	}

	public void update() {
		if(this.ord!=0) mayAddCar();
		for(Car c : this.cars){
			c.updatePosition();
			if (c.getPosition().absc>=0 && c.getPosition().absc<this.game.width && this.ord>-5 ){	//affiche si la voiture n'est pas loin
				c.addToGraphics();
			}
		}
	
	}

	public boolean isSafe(util.Case c) {			
			for (Car v : this.cars){
				if ( c.absc<(v.getPosition().absc+v.getLength()) && c.absc>=(v.getPosition().absc)) return false;			
			}		
		return true;
	}
	
	/**
	 * Ajoute une voiture au début de la voie avec probabilité égale à la
	 * densité, si la première case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight, this.speed));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}
	
	
	/*Partie 3*/
	
	/**
	 * modifie l'ordonnee de la voie et de toutes ses voitures
	 */
	public void modifOrd(int ordNew){
		this.ord=ordNew;
		for(Car c : this.cars){
			Case pos = new Case(c.getPosition().absc, ordNew);
			c.modifiePos(pos);
		}
		
	}

}
