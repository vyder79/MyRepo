package net.wajder.network;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Test class to check Neuron in action.
 * 
 * @author vyder
 *
 */
public class Test {
	
	// when TRUE, additional data is shown when computing weights
	final static boolean DEBUG = false;

	
	public static void main(String[] args) {
		/*
		 * iloœæ elementów tablicy okreœla iloœæ warstw sieci neuronowej,
		 * wartoœci poszczególnych elementów okreœlaj¹ iloœæ neuronów w ka¿dej warstwie
		 */
		final int[] layersDesc = {4, 2, 4};
		final int[] inputVector = {0, 0, 1, 0};
		final int[] outputVector = {0, 0, 1, 0};
		
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
				for (int i=0; i<layersDesc[layer-1]; i++) {
					weights.add(rand.nextDouble() - 0.5);  // zakres wag [-0.5 ; 0.5]
				}
				for (int j=0; j<layersDesc[layer]; j++) {
					Layer.add(new Neuron((ArrayList<Double>)weights.clone(), "n_"+layer+"_"+j, new ActivationFunction(ActivFuncEnum.SIGMOID)));
				}
				SingleLayer = new SingleLayer(Layer, "layer["+layer+"]");
				//SingleLayer.toStringOut();
				listOfLayers.add(SingleLayer);
			}
			
		}
		
		// utworzenie sieci
		NeuronNetwork net = new NeuronNetwork(listOfLayers, "my neural network");
		//System.out.println(net);


		net.work(inputVector);
		
		net.countErrors(outputVector);
		System.out.println(net);
		
		double meanSquareError = net.meanSquareError(inputVector);
		System.out.println("\r\n[meanSquareError: " + meanSquareError + "]");
		
		net.recalculateWeights();
		System.out.println(net);
		
		
		for (int i = 0; i < 200000; i++) {
			net.work(inputVector);
			net.countErrors(outputVector);
			meanSquareError = net.meanSquareError(inputVector);
			System.out.println("\r\n[meanSquareError: " + meanSquareError + "]");
			net.recalculateWeights();
		}
		
		System.out.println(net);
	}

}
