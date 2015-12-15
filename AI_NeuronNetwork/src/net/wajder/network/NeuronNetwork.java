package net.wajder.network;
import java.util.ArrayList;

/**
 * List of single layers of neurons -> NETWORK
 * 
 * @author vyder
 *
 */
public class NeuronNetwork {
	
	private ArrayList<SingleLayer> neuronNetwork;
	
	private String descrption;
	
	public NeuronNetwork(ArrayList<SingleLayer> neuronNetwork, String description) {
		this.descrption = description;
		this.neuronNetwork = neuronNetwork;
	}
	
	/** *****************************************************
	 * 
	 * main method to count output (MADALINE)
	 * 
	 *********************************************************/
	public String checkLetter(ArrayList<Integer> inputList){
		//normalizacja
		int ones = 0;
		ArrayList<Double> normalizedInputList = new ArrayList<>();
		// policzenie jedynek w literze
		for (Integer l : inputList){
			if (l == 1) {
				ones++;
			}
		}
		System.out.println("wektor wejœciowy: " + inputList + " jedynki:" + ones);
		
		// zamiana jedynek na znormalizowan¹ wartoœæ
		for (Integer l : inputList){
			if (l == 1) {
				normalizedInputList.add(1/Math.sqrt(ones));
			} else {
				normalizedInputList.add(0d);
			}
		}
		
		System.out.println("obliczanie:");
		
		ArrayList<Neuron> sl = this.neuronNetwork.get(1).getSingleLayer(); //neurony rozpoznaj¹ce litery
		ArrayList<Double> resultAfterLastLayer = new ArrayList<>();
		for (Neuron n : sl) {
			double similarity = n.activate(normalizedInputList);
			resultAfterLastLayer.add(similarity);
			if(Test.DEBUG){
				System.out.println("dla litery " + Character.toString ((char) Integer.parseInt(n.getDescription())) + " podobieñstwo wynosi: " + similarity);	
			}
			
		}
		
		return resultAfterLastLayer.toString();
	}
	
	/*
	 * lets get started
	 */
	public void doSomething(int[] inputVector) {
		
		ArrayList<Double> outs = new ArrayList<Double>();
		
		// warstwa 0
		for (int i=0; i<inputVector.length; i++) {
			outs.clear();
			outs.add((double)inputVector[i]);
			this.neuronNetwork.get(0).getSingleLayer().get(i).activate(outs);
		}
		
		// pozosta³e warstwy
		for (int l=1; l<this.neuronNetwork.size(); l++) {
			outs.clear();
			// tworzymy wektor wejœciowy z wyjœæ poprzedniej warstwy
			for (Neuron x : this.neuronNetwork.get(l-1).getSingleLayer()) {
				outs.add(x.getOut());
			}
			// ka¿demu z neuronów podajemy wektor wejœciowy z wag poprzedniej warstwy
			for (Neuron n : this.neuronNetwork.get(l).getSingleLayer()){
				n.activate(outs);
			}
		}
	}
	
	/*
	 * obliczenie b³êdu dla ka¿dgo neurona w sieci
	 */
	public void countErrors() {
		
		int layer = this.neuronNetwork.size();
		double delta = 0d;
		
		for (Neuron n : this.neuronNetwork.get(layer - 1).getSingleLayer()) {
			n.setError(n.getOut()*(1-n.getOut()));
		}
		
		int l = 0; // numer wagi
		for (Neuron n : this.neuronNetwork.get(layer - 2).getSingleLayer()) {
			for (Neuron nlast : this.neuronNetwork.get(layer - 1).getSingleLayer()) {
				delta += nlast.getOut()*nlast.getWeights().get(l);
			}
			n.setError(delta*(n.getOut()*(1-n.getOut())));
			l++;
		}
	}
	
	/*
	 * obliczanie b³êdu œredniokwadratowego dla podanego wektora wejœciowego
	 */
	public double meanSquareError(int[] inputVector) {
		
		int layer = this.neuronNetwork.size();
		SingleLayer sLayer = this.neuronNetwork.get(layer - 1);
		int neuronCount = sLayer.getSingleLayer().size();
		if (Test.DEBUG) {
			System.out.println("\r\nb³¹d œredniokwadratowy:");
		}
		
		
		double delta = 0d;
		for (int i = 0; i < neuronCount; i++) {
			double out = sLayer.getSingleLayer().get(i).getOut();
			double sq = out - inputVector[i];
			delta += sq*sq;
			if (Test.DEBUG) {
				System.out.println("out: " + out + " inputVector["+i+"]: " + inputVector[i] + " delta: " + delta);
				
			}
		}
		if (Test.DEBUG) {
			System.out.println("["+delta +"/"+neuronCount+"]");
		}
		return delta / neuronCount;
	}
	
	
	public ArrayList<SingleLayer> getNeuronNetwork() {
		return neuronNetwork;
	}

	public void setNeuronNetwork(ArrayList<SingleLayer> neuronNetwork) {
		this.neuronNetwork = neuronNetwork;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	@Override
	public String toString(){
		if (this.neuronNetwork != null && this.neuronNetwork.size() > 0) {
			String out = "";
			for (SingleLayer sl : this.neuronNetwork){
				out += sl.toStringOutString();
			}
			return descrption + out;
		}
		return descrption + " is empty!";
		
	}

	


	
}
