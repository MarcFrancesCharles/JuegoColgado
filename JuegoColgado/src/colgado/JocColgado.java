/*Crear una versión del juego del "Colgado" en Java, en el cual los jugadores intentan adivinar una palabra secreta, letra por letra, en un número limitado de rondas.
 *  El juego terminará cuando un jugador adivine la palabra correctamente o se alcance el límite de intentos permitidos.

Reglas del juego:

El juego comienza pidiendo al administrador (un jugador) que elija una palabra secreta, la cual debe ser oculta durante el juego.
Cada jugador tiene que adivinar una letra de la palabra secreta en cada ronda.
El número máximo de intentos por jugador es 6.
Si un jugador adivina correctamente una letra, esta se revela en su posición en la palabra secreta.
Si un jugador se queda sin intentos, pierde la ronda.
El jugador que adivine toda la palabra primero o quien gane más rondas será el ganador.
Instrucciones:

El programa preguntar cuantos jugadores quieren jugar. El mínimo de jugadores tiene que ser 2.
El programa preguntará cuantas rondas se quieren jugar.
Para cada ronda, el programa debe:
Mostrar las letras acertadas de la palabra secreta (con guiones bajos para las letras no adivinadas).
Solicitar a los jugadores que ingresen una letra.
Comprobar si la letra ingresada es correcta.
Actualizar la palabra secreta con las letras adivinadas correctamente.
Contabilizar los intentos restantes para cada jugador.
Mostrar el estado de la palabra secreta después de cada intento.
Al finalizar las rondas, mostrar el nombre del jugador que haya ganado más rondas.*/
package colgado;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class JocColgado {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] paraules = new String[] {"patata", "hipopotamo", "pernil", "gelat", "tallarins", "mandonguilles", "gat", "taula"};
        Random generadorNumeros = new Random();

        String enter;
        String paraulaEscollida;
        String opcioLletra = null;
        int vidas = 5;
        int acerts = 0;
        int numJugadors = 0; 
        int numRondes = 0; 
        int partidaActual = 1;
        int tamanyParaula;
        char[] resposta;
        int[] acertsJugadors;
        int jugadorGuanyador = 0;
        int acertsMesGran = 0;


        System.out.println("|=====================================|");
        System.out.println("          BENVINGUT AL PENJAT!       ");
        System.out.println("|=====================================|");
        System.out.println("-> Endevina la paraula amagada lletra a lletra.");
        System.out.println("-> Si t'equivoques massa vegades... et penjaran!");
        System.out.println("\n \n \n Normes del joc:");
        System.out.println("- Pots proposar una lletra per torn.");
        System.out.println("- Cada error et portarà més a prop del penjat.");
        System.out.println("- Endevina la paraula abans que sigui massa tard!");
        System.out.println("|******************************************|");
        System.out.println(" Toca ENTER per començar el joc! Bona sort! ");
        System.out.println("|******************************************|");

        enter = s.nextLine();

        paraulaEscollida = paraules[generadorNumeros.nextInt(paraules.length)];
        tamanyParaula = paraulaEscollida.length();
        resposta = new char[tamanyParaula];

        
    	while (numJugadors < 1 || numJugadors > 5 ) {
    		try { // Intenta fer codi
	    		System.out.println("\n Cuants Jugadors voleu ser (1-5) :");
	    		numJugadors = s.nextInt();
	    		
	    		if (numJugadors < 1 || numJugadors > 5) {
	    			System.out.println("Jugadors de 1 a 5");
	    		}
    			
    		}catch (InputMismatchException e) {  // Si detecta error , fa codig cacth
    			System.out.println("\n Només numeros");
    			s.nextLine();
    		}
    	}
        	
    	acertsJugadors = new int[numJugadors]; // Inicialitzem array acertsJugadors amb tants espais com jugadors hi hagin.
    	
    	
    	 
    	while (numRondes < 1 || numRondes > 10 ) {
    		try {
	    		System.out.println("\n Cuants partides voleu jugar?");
	    		numRondes = s.nextInt();
	    		
	    		if (numRondes < 1 || numRondes > 10) {
	    			System.out.println("Partides de 1 a 10");
	    		}
    			
    		}catch (InputMismatchException e) {
    			System.out.println("\n Només numeros");
    			s.nextLine();
    		}
    	}
        
        while (partidaActual <= numRondes ) {
        	System.out.println("+=====================================+");
        	System.out.println("|        PARTIDA "+partidaActual+"       ");
        	System.out.println("|=====================================+");
        	System.out.println("\n \n La Paraula que els hi a toca té " + tamanyParaula + " lletres.");
        	System.out.println("Qui l'adivini abans de fallar 6 cops guanya");
        	
        	for (int i = 0; i < tamanyParaula; i++) { // Preparem per mostrar les lletres com "x"
        		resposta[i] = 'x';
        	}
        	
        	for (int x = 0; x < acertsJugadors.length; x++) { // Establim els acerts dels jugadors a 0
    			acertsJugadors[x] = 0;
    		}
        	
	        while (vidas > 0 && acerts < tamanyParaula) {
	        	for(int x = 0; x < numJugadors; x++) { // For que controla el torn dels jugadors
	        		System.out.println("Torn del jugador " + (x+1));
	        		
	        		System.out.println("\n Paraula: " + String.valueOf(resposta));
		            System.out.println("Introdueix una lletra:");
		            
		            if (opcioLletra == null || opcioLletra.isEmpty()) {   
		                // Llimpia buufer
		                s.nextLine();
		            }
		            
		            do {
		                opcioLletra = s.nextLine().toLowerCase(); // passe a minuscules
		                if (opcioLletra.isEmpty() || opcioLletra.length() > 1) {
		                    System.out.println("Si us plau, introdueix només una lletra.");
		                }
		            } while (opcioLletra.isEmpty() || opcioLletra.length() > 1);
		            
		            opcioLletra.toLowerCase();
		
		            boolean encertat = false;
		            for (int i = 0; i < tamanyParaula; i++) {
		                if (paraulaEscollida.charAt(i) == opcioLletra.charAt(0) && resposta[i] == 'x') {
		                    resposta[i] = opcioLletra.charAt(0);
		                    encertat = true;
		                    acerts++;
		                    acertsJugadors[x]++;
		                }
		            }
		
		            if (!encertat) {
		                vidas--;
		                System.out.println("Incorrecte! Us queden " + vidas + " vides.");
		            }
		
		            if (acerts == tamanyParaula) {
		            	for (int i = 0; i < acertsJugadors.length; i++) { // Comprovem el jugador amb més encerts
			            	if(acertsJugadors[i] > acertsMesGran) {
			            		acertsMesGran = acertsJugadors[i];
			            		jugadorGuanyador = i;
			            	}
						}
		            	System.out.println("Ha guanyat el jugador " + (jugadorGuanyador + 1));
		            	break;
		            } else if (vidas == 0) {
		                System.out.println("No heu endivinat la paraula, ha guanyat el jugador " + jugadorGuanyador);
		                break;
		            }
		            
		            
	        	}
	        }
	        partidaActual++;
            vidas = 6;   //Reinicio contador de vides i acets
            acerts = 0;
            
            paraulaEscollida = paraules[generadorNumeros.nextInt(paraules.length)];
            tamanyParaula = paraulaEscollida.length();
            resposta = new char[tamanyParaula];
            
            

        }

        s.close();
    }
}