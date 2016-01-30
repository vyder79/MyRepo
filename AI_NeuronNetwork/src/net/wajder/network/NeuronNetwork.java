package net.wajder.network;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * List of single layers of neurons -> NETWORK
 * 
 * @author vyder
 */
public class NeuronNetwork implements Serializable {

	private static final long serialVersionUID = -8088643370245104127L;
	
	private ArrayList<SingleLayer> neuronNetwork;
	private String descrption;
	private int bias;
	
	/**
	 * @param neuronNetwork
	 * @param description
	 */
	public NeuronNetwork(ArrayList<SingleLayer> neuronNetwork, String description) {
		this.descrption = description;
		this.neuronNetwork = neuronNetwork;
		this.bias = Constants.BIAS;
	}
	
	
	/**
	 * @param layersDesc
	 * @param description
	 */
	@SuppressWarnings("unchecked")
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
				listOfLayers.add(SingleLayer);
				
			} else { // kolejne warstwy
				
				weights.clear();
				Layer.clear();
				for (int j=0; j<layersDesc[layer]; j++) {
					weights.clear();
					for (int i=0; i<layersDesc[layer-1] + 1; i++) { // jedna waga wiêcej dla biasu
						weights.add(rand.nextDouble() - 0.5);  // zakres wag [-0.5 ; 0.5]
					}
					Layer.add(new Neuron((ArrayList<Double>)weights.clone(), "n_"+layer+"_"+j, new ActivationFunction(ActivFuncEnum.SIGMOID)));
				}
				SingleLayer = new SingleLayer(Layer, "layer["+layer+"]");
				listOfLayers.add(SingleLayer);
			}
			
		}
		this.descrption = description;
		this.neuronNetwork = listOfLayers;
		this.bias = Constants.BIAS;
	}
	
	
	public void networkLearning(ArrayList<TreningPattern> treningPatterns){
		
		// wstêpne wyznaczenie b³êdu dla sieci
		this.work(treningPatterns.get(0).getInputArray());
		this.countErrors(treningPatterns.get(0).getOutputArray());
		double meanSquareError = this.meanSquareError(treningPatterns.get(0).getInputArray());
		this.recalculateWeights();
		
		
		long iterations = 0L;
		while (meanSquareError > Constants.ERROR_VALUE && iterations < 100000000) {
			meanSquareError = 0d;
			// TODO: nauka powinna za ka¿dym razem korzystaæ z innej kolejnoœci wzorców treningowych
			for (TreningPattern tp : treningPatterns){
				this.work(tp.getInputArray());
				this.countErrors(tp.getOutputArray());
				meanSquareError += this.meanSquareError(tp.getInputArray());
				this.recalculateWeights();
			}		
			iterations++;
			if (Constants.DEBUG) {
				System.out.println(meanSquareError);
			}
		}
		
		System.out.println(this);
		System.out.println("\r\n[meanSquareError: " + meanSquareError + "] after "+iterations+" iterations");
	}
	
	/*
	 * lets get started
	 */
	public void work(double[] inputVector) {
		ArrayList<Double> outs = new ArrayList<Double>();
		
		// warstwa 0
		for (int i=0; i<inputVector.length; i++) {
			outs.clear();
			outs.add(inputVector[i]);
			this.neuronNetwork.get(0).getNeurons().get(i).activate(outs);
		}
		
		// pozosta³e warstwy
		for (int l=1; l<this.neuronNetwork.size(); l++) {
			outs.clear();
			// tworzymy wektor wejœciowy z wyjœæ poprzedniej warstwy
			for (Neuron x : this.neuronNetwork.get(l-1).getNeurons()) {
				outs.add(x.getOut());
			}
			outs.add(Double.parseDouble(Constants.BIAS+""));
			// ka¿demu z neuronów podajemy wektor wejœciowy z wag poprzedniej warstwy
			for (Neuron n : this.neuronNetwork.get(l).getNeurons()){
				n.activate(outs);
			}
		}
	}
	
	/*
	 * obliczenie b³êdu dla ka¿dgo neurona w sieci
	 */
	public void countErrors(double[] outputVector) {
		
		int layer = this.neuronNetwork.size();
		double delta = 0d;
		int counter = 0;
		
		//ostatnia warstwa sieci neuronowej
		for (Neuron n : this.neuronNetwork.get(layer - 1).getNeurons()) {
			n.setError(n.getOut()*(1-n.getOut())*(outputVector[counter]-n.getOut()));
			counter++;
		}
		
		// pozosta³ê warstwy poza wejsciow¹
		for (int currentLayerNumber = layer; currentLayerNumber > 2; currentLayerNumber--) {
			int l = 0; // numer wagi
			for (Neuron n : this.neuronNetwork.get(currentLayerNumber - 2).getNeurons()) {
				for (Neuron neuronFromPrevLayer : this.neuronNetwork.get(currentLayerNumber - 1).getNeurons()) {
					delta += neuronFromPrevLayer.getError()*neuronFromPrevLayer.getWeights().get(l);
				}
				n.setError(delta*(n.getOut()*(1-n.getOut())));
				l++;
			}
		}
	}
	
