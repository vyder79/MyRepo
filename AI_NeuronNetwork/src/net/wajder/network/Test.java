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

		ArrayList<TreningPattern> treningPatterns = new ArrayList<>();
		treningPatterns.add(new TreningPattern(new double[] {1d, 0d, 0d, 0d}, new double[] {1d, 0d, 0d, 0d}));
		treningPatterns.add(new TreningPattern(new double[] {0d, 1d, 0d, 0d}, new double[] {0d, 1d, 0d, 0d}));
		treningPatterns.add(new TreningPattern(new double[] {0d, 0d, 1d, 0d}, new double[] {0d, 0d, 1d, 0d}));
		treningPatterns.add(new TreningPattern(new double[] {0d, 0d, 0d, 1d}, new double[] {0d, 0d, 0d, 1d}));
		
		NeuronNetwork net = null;
		
		if (Constants.LEARN) {
			/*
			 * ilo�� element�w tablicy okre�la ilo�� warstw sieci neuronowej,
			 * warto�ci poszczeg�lnych element�w okre�laj� ilo�� neuron�w w ka�dej warstwie
			 */
			net = new NeuronNetwork(new int[] {4, 2, 4}, "my neural network 3rd edition");
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
		
		System.out.println("\r\n********************************\r\nParametry:");
		System.out.println("ERROR_VALUE (stop): " + Constants.ERROR_VALUE);
		System.out.println("EPSILON : " + Constants.EPSILON);
		System.out.println("BIAS: " + Constants.BIAS);
		System.out.println("********************************\r\n");
		
		/*
		 * prezentacja wynik�w dzia�ania nauczonej sieci
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
				System.out.println("nie uda�o si� zapisa� sieci do pliku...");
				//e.printStackTrace();
			} catch (IOException e) {
				System.out.println("problemy we/wy...");
				//e.printStackTrace();
			}
		}
		

	}

}
