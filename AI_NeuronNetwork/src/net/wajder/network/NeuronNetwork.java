package net.wajder.network;
import java.util.ArrayList;
import java.util.Random;

/**
 * List of single layers of neurons -> NETWORK
 * 
 * @author vyder
 *
 */
public class NeuronNetwork {
	private ArrayList<SingleLayer> neuronNetwork;
	private String descrption;
	
	/**
	 * @param neuronNetwork
	 * @param description
	 */
	public NeuronNetwork(ArrayList<SingleLayer> neuronNetwork, String description) {
		this.descrption = description;
		this.neuronNetwork = neuronNetwork;
	}
	
	
	/**
	 * @param layersDesc
	 * @param description
	 */
	public NeuronNetwork(int[] layersDesc, String description){
		ArrayList<SingleLayer> listOfLayers = new ArrayList<>();
		
		for (int layer = 0; layer < layersDesc.length; layer++){
			ArrayList<Double> weights = new ArrayList<>();
			ArrayList<Neuron> Layer = new ArrayList<>();
			SingleLayer SingleLayer = new SingleLayer(Layer, "init layer");
			Random rand = new Random();
			
			if (layer == 0) { // warstwa pierwsza ró¿ni siê od pozosta³ych
				
				weights.clear();
				Layer.clear();
				weights.add(1d);
				for (int j=0; j<layersDesc[layer]; j++){
					Layer.add(new Neuron((ArrayList<Double>)weights.clone(), "n_"+layer+"_"+j, new ActivationFunction(ActivFuncEnum.LINEAR)));
				}
				SingleLayer = new SingleLayer(Layer, "layer["+layer+"]");
				//SingleLayer.toStringOut();
				listOfLayers.add(SingleLayer);
				
			} else { // kolejne warstwy
				
				weights.clear();
				Layer.clear();
				for (int j=0; j<layersDesc[layer]; j++) {
					weights.clear();
					for (int i=0; i<layersDesc[layer-1]; i++) {
						weights.add(rand.nextDouble() - 0.5);  // zakres wag [-0.5 ; 0.5]
					}
					Layer.add(new Neuron((ArrayList<Double>)weights.clone(), "n_"+layer+"_"+j, new ActivationFunction(ActivFuncEnum.SIGMOID)));
				}
				SingleLayer = new SingleLayer(Layer, "layer["+layer+"]");
				//SingleLayer.toStringOut();
				listOfLayers.add(SingleLayer);
			}
			
		}
		
		this.descrption = description;
		this.neuronNetwork = listOfLayers;
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
		
		ArrayList<Neuron> sl = this.neuronNetwork.get(1).getNeurons(); //neurony rozpoznaj¹ce litery
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
	public void work(int[] inputVector) {
		
		ArrayList<Double> outs = new ArrayList<Double>();
		
		// warstwa 0
		for (int i=0; i<inputVector.length; i++) {
			outs.clear();
			outs.add((double)inputVector[i]);
			this.neuronNetwork.get(0).getNeurons().get(i).activate(outs);
		}
		
		// pozosta³e warstwy
		for (int l=1; l<this.neuronNetwork.size(); l++) {
			outs.clear();
			// tworzymy wektor wejœciowy z wyjœæ poprzedniej warstwy
			for (Neuron x : this.neuronNetwork.get(l-1).getNeurons()) {
				outs.add(x.getOut());
			}
			// ka¿demu z neuronów podajemy wektor wejœciowy z wag poprzedniej warstwy
			for (Neuron n : this.neuronNetwork.get(l).getNeurons()){
				n.activate(outs);
			}
		}
	}
	
	/*
	 * obliczenie b³êdu dla ka¿dgo neurona w sieci
	 */
	public void countErrors(int[] outputVector) {
		
		int layer = this.neuronNetwork.size();
		double delta = 0d;
		int counter = 0;
		
		for (Neuron n : this.neuronNetwork.get(layer - 1).getNeurons()) {
			//n.setError((outputVector[counter] - n.getOut())*(n.getOut()*(1-n.getOut())));
			//////n.setError((n.getWeightedSum()*(1-n.getWeightedSum()) * (outputVector[counter]-n.getOut())));
			n.setError(outputVector[counter]-n.getOut());
			//System.out.println(n.getDescription() + " err: " + n.getError() + " out: " + n.getOut() + " weightedSum: " + n.getWeightedSum());
			counter++;
		}
		
		int l = 0; // numer wagi
		for (Neuron n : this.neuronNetwork.get(layer - 2).getNeurons()) {
			for (Neuron nlast : this.neuronNetwork.get(layer - 1).getNeurons()) {
				//delta += nlast.getOut()*nlast.getWeights().get(l);
				delta += nlast.getError()*nlast.getWeights().get(l);
			}
			//n.setError(delta*(n.getOut()*(1-n.getOut())));
			n.setError(delta*(n.getWeightedSum()*(1-n.getWeightedSum())));
			//System.out.println(n.getDescription() + " err: " + n.getError() + " out: " + n.getOut() + " weightedSum: " + n.getWeightedSum());
			l++;
		}
	}
	
	/*
	 * obliczanie b³êdu œredniokwadratowego dla podanego wektora wejœciowego
	 */
	public double meanSquareError(int[] inputVector) {
		
		int layer = this.neuronNetwork.size();
		SingleLayer sLayer = this.neuronNetwork.get(layer - 1);
		int neuronCount = sLayer.getNeurons().size();
		if (Test.DEBUG) {
			System.out.println("\r\nb³¹d œredniokwadratowy:");
		}
		
		double delta = 0d;
		for (int i = 0; i < neuronCount; i++) {
			double out = sLayer.getNeurons().get(i).getOut();
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
	
	/*
	 * przeliczenie wag neuronów w sieci
	 */
	public void recalculateWeights() {
		
		int numberOfLayers = this.neuronNetwork.size();
		SingleLayer layer = this.neuronNetwork.get(numberOfLayers-1); // ostatnia warstwa
		ArrayList<Neuron> neurons = layer.getNeurons();
		
		SingleLayer layer_1 = this.neuronNetwork.get(numberOfLayers-2); // œrodkowa warstwa
		ArrayList<Neuron> neurons_1 = layer_1.getNeurons();
		
		for (Neuron n : neurons) {
			ArrayList<Double> newWeights = new ArrayList<>();
			for (int i = 0; i < n.getWeights().size(); i++) {
				newWeights.add(n.getWeights().get(i) + (Constants.EPSILON * n.getError()) * neurons_1.get(i).getOut());
				//newWeights.add(12d);
			}
			//System.out.println("\r\n" + n.getDescription());
			//System.out.println("before " + n.getWeights());
			n.getWeights().clear();
			n.setWeights(newWeights);
			//System.out.println("after " + n.getWeights());
		}
		
		SingleLayer layer_2 = this.neuronNetwork.get(numberOfLayers-3); // pierwsza warstwa
		ArrayList<Neuron> neurons_2 = layer_2.getNeurons();

		for (Neuron n : neurons_1) {
			ArrayList<Double> newWeights = new ArrayList<>();
			for (int i = 0; i < n.getWeights().size(); i++) {
				newWeights.add(n.getWeights().get(i) + (Constants.EPSILON * n.getError()) * neurons_2.get(i).getOut());
				//newWeights.add(12d);
			}
			//System.out.println("\r\n" + n.getDescription());
			//System.out.println("before " + n.getWeights());
			n.getWeights().clear();
			n.setWeights(newWeights);
			//System.out.println("after " + n.getWeights());
		}
		
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
	
	public String output(){
		if (this.neuronNetwork != null && this.neuronNetwork.size() > 0) {
			String out = "\r\n";
			ArrayList<Neuron> neurons = this.neuronNetwork.get(this.neuronNetwork.size() - 1).getNeurons();
			for (Neuron n : neurons){
				out += n.toStringOutNeuronOutput();
			}
			return out + "\r\n";
		}
		return descrption + " is empty!";
	}


}
