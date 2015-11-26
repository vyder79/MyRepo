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
					Layer.add(new Neuron(weights, "n_"+layer+"_"+j, new ActivationFunction()));
				}
				SingleLayer = new SingleLayer(Layer, "layer["+layer+"]");
				//SingleLayer.toStringOut();
				listOfLayers.add(SingleLayer);
				
			} else { // kolejne warstwy
				
				weights.clear();
				Layer.clear();
				for (int i=0; i<layersDesc[layer-1]; i++) {
					weights.add(rand.nextDouble());
				}
				for (int j=0; j<layersDesc[layer]; j++) {
					Layer.add(new Neuron(weights, "n_"+layer+"_"+j, new ActivationFunction()));
				}
				SingleLayer = new SingleLayer(Layer, "layer["+layer+"]");
				//SingleLayer.toStringOut();
				listOfLayers.add(SingleLayer);
			}
			
		}
		
		NeuronNetwork net = new NeuronNetwork(listOfLayers, "my neural network");
		System.out.println(net);
		
	}

}
