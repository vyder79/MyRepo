package net.wajder.network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Implementacja sieci neuronowej list pojedynczych warst
 * 
 */
public class NeuronNetwork implements Serializable {

	private static final long serialVersionUID = -8088643370245104127L;

	private ArrayList<SingleLayer> neuronNetwork;
	private String descrption;
	private int bias;

	// Do Genetycznego Algorytmu Nauczania


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
	public NeuronNetwork(int[] layersDesc, String description) {
		ArrayList<SingleLayer> listOfLayers = new ArrayList<>();

		for (int layer = 0; layer < layersDesc.length; layer++) {
			ArrayList<Double> weights = new ArrayList<>();
			ArrayList<Neuron> Layer = new ArrayList<>();
			SingleLayer SingleLayer = new SingleLayer(Layer, "init layer");
			Random rand = new Random();

			if (layer == 0) { // warstwa pierwsza rï¿½ï¿½ni siï¿½ od pozostaï¿½ych

				weights.clear();
				Layer.clear();
				weights.add(1d);
				for (int j = 0; j < layersDesc[layer]; j++) {
					Layer.add(new Neuron((ArrayList<Double>) weights.clone(), "n_" + layer + "_" + j,
							new ActivationFunction(ActivFuncEnum.LINEAR)));
				}
				SingleLayer = new SingleLayer(Layer, "layer[" + layer + "]");
				listOfLayers.add(SingleLayer);

			} else { // kolejne warstwy /// TO by zostaÅ‚o

				weights.clear();
				Layer.clear();
				for (int j = 0; j < layersDesc[layer]; j++) {
					weights.clear();
					for (int i = 0; i < layersDesc[layer - 1] + 1; i++) { // jedna waga  wiÄ™cej dla biasu
						weights.add(rand.nextDouble() - 0.5); // zakres wag[-0.5 ; 0.5]
					}
					Layer.add(new Neuron((ArrayList<Double>) weights.clone(), "n_" + layer + "_" + j,
							new ActivationFunction(ActivFuncEnum.SIGMOID)));
				}
				SingleLayer = new SingleLayer(Layer, "layer[" + layer + "]");
				listOfLayers.add(SingleLayer);
			}

		}
		this.descrption = description;
		this.neuronNetwork = listOfLayers;
		this.bias = Constants.BIAS;
	}


	public double blad(TreningPattern treningPatterns) {
		double bladDlaWzorca = 0.0;

		int layer = this.neuronNetwork.size();
		SingleLayer sLayer = this.neuronNetwork.get(layer - 1);
		int neuronCount = sLayer.getNeurons().size();

		int counter = treningPatterns.getOutputArray().length;

		// System.out.println("WYJSCIE " +
		// sLayer.getNeurons().get(0).getOut());
		for (int i = 0; i < counter; i++) {
			bladDlaWzorca += Math.abs(treningPatterns.getOutputArray()[i] - sLayer.getNeurons().get(i).getOut());
		}
		// System.out.println(treningPattern.getOutputArray()[0] + " - " +
		// sLayer.getNeurons().get(0).getOut() + " = " + blad);

		return bladDlaWzorca / counter;
	}

	public void networkLearning(ArrayList<TreningPattern> treningPatterns) {

		// wstï¿½pne wyznaczenie bï¿½ï¿½du dla sieci
		this.work(treningPatterns.get(0).getInputArray());
		this.countErrors(treningPatterns.get(0).getOutputArray());
		//double meanSquareError = blad(treningPatterns.get(0));
		double meanSquareError = 10000000.0;
		this.recalculateWeights(); // metoda wstecznej propagacji bï¿½ï¿½du

		long iterations = 0L;
		while (Math.abs(meanSquareError) > Constants.ERROR_VALUE && iterations < 10000000) {
			meanSquareError = 0d;
			

			if (Constants.RANDOMIZE_TRENING_PATTERNS) {
				treningPatterns =  randomizeTrenningPatternList(treningPatterns);
			}
			
			for (TreningPattern tp : treningPatterns) {
				this.work(tp.getInputArray());
				this.countErrors(tp.getOutputArray());
				meanSquareError += this.meanSquareError(tp.getOutputArray());
				//meanSquareError = blad(tp); // ------------------------------------------------------------------------------------
				//System.out.println("WZORZEC BLAD " + blad(tp));
				this.recalculateWeights();
			}
			iterations++;
			meanSquareError /= treningPatterns.size();
			if (Constants.DEBUG) {
				System.out.println(meanSquareError);
			}
		}

		//System.out.println(this);
		System.out.println("\r\n[meanSquareError: " + String.format("%.8f", meanSquareError) + "] after " + iterations + " iterations");
		
//		for (TreningPattern tp : treningPatterns) {
//			this.work(tp.getInputArray());
//			int layer = this.neuronNetwork.size();
//			SingleLayer sLayer = this.neuronNetwork.get(layer - 1);
//			int neuronCount = sLayer.getNeurons().size();
//
////			for (Neuron n : sLayer.getNeurons()) {
////				System.out.println("WYJÅšIE Z SIECI " + n.getOut());
////			}
//		}
	}

