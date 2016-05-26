package net.wajder.network;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		
		/*
		[40, 51, 230, 552, 371, 134, 350, 525, 219, 57, 596, 240, 6, 142, 1714, 70, 94, 60, 1550, 39] 0.5
    	[40, 149, 436, 553, 577, 150, 556, 515, 425, 54, 1, 96, 212, 161, 2126, 70, 506, 60, 1962, 39] 0
    	[40, 195, 567, 558, 708, 153, 687, 507, 556, 60, 132, 100, 343, 144, 2388, 70, 768, 60, 2224, 39] 1
		 */

		ArrayList<TreningPattern> treningPatterns = new ArrayList<>();
//		treningPatterns.add(new TreningPattern(new double[] {196.0, 219.0, 82.0}, new double[] {1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {0.2d, 1d, 0.1d, 0.02d}, new double[] {0.2d, 1d, 0.1d, 0.1d}));
//		treningPatterns.add(new TreningPattern(new double[] {0.03d, 0.2d, 1d, 0.4d}, new double[] {0.1d, 0.2d, 1d, 0.1d}));
//		treningPatterns.add(new TreningPattern(new double[] {0.1d, 0.7d, 0.2d, 0.97d}, new double[] {0.2d, 0.1d, 0.1d, 1d}));
		
		treningPatterns.add(new TreningPattern(
				new double[] {40d, 51d, 230d, 552d, 371d, 134d, 350d, 525d, 219d, 57d, 596d, 240d, 6d, 142d, 1714d, 70d, 94d, 60d, 1550d, 39d}, 
				new double[] {0.0, 1.0, 0.0})); // 0.5
		treningPatterns.add(new TreningPattern(
				new double[] {40d, 149d, 436d, 553d, 577d, 150d, 556d, 515d, 425d, 54d, 1d, 96d, 212d, 161d, 2126d, 70d, 506d, 60d, 1962d, 39d}, 
				new double[] {1.0, 0.0, 0.0})); // 0.0
		treningPatterns.add(new TreningPattern(
				new double[] {40d, 195d, 567d, 558d, 708d, 153d, 687d, 507d, 556d, 60d, 132d, 100d, 343d, 144d, 2388d, 70d, 768d, 60d, 2224d, 39d}, 
				new double[] {0.0, 0.0, 1.0})); // 1.0
		
//		treningPatterns.add(new TreningPattern(
//				new double[] {0.0, 0.0, 1.0}, 
//				new double[] {0.0}));
//		treningPatterns.add(new TreningPattern(
//				new double[] {1.0, 0.0, 0.0}, 
//				new double[] {0.5}));
//		treningPatterns.add(new TreningPattern(
//				new double[] {0.0, 1.0, 1.0}, 
//				new double[] {1.0}));
		
		NeuronNetwork net = null;
		
		if (Constants.LEARN) {
			/*
			 * iloœæ elementów tablicy okreœla iloœæ warstw sieci neuronowej,
			 * wartoœci poszczególnych elementów okreœlaj¹ iloœæ neuronów w ka¿dej warstwie
			 */
			net = new NeuronNetwork(new int[] {20, 20, 10, 3}, "my neural network 3rd edition");
			net.networkLearning(treningPatterns);
		} else {
			/**
			 * deserializacja sieci z pliku
			 */
			ObjectInputStream we = null;
			try {
				we = new ObjectInputStream(new FileInputStream(Constants.PATH_TO_NETWORK_FILE));
				net = (NeuronNetwork)we.readObject();
				System.out.println("WCZYTUJEMY Z PLIKU !!!");
			} catch (ClassNotFoundException e1) {
				System.out.println("nie znleziono pliku...");
				//e1.printStackTrace();
			} catch (FileNotFoundException e2) {
				System.out.println("nie znleziono pliku...");
				//e2.printStackTrace();
			} catch (IOException e2) {
				System.out.println("problemy we/wy...");
				//e2.printStackTrace();
			}
		}
		
		System.out.println("\r\n********************************\r\nParameters:");
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
		
		// zapisujemy tylko po nauce, po wczytaniu niema sensu :)
		if (Constants.SAVE && Constants.LEARN) {
			/**
			 * serializacja wyuczonej sieci
			 */
			try {
				@SuppressWarnings("resource")
				ObjectOutputStream wy = new ObjectOutputStream(new FileOutputStream(Constants.PATH_TO_NETWORK_FILE));
				wy.writeObject(net);
				@SuppressWarnings("resource")
				ObjectOutputStream wy2 = new ObjectOutputStream(new FileOutputStream(Constants.PATH_TO_NETWORK_FILE_WITH_TIME));
				wy2.writeObject(net);
			} catch (FileNotFoundException e) {
				System.out.println("nie uda³o siê zapisaæ sieci do pliku...");
				//e.printStackTrace();
			} catch (IOException e) {
				System.out.println("problemy we/wy...");
				//e.printStackTrace();
			}
		}
		

	}

}
