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
			System.out.println("dla litery " + Character.toString ((char) Integer.parseInt(n.getDescription())) + " podobieñstwo wynosi: " + similarity);
		}
		
		return resultAfterLastLayer.toString();
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
