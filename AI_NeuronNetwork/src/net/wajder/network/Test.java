package net.wajder.network;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Test class to check Neuron in action.
 * 
 * @author vyder
 *
 */
public class Test {
	
	public static void main(String[] args) {

		ArrayList<TreningPattern> treningPatterns = new ArrayList<>();
		treningPatterns.add(new TreningPattern(new double[] {1d, 0d, 0d, 0d}, new double[] {1d, 0d, 0d, 0d}));
		treningPatterns.add(new TreningPattern(new double[] {0d, 1d, 0d, 0d}, new double[] {0d, 1d, 0d, 0d}));
		treningPatterns.add(new TreningPattern(new double[] {0d, 0d, 1d, 0d}, new double[] {0d, 0d, 1d, 0d}));
		treningPatterns.add(new TreningPattern(new double[] {0d, 0d, 0d, 1d}, new double[] {0d, 0d, 0d, 1d}));
		
		/*
		 * iloœæ elementów tablicy okreœla iloœæ warstw sieci neuronowej,
		 * wartoœci poszczególnych elementów okreœlaj¹ iloœæ neuronów w ka¿dej warstwie
		 */
		NeuronNetwork net = new NeuronNetwork(new int[] {4, 2, 3, 4}, "my neural network 3rd edition");
		net.networkLearning(treningPatterns);
		
		System.out.println("\r\n********************************\r\nParametry:");
		System.out.println("ERROR_VALUE (stop): " + Constants.ERROR_VALUE);
		System.out.println("EPSILON : " + Constants.EPSILON);
		System.out.println("BIAS: " + Constants.BIAS);
		System.out.println("********************************\r\n");
		
		/*
		 * prezentacja wyników dzia³ania nauczonej sieci
		 */		
		for (TreningPattern tp : treningPatterns){
			System.out.print("results for: " + Arrays.toString(tp.getOutputArray()));
			net.work(tp.getInputArray());
			System.out.println(net.output());
		}

	}

}
