package net;
import java.util.Arrays;

public class Test {
	
	// when TRUE, additional data is shown when computing weights
	final static boolean DEBUG = false;

	public static void main(String[] args) {
		/*
		 * iloœæ elementów tablicy okreœla iloœæ warstw sieci neuronowej,
		 * wartoœci poszczególnych elementów okreœlaj¹ iloœæ neuronów w ka¿dej warstwie
		 */
		final int[] layersDesc = {4, 2, 4};
		
		final int[] inputVector0 = {1, 0, 0, 0};
		final int[] inputVector1 = {0, 1, 0, 0};
		final int[] inputVector2 = {0, 0, 1, 0};
		final int[] inputVector3 = {0, 0, 0, 1};
		
		final int[] outputVector0 = {1, 0, 0, 0};
		final int[] outputVector1 = {0, 1, 0, 0};
		final int[] outputVector2 = {0, 0, 1, 0};
		final int[] outputVector3 = {0, 0, 0, 1};

		NeuronNetwork net = new NeuronNetwork(layersDesc, "");
		net.work(inputVector0);
		
		net.countErrors(outputVector0);
		
		double meanSquareError = net.meanSquareError(inputVector0);
		
		net.recalculateWeights();
		
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
			System.out.println(iterations + " : " + meanSquareError + " " + " : " + meanSquareError1 + " : " + meanSquareError2 + " : " + meanSquareError3 + " : " + meanSquareError4 + " ");
			iterations++;
		}

		System.out.println("\r\n b³¹d: " + meanSquareError + ", "+iterations+" iteracji");
		
		/*
		 * prezentacja wyników dzia³ania nauczonej sieci
		 */
		System.out.println(Arrays.toString(outputVector0));
		net.work(inputVector0);
		System.out.println(net.output());
		
		System.out.println(Arrays.toString(inputVector1));
		net.work(inputVector1);
		System.out.println(net.output());
		
		System.out.println(Arrays.toString(inputVector2));
		net.work(inputVector2);
		System.out.println(net.output());
		
		System.out.println(Arrays.toString(inputVector3));
		net.work(inputVector3);
		System.out.println(net.output());
		
	}

}
