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
		final int[] inputVector1 = {0, 0, 0, 1};
		final int[] inputVector2 = {1, 0, 0, 0};
		final int[] inputVector3 = {0, 1, 0, 0};
		
		final int[] outputVector = {0, 0, 1, 0};
		final int[] outputVector1 = {0, 0, 0, 1};
		final int[] outputVector2 = {1, 0, 0, 0};
		final int[] outputVector3 = {0, 1, 0, 0};
		
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
		
		long iterations = 0L;
		//for (int i = 0; i < 20000000; i++) {
		while (meanSquareError > 0.10) {
			net.work(inputVector);
			net.countErrors(outputVector);
			double meanSquareError1 = net.meanSquareError(inputVector);
			net.recalculateWeights();
			
			net.work(inputVector1);
			net.countErrors(outputVector1);
			double meanSquareError2 = net.meanSquareError(inputVector1);
			net.recalculateWeights();
			
			net.work(inputVector2);
			net.countErrors(outputVector2);
			double meanSquareError3 = net.meanSquareError(inputVector2);
			net.recalculateWeights();
			
			net.work(inputVector3);
			net.countErrors(outputVector3);
			double meanSquareError4 = net.meanSquareError(inputVector3);
			net.recalculateWeights();
			
			meanSquareError =  (meanSquareError1 + meanSquareError2 + meanSquareError3 + meanSquareError4) / 4;
			if (iterations % 100000 == 0) System.out.println(" [meanSquareError: " + meanSquareError + "]" + "[" + meanSquareError1 + " : " + meanSquareError2 + " : " + meanSquareError3 + " : " + meanSquareError4 + "]");
			
			iterations++;
		}
		
		System.out.println(net);
		System.out.println("\r\n[meanSquareError: " + meanSquareError + "] after "+iterations+" iterations");
		
		
		System.out.println("results for [0, 0, 1, 0]:");
		net.work(inputVector);
		System.out.println(net);
		
		System.out.println("results for [0, 0, 0, 1]:");
		net.work(inputVector1);
		System.out.println(net);
		
		System.out.println("results for [1, 0, 0, 0]:");
		net.work(inputVector2);
		System.out.println(net);
		
		System.out.println("results for [0, 1, 0, 0]:");
		net.work(inputVector3);
		System.out.println(net);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	public static void main3(String[] args) {
//		/*
//		 * iloœæ elementów tablicy okreœla iloœæ warstw sieci neuronowej,
//		 * wartoœci poszczególnych elementów okreœlaj¹ iloœæ neuronów w ka¿dej warstwie
//		 */
//		final int[] layersDesc = {4, 2, 4};
//		final int[] inputVector = {0, 0, 1, 0};
//		final int[] outputVector = {0, 0, 1, 0};
//		
//		ArrayList<SingleLayer> listOfLayers = new ArrayList<>();
//		
//		for (int layer = 0; layer < layersDesc.length; layer++){
//			ArrayList<Double> weights = new ArrayList<>();
//			ArrayList<Neuron> Layer = new ArrayList<>();
//			SingleLayer SingleLayer = new SingleLayer(Layer, "init layer");
//			Random rand = new Random();
//			
//			if (layer == 0) { // warstwa pierwsza ró¿ni siê od pozosta³ych
//				
//				weights.clear();
//				Layer.clear();
//				weights.add(1d);
//				for (int j=0; j<layersDesc[layer]; j++){
//					Layer.add(new Neuron((ArrayList<Double>)weights.clone(), "n_"+layer+"_"+j, new ActivationFunction(ActivFuncEnum.LINEAR)));
//				}
//				SingleLayer = new SingleLayer(Layer, "layer["+layer+"]");
//				//SingleLayer.toStringOut();
//				listOfLayers.add(SingleLayer);
//				
//			} else if (layer == 1) { // kolejne warstwy
//				
//				weights.clear();
//				Layer.clear();
//
//					weights.clear();
//
//						weights.add(1d);
//						weights.add(2d);
//						weights.add(2d);
//						weights.add(1d);
//
//					Layer.add(new Neuron((ArrayList<Double>)weights.clone(), "n_"+layer+"_0", new ActivationFunction(ActivFuncEnum.SIGMOID)));
//					
//					weights.clear();
//
//					weights.add(2d);
//					weights.add(1d);
//					weights.add(2d);
//					weights.add(1d);
//
//					Layer.add(new Neuron((ArrayList<Double>)weights.clone(), "n_"+layer+"_1", new ActivationFunction(ActivFuncEnum.SIGMOID)));
//
//				SingleLayer = new SingleLayer(Layer, "layer["+layer+"]");
//				//SingleLayer.toStringOut();
//				listOfLayers.add(SingleLayer);
//			} else if (layer == 2) { // kolejne warstwy
//				
//				weights.clear();
//				Layer.clear();
//
//					weights.clear();
//
//					weights.add(1d);
//					weights.add(1d);
//
//					Layer.add(new Neuron((ArrayList<Double>)weights.clone(), "n_"+layer+"_0", new ActivationFunction(ActivFuncEnum.SIGMOID)));
//					
//					weights.clear();
//
//					weights.add(2d);
//					weights.add(1d);
//
//					Layer.add(new Neuron((ArrayList<Double>)weights.clone(), "n_"+layer+"_1", new ActivationFunction(ActivFuncEnum.SIGMOID)));
//					
//					weights.clear();
//
//					weights.add(1d);
//					weights.add(2d);
//
//					Layer.add(new Neuron((ArrayList<Double>)weights.clone(), "n_"+layer+"_2", new ActivationFunction(ActivFuncEnum.SIGMOID)));
//					
//					weights.clear();
//	
//					weights.add(1d);
//					weights.add(1d);
//
//				Layer.add(new Neuron((ArrayList<Double>)weights.clone(), "n_"+layer+"_3", new ActivationFunction(ActivFuncEnum.SIGMOID)));
//
//				SingleLayer = new SingleLayer(Layer, "layer["+layer+"]");
//				//SingleLayer.toStringOut();
//				listOfLayers.add(SingleLayer);
//			}
//			
//		}
//		
//		// utworzenie sieci
//		NeuronNetwork net = new NeuronNetwork(listOfLayers, "my neural network");
//		//System.out.println(net);
//
//
//		net.work(inputVector);
//		
//		net.countErrors(outputVector);
//		System.out.println(net);
//		
//		double meanSquareError = net.meanSquareError(inputVector);
//		System.out.println("\r\n[meanSquareError: " + meanSquareError + "]");
//		
//		net.recalculateWeights();
//		System.out.println(net);
//		
//		long iterations = 0L;
//		//for (int i = 0; i < 20000000; i++) {
//		while (meanSquareError > 0.10) {
//			net.work(inputVector);
//			net.countErrors(outputVector);
//			meanSquareError = net.meanSquareError(inputVector);
//			if (iterations % 1000 == 0) {
//				System.out.println("[meanSquareError: " + meanSquareError + "]");
//			}
//			net.recalculateWeights();
//			iterations++;
//		}
//		
//		System.out.println(net);
//		System.out.println("\r\n[meanSquareError: " + meanSquareError + "] after "+iterations+" iterations");
//	}

}