	// FIXME
	private ArrayList<TreningPattern> randomizeTrenningPatternList(ArrayList<TreningPattern> treningPatterns) {
		ArrayList<TreningPattern> randomizedTrenningPatternList = new ArrayList<TreningPattern>();
		int tpCount = treningPatterns.size() - 1;
		ArrayList<TreningPattern> tempList = (ArrayList<TreningPattern>) treningPatterns.clone();
		Random rand = new Random();

		int  n = rand.nextInt(50) + 1;
		
		//System.out.println("treningPatterns: " + treningPatterns.size());
		//System.out.println("treningPatterns: " + treningPatterns.get(5));
		
		for (int i=0; i <= tpCount; i++) {
			int r = rand.nextInt(tempList.size());
			
			//System.out.println("r: " + r);
			
			randomizedTrenningPatternList.add(treningPatterns.get(r));
			tempList.remove(r);
		}

		//System.out.println("randomizedTrenningPatternList: " + randomizedTrenningPatternList.size());
		//System.out.println("randomizedTrenningPatternList: " + randomizedTrenningPatternList.get(5));
		treningPatterns = (ArrayList<TreningPattern>) randomizedTrenningPatternList.clone();
		return randomizedTrenningPatternList;
	}
	


	/*
	 * lets get started
	 */
	public void work(double[] inputVector) {
		ArrayList<Double> outs = new ArrayList<Double>();

		// warstwa 0
		for (int i = 0; i < inputVector.length; i++) {
			outs.clear();
			outs.add(inputVector[i]);
			this.neuronNetwork.get(0).getNeurons().get(i).activate(outs);
		}

		// pozostaï¿½e warstwy
		for (int l = 1; l < this.neuronNetwork.size(); l++) {
			outs.clear();
			// tworzymy wektor wejï¿½ciowy z wyjï¿½ï¿½ poprzedniej warstwy
			for (Neuron x : this.neuronNetwork.get(l - 1).getNeurons()) {
				outs.add(x.getOut());
			}
			outs.add(Double.parseDouble(Constants.BIAS + ""));
			// kaï¿½demu z neuronï¿½w podajemy wektor wejï¿½ciowy z wag poprzedniej
			// warstwy
			for (Neuron n : this.neuronNetwork.get(l).getNeurons()) {
				n.activate(outs);
			}
		}
	}

	/*
	 * obliczenie bï¿½ï¿½du dla kaï¿½dgo neurona w sieci
	 */
	public void countErrors(double[] outputVector) {

		int layer = this.neuronNetwork.size();
		double delta = 0d;
		int counter = 0;

		// ostatnia warstwa sieci neuronowej
		for (Neuron n : this.neuronNetwork.get(layer - 1).getNeurons()) {
			n.setError(n.getOut() * (1 - n.getOut()) * (outputVector[counter] - n.getOut()));
			counter++;
		}

		// pozostaï¿½ï¿½ warstwy poza wejsciowï¿½
		for (int currentLayerNumber = layer; currentLayerNumber > 2; currentLayerNumber--) {
			int l = 0; // numer wagi
			for (Neuron n : this.neuronNetwork.get(currentLayerNumber - 2).getNeurons()) {
				for (Neuron neuronFromPrevLayer : this.neuronNetwork.get(currentLayerNumber - 1).getNeurons()) {
					delta += neuronFromPrevLayer.getError() * neuronFromPrevLayer.getWeights().get(l);
				}
				n.setError(delta * (n.getOut() * (1 - n.getOut())));
				l++;
			}
		}
	}


	/*
	 * obliczanie bï¿½ï¿½du ï¿½redniokwadratowego dla podanego wektora wejï¿½ciowego
	 */
	public double meanSquareError(double[] inputVector) {

		int layer = this.neuronNetwork.size();
		SingleLayer sLayer = this.neuronNetwork.get(layer - 1);
		int neuronCount = sLayer.getNeurons().size();
		if (Constants.DEBUG_ERROR) {
			System.out.println("\r\nbłąd średniokwadratowy:");
		}

		double delta = 0d;
		double maxError = 0d;
		
		for (int i = 0; i < neuronCount; i++) {
			double out = sLayer.getNeurons().get(i).getOut();
			double sq = out - inputVector[i];
			delta += sq * sq;
			
			if (maxError < sq) {
				maxError = sq;
			}
			
			if (Constants.DEBUG_ERROR) {
				System.out.println("out: " + out + " inputVector[" + i + "]: " + inputVector[i] + " delta: " + delta + " sq: " + sq + " maxError: " + maxError);
			}
		}
		if (Constants.DEBUG_ERROR) {
			System.out.println("[" + delta + "/" + neuronCount + "]");
		}
		return delta / neuronCount;
		//return maxError;
	}

