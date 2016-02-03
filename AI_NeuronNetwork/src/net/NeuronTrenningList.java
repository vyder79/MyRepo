package net;
import java.util.ArrayList;
import java.util.Random;

public class NeuronTrenningList {
	
	ArrayList<TreningPattern> treningList = new ArrayList<TreningPattern>();

	public NeuronTrenningList() {
		for (int i=0; i<10; i++) {
			this.treningList.add(new TreningPattern(getRandomList(), getRandomDouble()));
		}
		showTreningList();
	}
	
	private double getRandomDouble(){
		Random r = new Random();
		return r.nextDouble();
	}
	
	private ArrayList<Double> getRandomList(){
		ArrayList<Double> al = new ArrayList<>();
		for (int i=0; i<10; i++) {
			al.add(getRandomDouble());
		}
		return al;
	}
	
	public void showTreningList(){
		for (TreningPattern tp : this.treningList) {
			System.out.println(tp.getInputList().toString() + " " + tp.getOutput());
		}
	}

	public ArrayList<TreningPattern> getTreningList() {
		return treningList;
	}

	public void setTreningList(ArrayList<TreningPattern> treningList) {
		this.treningList = treningList;
	}

}
