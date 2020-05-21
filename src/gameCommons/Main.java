package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Scanner;

import javax.swing.Timer;

import environment.Environment;
import environment.EnvironmentInf;
import frog.Frog;
import frog.FrogInf;
import givenEnvironment.GivenEnvironment;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

public class Main {

	public static void main(String[] args) {
		
		

		//Caractéristiques du jeu
		int width = 26;
		int height = 20;
		int tempo = 150;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.3;
		
		System.out.print("Veuillez choisir un mode de jeu : \n -Mode Normal. (n) \n -Mode Infini. (i) \n" );
		
		Scanner mode = new Scanner(System.in);
		String str = mode.nextLine();
		
		System.out.print("Veuillez choisir un niveau de difficulté : \n -Moyen. (m) \n -Difficile. (d) \n -Extreme. (h) \n" );
		
		String str1 = mode.nextLine();
		if(str1.equals("d")) defaultDensity=0.175;
		else if(str1.equals("h")) defaultDensity=0.35;
		else defaultDensity=0.1;
		
		
		//Création de l'interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Création de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
		//Création et liason de la grenouille
		IFrog frog;
		
		if(str.equals("i")) frog = new FrogInf(game);			
		else frog = new Frog(game);
		game.setFrog(frog);
		graphic.setFrog(frog);
		
		
		//Création et liaison de l'environnement
		IEnvironment env ;
		if (str.equals("i")) env = new EnvironmentInf(game);
		else env = new Environment(game);
		game.setEnvironment(env);
		game.time=Instant.now();
				
		//Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				graphic.repaint();
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