	/*
	 * przeliczenie wag neuronï¿½w w sieci
	 * 
	 */
	public void recalculateWeights() {

		int numberOfLayers = this.neuronNetwork.size();
		SingleLayer layer = this.neuronNetwork.get(numberOfLayers - 1); // ostatnia
																		// warstwa
		ArrayList<Neuron> neurons = layer.getNeurons();

		SingleLayer layer_1 = this.neuronNetwork.get(numberOfLayers - 2); // ï¿½rodkowa
																			// warstwa
		ArrayList<Neuron> neurons_1 = layer_1.getNeurons();

		for (Neuron n : neurons) {
			ArrayList<Double> newWeights = new ArrayList<>();
			for (int i = 0; i < n.getWeights().size(); i++) {
				newWeights.add(n.getWeights().get(i) + (Constants.EPSILON * n.getError())
						* (i == n.getWeights().size() - 1 ? this.bias : neurons_1.get(i).getOut()));
			}
			n.getWeights().clear();
			n.setWeights(newWeights);
		}

		SingleLayer layer_2 = this.neuronNetwork.get(numberOfLayers - 3); // pierwsza
																			// warstwa
		ArrayList<Neuron> neurons_2 = layer_2.getNeurons();

		for (Neuron n : neurons_1) {
			ArrayList<Double> newWeights = new ArrayList<>();
			for (int i = 0; i < n.getWeights().size(); i++) {
				newWeights.add(n.getWeights().get(i) + (Constants.EPSILON * n.getError())
						* (i == n.getWeights().size() - 1 ? this.bias : neurons_2.get(i).getOut()));
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
	public String toString() { // cala siec wraz z wagami i bï¿½ï¿½dami neuronï¿½w
		if (this.neuronNetwork != null && this.neuronNetwork.size() > 0) {
			String out = "";
			for (SingleLayer sl : this.neuronNetwork) {
				out += sl.toStringOutString();
			}
			return descrption + out;
		}
		return descrption + " is empty!";

	}

	public String output() { // tylko wyjï¿½cia neuronï¿½w
		if (this.neuronNetwork != null && this.neuronNetwork.size() > 0) {
			String out = " : ";
//			out += "\r\n hidden layers outs:\r\n";
//			for (int l = 1; l < this.neuronNetwork.size() - 1; l++) {
//				ArrayList<Neuron> neurons = this.neuronNetwork.get(l).getNeurons();
//				for (Neuron n : neurons) {
//					out += n.toStringOutNeuronOutput();
//				}
//				out += "\r\n";
//			}

			//out += "output layer outs:\r\n";
			ArrayList<Neuron> neurons2 = this.neuronNetwork.get(this.neuronNetwork.size() - 1).getNeurons();
			for (Neuron n : neurons2) {
				// out += n.toStringOutNeuronOutputRounded();
				out += n.toStringOutNeuronOutput();
			}
			return out + "\r\n";
		}
		return descrption + " is empty!";
	}
	
	public double[] calculatedOut() {
		double[] out = {0, 0, 0};
		int k = 0;
		ArrayList<Neuron> neurons2 = this.neuronNetwork.get(this.neuronNetwork.size() - 1).getNeurons();
		for (Neuron n : neurons2) {
			out[k] = n.getOut();
			k++;
		}
		return out;
	}

	/**
	 * *****************************************************
	 * 
	 * main method to count output (MADALINE)
	 * 
	 *********************************************************/
	public String checkLetter(ArrayList<Integer> inputList) {
		// normalizacja
		int ones = 0;
		ArrayList<Double> normalizedInputList = new ArrayList<>();
		// policzenie jedynek w literze
		for (Integer l : inputList) {
			if (l == 1) {
				ones++;
			}
		}
		System.out.println("wektor wejï¿½ciowy: " + inputList + " jedynki:" + ones);

		// zamiana jedynek na znormalizowanï¿½ wartoï¿½ï¿½
		for (Integer l : inputList) {
			if (l == 1) {
				normalizedInputList.add(1 / Math.sqrt(ones));
			} else {
				normalizedInputList.add(0d);
			}
		}

		System.out.println("obliczanie:");

		ArrayList<Neuron> sl = this.neuronNetwork.get(1).getNeurons(); // neurony
																		// rozpoznajï¿½ce
																		// litery
		ArrayList<Double> resultAfterLastLayer = new ArrayList<>();
		for (Neuron n : sl) {
			double similarity = n.activate(normalizedInputList);
			resultAfterLastLayer.add(similarity);
			if (Constants.DEBUG) {
				System.out.println("dla litery " + Character.toString((char) Integer.parseInt(n.getDescription()))
						+ " podobieï¿½stwo wynosi: " + similarity);
			}

		}

		return resultAfterLastLayer.toString();
	}

//	public Chromosom getNajlepszy() {
//		return najlepszy;
//	}
//
//	public void setNajlepszy(Chromosom najlepszy) {
//		this.najlepszy = najlepszy;
//	}

}