package zadanie2_Problem3_Turniej;

import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		
		int numberOfPlayers = 8;
		
		Turniej turniej = new Turniej();
		turniej.createListOfPlayers(numberOfPlayers);
		
		// utworzenie listy dla ka�dego playera z kim ju� walczy� (0 - nie walczy�, 1 - walczy�)
		ArrayList<Integer> alreadyFightedList = new ArrayList<>();
		for (int i = 0; i < numberOfPlayers; i++){
			alreadyFightedList.add(0);
		}
		// ustawienie listy ka�demu z player�w (jako kopii listy utworzonej powy�ej)
		// teoretycznei to powinno si� odbywa� w klasie Player :)
		for (Player player : turniej.listOfPlayers) {
			player.setAlreadyFightedList((ArrayList<Integer>)alreadyFightedList.clone());
		}
		
		turniej.createListOfOpponents(turniej.listOfPlayers);
		
		turniej.sheduling(numberOfPlayers -1, turniej.listOfPlayers);
		
	}

}
