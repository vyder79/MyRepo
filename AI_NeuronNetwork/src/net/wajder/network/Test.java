package net.wajder.network;
import java.util.Arrays;

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
		
		/*
		 * TODO: do zamiany na wzorce treningowe
		 */
		final double[] inputVector0 = {1d, 0d, 0d, 0d};
		final double[] inputVector1 = {0d, 1d, 0d, 0d};
		final double[] inputVector2 = {0d, 0d, 1d, 0d};
		final double[] inputVector3 = {0d, 0d, 0d, 1d};
		
		final double[] outputVector0 = {1d, 0d, 0d, 0d};
		final double[] outputVector1 = {0d, 1d, 0d, 0d};
		final double[] outputVector2 = {0d, 0d, 1d, 0d};
		final double[] outputVector3 = {0d, 0d, 0d, 1d};
		
		NeuronNetwork net = new NeuronNetwork(layersDesc, "my neural network second edition");
		
		net.work(inputVector0);
		net.countErrors(outputVector0);
		double meanSquareError = net.meanSquareError(inputVector0);
		net.recalculateWeights();
		
		/*
		 * TODO nauczanie do NeuronNetwork przenieœæ
		 * 
		 */
		long iterations = 0L;
		while (meanSquareError > Constants.ERROR_VALUE && iterations < 100000000) {
			net.work(inputVector0);
			net.countErrors(outputVector0);
			double meanSquareError1 = net.meanSquareError(inputVector0);
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
			if (iterations % 1000000 == 0) {
				System.out.println(iterations + " : " + meanSquareError + " " + " : " + meanSquareError1 + " : " + meanSquareError2 + " : " + meanSquareError3 + " : " + meanSquareError4 + " ");
			}
		
			iterations++;
		}
		
		System.out.println(net);
		System.out.println("\r\n[meanSquareError: " + meanSquareError + "] after "+iterations+" iterations");
		
		/*
		 * prezentacja wyników dzia³ania nauczonej sieci
		 */
		System.out.print("results for: " + Arrays.toString(outputVector0));
		net.work(inputVector0);
		System.out.println(net.output());
		
		System.out.print("results for: " + Arrays.toString(inputVector1));
		net.work(inputVector1);
		System.out.println(net.output());
		
		System.out.print("results for: " + Arrays.toString(inputVector2));
		net.work(inputVector2);
		System.out.println(net.output());
		
		System.out.print("results for: " + Arrays.toString(inputVector3));
		net.work(inputVector3);
		System.out.println(net.output());

	}

}