//	public void countErrors(double[] outputVector) {
//		
//		int layer = this.neuronNetwork.size();
//		double delta = 0d;
//		int counter = 0;
//		
//		for (Neuron n : this.neuronNetwork.get(layer - 1).getNeurons()) {
//			n.setError(n.getOut()*(1-n.getOut())*(outputVector[counter]-n.getOut()));
//			counter++;
//		}
//		
//		int l = 0; // numer wagi
//		for (Neuron n : this.neuronNetwork.get(layer - 2).getNeurons()) {
//			for (Neuron nlast : this.neuronNetwork.get(layer - 1).getNeurons()) {
//				delta += nlast.getError()*nlast.getWeights().get(l);
//			}
//			n.setError(delta*(n.getOut()*(1-n.getOut())));
//			l++;
//		}
//	}
	
	/*
	 * obliczanie b³êdu œredniokwadratowego dla podanego wektora wejœciowego
	 */
	public double meanSquareError(double[] inputVector) {
		
		int layer = this.neuronNetwork.size();
		SingleLayer sLayer = this.neuronNetwork.get(layer - 1);
		int neuronCount = sLayer.getNeurons().size();
		if (Constants.DEBUG) {
			System.out.println("\r\nb³¹d œredniokwadratowy:");
		}
		
		double delta = 0d;
		for (int i = 0; i < neuronCount; i++) {
			double out = sLayer.getNeurons().get(i).getOut();
			double sq = out - inputVector[i];
			delta += sq*sq;
			if (Constants.DEBUG) {
				System.out.println("out: " + out + " inputVector["+i+"]: " + inputVector[i] + " delta: " + delta);
			}
		}
		if (Constants.DEBUG) {
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
				newWeights.add(n.getWeights().get(i) + (Constants.EPSILON * n.getError()) * (i == n.getWeights().size()-1 ? this.bias : neurons_1.get(i).getOut()) );
			}
			n.getWeights().clear();
			n.setWeights(newWeights);
		}
		
		SingleLayer layer_2 = this.neuronNetwork.get(numberOfLayers-3); // pierwsza warstwa
		ArrayList<Neuron> neurons_2 = layer_2.getNeurons();

		for (Neuron n : neurons_1) {
			ArrayList<Double> newWeights = new ArrayList<>();
			for (int i = 0; i < n.getWeights().size(); i++) {
				newWeights.add(n.getWeights().get(i) + (Constants.EPSILON * n.getError()) * (i == n.getWeights().size()-1 ? this.bias : neurons_2.get(i).getOut())  );
			}
			n.getWeights().clear();
			n.setWeights(newWeights);
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
	
	public int getBias() {
		return bias;
	}

	public void setBias(int bias) {
		this.bias = bias;
	}

	@Override
	public String toString(){ // cala siec wraz z wagami i b³êdami neuronów
		if (this.neuronNetwork != null && this.neuronNetwork.size() > 0) {
			String out = "";
			for (SingleLayer sl : this.neuronNetwork){
				out += sl.toStringOutString();
			}
			return descrption + out;
		}
		return descrption + " is empty!";
		
	}
	
	public String output(){ // tylko wyjœcia neuronów
		if (this.neuronNetwork != null && this.neuronNetwork.size() > 0) {
			String out = "\r\n hidden layers:\r\n";
			for (int l = 1; l < this.neuronNetwork.size()-1; l++) {
				ArrayList<Neuron> neurons = this.neuronNetwork.get(l).getNeurons();
				for (Neuron n : neurons){
					out += n.toStringOutNeuronOutput();
				}
				out += "\r\n";
			}

			out += "output layer:\r\n";
			ArrayList<Neuron> neurons2 = this.neuronNetwork.get(this.neuronNetwork.size() - 1).getNeurons();
			for (Neuron n : neurons2){
				out += n.toStringOutNeuronOutputRounded();
			}
			return out + "\r\n";
		}
		return descrption + " is empty!";
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
			if(Constants.DEBUG){
				System.out.println("dla litery " + Character.toString ((char) Integer.parseInt(n.getDescription())) + " podobieñstwo wynosi: " + similarity);	
			}
			
		}
		
		return resultAfterLastLayer.toString();
	}
	
}
