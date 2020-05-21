package environment;

import java.awt.Color;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private int speed;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	//TODO Constructeur(s)
	public Car (Game game, Case leftPosition, boolean leftToRight, int speed){
		this.game=game;
		this.leftPosition=leftPosition;
		this.leftToRight=leftToRight;
		this.length=game.randomGen.nextInt(2)+1;
		this.speed=speed;
		
	}
	
	//TODO : ajout de methodes
	public Case getPosition(){
		return this.leftPosition;
	}
	public int getLength(){
		return this.length;
	}
	public void updatePosition() {
		Case c ;
		if (this.leftToRight) c = new Case (this.leftPosition.absc+this.speed,this.leftPosition.ord);
		else c = new Case (this.leftPosition.absc-this.speed,this.leftPosition.ord);
		this.leftPosition = c;
	}
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	public void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}
	public void modifiePos(Case c){
		this.leftPosition=c;
	}

}
