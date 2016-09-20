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
		NeuronNetwork net = null;
		
		if (Constants.LEARN) {
			/*
			 * iloœæ elementów tablicy okreœla iloœæ warstw sieci neuronowej,
			 * wartoœci poszczególnych elementów okreœlaj¹ iloœæ neuronów w ka¿dej warstwie
			 */
			//buildTrenningPatternList(treningPatterns);
			//buildTrenningPatternList2(treningPatterns);
			
			//buildTrenningPatternList100(treningPatterns, 15);
			//buildTrenningPatternList010(treningPatterns, 15);
			//buildTrenningPatternList001(treningPatterns, 15);
			
			PatternSorting ps = new PatternSorting();
			ps.getPatterns(treningPatterns);
			ps.getPatterns700(treningPatterns);
			
			System.out.println("treningPatterns count: " + treningPatterns.size());
			
			ArrayList<TreningPattern> treningPatternsTemp = (ArrayList<TreningPattern>) treningPatterns.clone();
			int i = 250;
			for (TreningPattern tp : treningPatternsTemp) {
				if (tp.getOutputArray()[1] == 1 && i > 0) {
					treningPatterns.remove(tp);
					i--;
				}
			}
			
			System.out.println("treningPatterns count: " + treningPatterns.size());
			
			net = new NeuronNetwork(Constants.STRUCTURE, "my neural network 4rd edition");
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
			} catch (FileNotFoundException e2) {
				System.out.println("nie znleziono pliku...");
			} catch (IOException e2) {
				System.out.println("problemy we/wy...");
			}
		}
		
		System.out.println("\r\n********************************\r\nParameters:");
		System.out.println("STRUCTURE: " + Arrays.toString(Constants.STRUCTURE));
		System.out.println("traininglist: " + treningPatterns.size());
		System.out.println("ERROR_VALUE (stop): " + Constants.ERROR_VALUE);
		System.out.println("EPSILON : " + Constants.EPSILON);
		System.out.println("BIAS: " + Constants.BIAS);
		System.out.println("BETA: " + Constants.BETA);
		System.out.println("********************************\r\n");
		
		/*
		 * prezentacja wyników dzia³ania nauczonej sieci
		 */		
		for (TreningPattern tp : treningPatterns){
			//System.out.print("results for: " + Arrays.toString(tp.getOutputArray()));
			for (double s : tp.getOutputArray()) {
				System.out.print(" " + (int)s);
			}
			net.work(tp.getInputArray());
			System.out.print(net.output());
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
			} catch (IOException e) {
				System.out.println("problemy we/wy...");
			}
		}
		

	}
	
	private static void buildTrenningPatternList(ArrayList<TreningPattern> treningPatterns){

//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 169.0, 120.0, 219.0, 120.0, 269.0, 80.0, 319.0, 160.0, 369.0, 160.0, 419.0, 200.0, 469.0, 200.0, 519.0, 120.0, 2564.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 164.0, 120.0, 214.0, 120.0, 264.0, 80.0, 314.0, 160.0, 364.0, 160.0, 414.0, 200.0, 464.0, 200.0, 514.0, 120.0, 2554.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 159.0, 120.0, 209.0, 120.0, 259.0, 80.0, 309.0, 160.0, 359.0, 160.0, 409.0, 200.0, 459.0, 200.0, 509.0, 120.0, 2544.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 154.0, 120.0, 204.0, 120.0, 254.0, 80.0, 304.0, 160.0, 354.0, 160.0, 404.0, 200.0, 454.0, 200.0, 504.0, 120.0, 2534.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 149.0, 120.0, 199.0, 120.0, 249.0, 80.0, 299.0, 160.0, 349.0, 160.0, 399.0, 200.0, 449.0, 200.0, 499.0, 120.0, 2524.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 144.0, 120.0, 194.0, 120.0, 244.0, 80.0, 294.0, 160.0, 344.0, 160.0, 394.0, 200.0, 444.0, 200.0, 494.0, 120.0, 2514.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 139.0, 120.0, 189.0, 120.0, 239.0, 80.0, 289.0, 160.0, 339.0, 160.0, 389.0, 200.0, 439.0, 200.0, 489.0, 120.0, 2504.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 134.0, 120.0, 184.0, 120.0, 234.0, 80.0, 284.0, 160.0, 334.0, 160.0, 384.0, 200.0, 434.0, 200.0, 484.0, 120.0, 2494.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 129.0, 120.0, 179.0, 120.0, 229.0, 80.0, 279.0, 160.0, 329.0, 160.0, 379.0, 200.0, 429.0, 200.0, 479.0, 120.0, 2484.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 124.0, 120.0, 174.0, 120.0, 224.0, 80.0, 274.0, 160.0, 324.0, 160.0, 374.0, 200.0, 424.0, 200.0, 474.0, 120.0, 2474.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 119.0, 120.0, 169.0, 120.0, 219.0, 80.0, 269.0, 160.0, 319.0, 160.0, 369.0, 200.0, 419.0, 200.0, 469.0, 120.0, 2464.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 114.0, 120.0, 164.0, 120.0, 214.0, 80.0, 264.0, 160.0, 314.0, 160.0, 364.0, 200.0, 414.0, 200.0, 464.0, 120.0, 2454.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 109.0, 120.0, 159.0, 120.0, 209.0, 80.0, 259.0, 160.0, 309.0, 160.0, 359.0, 200.0, 409.0, 200.0, 459.0, 120.0, 2444.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 104.0, 120.0, 154.0, 120.0, 204.0, 80.0, 254.0, 160.0, 304.0, 160.0, 354.0, 200.0, 404.0, 200.0, 454.0, 120.0, 2434.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 99.0, 120.0, 149.0, 120.0, 199.0, 80.0, 249.0, 160.0, 299.0, 160.0, 349.0, 200.0, 399.0, 200.0, 449.0, 120.0, 2424.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 94.0, 120.0, 144.0, 120.0, 194.0, 80.0, 244.0, 160.0, 294.0, 160.0, 344.0, 200.0, 394.0, 200.0, 444.0, 120.0, 2414.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 89.0, 120.0, 139.0, 120.0, 189.0, 80.0, 239.0, 160.0, 289.0, 160.0, 339.0, 200.0, 389.0, 200.0, 439.0, 120.0, 2404.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 52.0, 84.0, 120.0, 134.0, 120.0, 184.0, 80.0, 234.0, 160.0, 284.0, 160.0, 334.0, 200.0, 384.0, 200.0, 434.0, 120.0, 2394.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 42.0, 79.0, 120.0, 129.0, 120.0, 179.0, 80.0, 229.0, 160.0, 279.0, 160.0, 329.0, 200.0, 379.0, 200.0, 429.0, 120.0, 2384.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 32.0, 74.0, 120.0, 124.0, 120.0, 174.0, 80.0, 224.0, 160.0, 274.0, 160.0, 324.0, 200.0, 374.0, 200.0, 424.0, 120.0, 2374.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 69.0, 120.0, 119.0, 120.0, 169.0, 80.0, 219.0, 160.0, 269.0, 160.0, 319.0, 200.0, 369.0, 200.0, 419.0, 120.0, 2364.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 64.0, 120.0, 114.0, 120.0, 164.0, 80.0, 214.0, 160.0, 264.0, 160.0, 314.0, 200.0, 364.0, 200.0, 414.0, 120.0, 2354.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 59.0, 120.0, 109.0, 120.0, 159.0, 80.0, 209.0, 160.0, 259.0, 160.0, 309.0, 200.0, 359.0, 200.0, 409.0, 120.0, 2344.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 54.0, 120.0, 104.0, 120.0, 154.0, 80.0, 204.0, 160.0, 254.0, 160.0, 304.0, 200.0, 354.0, 200.0, 404.0, 120.0, 2334.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 49.0, 120.0, 99.0, 120.0, 149.0, 80.0, 199.0, 160.0, 249.0, 160.0, 299.0, 200.0, 349.0, 200.0, 399.0, 120.0, 2324.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 44.0, 120.0, 94.0, 120.0, 144.0, 80.0, 194.0, 160.0, 244.0, 160.0, 294.0, 200.0, 344.0, 200.0, 394.0, 120.0, 2314.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 39.0, 120.0, 89.0, 120.0, 139.0, 80.0, 189.0, 160.0, 239.0, 160.0, 289.0, 200.0, 339.0, 200.0, 389.0, 120.0, 2304.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 34.0, 120.0, 84.0, 120.0, 134.0, 80.0, 184.0, 160.0, 234.0, 160.0, 284.0, 200.0, 334.0, 200.0, 384.0, 120.0, 2294.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 29.0, 120.0, 79.0, 120.0, 129.0, 80.0, 179.0, 160.0, 229.0, 160.0, 279.0, 200.0, 329.0, 200.0, 379.0, 120.0, 2284.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 24.0, 120.0, 74.0, 120.0, 124.0, 80.0, 174.0, 160.0, 224.0, 160.0, 274.0, 200.0, 324.0, 200.0, 374.0, 120.0, 2274.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 19.0, 120.0, 69.0, 120.0, 119.0, 80.0, 169.0, 160.0, 219.0, 160.0, 269.0, 200.0, 319.0, 200.0, 369.0, 120.0, 2264.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 14.0, 120.0, 64.0, 120.0, 114.0, 80.0, 164.0, 160.0, 214.0, 160.0, 264.0, 200.0, 314.0, 200.0, 364.0, 120.0, 2254.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 9.0, 120.0, 59.0, 120.0, 109.0, 80.0, 159.0, 160.0, 209.0, 160.0, 259.0, 200.0, 309.0, 200.0, 359.0, 120.0, 2244.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 4.0, 120.0, 54.0, 120.0, 104.0, 80.0, 154.0, 160.0, 204.0, 160.0, 254.0, 200.0, 304.0, 200.0, 354.0, 120.0, 2234.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, -1.0, 120.0, 49.0, 120.0, 99.0, 80.0, 149.0, 160.0, 199.0, 160.0, 249.0, 200.0, 299.0, 200.0, 349.0, 120.0, 2224.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 395.0, 80.0, 44.0, 120.0, 94.0, 80.0, 144.0, 160.0, 194.0, 160.0, 244.0, 200.0, 294.0, 200.0, 344.0, 120.0, 2214.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 390.0, 80.0, 39.0, 120.0, 89.0, 80.0, 139.0, 160.0, 189.0, 160.0, 239.0, 200.0, 289.0, 200.0, 339.0, 120.0, 2204.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 385.0, 80.0, 34.0, 120.0, 84.0, 80.0, 134.0, 160.0, 184.0, 160.0, 234.0, 200.0, 284.0, 200.0, 334.0, 120.0, 2194.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 380.0, 80.0, 29.0, 120.0, 79.0, 80.0, 129.0, 160.0, 179.0, 160.0, 229.0, 200.0, 279.0, 200.0, 329.0, 120.0, 2184.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 375.0, 80.0, 24.0, 120.0, 74.0, 80.0, 124.0, 160.0, 174.0, 160.0, 224.0, 200.0, 274.0, 200.0, 324.0, 120.0, 2174.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 370.0, 80.0, 19.0, 120.0, 69.0, 80.0, 119.0, 160.0, 169.0, 160.0, 219.0, 200.0, 269.0, 200.0, 319.0, 120.0, 2164.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 365.0, 80.0, 14.0, 120.0, 64.0, 80.0, 114.0, 160.0, 164.0, 160.0, 214.0, 200.0, 264.0, 200.0, 314.0, 120.0, 2154.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 360.0, 80.0, 9.0, 120.0, 59.0, 80.0, 109.0, 160.0, 159.0, 160.0, 209.0, 200.0, 259.0, 200.0, 309.0, 120.0, 2144.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 355.0, 80.0, 4.0, 120.0, 54.0, 80.0, 104.0, 160.0, 154.0, 160.0, 204.0, 200.0, 254.0, 200.0, 304.0, 120.0, 2134.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 350.0, 80.0, -1.0, 120.0, 49.0, 80.0, 99.0, 160.0, 149.0, 160.0, 199.0, 200.0, 249.0, 200.0, 299.0, 120.0, 2124.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 345.0, 80.0, 395.0, 240.0, 44.0, 80.0, 94.0, 160.0, 144.0, 160.0, 194.0, 200.0, 244.0, 200.0, 294.0, 120.0, 2114.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 340.0, 80.0, 390.0, 240.0, 39.0, 80.0, 89.0, 160.0, 139.0, 160.0, 189.0, 200.0, 239.0, 200.0, 289.0, 120.0, 2104.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 335.0, 80.0, 385.0, 240.0, 34.0, 80.0, 84.0, 160.0, 134.0, 160.0, 184.0, 200.0, 234.0, 200.0, 284.0, 120.0, 2094.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 330.0, 80.0, 380.0, 240.0, 29.0, 80.0, 79.0, 160.0, 129.0, 160.0, 179.0, 200.0, 229.0, 200.0, 279.0, 120.0, 2084.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 325.0, 80.0, 375.0, 240.0, 24.0, 80.0, 74.0, 160.0, 124.0, 160.0, 174.0, 200.0, 224.0, 200.0, 274.0, 120.0, 2074.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 320.0, 80.0, 370.0, 240.0, 19.0, 80.0, 69.0, 160.0, 119.0, 160.0, 169.0, 200.0, 219.0, 200.0, 269.0, 120.0, 2064.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 315.0, 80.0, 365.0, 240.0, 14.0, 80.0, 64.0, 160.0, 114.0, 160.0, 164.0, 200.0, 214.0, 200.0, 264.0, 120.0, 2054.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 310.0, 80.0, 360.0, 240.0, 9.0, 80.0, 59.0, 160.0, 109.0, 160.0, 159.0, 200.0, 209.0, 200.0, 259.0, 120.0, 2044.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 305.0, 80.0, 355.0, 240.0, 4.0, 80.0, 54.0, 160.0, 104.0, 160.0, 154.0, 200.0, 204.0, 200.0, 254.0, 120.0, 2034.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 300.0, 80.0, 350.0, 240.0, -1.0, 80.0, 49.0, 160.0, 99.0, 160.0, 149.0, 200.0, 199.0, 200.0, 249.0, 120.0, 2024.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 295.0, 80.0, 345.0, 240.0, 395.0, 200.0, 44.0, 160.0, 94.0, 160.0, 144.0, 200.0, 194.0, 200.0, 244.0, 120.0, 2014.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 290.0, 80.0, 340.0, 240.0, 390.0, 200.0, 39.0, 160.0, 89.0, 160.0, 139.0, 200.0, 189.0, 200.0, 239.0, 120.0, 2004.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 285.0, 80.0, 335.0, 240.0, 385.0, 200.0, 34.0, 160.0, 84.0, 160.0, 134.0, 200.0, 184.0, 200.0, 234.0, 120.0, 1994.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 280.0, 80.0, 330.0, 240.0, 380.0, 200.0, 29.0, 160.0, 79.0, 160.0, 129.0, 200.0, 179.0, 200.0, 229.0, 120.0, 1984.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 275.0, 80.0, 325.0, 240.0, 375.0, 200.0, 24.0, 160.0, 74.0, 160.0, 124.0, 200.0, 174.0, 200.0, 224.0, 120.0, 1974.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 270.0, 80.0, 320.0, 240.0, 370.0, 200.0, 19.0, 160.0, 69.0, 160.0, 119.0, 200.0, 169.0, 200.0, 219.0, 120.0, 1964.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 265.0, 80.0, 315.0, 240.0, 365.0, 200.0, 14.0, 160.0, 64.0, 160.0, 114.0, 200.0, 164.0, 200.0, 214.0, 120.0, 1954.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 260.0, 80.0, 310.0, 240.0, 360.0, 200.0, 9.0, 160.0, 59.0, 160.0, 109.0, 200.0, 159.0, 200.0, 209.0, 120.0, 1944.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 38.0, 255.0, 80.0, 305.0, 240.0, 355.0, 200.0, 4.0, 160.0, 54.0, 160.0, 104.0, 200.0, 154.0, 200.0, 204.0, 120.0, 1934.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 46.0, 250.0, 80.0, 300.0, 240.0, 350.0, 200.0, -1.0, 160.0, 49.0, 160.0, 99.0, 200.0, 149.0, 200.0, 199.0, 120.0, 1924.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 46.0, 245.0, 80.0, 295.0, 240.0, 345.0, 200.0, 395.0, 80.0, 44.0, 160.0, 94.0, 200.0, 144.0, 200.0, 194.0, 120.0, 1914.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 46.0, 240.0, 80.0, 290.0, 240.0, 340.0, 200.0, 390.0, 80.0, 39.0, 160.0, 89.0, 200.0, 139.0, 200.0, 189.0, 120.0, 1904.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 46.0, 235.0, 80.0, 285.0, 240.0, 335.0, 200.0, 385.0, 80.0, 34.0, 160.0, 84.0, 200.0, 134.0, 200.0, 184.0, 120.0, 1894.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 48.0, 230.0, 80.0, 280.0, 240.0, 330.0, 200.0, 380.0, 80.0, 29.0, 160.0, 79.0, 200.0, 129.0, 200.0, 179.0, 120.0, 1884.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 58.0, 225.0, 80.0, 275.0, 240.0, 325.0, 200.0, 375.0, 80.0, 24.0, 160.0, 74.0, 200.0, 124.0, 200.0, 174.0, 120.0, 1874.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 64.0, 220.0, 80.0, 270.0, 240.0, 320.0, 200.0, 370.0, 80.0, 19.0, 160.0, 69.0, 200.0, 119.0, 200.0, 169.0, 120.0, 1864.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 64.0, 215.0, 80.0, 265.0, 240.0, 315.0, 200.0, 365.0, 80.0, 14.0, 160.0, 64.0, 200.0, 114.0, 200.0, 164.0, 120.0, 1854.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 74.0, 210.0, 80.0, 260.0, 240.0, 310.0, 200.0, 360.0, 80.0, 9.0, 160.0, 59.0, 200.0, 109.0, 200.0, 159.0, 120.0, 1844.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 78.0, 205.0, 80.0, 255.0, 240.0, 305.0, 200.0, 355.0, 80.0, 4.0, 160.0, 54.0, 200.0, 104.0, 200.0, 154.0, 120.0, 1834.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 78.0, 200.0, 80.0, 250.0, 240.0, 300.0, 200.0, 350.0, 80.0, -1.0, 160.0, 49.0, 200.0, 99.0, 200.0, 149.0, 120.0, 1824.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 78.0, 195.0, 80.0, 245.0, 240.0, 295.0, 200.0, 345.0, 80.0, 395.0, 200.0, 44.0, 200.0, 94.0, 200.0, 144.0, 120.0, 1814.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 78.0, 190.0, 80.0, 240.0, 240.0, 290.0, 200.0, 340.0, 80.0, 390.0, 200.0, 39.0, 200.0, 89.0, 200.0, 139.0, 120.0, 1804.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 78.0, 185.0, 80.0, 235.0, 240.0, 285.0, 200.0, 335.0, 80.0, 385.0, 200.0, 34.0, 200.0, 84.0, 200.0, 134.0, 120.0, 1794.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 78.0, 180.0, 80.0, 230.0, 240.0, 280.0, 200.0, 330.0, 80.0, 380.0, 200.0, 29.0, 200.0, 79.0, 200.0, 129.0, 120.0, 1784.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 175.0, 80.0, 225.0, 240.0, 275.0, 200.0, 325.0, 80.0, 375.0, 200.0, 24.0, 200.0, 74.0, 200.0, 124.0, 120.0, 1774.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 170.0, 80.0, 220.0, 240.0, 270.0, 200.0, 320.0, 80.0, 370.0, 200.0, 19.0, 200.0, 69.0, 200.0, 119.0, 120.0, 1764.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 56.0, 165.0, 80.0, 215.0, 240.0, 265.0, 200.0, 315.0, 80.0, 365.0, 200.0, 14.0, 200.0, 64.0, 200.0, 114.0, 120.0, 1754.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 56.0, 160.0, 80.0, 210.0, 240.0, 260.0, 200.0, 310.0, 80.0, 360.0, 200.0, 9.0, 200.0, 59.0, 200.0, 109.0, 120.0, 1744.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 48.0, 155.0, 80.0, 205.0, 240.0, 255.0, 200.0, 305.0, 80.0, 355.0, 200.0, 4.0, 200.0, 54.0, 200.0, 104.0, 120.0, 1734.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 48.0, 150.0, 80.0, 200.0, 240.0, 250.0, 200.0, 300.0, 80.0, 350.0, 200.0, -1.0, 200.0, 49.0, 200.0, 99.0, 120.0, 1724.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 44.0, 145.0, 80.0, 195.0, 240.0, 245.0, 200.0, 295.0, 80.0, 345.0, 200.0, 395.0, 160.0, 44.0, 200.0, 94.0, 120.0, 1714.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 44.0, 140.0, 80.0, 190.0, 240.0, 240.0, 200.0, 290.0, 80.0, 340.0, 200.0, 390.0, 160.0, 39.0, 200.0, 89.0, 120.0, 1704.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 40.0, 135.0, 80.0, 185.0, 240.0, 235.0, 200.0, 285.0, 80.0, 335.0, 200.0, 385.0, 160.0, 34.0, 200.0, 84.0, 120.0, 1694.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 130.0, 80.0, 180.0, 240.0, 230.0, 200.0, 280.0, 80.0, 330.0, 200.0, 380.0, 160.0, 29.0, 200.0, 79.0, 120.0, 1684.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 125.0, 80.0, 175.0, 240.0, 225.0, 200.0, 275.0, 80.0, 325.0, 200.0, 375.0, 160.0, 24.0, 200.0, 74.0, 120.0, 1674.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 120.0, 80.0, 170.0, 240.0, 220.0, 200.0, 270.0, 80.0, 320.0, 200.0, 370.0, 160.0, 19.0, 200.0, 69.0, 120.0, 1664.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 115.0, 80.0, 165.0, 240.0, 215.0, 200.0, 265.0, 80.0, 315.0, 200.0, 365.0, 160.0, 14.0, 200.0, 64.0, 120.0, 1654.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 110.0, 80.0, 160.0, 240.0, 210.0, 200.0, 260.0, 80.0, 310.0, 200.0, 360.0, 160.0, 9.0, 200.0, 59.0, 120.0, 1644.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 105.0, 80.0, 155.0, 240.0, 205.0, 200.0, 255.0, 80.0, 305.0, 200.0, 355.0, 160.0, 4.0, 200.0, 54.0, 120.0, 1634.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 100.0, 80.0, 150.0, 240.0, 200.0, 200.0, 250.0, 80.0, 300.0, 200.0, 350.0, 160.0, -1.0, 200.0, 49.0, 120.0, 1624.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 95.0, 80.0, 145.0, 240.0, 195.0, 200.0, 245.0, 80.0, 295.0, 200.0, 345.0, 160.0, 395.0, 80.0, 44.0, 120.0, 1614.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 90.0, 80.0, 140.0, 240.0, 190.0, 200.0, 240.0, 80.0, 290.0, 200.0, 340.0, 160.0, 390.0, 80.0, 39.0, 120.0, 1604.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 85.0, 80.0, 135.0, 240.0, 185.0, 200.0, 235.0, 80.0, 285.0, 200.0, 335.0, 160.0, 385.0, 80.0, 34.0, 120.0, 1594.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 80.0, 80.0, 130.0, 240.0, 180.0, 200.0, 230.0, 80.0, 280.0, 200.0, 330.0, 160.0, 380.0, 80.0, 29.0, 120.0, 1584.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 75.0, 80.0, 125.0, 240.0, 175.0, 200.0, 225.0, 80.0, 275.0, 200.0, 325.0, 160.0, 375.0, 80.0, 24.0, 120.0, 1574.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 70.0, 80.0, 120.0, 240.0, 170.0, 200.0, 220.0, 80.0, 270.0, 200.0, 320.0, 160.0, 370.0, 80.0, 19.0, 120.0, 1564.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 65.0, 80.0, 115.0, 240.0, 165.0, 200.0, 215.0, 80.0, 265.0, 200.0, 315.0, 160.0, 365.0, 80.0, 14.0, 120.0, 1554.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 60.0, 80.0, 110.0, 240.0, 160.0, 200.0, 210.0, 80.0, 260.0, 200.0, 310.0, 160.0, 360.0, 80.0, 9.0, 120.0, 1544.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 55.0, 80.0, 105.0, 240.0, 155.0, 200.0, 205.0, 80.0, 255.0, 200.0, 305.0, 160.0, 355.0, 80.0, 4.0, 120.0, 1534.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 50.0, 80.0, 100.0, 240.0, 150.0, 200.0, 200.0, 80.0, 250.0, 200.0, 300.0, 160.0, 350.0, 80.0, -1.0, 120.0, 1524.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 45.0, 80.0, 95.0, 240.0, 145.0, 200.0, 195.0, 80.0, 245.0, 200.0, 295.0, 160.0, 345.0, 80.0, 395.0, 40.0, 1514.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 40.0, 80.0, 90.0, 240.0, 140.0, 200.0, 190.0, 80.0, 240.0, 200.0, 290.0, 160.0, 340.0, 80.0, 390.0, 40.0, 1504.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 35.0, 80.0, 85.0, 240.0, 135.0, 200.0, 185.0, 80.0, 235.0, 200.0, 285.0, 160.0, 335.0, 80.0, 385.0, 40.0, 1494.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 30.0, 80.0, 80.0, 240.0, 130.0, 200.0, 180.0, 80.0, 230.0, 200.0, 280.0, 160.0, 330.0, 80.0, 380.0, 40.0, 1484.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 25.0, 80.0, 75.0, 240.0, 125.0, 200.0, 175.0, 80.0, 225.0, 200.0, 275.0, 160.0, 325.0, 80.0, 375.0, 40.0, 1474.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 20.0, 80.0, 70.0, 240.0, 120.0, 200.0, 170.0, 80.0, 220.0, 200.0, 270.0, 160.0, 320.0, 80.0, 370.0, 40.0, 1464.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 15.0, 80.0, 65.0, 240.0, 115.0, 200.0, 165.0, 80.0, 215.0, 200.0, 265.0, 160.0, 315.0, 80.0, 365.0, 40.0, 1454.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 10.0, 80.0, 60.0, 240.0, 110.0, 200.0, 160.0, 80.0, 210.0, 200.0, 260.0, 160.0, 310.0, 80.0, 360.0, 40.0, 1444.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 5.0, 80.0, 55.0, 240.0, 105.0, 200.0, 155.0, 80.0, 205.0, 200.0, 255.0, 160.0, 305.0, 80.0, 355.0, 40.0, 1434.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 0.0, 80.0, 50.0, 240.0, 100.0, 200.0, 150.0, 80.0, 200.0, 200.0, 250.0, 160.0, 300.0, 80.0, 350.0, 40.0, 1424.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 36.0, 396.0, 160.0, 45.0, 240.0, 95.0, 200.0, 145.0, 80.0, 195.0, 200.0, 245.0, 160.0, 295.0, 80.0, 345.0, 40.0, 1414.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 46.0, 391.0, 160.0, 40.0, 240.0, 90.0, 200.0, 140.0, 80.0, 190.0, 200.0, 240.0, 160.0, 290.0, 80.0, 340.0, 40.0, 1404.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 56.0, 386.0, 160.0, 35.0, 240.0, 85.0, 200.0, 135.0, 80.0, 185.0, 200.0, 235.0, 160.0, 285.0, 80.0, 335.0, 40.0, 1394.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 66.0, 381.0, 160.0, 30.0, 240.0, 80.0, 200.0, 130.0, 80.0, 180.0, 200.0, 230.0, 160.0, 280.0, 80.0, 330.0, 40.0, 1384.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 76.0, 376.0, 160.0, 25.0, 240.0, 75.0, 200.0, 125.0, 80.0, 175.0, 200.0, 225.0, 160.0, 275.0, 80.0, 325.0, 40.0, 1374.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 86.0, 371.0, 160.0, 20.0, 240.0, 70.0, 200.0, 120.0, 80.0, 170.0, 200.0, 220.0, 160.0, 270.0, 80.0, 320.0, 40.0, 1364.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 96.0, 366.0, 160.0, 15.0, 240.0, 65.0, 200.0, 115.0, 80.0, 165.0, 200.0, 215.0, 160.0, 265.0, 80.0, 315.0, 40.0, 1354.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 106.0, 361.0, 160.0, 10.0, 240.0, 60.0, 200.0, 110.0, 80.0, 160.0, 200.0, 210.0, 160.0, 260.0, 80.0, 310.0, 40.0, 1344.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 116.0, 356.0, 160.0, 5.0, 240.0, 55.0, 200.0, 105.0, 80.0, 155.0, 200.0, 205.0, 160.0, 255.0, 80.0, 305.0, 40.0, 1334.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 351.0, 160.0, 0.0, 240.0, 50.0, 200.0, 100.0, 80.0, 150.0, 200.0, 200.0, 160.0, 250.0, 80.0, 300.0, 40.0, 1324.0, 40.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 346.0, 160.0, 396.0, 40.0, 45.0, 200.0, 95.0, 80.0, 145.0, 200.0, 195.0, 160.0, 245.0, 80.0, 295.0, 40.0, 1314.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 341.0, 160.0, 391.0, 40.0, 40.0, 200.0, 90.0, 80.0, 140.0, 200.0, 190.0, 160.0, 240.0, 80.0, 290.0, 40.0, 1304.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 336.0, 160.0, 386.0, 40.0, 35.0, 200.0, 85.0, 80.0, 135.0, 200.0, 185.0, 160.0, 235.0, 80.0, 285.0, 40.0, 1294.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 331.0, 160.0, 381.0, 40.0, 30.0, 200.0, 80.0, 80.0, 130.0, 200.0, 180.0, 160.0, 230.0, 80.0, 280.0, 40.0, 1284.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 326.0, 160.0, 376.0, 40.0, 25.0, 200.0, 75.0, 80.0, 125.0, 200.0, 175.0, 160.0, 225.0, 80.0, 275.0, 40.0, 1274.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 321.0, 160.0, 371.0, 40.0, 20.0, 200.0, 70.0, 80.0, 120.0, 200.0, 170.0, 160.0, 220.0, 80.0, 270.0, 40.0, 1264.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 316.0, 160.0, 366.0, 40.0, 15.0, 200.0, 65.0, 80.0, 115.0, 200.0, 165.0, 160.0, 215.0, 80.0, 265.0, 40.0, 1254.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 311.0, 160.0, 361.0, 40.0, 10.0, 200.0, 60.0, 80.0, 110.0, 200.0, 160.0, 160.0, 210.0, 80.0, 260.0, 40.0, 1244.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 306.0, 160.0, 356.0, 40.0, 5.0, 200.0, 55.0, 80.0, 105.0, 200.0, 155.0, 160.0, 205.0, 80.0, 255.0, 40.0, 1234.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 301.0, 160.0, 351.0, 40.0, 0.0, 200.0, 50.0, 80.0, 100.0, 200.0, 150.0, 160.0, 200.0, 80.0, 250.0, 40.0, 1224.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 296.0, 160.0, 346.0, 40.0, 396.0, 80.0, 45.0, 80.0, 95.0, 200.0, 145.0, 160.0, 195.0, 80.0, 245.0, 40.0, 1214.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 291.0, 160.0, 341.0, 40.0, 391.0, 80.0, 40.0, 80.0, 90.0, 200.0, 140.0, 160.0, 190.0, 80.0, 240.0, 40.0, 1204.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 286.0, 160.0, 336.0, 40.0, 386.0, 80.0, 35.0, 80.0, 85.0, 200.0, 135.0, 160.0, 185.0, 80.0, 235.0, 40.0, 1194.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 281.0, 160.0, 331.0, 40.0, 381.0, 80.0, 30.0, 80.0, 80.0, 200.0, 130.0, 160.0, 180.0, 80.0, 230.0, 40.0, 1184.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 118.0, 276.0, 160.0, 326.0, 40.0, 376.0, 80.0, 25.0, 80.0, 75.0, 200.0, 125.0, 160.0, 175.0, 80.0, 225.0, 40.0, 1174.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 271.0, 160.0, 321.0, 40.0, 371.0, 80.0, 20.0, 80.0, 70.0, 200.0, 120.0, 160.0, 170.0, 80.0, 220.0, 40.0, 1164.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 266.0, 160.0, 316.0, 40.0, 366.0, 80.0, 15.0, 80.0, 65.0, 200.0, 115.0, 160.0, 165.0, 80.0, 215.0, 40.0, 1154.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 261.0, 160.0, 311.0, 40.0, 361.0, 80.0, 10.0, 80.0, 60.0, 200.0, 110.0, 160.0, 160.0, 80.0, 210.0, 40.0, 1144.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 256.0, 160.0, 306.0, 40.0, 356.0, 80.0, 5.0, 80.0, 55.0, 200.0, 105.0, 160.0, 155.0, 80.0, 205.0, 40.0, 1134.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 251.0, 160.0, 301.0, 40.0, 351.0, 80.0, 0.0, 80.0, 50.0, 200.0, 100.0, 160.0, 150.0, 80.0, 200.0, 40.0, 1124.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 246.0, 160.0, 296.0, 40.0, 346.0, 80.0, 396.0, 240.0, 45.0, 200.0, 95.0, 160.0, 145.0, 80.0, 195.0, 40.0, 1114.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 241.0, 160.0, 291.0, 40.0, 341.0, 80.0, 391.0, 240.0, 40.0, 200.0, 90.0, 160.0, 140.0, 80.0, 190.0, 40.0, 1104.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 236.0, 160.0, 286.0, 40.0, 336.0, 80.0, 386.0, 240.0, 35.0, 200.0, 85.0, 160.0, 135.0, 80.0, 185.0, 40.0, 1094.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 231.0, 160.0, 281.0, 40.0, 331.0, 80.0, 381.0, 240.0, 30.0, 200.0, 80.0, 160.0, 130.0, 80.0, 180.0, 40.0, 1084.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 226.0, 160.0, 276.0, 40.0, 326.0, 80.0, 376.0, 240.0, 25.0, 200.0, 75.0, 160.0, 125.0, 80.0, 175.0, 40.0, 1074.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 221.0, 160.0, 271.0, 40.0, 321.0, 80.0, 371.0, 240.0, 20.0, 200.0, 70.0, 160.0, 120.0, 80.0, 170.0, 40.0, 1064.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 216.0, 160.0, 266.0, 40.0, 316.0, 80.0, 366.0, 240.0, 15.0, 200.0, 65.0, 160.0, 115.0, 80.0, 165.0, 40.0, 1054.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 211.0, 160.0, 261.0, 40.0, 311.0, 80.0, 361.0, 240.0, 10.0, 200.0, 60.0, 160.0, 110.0, 80.0, 160.0, 40.0, 1044.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 206.0, 160.0, 256.0, 40.0, 306.0, 80.0, 356.0, 240.0, 5.0, 200.0, 55.0, 160.0, 105.0, 80.0, 155.0, 40.0, 1034.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 201.0, 160.0, 251.0, 40.0, 301.0, 80.0, 351.0, 240.0, 0.0, 200.0, 50.0, 160.0, 100.0, 80.0, 150.0, 40.0, 1024.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 196.0, 160.0, 246.0, 40.0, 296.0, 80.0, 346.0, 240.0, 396.0, 160.0, 45.0, 160.0, 95.0, 80.0, 145.0, 40.0, 1014.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 191.0, 160.0, 241.0, 40.0, 291.0, 80.0, 341.0, 240.0, 391.0, 160.0, 40.0, 160.0, 90.0, 80.0, 140.0, 40.0, 1004.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 186.0, 160.0, 236.0, 40.0, 286.0, 80.0, 336.0, 240.0, 386.0, 160.0, 35.0, 160.0, 85.0, 80.0, 135.0, 40.0, 994.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 181.0, 160.0, 231.0, 40.0, 281.0, 80.0, 331.0, 240.0, 381.0, 160.0, 30.0, 160.0, 80.0, 80.0, 130.0, 40.0, 984.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 176.0, 160.0, 226.0, 40.0, 276.0, 80.0, 326.0, 240.0, 376.0, 160.0, 25.0, 160.0, 75.0, 80.0, 125.0, 40.0, 974.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 171.0, 160.0, 221.0, 40.0, 271.0, 80.0, 321.0, 240.0, 371.0, 160.0, 20.0, 160.0, 70.0, 80.0, 120.0, 40.0, 964.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 166.0, 160.0, 216.0, 40.0, 266.0, 80.0, 316.0, 240.0, 366.0, 160.0, 15.0, 160.0, 65.0, 80.0, 115.0, 40.0, 954.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 161.0, 160.0, 211.0, 40.0, 261.0, 80.0, 311.0, 240.0, 361.0, 160.0, 10.0, 160.0, 60.0, 80.0, 110.0, 40.0, 944.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 156.0, 160.0, 206.0, 40.0, 256.0, 80.0, 306.0, 240.0, 356.0, 160.0, 5.0, 160.0, 55.0, 80.0, 105.0, 40.0, 934.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 151.0, 160.0, 201.0, 40.0, 251.0, 80.0, 301.0, 240.0, 351.0, 160.0, 0.0, 160.0, 50.0, 80.0, 100.0, 40.0, 924.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 146.0, 160.0, 196.0, 40.0, 246.0, 80.0, 296.0, 240.0, 346.0, 160.0, 396.0, 160.0, 45.0, 80.0, 95.0, 40.0, 914.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 141.0, 160.0, 191.0, 40.0, 241.0, 80.0, 291.0, 240.0, 341.0, 160.0, 391.0, 160.0, 40.0, 80.0, 90.0, 40.0, 904.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 136.0, 160.0, 186.0, 40.0, 236.0, 80.0, 286.0, 240.0, 336.0, 160.0, 386.0, 160.0, 35.0, 80.0, 85.0, 40.0, 894.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 131.0, 160.0, 181.0, 40.0, 231.0, 80.0, 281.0, 240.0, 331.0, 160.0, 381.0, 160.0, 30.0, 80.0, 80.0, 40.0, 884.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 126.0, 160.0, 176.0, 40.0, 226.0, 80.0, 276.0, 240.0, 326.0, 160.0, 376.0, 160.0, 25.0, 80.0, 75.0, 40.0, 874.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 121.0, 160.0, 171.0, 40.0, 221.0, 80.0, 271.0, 240.0, 321.0, 160.0, 371.0, 160.0, 20.0, 80.0, 70.0, 40.0, 864.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 116.0, 160.0, 166.0, 40.0, 216.0, 80.0, 266.0, 240.0, 316.0, 160.0, 366.0, 160.0, 15.0, 80.0, 65.0, 40.0, 854.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 111.0, 160.0, 161.0, 40.0, 211.0, 80.0, 261.0, 240.0, 311.0, 160.0, 361.0, 160.0, 10.0, 80.0, 60.0, 40.0, 844.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 106.0, 160.0, 156.0, 40.0, 206.0, 80.0, 256.0, 240.0, 306.0, 160.0, 356.0, 160.0, 5.0, 80.0, 55.0, 40.0, 834.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 101.0, 160.0, 151.0, 40.0, 201.0, 80.0, 251.0, 240.0, 301.0, 160.0, 351.0, 160.0, 0.0, 80.0, 50.0, 40.0, 824.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 96.0, 160.0, 146.0, 40.0, 196.0, 80.0, 246.0, 240.0, 296.0, 160.0, 346.0, 160.0, 396.0, 160.0, 45.0, 40.0, 814.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 91.0, 160.0, 141.0, 40.0, 191.0, 80.0, 241.0, 240.0, 291.0, 160.0, 341.0, 160.0, 391.0, 160.0, 40.0, 40.0, 804.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 86.0, 160.0, 136.0, 40.0, 186.0, 80.0, 236.0, 240.0, 286.0, 160.0, 336.0, 160.0, 386.0, 160.0, 35.0, 40.0, 794.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 81.0, 160.0, 131.0, 40.0, 181.0, 80.0, 231.0, 240.0, 281.0, 160.0, 331.0, 160.0, 381.0, 160.0, 30.0, 40.0, 784.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 76.0, 160.0, 126.0, 40.0, 176.0, 80.0, 226.0, 240.0, 276.0, 160.0, 326.0, 160.0, 376.0, 160.0, 25.0, 40.0, 774.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 71.0, 160.0, 121.0, 40.0, 171.0, 80.0, 221.0, 240.0, 271.0, 160.0, 321.0, 160.0, 371.0, 160.0, 20.0, 40.0, 764.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 66.0, 160.0, 116.0, 40.0, 166.0, 80.0, 216.0, 240.0, 266.0, 160.0, 316.0, 160.0, 366.0, 160.0, 15.0, 40.0, 754.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 61.0, 160.0, 111.0, 40.0, 161.0, 80.0, 211.0, 240.0, 261.0, 160.0, 311.0, 160.0, 361.0, 160.0, 10.0, 40.0, 744.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 56.0, 160.0, 106.0, 40.0, 156.0, 80.0, 206.0, 240.0, 256.0, 160.0, 306.0, 160.0, 356.0, 160.0, 5.0, 40.0, 734.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 51.0, 160.0, 101.0, 40.0, 151.0, 80.0, 201.0, 240.0, 251.0, 160.0, 301.0, 160.0, 351.0, 160.0, 0.0, 40.0, 724.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 46.0, 160.0, 96.0, 40.0, 146.0, 80.0, 196.0, 240.0, 246.0, 160.0, 296.0, 160.0, 346.0, 160.0, 396.0, 160.0, 714.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 41.0, 160.0, 91.0, 40.0, 141.0, 80.0, 191.0, 240.0, 241.0, 160.0, 291.0, 160.0, 341.0, 160.0, 391.0, 160.0, 704.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 36.0, 160.0, 86.0, 40.0, 136.0, 80.0, 186.0, 240.0, 236.0, 160.0, 286.0, 160.0, 336.0, 160.0, 386.0, 160.0, 694.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 31.0, 160.0, 81.0, 40.0, 131.0, 80.0, 181.0, 240.0, 231.0, 160.0, 281.0, 160.0, 331.0, 160.0, 381.0, 160.0, 684.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 26.0, 160.0, 76.0, 40.0, 126.0, 80.0, 176.0, 240.0, 226.0, 160.0, 276.0, 160.0, 326.0, 160.0, 376.0, 160.0, 674.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 21.0, 160.0, 71.0, 40.0, 121.0, 80.0, 171.0, 240.0, 221.0, 160.0, 271.0, 160.0, 321.0, 160.0, 371.0, 160.0, 664.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 16.0, 160.0, 66.0, 40.0, 116.0, 80.0, 166.0, 240.0, 216.0, 160.0, 266.0, 160.0, 316.0, 160.0, 366.0, 160.0, 654.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 11.0, 160.0, 61.0, 40.0, 111.0, 80.0, 161.0, 240.0, 211.0, 160.0, 261.0, 160.0, 311.0, 160.0, 361.0, 160.0, 644.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 6.0, 160.0, 56.0, 40.0, 106.0, 80.0, 156.0, 240.0, 206.0, 160.0, 256.0, 160.0, 306.0, 160.0, 356.0, 160.0, 634.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 1.0, 160.0, 51.0, 40.0, 101.0, 80.0, 151.0, 240.0, 201.0, 160.0, 251.0, 160.0, 301.0, 160.0, 351.0, 160.0, 624.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 397.0, 120.0, 46.0, 40.0, 96.0, 80.0, 146.0, 240.0, 196.0, 160.0, 246.0, 160.0, 296.0, 160.0, 346.0, 160.0, 614.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 392.0, 120.0, 41.0, 40.0, 91.0, 80.0, 141.0, 240.0, 191.0, 160.0, 241.0, 160.0, 291.0, 160.0, 341.0, 160.0, 604.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 387.0, 120.0, 36.0, 40.0, 86.0, 80.0, 136.0, 240.0, 186.0, 160.0, 236.0, 160.0, 286.0, 160.0, 336.0, 160.0, 594.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 382.0, 120.0, 31.0, 40.0, 81.0, 80.0, 131.0, 240.0, 181.0, 160.0, 231.0, 160.0, 281.0, 160.0, 331.0, 160.0, 584.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 377.0, 120.0, 26.0, 40.0, 76.0, 80.0, 126.0, 240.0, 176.0, 160.0, 226.0, 160.0, 276.0, 160.0, 326.0, 160.0, 574.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 372.0, 120.0, 21.0, 40.0, 71.0, 80.0, 121.0, 240.0, 171.0, 160.0, 221.0, 160.0, 271.0, 160.0, 321.0, 160.0, 564.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 367.0, 120.0, 16.0, 40.0, 66.0, 80.0, 116.0, 240.0, 166.0, 160.0, 216.0, 160.0, 266.0, 160.0, 316.0, 160.0, 554.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 362.0, 120.0, 11.0, 40.0, 61.0, 80.0, 111.0, 240.0, 161.0, 160.0, 211.0, 160.0, 261.0, 160.0, 311.0, 160.0, 544.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 357.0, 120.0, 6.0, 40.0, 56.0, 80.0, 106.0, 240.0, 156.0, 160.0, 206.0, 160.0, 256.0, 160.0, 306.0, 160.0, 534.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 352.0, 120.0, 1.0, 40.0, 51.0, 80.0, 101.0, 240.0, 151.0, 160.0, 201.0, 160.0, 251.0, 160.0, 301.0, 160.0, 524.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 347.0, 120.0, 397.0, 200.0, 46.0, 80.0, 96.0, 240.0, 146.0, 160.0, 196.0, 160.0, 246.0, 160.0, 296.0, 160.0, 514.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 342.0, 120.0, 392.0, 200.0, 41.0, 80.0, 91.0, 240.0, 141.0, 160.0, 191.0, 160.0, 241.0, 160.0, 291.0, 160.0, 504.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 337.0, 120.0, 387.0, 200.0, 36.0, 80.0, 86.0, 240.0, 136.0, 160.0, 186.0, 160.0, 236.0, 160.0, 286.0, 160.0, 494.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 332.0, 120.0, 382.0, 200.0, 31.0, 80.0, 81.0, 240.0, 131.0, 160.0, 181.0, 160.0, 231.0, 160.0, 281.0, 160.0, 484.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 327.0, 120.0, 377.0, 200.0, 26.0, 80.0, 76.0, 240.0, 126.0, 160.0, 176.0, 160.0, 226.0, 160.0, 276.0, 160.0, 474.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 322.0, 120.0, 372.0, 200.0, 21.0, 80.0, 71.0, 240.0, 121.0, 160.0, 171.0, 160.0, 221.0, 160.0, 271.0, 160.0, 464.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 317.0, 120.0, 367.0, 200.0, 16.0, 80.0, 66.0, 240.0, 116.0, 160.0, 166.0, 160.0, 216.0, 160.0, 266.0, 160.0, 454.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 312.0, 120.0, 362.0, 200.0, 11.0, 80.0, 61.0, 240.0, 111.0, 160.0, 161.0, 160.0, 211.0, 160.0, 261.0, 160.0, 444.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 307.0, 120.0, 357.0, 200.0, 6.0, 80.0, 56.0, 240.0, 106.0, 160.0, 156.0, 160.0, 206.0, 160.0, 256.0, 160.0, 434.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 302.0, 120.0, 352.0, 200.0, 1.0, 80.0, 51.0, 240.0, 101.0, 160.0, 151.0, 160.0, 201.0, 160.0, 251.0, 160.0, 424.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 297.0, 120.0, 347.0, 200.0, 397.0, 200.0, 46.0, 240.0, 96.0, 160.0, 146.0, 160.0, 196.0, 160.0, 246.0, 160.0, 414.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 292.0, 120.0, 342.0, 200.0, 392.0, 200.0, 41.0, 240.0, 91.0, 160.0, 141.0, 160.0, 191.0, 160.0, 241.0, 160.0, 404.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 287.0, 120.0, 337.0, 200.0, 387.0, 200.0, 36.0, 240.0, 86.0, 160.0, 136.0, 160.0, 186.0, 160.0, 236.0, 160.0, 394.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 282.0, 120.0, 332.0, 200.0, 382.0, 200.0, 31.0, 240.0, 81.0, 160.0, 131.0, 160.0, 181.0, 160.0, 231.0, 160.0, 384.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 277.0, 120.0, 327.0, 200.0, 377.0, 200.0, 26.0, 240.0, 76.0, 160.0, 126.0, 160.0, 176.0, 160.0, 226.0, 160.0, 374.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 272.0, 120.0, 322.0, 200.0, 372.0, 200.0, 21.0, 240.0, 71.0, 160.0, 121.0, 160.0, 171.0, 160.0, 221.0, 160.0, 364.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 110.0, 267.0, 120.0, 317.0, 200.0, 367.0, 200.0, 16.0, 240.0, 66.0, 160.0, 116.0, 160.0, 166.0, 160.0, 216.0, 160.0, 354.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 100.0, 262.0, 120.0, 312.0, 200.0, 362.0, 200.0, 11.0, 240.0, 61.0, 160.0, 111.0, 160.0, 161.0, 160.0, 211.0, 160.0, 344.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 90.0, 257.0, 120.0, 307.0, 200.0, 357.0, 200.0, 6.0, 240.0, 56.0, 160.0, 106.0, 160.0, 156.0, 160.0, 206.0, 160.0, 334.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 80.0, 252.0, 120.0, 302.0, 200.0, 352.0, 200.0, 1.0, 240.0, 51.0, 160.0, 101.0, 160.0, 151.0, 160.0, 201.0, 160.0, 324.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 70.0, 247.0, 120.0, 297.0, 200.0, 347.0, 200.0, 397.0, 160.0, 46.0, 160.0, 96.0, 160.0, 146.0, 160.0, 196.0, 160.0, 314.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 242.0, 120.0, 292.0, 200.0, 342.0, 200.0, 392.0, 160.0, 41.0, 160.0, 91.0, 160.0, 141.0, 160.0, 191.0, 160.0, 304.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 50.0, 237.0, 120.0, 287.0, 200.0, 337.0, 200.0, 387.0, 160.0, 36.0, 160.0, 86.0, 160.0, 136.0, 160.0, 186.0, 160.0, 294.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 40.0, 232.0, 120.0, 282.0, 200.0, 332.0, 200.0, 382.0, 160.0, 31.0, 160.0, 81.0, 160.0, 131.0, 160.0, 181.0, 160.0, 284.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 227.0, 120.0, 277.0, 200.0, 327.0, 200.0, 377.0, 160.0, 26.0, 160.0, 76.0, 160.0, 126.0, 160.0, 176.0, 160.0, 274.0, 40.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 222.0, 120.0, 272.0, 200.0, 322.0, 200.0, 372.0, 160.0, 21.0, 160.0, 71.0, 160.0, 121.0, 160.0, 171.0, 160.0, 264.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 217.0, 120.0, 267.0, 200.0, 317.0, 200.0, 367.0, 160.0, 16.0, 160.0, 66.0, 160.0, 116.0, 160.0, 166.0, 160.0, 254.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 212.0, 120.0, 262.0, 200.0, 312.0, 200.0, 362.0, 160.0, 11.0, 160.0, 61.0, 160.0, 111.0, 160.0, 161.0, 160.0, 244.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 207.0, 120.0, 257.0, 200.0, 307.0, 200.0, 357.0, 160.0, 6.0, 160.0, 56.0, 160.0, 106.0, 160.0, 156.0, 160.0, 234.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 202.0, 120.0, 252.0, 200.0, 302.0, 200.0, 352.0, 160.0, 1.0, 160.0, 51.0, 160.0, 101.0, 160.0, 151.0, 160.0, 224.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 197.0, 120.0, 247.0, 200.0, 297.0, 200.0, 347.0, 160.0, 397.0, 240.0, 46.0, 160.0, 96.0, 160.0, 146.0, 160.0, 214.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 192.0, 120.0, 242.0, 200.0, 292.0, 200.0, 342.0, 160.0, 392.0, 240.0, 41.0, 160.0, 91.0, 160.0, 141.0, 160.0, 204.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 187.0, 120.0, 237.0, 200.0, 287.0, 200.0, 337.0, 160.0, 387.0, 240.0, 36.0, 160.0, 86.0, 160.0, 136.0, 160.0, 194.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 182.0, 120.0, 232.0, 200.0, 282.0, 200.0, 332.0, 160.0, 382.0, 240.0, 31.0, 160.0, 81.0, 160.0, 131.0, 160.0, 184.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 177.0, 120.0, 227.0, 200.0, 277.0, 200.0, 327.0, 160.0, 377.0, 240.0, 26.0, 160.0, 76.0, 160.0, 126.0, 160.0, 174.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 172.0, 120.0, 222.0, 200.0, 272.0, 200.0, 322.0, 160.0, 372.0, 240.0, 21.0, 160.0, 71.0, 160.0, 121.0, 160.0, 164.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 167.0, 120.0, 217.0, 200.0, 267.0, 200.0, 317.0, 160.0, 367.0, 240.0, 16.0, 160.0, 66.0, 160.0, 116.0, 160.0, 154.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 162.0, 120.0, 212.0, 200.0, 262.0, 200.0, 312.0, 160.0, 362.0, 240.0, 11.0, 160.0, 61.0, 160.0, 111.0, 160.0, 144.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 157.0, 120.0, 207.0, 200.0, 257.0, 200.0, 307.0, 160.0, 357.0, 240.0, 6.0, 160.0, 56.0, 160.0, 106.0, 160.0, 134.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 152.0, 120.0, 202.0, 200.0, 252.0, 200.0, 302.0, 160.0, 352.0, 240.0, 1.0, 160.0, 51.0, 160.0, 101.0, 160.0, 124.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 147.0, 120.0, 197.0, 200.0, 247.0, 200.0, 297.0, 160.0, 347.0, 240.0, 397.0, 80.0, 46.0, 160.0, 96.0, 160.0, 114.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 142.0, 120.0, 192.0, 200.0, 242.0, 200.0, 292.0, 160.0, 342.0, 240.0, 392.0, 80.0, 41.0, 160.0, 91.0, 160.0, 104.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 137.0, 120.0, 187.0, 200.0, 237.0, 200.0, 287.0, 160.0, 337.0, 240.0, 387.0, 80.0, 36.0, 160.0, 86.0, 160.0, 94.0, 40.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 132.0, 120.0, 182.0, 200.0, 232.0, 200.0, 282.0, 160.0, 332.0, 240.0, 382.0, 80.0, 31.0, 160.0, 81.0, 160.0, 1992.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 127.0, 120.0, 177.0, 200.0, 227.0, 200.0, 277.0, 160.0, 327.0, 240.0, 377.0, 80.0, 26.0, 160.0, 76.0, 160.0, 1982.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 122.0, 120.0, 172.0, 200.0, 222.0, 200.0, 272.0, 160.0, 322.0, 240.0, 372.0, 80.0, 21.0, 160.0, 71.0, 160.0, 1972.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 117.0, 120.0, 167.0, 200.0, 217.0, 200.0, 267.0, 160.0, 317.0, 240.0, 367.0, 80.0, 16.0, 160.0, 66.0, 160.0, 1962.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 112.0, 120.0, 162.0, 200.0, 212.0, 200.0, 262.0, 160.0, 312.0, 240.0, 362.0, 80.0, 11.0, 160.0, 61.0, 160.0, 1952.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 34.0, 107.0, 120.0, 157.0, 200.0, 207.0, 200.0, 257.0, 160.0, 307.0, 240.0, 357.0, 80.0, 6.0, 160.0, 56.0, 160.0, 1942.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 44.0, 102.0, 120.0, 152.0, 200.0, 202.0, 200.0, 252.0, 160.0, 302.0, 240.0, 352.0, 80.0, 1.0, 160.0, 51.0, 160.0, 1932.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 52.0, 97.0, 120.0, 147.0, 200.0, 197.0, 200.0, 247.0, 160.0, 297.0, 240.0, 347.0, 80.0, 397.0, 200.0, 46.0, 160.0, 1922.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 52.0, 92.0, 120.0, 142.0, 200.0, 192.0, 200.0, 242.0, 160.0, 292.0, 240.0, 342.0, 80.0, 392.0, 200.0, 41.0, 160.0, 1912.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 52.0, 87.0, 120.0, 137.0, 200.0, 187.0, 200.0, 237.0, 160.0, 287.0, 240.0, 337.0, 80.0, 387.0, 200.0, 36.0, 160.0, 1902.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 52.0, 82.0, 120.0, 132.0, 200.0, 182.0, 200.0, 232.0, 160.0, 282.0, 240.0, 332.0, 80.0, 382.0, 200.0, 31.0, 160.0, 1892.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 52.0, 77.0, 120.0, 127.0, 200.0, 177.0, 200.0, 227.0, 160.0, 277.0, 240.0, 327.0, 80.0, 377.0, 200.0, 26.0, 160.0, 1882.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 52.0, 72.0, 120.0, 122.0, 200.0, 172.0, 200.0, 222.0, 160.0, 272.0, 240.0, 322.0, 80.0, 372.0, 200.0, 21.0, 160.0, 1872.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 52.0, 67.0, 120.0, 117.0, 200.0, 167.0, 200.0, 217.0, 160.0, 267.0, 240.0, 317.0, 80.0, 367.0, 200.0, 16.0, 160.0, 1862.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 56.0, 62.0, 120.0, 112.0, 200.0, 162.0, 200.0, 212.0, 160.0, 262.0, 240.0, 312.0, 80.0, 362.0, 200.0, 11.0, 160.0, 1852.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 57.0, 120.0, 107.0, 200.0, 157.0, 200.0, 207.0, 160.0, 257.0, 240.0, 307.0, 80.0, 357.0, 200.0, 6.0, 160.0, 1842.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 52.0, 120.0, 102.0, 200.0, 152.0, 200.0, 202.0, 160.0, 252.0, 240.0, 302.0, 80.0, 352.0, 200.0, 1.0, 160.0, 1832.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 47.0, 120.0, 97.0, 200.0, 147.0, 200.0, 197.0, 160.0, 247.0, 240.0, 297.0, 80.0, 347.0, 200.0, 397.0, 160.0, 1822.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 42.0, 120.0, 92.0, 200.0, 142.0, 200.0, 192.0, 160.0, 242.0, 240.0, 292.0, 80.0, 342.0, 200.0, 392.0, 160.0, 1812.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 37.0, 120.0, 87.0, 200.0, 137.0, 200.0, 187.0, 160.0, 237.0, 240.0, 287.0, 80.0, 337.0, 200.0, 387.0, 160.0, 1802.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 32.0, 120.0, 82.0, 200.0, 132.0, 200.0, 182.0, 160.0, 232.0, 240.0, 282.0, 80.0, 332.0, 200.0, 382.0, 160.0, 1792.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 27.0, 120.0, 77.0, 200.0, 127.0, 200.0, 177.0, 160.0, 227.0, 240.0, 277.0, 80.0, 327.0, 200.0, 377.0, 160.0, 1782.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 22.0, 120.0, 72.0, 200.0, 122.0, 200.0, 172.0, 160.0, 222.0, 240.0, 272.0, 80.0, 322.0, 200.0, 372.0, 160.0, 1772.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 17.0, 120.0, 67.0, 200.0, 117.0, 200.0, 167.0, 160.0, 217.0, 240.0, 267.0, 80.0, 317.0, 200.0, 367.0, 160.0, 1762.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 12.0, 120.0, 62.0, 200.0, 112.0, 200.0, 162.0, 160.0, 212.0, 240.0, 262.0, 80.0, 312.0, 200.0, 362.0, 160.0, 1752.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 7.0, 120.0, 57.0, 200.0, 107.0, 200.0, 157.0, 160.0, 207.0, 240.0, 257.0, 80.0, 307.0, 200.0, 357.0, 160.0, 1742.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 2.0, 120.0, 52.0, 200.0, 102.0, 200.0, 152.0, 160.0, 202.0, 240.0, 252.0, 80.0, 302.0, 200.0, 352.0, 160.0, 1732.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 398.0, 160.0, 47.0, 200.0, 97.0, 200.0, 147.0, 160.0, 197.0, 240.0, 247.0, 80.0, 297.0, 200.0, 347.0, 160.0, 1722.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 80.0, 393.0, 160.0, 42.0, 200.0, 92.0, 200.0, 142.0, 160.0, 192.0, 240.0, 242.0, 80.0, 292.0, 200.0, 342.0, 160.0, 1712.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 80.0, 388.0, 160.0, 37.0, 200.0, 87.0, 200.0, 137.0, 160.0, 187.0, 240.0, 237.0, 80.0, 287.0, 200.0, 337.0, 160.0, 1702.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 80.0, 383.0, 160.0, 32.0, 200.0, 82.0, 200.0, 132.0, 160.0, 182.0, 240.0, 232.0, 80.0, 282.0, 200.0, 332.0, 160.0, 1692.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 84.0, 378.0, 160.0, 27.0, 200.0, 77.0, 200.0, 127.0, 160.0, 177.0, 240.0, 227.0, 80.0, 277.0, 200.0, 327.0, 160.0, 1682.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 92.0, 373.0, 160.0, 22.0, 200.0, 72.0, 200.0, 122.0, 160.0, 172.0, 240.0, 222.0, 80.0, 272.0, 200.0, 322.0, 160.0, 1672.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 92.0, 368.0, 160.0, 17.0, 200.0, 67.0, 200.0, 117.0, 160.0, 167.0, 240.0, 217.0, 80.0, 267.0, 200.0, 317.0, 160.0, 1662.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 92.0, 363.0, 160.0, 12.0, 200.0, 62.0, 200.0, 112.0, 160.0, 162.0, 240.0, 212.0, 80.0, 262.0, 200.0, 312.0, 160.0, 1652.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 92.0, 358.0, 160.0, 7.0, 200.0, 57.0, 200.0, 107.0, 160.0, 157.0, 240.0, 207.0, 80.0, 257.0, 200.0, 307.0, 160.0, 1642.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 92.0, 353.0, 160.0, 2.0, 200.0, 52.0, 200.0, 102.0, 160.0, 152.0, 240.0, 202.0, 80.0, 252.0, 200.0, 302.0, 160.0, 1632.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 92.0, 348.0, 160.0, 398.0, 240.0, 47.0, 200.0, 97.0, 160.0, 147.0, 240.0, 197.0, 80.0, 247.0, 200.0, 297.0, 160.0, 1622.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 92.0, 343.0, 160.0, 393.0, 240.0, 42.0, 200.0, 92.0, 160.0, 142.0, 240.0, 192.0, 80.0, 242.0, 200.0, 292.0, 160.0, 1612.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 94.0, 338.0, 160.0, 388.0, 240.0, 37.0, 200.0, 87.0, 160.0, 137.0, 240.0, 187.0, 80.0, 237.0, 200.0, 287.0, 160.0, 1602.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 104.0, 333.0, 160.0, 383.0, 240.0, 32.0, 200.0, 82.0, 160.0, 132.0, 240.0, 182.0, 80.0, 232.0, 200.0, 282.0, 160.0, 1592.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 104.0, 328.0, 160.0, 378.0, 240.0, 27.0, 200.0, 77.0, 160.0, 127.0, 240.0, 177.0, 80.0, 227.0, 200.0, 277.0, 160.0, 1582.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 104.0, 323.0, 160.0, 373.0, 240.0, 22.0, 200.0, 72.0, 160.0, 122.0, 240.0, 172.0, 80.0, 222.0, 200.0, 272.0, 160.0, 1572.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 104.0, 318.0, 160.0, 368.0, 240.0, 17.0, 200.0, 67.0, 160.0, 117.0, 240.0, 167.0, 80.0, 217.0, 200.0, 267.0, 160.0, 1562.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 104.0, 313.0, 160.0, 363.0, 240.0, 12.0, 200.0, 62.0, 160.0, 112.0, 240.0, 162.0, 80.0, 212.0, 200.0, 262.0, 160.0, 1552.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 104.0, 308.0, 160.0, 358.0, 240.0, 7.0, 200.0, 57.0, 160.0, 107.0, 240.0, 157.0, 80.0, 207.0, 200.0, 257.0, 160.0, 1542.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 104.0, 303.0, 160.0, 353.0, 240.0, 2.0, 200.0, 52.0, 160.0, 102.0, 240.0, 152.0, 80.0, 202.0, 200.0, 252.0, 160.0, 1532.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 104.0, 298.0, 160.0, 348.0, 240.0, 398.0, 160.0, 47.0, 160.0, 97.0, 240.0, 147.0, 80.0, 197.0, 200.0, 247.0, 160.0, 1522.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 104.0, 293.0, 160.0, 343.0, 240.0, 393.0, 160.0, 42.0, 160.0, 92.0, 240.0, 142.0, 80.0, 192.0, 200.0, 242.0, 160.0, 1512.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 104.0, 288.0, 160.0, 338.0, 240.0, 388.0, 160.0, 37.0, 160.0, 87.0, 240.0, 137.0, 80.0, 187.0, 200.0, 237.0, 160.0, 1502.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 104.0, 283.0, 160.0, 333.0, 240.0, 383.0, 160.0, 32.0, 160.0, 82.0, 240.0, 132.0, 80.0, 182.0, 200.0, 232.0, 160.0, 1492.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 104.0, 278.0, 160.0, 328.0, 240.0, 378.0, 160.0, 27.0, 160.0, 77.0, 240.0, 127.0, 80.0, 177.0, 200.0, 227.0, 160.0, 1482.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 273.0, 160.0, 323.0, 240.0, 373.0, 160.0, 22.0, 160.0, 72.0, 240.0, 122.0, 80.0, 172.0, 200.0, 222.0, 160.0, 1472.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 268.0, 160.0, 318.0, 240.0, 368.0, 160.0, 17.0, 160.0, 67.0, 240.0, 117.0, 80.0, 167.0, 200.0, 217.0, 160.0, 1462.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 263.0, 160.0, 313.0, 240.0, 363.0, 160.0, 12.0, 160.0, 62.0, 240.0, 112.0, 80.0, 162.0, 200.0, 212.0, 160.0, 1452.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 258.0, 160.0, 308.0, 240.0, 358.0, 160.0, 7.0, 160.0, 57.0, 240.0, 107.0, 80.0, 157.0, 200.0, 207.0, 160.0, 1442.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 253.0, 160.0, 303.0, 240.0, 353.0, 160.0, 2.0, 160.0, 52.0, 240.0, 102.0, 80.0, 152.0, 200.0, 202.0, 160.0, 1432.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 248.0, 160.0, 298.0, 240.0, 348.0, 160.0, 398.0, 120.0, 47.0, 240.0, 97.0, 80.0, 147.0, 200.0, 197.0, 160.0, 1422.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 243.0, 160.0, 293.0, 240.0, 343.0, 160.0, 393.0, 120.0, 42.0, 240.0, 92.0, 80.0, 142.0, 200.0, 192.0, 160.0, 1412.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 238.0, 160.0, 288.0, 240.0, 338.0, 160.0, 388.0, 120.0, 37.0, 240.0, 87.0, 80.0, 137.0, 200.0, 187.0, 160.0, 1402.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 233.0, 160.0, 283.0, 240.0, 333.0, 160.0, 383.0, 120.0, 32.0, 240.0, 82.0, 80.0, 132.0, 200.0, 182.0, 160.0, 1392.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 228.0, 160.0, 278.0, 240.0, 328.0, 160.0, 378.0, 120.0, 27.0, 240.0, 77.0, 80.0, 127.0, 200.0, 177.0, 160.0, 1382.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 223.0, 160.0, 273.0, 240.0, 323.0, 160.0, 373.0, 120.0, 22.0, 240.0, 72.0, 80.0, 122.0, 200.0, 172.0, 160.0, 1372.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 218.0, 160.0, 268.0, 240.0, 318.0, 160.0, 368.0, 120.0, 17.0, 240.0, 67.0, 80.0, 117.0, 200.0, 167.0, 160.0, 1362.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 213.0, 160.0, 263.0, 240.0, 313.0, 160.0, 363.0, 120.0, 12.0, 240.0, 62.0, 80.0, 112.0, 200.0, 162.0, 160.0, 1352.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 208.0, 160.0, 258.0, 240.0, 308.0, 160.0, 358.0, 120.0, 7.0, 240.0, 57.0, 80.0, 107.0, 200.0, 157.0, 160.0, 1342.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 203.0, 160.0, 253.0, 240.0, 303.0, 160.0, 353.0, 120.0, 2.0, 240.0, 52.0, 80.0, 102.0, 200.0, 152.0, 160.0, 1332.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 198.0, 160.0, 248.0, 240.0, 298.0, 160.0, 348.0, 120.0, 398.0, 200.0, 47.0, 80.0, 97.0, 200.0, 147.0, 160.0, 1322.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 193.0, 160.0, 243.0, 240.0, 293.0, 160.0, 343.0, 120.0, 393.0, 200.0, 42.0, 80.0, 92.0, 200.0, 142.0, 160.0, 1312.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 188.0, 160.0, 238.0, 240.0, 288.0, 160.0, 338.0, 120.0, 388.0, 200.0, 37.0, 80.0, 87.0, 200.0, 137.0, 160.0, 1302.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 183.0, 160.0, 233.0, 240.0, 283.0, 160.0, 333.0, 120.0, 383.0, 200.0, 32.0, 80.0, 82.0, 200.0, 132.0, 160.0, 1292.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 178.0, 160.0, 228.0, 240.0, 278.0, 160.0, 328.0, 120.0, 378.0, 200.0, 27.0, 80.0, 77.0, 200.0, 127.0, 160.0, 1282.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 173.0, 160.0, 223.0, 240.0, 273.0, 160.0, 323.0, 120.0, 373.0, 200.0, 22.0, 80.0, 72.0, 200.0, 122.0, 160.0, 1272.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 168.0, 160.0, 218.0, 240.0, 268.0, 160.0, 318.0, 120.0, 368.0, 200.0, 17.0, 80.0, 67.0, 200.0, 117.0, 160.0, 1262.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 163.0, 160.0, 213.0, 240.0, 263.0, 160.0, 313.0, 120.0, 363.0, 200.0, 12.0, 80.0, 62.0, 200.0, 112.0, 160.0, 1252.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 158.0, 160.0, 208.0, 240.0, 258.0, 160.0, 308.0, 120.0, 358.0, 200.0, 7.0, 80.0, 57.0, 200.0, 107.0, 160.0, 1242.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 153.0, 160.0, 203.0, 240.0, 253.0, 160.0, 303.0, 120.0, 353.0, 200.0, 2.0, 80.0, 52.0, 200.0, 102.0, 160.0, 1232.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 148.0, 160.0, 198.0, 240.0, 248.0, 160.0, 298.0, 120.0, 348.0, 200.0, 398.0, 120.0, 47.0, 200.0, 97.0, 160.0, 1222.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 143.0, 160.0, 193.0, 240.0, 243.0, 160.0, 293.0, 120.0, 343.0, 200.0, 393.0, 120.0, 42.0, 200.0, 92.0, 160.0, 1212.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 138.0, 160.0, 188.0, 240.0, 238.0, 160.0, 288.0, 120.0, 338.0, 200.0, 388.0, 120.0, 37.0, 200.0, 87.0, 160.0, 1202.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 133.0, 160.0, 183.0, 240.0, 233.0, 160.0, 283.0, 120.0, 333.0, 200.0, 383.0, 120.0, 32.0, 200.0, 82.0, 160.0, 1192.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 128.0, 160.0, 178.0, 240.0, 228.0, 160.0, 278.0, 120.0, 328.0, 200.0, 378.0, 120.0, 27.0, 200.0, 77.0, 160.0, 1182.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 123.0, 160.0, 173.0, 240.0, 223.0, 160.0, 273.0, 120.0, 323.0, 200.0, 373.0, 120.0, 22.0, 200.0, 72.0, 160.0, 1172.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 118.0, 160.0, 168.0, 240.0, 218.0, 160.0, 268.0, 120.0, 318.0, 200.0, 368.0, 120.0, 17.0, 200.0, 67.0, 160.0, 1162.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 113.0, 160.0, 163.0, 240.0, 213.0, 160.0, 263.0, 120.0, 313.0, 200.0, 363.0, 120.0, 12.0, 200.0, 62.0, 160.0, 1152.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 108.0, 160.0, 158.0, 240.0, 208.0, 160.0, 258.0, 120.0, 308.0, 200.0, 358.0, 120.0, 7.0, 200.0, 57.0, 160.0, 1142.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 103.0, 160.0, 153.0, 240.0, 203.0, 160.0, 253.0, 120.0, 303.0, 200.0, 353.0, 120.0, 2.0, 200.0, 52.0, 160.0, 1132.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 98.0, 160.0, 148.0, 240.0, 198.0, 160.0, 248.0, 120.0, 298.0, 200.0, 348.0, 120.0, 398.0, 240.0, 47.0, 160.0, 1122.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 93.0, 160.0, 143.0, 240.0, 193.0, 160.0, 243.0, 120.0, 293.0, 200.0, 343.0, 120.0, 393.0, 240.0, 42.0, 160.0, 1112.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 88.0, 160.0, 138.0, 240.0, 188.0, 160.0, 238.0, 120.0, 288.0, 200.0, 338.0, 120.0, 388.0, 240.0, 37.0, 160.0, 1102.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 83.0, 160.0, 133.0, 240.0, 183.0, 160.0, 233.0, 120.0, 283.0, 200.0, 333.0, 120.0, 383.0, 240.0, 32.0, 160.0, 1092.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 78.0, 160.0, 128.0, 240.0, 178.0, 160.0, 228.0, 120.0, 278.0, 200.0, 328.0, 120.0, 378.0, 240.0, 27.0, 160.0, 1082.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 73.0, 160.0, 123.0, 240.0, 173.0, 160.0, 223.0, 120.0, 273.0, 200.0, 323.0, 120.0, 373.0, 240.0, 22.0, 160.0, 1072.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 68.0, 160.0, 118.0, 240.0, 168.0, 160.0, 218.0, 120.0, 268.0, 200.0, 318.0, 120.0, 368.0, 240.0, 17.0, 160.0, 1062.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 63.0, 160.0, 113.0, 240.0, 163.0, 160.0, 213.0, 120.0, 263.0, 200.0, 313.0, 120.0, 363.0, 240.0, 12.0, 160.0, 1052.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 58.0, 160.0, 108.0, 240.0, 158.0, 160.0, 208.0, 120.0, 258.0, 200.0, 308.0, 120.0, 358.0, 240.0, 7.0, 160.0, 1042.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 53.0, 160.0, 103.0, 240.0, 153.0, 160.0, 203.0, 120.0, 253.0, 200.0, 303.0, 120.0, 353.0, 240.0, 2.0, 160.0, 1032.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 48.0, 160.0, 98.0, 240.0, 148.0, 160.0, 198.0, 120.0, 248.0, 200.0, 298.0, 120.0, 348.0, 240.0, 398.0, 240.0, 1022.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 43.0, 160.0, 93.0, 240.0, 143.0, 160.0, 193.0, 120.0, 243.0, 200.0, 293.0, 120.0, 343.0, 240.0, 393.0, 240.0, 1012.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 38.0, 160.0, 88.0, 240.0, 138.0, 160.0, 188.0, 120.0, 238.0, 200.0, 288.0, 120.0, 338.0, 240.0, 388.0, 240.0, 1002.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 33.0, 160.0, 83.0, 240.0, 133.0, 160.0, 183.0, 120.0, 233.0, 200.0, 283.0, 120.0, 333.0, 240.0, 383.0, 240.0, 992.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 28.0, 160.0, 78.0, 240.0, 128.0, 160.0, 178.0, 120.0, 228.0, 200.0, 278.0, 120.0, 328.0, 240.0, 378.0, 240.0, 982.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 23.0, 160.0, 73.0, 240.0, 123.0, 160.0, 173.0, 120.0, 223.0, 200.0, 273.0, 120.0, 323.0, 240.0, 373.0, 240.0, 972.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 18.0, 160.0, 68.0, 240.0, 118.0, 160.0, 168.0, 120.0, 218.0, 200.0, 268.0, 120.0, 318.0, 240.0, 368.0, 240.0, 962.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 13.0, 160.0, 63.0, 240.0, 113.0, 160.0, 163.0, 120.0, 213.0, 200.0, 263.0, 120.0, 313.0, 240.0, 363.0, 240.0, 952.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 8.0, 160.0, 58.0, 240.0, 108.0, 160.0, 158.0, 120.0, 208.0, 200.0, 258.0, 120.0, 308.0, 240.0, 358.0, 240.0, 942.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 3.0, 160.0, 53.0, 240.0, 103.0, 160.0, 153.0, 120.0, 203.0, 200.0, 253.0, 120.0, 303.0, 240.0, 353.0, 240.0, 932.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 108.0, 399.0, 160.0, 48.0, 240.0, 98.0, 160.0, 148.0, 120.0, 198.0, 200.0, 248.0, 120.0, 298.0, 240.0, 348.0, 240.0, 922.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 98.0, 394.0, 160.0, 43.0, 240.0, 93.0, 160.0, 143.0, 120.0, 193.0, 200.0, 243.0, 120.0, 293.0, 240.0, 343.0, 240.0, 912.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 88.0, 389.0, 160.0, 38.0, 240.0, 88.0, 160.0, 138.0, 120.0, 188.0, 200.0, 238.0, 120.0, 288.0, 240.0, 338.0, 240.0, 902.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 78.0, 384.0, 160.0, 33.0, 240.0, 83.0, 160.0, 133.0, 120.0, 183.0, 200.0, 233.0, 120.0, 283.0, 240.0, 333.0, 240.0, 892.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 379.0, 160.0, 28.0, 240.0, 78.0, 160.0, 128.0, 120.0, 178.0, 200.0, 228.0, 120.0, 278.0, 240.0, 328.0, 240.0, 882.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 374.0, 160.0, 23.0, 240.0, 73.0, 160.0, 123.0, 120.0, 173.0, 200.0, 223.0, 120.0, 273.0, 240.0, 323.0, 240.0, 872.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 369.0, 160.0, 18.0, 240.0, 68.0, 160.0, 118.0, 120.0, 168.0, 200.0, 218.0, 120.0, 268.0, 240.0, 318.0, 240.0, 862.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 364.0, 160.0, 13.0, 240.0, 63.0, 160.0, 113.0, 120.0, 163.0, 200.0, 213.0, 120.0, 263.0, 240.0, 313.0, 240.0, 852.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 359.0, 160.0, 8.0, 240.0, 58.0, 160.0, 108.0, 120.0, 158.0, 200.0, 208.0, 120.0, 258.0, 240.0, 308.0, 240.0, 842.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 354.0, 160.0, 3.0, 240.0, 53.0, 160.0, 103.0, 120.0, 153.0, 200.0, 203.0, 120.0, 253.0, 240.0, 303.0, 240.0, 832.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 349.0, 160.0, 399.0, 40.0, 48.0, 160.0, 98.0, 120.0, 148.0, 200.0, 198.0, 120.0, 248.0, 240.0, 298.0, 240.0, 822.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 344.0, 160.0, 394.0, 40.0, 43.0, 160.0, 93.0, 120.0, 143.0, 200.0, 193.0, 120.0, 243.0, 240.0, 293.0, 240.0, 812.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 339.0, 160.0, 389.0, 40.0, 38.0, 160.0, 88.0, 120.0, 138.0, 200.0, 188.0, 120.0, 238.0, 240.0, 288.0, 240.0, 802.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 334.0, 160.0, 384.0, 40.0, 33.0, 160.0, 83.0, 120.0, 133.0, 200.0, 183.0, 120.0, 233.0, 240.0, 283.0, 240.0, 792.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 329.0, 160.0, 379.0, 40.0, 28.0, 160.0, 78.0, 120.0, 128.0, 200.0, 178.0, 120.0, 228.0, 240.0, 278.0, 240.0, 782.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 324.0, 160.0, 374.0, 40.0, 23.0, 160.0, 73.0, 120.0, 123.0, 200.0, 173.0, 120.0, 223.0, 240.0, 273.0, 240.0, 772.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 319.0, 160.0, 369.0, 40.0, 18.0, 160.0, 68.0, 120.0, 118.0, 200.0, 168.0, 120.0, 218.0, 240.0, 268.0, 240.0, 762.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 314.0, 160.0, 364.0, 40.0, 13.0, 160.0, 63.0, 120.0, 113.0, 200.0, 163.0, 120.0, 213.0, 240.0, 263.0, 240.0, 752.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 309.0, 160.0, 359.0, 40.0, 8.0, 160.0, 58.0, 120.0, 108.0, 200.0, 158.0, 120.0, 208.0, 240.0, 258.0, 240.0, 742.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 304.0, 160.0, 354.0, 40.0, 3.0, 160.0, 53.0, 120.0, 103.0, 200.0, 153.0, 120.0, 203.0, 240.0, 253.0, 240.0, 732.0, 70.0}, new double[] {0.0, 1.0, 0.0}));

		
	}
	
	private static void buildTrenningPatternList2(ArrayList<TreningPattern> treningPatterns){
		
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 398.0, 80.0, 47.0, 200.0, 97.0, 80.0, 147.0, 200.0, 197.0, 160.0, 247.0, 80.0, 297.0, 200.0, 347.0, 80.0, 355.0, 200.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 250.0, 199.0, 80.0, 249.0, 120.0, 299.0, 80.0, 349.0, 160.0, 399.0, 200.0, 48.0, 200.0, 98.0, 200.0, 148.0, 200.0, 1155.0, 200.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 358.0, 200.0, 7.0, 160.0, 57.0, 200.0, 107.0, 240.0, 157.0, 200.0, 207.0, 200.0, 257.0, 80.0, 307.0, 200.0, 9623.0, 120.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 159.0, 120.0, 209.0, 240.0, 259.0, 160.0, 309.0, 200.0, 359.0, 200.0, 8.0, 240.0, 58.0, 160.0, 108.0, 80.0, 8423.0, 120.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 128.0, 200.0, 178.0, 160.0, 228.0, 160.0, 278.0, 80.0, 28.0, 200.0, 77.0, 200.0, 1207.0, 80.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0}));
		
		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 192.0, 160.0, 242.0, 40.0, 292.0, 80.0, 342.0, 160.0, 392.0, 160.0, 442.0, 120.0, 492.0, 120.0, 542.0, 120.0, 1755.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 393.0, 160.0, 42.0, 40.0, 92.0, 80.0, 142.0, 160.0, 192.0, 160.0, 242.0, 120.0, 292.0, 120.0, 342.0, 120.0, 6355.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 106.0, 397.0, 80.0, 46.0, 80.0, 96.0, 40.0, 146.0, 160.0, 196.0, 240.0, 246.0, 240.0, 296.0, 120.0, 346.0, 240.0, 3155.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 118.0, 198.0, 80.0, 248.0, 160.0, 298.0, 80.0, 348.0, 120.0, 398.0, 200.0, 47.0, 80.0, 97.0, 200.0, 147.0, 80.0, 1955.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 244.0, 200.0, 200.0, 250.0, 160.0, 300.0, 200.0, 350.0, 200.0, -1.0, 200.0, 49.0, 40.0, 99.0, 120.0, 149.0, 120.0, 355.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		
		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 194.0, 200.0, 244.0, 120.0, 294.0, 120.0, 344.0, 260.0, 294.0, 120.0, 43.0, 200.0, 93.0, 180.0, 143.0, 160.0, 2155.0, 200.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 195.0, 200.0, 245.0, 240.0, 295.0, 240.0, 345.0, 200.0, 395.0, 120.0, 44.0, 80.0, 94.0, 160.0, 144.0, 120.0, 4355.0, 200.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 197.0, 80.0, 247.0, 200.0, 297.0, 80.0, 347.0, 200.0, 397.0, 160.0, 46.0, 240.0, 96.0, 120.0, 146.0, 240.0, 2755.0, 200.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 238.0, 0.0, 200.0, 50.0, 160.0, 100.0, 200.0, 150.0, 200.0, 200.0, 240.0, 250.0, 120.0, 300.0, 160.0, 350.0, 160.0, 1890.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 157.0, 120.0, 207.0, 160.0, 257.0, 200.0, 307.0, 240.0, 357.0, 200.0, 6.0, 200.0, 56.0, 200.0, 106.0, 120.0, 10023.0, 120.0}, new double[] {1.0, 0.0, 0.0}));

		treningPatterns.add(new TreningPattern(new double[] {40.0, 32.0, 189.0, 80.0, 239.0, 80.0, 289.0, 80.0, 339.0, 120.0, 389.0, 160.0, 38.0, 160.0, 88.0, 120.0, 138.0, 80.0, 444.0, 3.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 187.0, 200.0, 237.0, 200.0, 287.0, 200.0, 337.0, 120.0, 387.0, 160.0, 437.0, 240.0, 487.0, 120.0, 537.0, 160.0, 1224.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 391.0, 200.0, 40.0, 240.0, 90.0, 240.0, 140.0, 160.0, 190.0, 160.0, 240.0, 120.0, 290.0, 40.0, 340.0, 200.0, 1152.0, 70.0}, new double[] {0.0, 1.0, 0.0}));

		
		treningPatterns.add(new TreningPattern(new double[] {40.0, 102.0, 388.0, 40.0, 37.0, 200.0, 87.0, 200.0, 137.0, 120.0, 187.0, 160.0, 237.0, 240.0, 287.0, 120.0, 337.0, 160.0, 824.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 188.0, 40.0, 238.0, 160.0, 288.0, 40.0, 338.0, 160.0, 388.0, 240.0, 37.0, 240.0, 87.0, 120.0, 137.0, 160.0, 424.0, 200.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 102.0, 389.0, 80.0, 38.0, 160.0, 88.0, 40.0, 138.0, 160.0, 188.0, 240.0, 238.0, 160.0, 288.0, 120.0, 338.0, 80.0, 24.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 390.0, 120.0, 39.0, 80.0, 89.0, 80.0, 139.0, 120.0, 189.0, 160.0, 239.0, 200.0, 289.0, 120.0, 339.0, 200.0, 1952.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 190.0, 120.0, 240.0, 240.0, 290.0, 240.0, 340.0, 160.0, 390.0, 160.0, 39.0, 200.0, 89.0, 120.0, 139.0, 200.0, 1552.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 70.0, 191.0, 200.0, 241.0, 240.0, 291.0, 160.0, 341.0, 160.0, 391.0, 240.0, 40.0, 120.0, 90.0, 40.0, 140.0, 200.0, 752.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 392.0, 40.0, 41.0, 240.0, 91.0, 160.0, 141.0, 160.0, 191.0, 240.0, 241.0, 120.0, 291.0, 160.0, 341.0, 40.0, 352.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 192.0, 40.0, 242.0, 160.0, 292.0, 240.0, 342.0, 120.0, 392.0, 120.0, 41.0, 120.0, 91.0, 160.0, 141.0, 40.0, 1860.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 100.0, 393.0, 240.0, 42.0, 160.0, 92.0, 240.0, 142.0, 120.0, 192.0, 120.0, 242.0, 160.0, 292.0, 240.0, 342.0, 120.0, 1460.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 193.0, 240.0, 243.0, 120.0, 293.0, 40.0, 343.0, 160.0, 393.0, 120.0, 42.0, 160.0, 92.0, 240.0, 142.0, 120.0, 1060.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 394.0, 120.0, 43.0, 120.0, 93.0, 40.0, 143.0, 160.0, 193.0, 120.0, 243.0, 120.0, 293.0, 200.0, 343.0, 40.0, 660.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 194.0, 120.0, 244.0, 40.0, 294.0, 160.0, 344.0, 160.0, 394.0, 80.0, 43.0, 120.0, 93.0, 200.0, 143.0, 40.0, 260.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 90.0, 395.0, 240.0, 44.0, 40.0, 94.0, 160.0, 144.0, 160.0, 194.0, 80.0, 244.0, 80.0, 294.0, 160.0, 344.0, 240.0, 1768.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 195.0, 240.0, 245.0, 160.0, 295.0, 40.0, 345.0, 160.0, 395.0, 240.0, 44.0, 80.0, 94.0, 160.0, 144.0, 240.0, 1368.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 108.0, 396.0, 80.0, 45.0, 160.0, 95.0, 40.0, 145.0, 160.0, 195.0, 240.0, 245.0, 160.0, 295.0, 120.0, 345.0, 120.0, 968.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 64.0, 196.0, 80.0, 246.0, 80.0, 296.0, 160.0, 346.0, 240.0, 396.0, 120.0, 45.0, 160.0, 95.0, 120.0, 145.0, 120.0, 568.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 397.0, 80.0, 46.0, 80.0, 96.0, 160.0, 146.0, 240.0, 196.0, 120.0, 246.0, 120.0, 296.0, 240.0, 346.0, 160.0, 168.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 74.0, 197.0, 80.0, 247.0, 80.0, 297.0, 240.0, 347.0, 80.0, 397.0, 40.0, 46.0, 120.0, 96.0, 240.0, 146.0, 160.0, 1756.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 398.0, 160.0, 47.0, 80.0, 97.0, 240.0, 147.0, 80.0, 197.0, 40.0, 247.0, 160.0, 297.0, 160.0, 347.0, 160.0, 1356.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 210.0, 198.0, 160.0, 248.0, 80.0, 298.0, 160.0, 348.0, 160.0, 398.0, 240.0, 47.0, 160.0, 97.0, 160.0, 147.0, 160.0, 956.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 194.0, 399.0, 120.0, 48.0, 80.0, 98.0, 160.0, 148.0, 160.0, 198.0, 240.0, 248.0, 120.0, 298.0, 120.0, 348.0, 240.0, 556.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 174.0, 199.0, 120.0, 249.0, 160.0, 299.0, 160.0, 349.0, 160.0, 399.0, 120.0, 48.0, 120.0, 98.0, 120.0, 148.0, 240.0, 156.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 200.0, -1.0, 120.0, 49.0, 160.0, 99.0, 160.0, 149.0, 160.0, 199.0, 120.0, 249.0, 80.0, 299.0, 80.0, 349.0, 120.0, 419.0, 109.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 166.0, 200.0, 40.0, 250.0, 120.0, 300.0, 120.0, 350.0, 80.0, -1.0, 120.0, 49.0, 80.0, 99.0, 80.0, 149.0, 120.0, 19.0, 109.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 156.0, 0.0, 40.0, 50.0, 120.0, 100.0, 120.0, 150.0, 80.0, 200.0, 120.0, 250.0, 80.0, 300.0, 120.0, 350.0, 120.0, 601.0, 69.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 156.0, 201.0, 40.0, 251.0, 160.0, 301.0, 200.0, 351.0, 80.0, 0.0, 120.0, 50.0, 80.0, 100.0, 120.0, 150.0, 120.0, 201.0, 69.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 236.0, 1.0, 40.0, 51.0, 160.0, 101.0, 200.0, 151.0, 80.0, 201.0, 200.0, 251.0, 160.0, 301.0, 200.0, 351.0, 200.0, 654.0, 161.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 236.0, 202.0, 120.0, 252.0, 40.0, 302.0, 160.0, 352.0, 40.0, 1.0, 200.0, 51.0, 160.0, 101.0, 200.0, 151.0, 200.0, 254.0, 161.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 212.0, 2.0, 120.0, 52.0, 40.0, 102.0, 160.0, 152.0, 40.0, 202.0, 120.0, 252.0, 80.0, 302.0, 80.0, 352.0, 160.0, 692.0, 193.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 150.0, 203.0, 40.0, 253.0, 200.0, 303.0, 200.0, 353.0, 240.0, 2.0, 120.0, 52.0, 80.0, 102.0, 80.0, 152.0, 160.0, 292.0, 193.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 116.0, 3.0, 40.0, 53.0, 200.0, 103.0, 200.0, 153.0, 240.0, 203.0, 200.0, 253.0, 200.0, 303.0, 80.0, 353.0, 200.0, 869.0, 102.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 204.0, 40.0, 254.0, 240.0, 304.0, 80.0, 354.0, 120.0, 3.0, 200.0, 53.0, 200.0, 103.0, 80.0, 153.0, 200.0, 469.0, 102.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 4.0, 40.0, 54.0, 240.0, 104.0, 80.0, 154.0, 120.0, 204.0, 200.0, 254.0, 80.0, 304.0, 40.0, 354.0, 240.0, 1976.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 158.0, 205.0, 80.0, 255.0, 120.0, 305.0, 200.0, 355.0, 160.0, 4.0, 200.0, 54.0, 80.0, 104.0, 40.0, 154.0, 240.0, 1576.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 158.0, 5.0, 80.0, 55.0, 120.0, 105.0, 200.0, 206.0, 240.0, 255.0, 80.0, 305.0, 160.0, 355.0, 240.0, 1176.0, 70.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 206.0, 80.0, 256.0, 80.0, 306.0, 240.0, 6.0, 240.0, 55.0, 80.0, 105.0, 160.0, 155.0, 240.0, 776.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 6.0, 80.0, 56.0, 80.0, 106.0, 240.0, 207.0, 160.0, 256.0, 240.0, 306.0, 80.0, 356.0, 240.0, 376.0, 70.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 110.0, 207.0, 80.0, 257.0, 120.0, 307.0, 80.0, 7.0, 160.0, 56.0, 240.0, 106.0, 80.0, 156.0, 240.0, 1884.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 164.0, 7.0, 80.0, 57.0, 120.0, 107.0, 80.0, 208.0, 160.0, 257.0, 160.0, 307.0, 160.0, 357.0, 160.0, 1484.0, 70.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 118.0, 208.0, 80.0, 258.0, 240.0, 308.0, 120.0, 8.0, 160.0, 57.0, 160.0, 107.0, 160.0, 157.0, 160.0, 1084.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 146.0, 8.0, 80.0, 58.0, 240.0, 108.0, 120.0, 209.0, 80.0, 258.0, 240.0, 308.0, 200.0, 358.0, 80.0, 684.0, 70.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 134.0, 209.0, 120.0, 259.0, 200.0, 309.0, 80.0, 9.0, 80.0, 58.0, 240.0, 108.0, 200.0, 158.0, 80.0, 284.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 150.0, 9.0, 120.0, 59.0, 200.0, 109.0, 80.0, 210.0, 80.0, 259.0, 120.0, 309.0, 80.0, 359.0, 240.0, 739.0, 91.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 168.0, 210.0, 120.0, 260.0, 120.0, 310.0, 160.0, 10.0, 80.0, 59.0, 120.0, 109.0, 80.0, 159.0, 240.0, 339.0, 91.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 208.0, 10.0, 120.0, 60.0, 120.0, 110.0, 160.0, 211.0, 120.0, 260.0, 240.0, 310.0, 120.0, 360.0, 120.0, 887.0, 59.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 160.0, 211.0, 160.0, 261.0, 200.0, 311.0, 80.0, 11.0, 120.0, 60.0, 240.0, 110.0, 120.0, 160.0, 120.0, 487.0, 59.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 250.0, 11.0, 160.0, 61.0, 200.0, 111.0, 80.0, 212.0, 120.0, 261.0, 40.0, 311.0, 40.0, 361.0, 160.0, 87.0, 59.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 172.0, 212.0, 160.0, 262.0, 160.0, 312.0, 40.0, 12.0, 120.0, 61.0, 40.0, 111.0, 40.0, 161.0, 160.0, 597.0, 211.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 94.0, 12.0, 160.0, 62.0, 160.0, 112.0, 40.0, 213.0, 200.0, 262.0, 80.0, 312.0, 80.0, 362.0, 160.0, 197.0, 211.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 213.0, 200.0, 263.0, 160.0, 313.0, 120.0, 13.0, 200.0, 62.0, 80.0, 112.0, 80.0, 162.0, 160.0, 501.0, 231.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 66.0, 13.0, 200.0, 63.0, 160.0, 113.0, 120.0, 214.0, 40.0, 263.0, 120.0, 313.0, 80.0, 363.0, 120.0, 101.0, 231.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 200.0, 214.0, 160.0, 264.0, 200.0, 314.0, 200.0, 14.0, 40.0, 63.0, 120.0, 113.0, 80.0, 163.0, 120.0, 232.0, 213.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 246.0, 14.0, 160.0, 64.0, 200.0, 114.0, 200.0, 215.0, 160.0, 264.0, 160.0, 314.0, 160.0, 364.0, 40.0, 1740.0, 70.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 218.0, 215.0, 120.0, 265.0, 120.0, 315.0, 120.0, 15.0, 160.0, 64.0, 160.0, 114.0, 160.0, 164.0, 40.0, 1340.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 156.0, 15.0, 120.0, 65.0, 120.0, 115.0, 120.0, 216.0, 200.0, 265.0, 40.0, 315.0, 120.0, 365.0, 120.0, 940.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 84.0, 216.0, 240.0, 266.0, 200.0, 316.0, 40.0, 16.0, 200.0, 65.0, 40.0, 115.0, 120.0, 165.0, 120.0, 540.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 16.0, 240.0, 66.0, 200.0, 116.0, 40.0, 217.0, 160.0, 266.0, 40.0, 316.0, 40.0, 366.0, 200.0, 140.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 108.0, 217.0, 200.0, 267.0, 120.0, 317.0, 240.0, 17.0, 160.0, 66.0, 40.0, 116.0, 40.0, 166.0, 200.0, 1648.0, 70.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 17.0, 200.0, 67.0, 120.0, 117.0, 240.0, 218.0, 240.0, 267.0, 240.0, 317.0, 120.0, 367.0, 240.0, 1248.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 218.0, 120.0, 268.0, 200.0, 318.0, 240.0, 18.0, 240.0, 67.0, 240.0, 117.0, 120.0, 167.0, 240.0, 848.0, 70.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 56.0, 18.0, 120.0, 68.0, 200.0, 118.0, 240.0, 219.0, 240.0, 268.0, 200.0, 318.0, 200.0, 368.0, 240.0, 448.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 110.0, 219.0, 80.0, 269.0, 240.0, 319.0, 160.0, 19.0, 240.0, 68.0, 200.0, 118.0, 200.0, 168.0, 240.0, 48.0, 70.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 19.0, 80.0, 69.0, 240.0, 119.0, 160.0, 220.0, 40.0, 269.0, 80.0, 319.0, 80.0, 369.0, 200.0, 458.0, 35.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 144.0, 220.0, 200.0, 270.0, 120.0, 320.0, 120.0, 20.0, 40.0, 69.0, 80.0, 119.0, 80.0, 169.0, 200.0, 58.0, 35.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 156.0, 20.0, 200.0, 70.0, 120.0, 120.0, 120.0, 221.0, 200.0, 270.0, 200.0, 320.0, 80.0, 370.0, 160.0, 357.0, 7.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 221.0, 40.0, 271.0, 240.0, 321.0, 40.0, 21.0, 200.0, 70.0, 200.0, 120.0, 80.0, 170.0, 160.0, 1956.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 88.0, 72.0, 240.0, 121.0, 40.0, 222.0, 160.0, 271.0, 120.0, 321.0, 200.0, 371.0, 40.0, 1556.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 76.0, 273.0, 240.0, 322.0, 240.0, 22.0, 160.0, 71.0, 120.0, 121.0, 200.0, 171.0, 40.0, 1156.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 76.0, 73.0, 240.0, 122.0, 240.0, 223.0, 120.0, 272.0, 240.0, 322.0, 80.0, 372.0, 200.0, 756.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 274.0, 200.0, 323.0, 40.0, 23.0, 120.0, 72.0, 240.0, 122.0, 80.0, 172.0, 200.0, 356.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 74.0, 200.0, 123.0, 40.0, 224.0, 240.0, 273.0, 80.0, 323.0, 240.0, 373.0, 200.0, 1890.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 275.0, 200.0, 324.0, 80.0, 24.0, 240.0, 73.0, 80.0, 123.0, 240.0, 173.0, 200.0, 1490.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 75.0, 200.0, 124.0, 80.0, 225.0, 160.0, 274.0, 40.0, 324.0, 240.0, 374.0, 120.0, 1090.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 94.0, 276.0, 120.0, 325.0, 240.0, 25.0, 160.0, 74.0, 40.0, 124.0, 240.0, 174.0, 120.0, 690.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 68.0, 76.0, 120.0, 125.0, 240.0, 226.0, 160.0, 275.0, 120.0, 325.0, 160.0, 375.0, 240.0, 290.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 74.0, 277.0, 120.0, 326.0, 80.0, 26.0, 160.0, 75.0, 120.0, 125.0, 160.0, 175.0, 240.0, 1798.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 220.0, 77.0, 120.0, 126.0, 80.0, 227.0, 120.0, 276.0, 160.0, 326.0, 200.0, 376.0, 120.0, 1398.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 250.0, 278.0, 40.0, 327.0, 40.0, 27.0, 120.0, 76.0, 160.0, 126.0, 200.0, 176.0, 120.0, 998.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));

		
	}
	
	private static void buildTrenningPatternList100(ArrayList<TreningPattern> treningPatterns, int c){
		
		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 194.0, 200.0, 244.0, 120.0, 294.0, 120.0, 344.0, 260.0, 294.0, 120.0, 43.0, 200.0, 93.0, 180.0, 143.0, 160.0, 2155.0, 200.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 195.0, 200.0, 245.0, 240.0, 295.0, 240.0, 345.0, 200.0, 395.0, 120.0, 44.0, 80.0, 94.0, 160.0, 144.0, 120.0, 4355.0, 200.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 197.0, 80.0, 247.0, 200.0, 297.0, 80.0, 347.0, 200.0, 397.0, 160.0, 46.0, 240.0, 96.0, 120.0, 146.0, 240.0, 2755.0, 200.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 238.0, 0.0, 200.0, 50.0, 160.0, 100.0, 200.0, 150.0, 200.0, 200.0, 240.0, 250.0, 120.0, 300.0, 160.0, 350.0, 160.0, 1890.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 157.0, 120.0, 207.0, 160.0, 257.0, 200.0, 307.0, 240.0, 357.0, 200.0, 6.0, 200.0, 56.0, 200.0, 106.0, 120.0, 10023.0, 120.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 62.0, 188.0, 40.0, 238.0, 160.0, 288.0, 40.0, 338.0, 160.0, 388.0, 240.0, 37.0, 240.0, 87.0, 120.0, 137.0, 160.0, 424.0, 200.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 390.0, 120.0, 39.0, 80.0, 89.0, 80.0, 139.0, 120.0, 189.0, 160.0, 239.0, 200.0, 289.0, 120.0, 339.0, 200.0, 1952.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 70.0, 191.0, 200.0, 241.0, 240.0, 291.0, 160.0, 341.0, 160.0, 391.0, 240.0, 40.0, 120.0, 90.0, 40.0, 140.0, 200.0, 752.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 192.0, 40.0, 242.0, 160.0, 292.0, 240.0, 342.0, 120.0, 392.0, 120.0, 41.0, 120.0, 91.0, 160.0, 141.0, 40.0, 1860.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 193.0, 240.0, 243.0, 120.0, 293.0, 40.0, 343.0, 160.0, 393.0, 120.0, 42.0, 160.0, 92.0, 240.0, 142.0, 120.0, 1060.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 108.0, 396.0, 80.0, 45.0, 160.0, 95.0, 40.0, 145.0, 160.0, 195.0, 240.0, 245.0, 160.0, 295.0, 120.0, 345.0, 120.0, 968.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 64.0, 196.0, 80.0, 246.0, 80.0, 296.0, 160.0, 346.0, 240.0, 396.0, 120.0, 45.0, 160.0, 95.0, 120.0, 145.0, 120.0, 568.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 397.0, 80.0, 46.0, 80.0, 96.0, 160.0, 146.0, 240.0, 196.0, 120.0, 246.0, 120.0, 296.0, 240.0, 346.0, 160.0, 168.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 194.0, 399.0, 120.0, 48.0, 80.0, 98.0, 160.0, 148.0, 160.0, 198.0, 240.0, 248.0, 120.0, 298.0, 120.0, 348.0, 240.0, 556.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 174.0, 199.0, 120.0, 249.0, 160.0, 299.0, 160.0, 349.0, 160.0, 399.0, 120.0, 48.0, 120.0, 98.0, 120.0, 148.0, 240.0, 156.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 166.0, 200.0, 40.0, 250.0, 120.0, 300.0, 120.0, 350.0, 80.0, -1.0, 120.0, 49.0, 80.0, 99.0, 80.0, 149.0, 120.0, 19.0, 109.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 156.0, 0.0, 40.0, 50.0, 120.0, 100.0, 120.0, 150.0, 80.0, 200.0, 120.0, 250.0, 80.0, 300.0, 120.0, 350.0, 120.0, 601.0, 69.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 212.0, 2.0, 120.0, 52.0, 40.0, 102.0, 160.0, 152.0, 40.0, 202.0, 120.0, 252.0, 80.0, 302.0, 80.0, 352.0, 160.0, 692.0, 193.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 150.0, 203.0, 40.0, 253.0, 200.0, 303.0, 200.0, 353.0, 240.0, 2.0, 120.0, 52.0, 80.0, 102.0, 80.0, 152.0, 160.0, 292.0, 193.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 116.0, 3.0, 40.0, 53.0, 200.0, 103.0, 200.0, 153.0, 240.0, 203.0, 200.0, 253.0, 200.0, 303.0, 80.0, 353.0, 200.0, 869.0, 102.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 204.0, 40.0, 254.0, 240.0, 304.0, 80.0, 354.0, 120.0, 3.0, 200.0, 53.0, 200.0, 103.0, 80.0, 153.0, 200.0, 469.0, 102.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 206.0, 80.0, 256.0, 80.0, 306.0, 240.0, 6.0, 240.0, 55.0, 80.0, 105.0, 160.0, 155.0, 240.0, 776.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 110.0, 207.0, 80.0, 257.0, 120.0, 307.0, 80.0, 7.0, 160.0, 56.0, 240.0, 106.0, 80.0, 156.0, 240.0, 1884.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 118.0, 208.0, 80.0, 258.0, 240.0, 308.0, 120.0, 8.0, 160.0, 57.0, 160.0, 107.0, 160.0, 157.0, 160.0, 1084.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 134.0, 209.0, 120.0, 259.0, 200.0, 309.0, 80.0, 9.0, 80.0, 58.0, 240.0, 108.0, 200.0, 158.0, 80.0, 284.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 160.0, 211.0, 160.0, 261.0, 200.0, 311.0, 80.0, 11.0, 120.0, 60.0, 240.0, 110.0, 120.0, 160.0, 120.0, 487.0, 59.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 172.0, 212.0, 160.0, 262.0, 160.0, 312.0, 40.0, 12.0, 120.0, 61.0, 40.0, 111.0, 40.0, 161.0, 160.0, 597.0, 211.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 94.0, 12.0, 160.0, 62.0, 160.0, 112.0, 40.0, 213.0, 200.0, 262.0, 80.0, 312.0, 80.0, 362.0, 160.0, 197.0, 211.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 66.0, 13.0, 200.0, 63.0, 160.0, 113.0, 120.0, 214.0, 40.0, 263.0, 120.0, 313.0, 80.0, 363.0, 120.0, 101.0, 231.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 218.0, 215.0, 120.0, 265.0, 120.0, 315.0, 120.0, 15.0, 160.0, 64.0, 160.0, 114.0, 160.0, 164.0, 40.0, 1340.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 156.0, 15.0, 120.0, 65.0, 120.0, 115.0, 120.0, 216.0, 200.0, 265.0, 40.0, 315.0, 120.0, 365.0, 120.0, 940.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 84.0, 216.0, 240.0, 266.0, 200.0, 316.0, 40.0, 16.0, 200.0, 65.0, 40.0, 115.0, 120.0, 165.0, 120.0, 540.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 16.0, 240.0, 66.0, 200.0, 116.0, 40.0, 217.0, 160.0, 266.0, 40.0, 316.0, 40.0, 366.0, 200.0, 140.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 17.0, 200.0, 67.0, 120.0, 117.0, 240.0, 218.0, 240.0, 267.0, 240.0, 317.0, 120.0, 367.0, 240.0, 1248.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 221.0, 40.0, 271.0, 240.0, 321.0, 40.0, 21.0, 200.0, 70.0, 200.0, 120.0, 80.0, 170.0, 160.0, 1956.0, 70.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 76.0, 273.0, 240.0, 322.0, 240.0, 22.0, 160.0, 71.0, 120.0, 121.0, 200.0, 171.0, 40.0, 1156.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 274.0, 200.0, 323.0, 40.0, 23.0, 120.0, 72.0, 240.0, 122.0, 80.0, 172.0, 200.0, 356.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 94.0, 276.0, 120.0, 325.0, 240.0, 25.0, 160.0, 74.0, 40.0, 124.0, 240.0, 174.0, 120.0, 690.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 68.0, 76.0, 120.0, 125.0, 240.0, 226.0, 160.0, 275.0, 120.0, 325.0, 160.0, 375.0, 240.0, 290.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 66.0, 13.0, 200.0, 63.0, 160.0, 113.0, 120.0, 214.0, 40.0, 263.0, 120.0, 313.0, 80.0, 363.0, 120.0, 101.0, 231.0, 0.0, 0.0}, new double[] {1.0, 0.0, 0.0}));
	
	}
	
	private static void buildTrenningPatternList010(ArrayList<TreningPattern> treningPatterns, int c){
		
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 398.0, 80.0, 47.0, 200.0, 97.0, 80.0, 147.0, 200.0, 197.0, 160.0, 247.0, 80.0, 297.0, 200.0, 347.0, 80.0, 355.0, 200.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 250.0, 199.0, 80.0, 249.0, 120.0, 299.0, 80.0, 349.0, 160.0, 399.0, 200.0, 48.0, 200.0, 98.0, 200.0, 148.0, 200.0, 1155.0, 200.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 358.0, 200.0, 7.0, 160.0, 57.0, 200.0, 107.0, 240.0, 157.0, 200.0, 207.0, 200.0, 257.0, 80.0, 307.0, 200.0, 9623.0, 120.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 159.0, 120.0, 209.0, 240.0, 259.0, 160.0, 309.0, 200.0, 359.0, 200.0, 8.0, 240.0, 58.0, 160.0, 108.0, 80.0, 8423.0, 120.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 128.0, 200.0, 178.0, 160.0, 228.0, 160.0, 278.0, 80.0, 28.0, 200.0, 77.0, 200.0, 1207.0, 80.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 275.0, 200.0, 324.0, 80.0, 24.0, 240.0, 73.0, 80.0, 123.0, 240.0, 173.0, 200.0, 1490.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 75.0, 200.0, 124.0, 80.0, 225.0, 160.0, 274.0, 40.0, 324.0, 240.0, 374.0, 120.0, 1090.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 76.0, 73.0, 240.0, 122.0, 240.0, 223.0, 120.0, 272.0, 240.0, 322.0, 80.0, 372.0, 200.0, 756.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 218.0, 120.0, 268.0, 200.0, 318.0, 240.0, 18.0, 240.0, 67.0, 240.0, 117.0, 120.0, 167.0, 240.0, 848.0, 70.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 158.0, 5.0, 80.0, 55.0, 120.0, 105.0, 200.0, 206.0, 240.0, 255.0, 80.0, 305.0, 160.0, 355.0, 240.0, 1176.0, 70.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 6.0, 80.0, 56.0, 80.0, 106.0, 240.0, 207.0, 160.0, 256.0, 240.0, 306.0, 80.0, 356.0, 240.0, 376.0, 70.0, 0.0, 0.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 236.0, 202.0, 120.0, 252.0, 40.0, 302.0, 160.0, 352.0, 40.0, 1.0, 200.0, 51.0, 160.0, 101.0, 200.0, 151.0, 200.0, 254.0, 161.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 4.0, 40.0, 54.0, 240.0, 104.0, 80.0, 154.0, 120.0, 204.0, 200.0, 254.0, 80.0, 304.0, 40.0, 354.0, 240.0, 1976.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 156.0, 201.0, 40.0, 251.0, 160.0, 301.0, 200.0, 351.0, 80.0, 0.0, 120.0, 50.0, 80.0, 100.0, 120.0, 150.0, 120.0, 201.0, 69.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 394.0, 120.0, 43.0, 120.0, 93.0, 40.0, 143.0, 160.0, 193.0, 120.0, 243.0, 120.0, 293.0, 200.0, 343.0, 40.0, 660.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 194.0, 120.0, 244.0, 40.0, 294.0, 160.0, 344.0, 160.0, 394.0, 80.0, 43.0, 120.0, 93.0, 200.0, 143.0, 40.0, 260.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 391.0, 200.0, 40.0, 240.0, 90.0, 240.0, 140.0, 160.0, 190.0, 160.0, 240.0, 120.0, 290.0, 40.0, 340.0, 200.0, 1152.0, 70.0}, new double[] {0.0, 1.0, 0.0}));
		
	}

	private static void buildTrenningPatternList001(ArrayList<TreningPattern> treningPatterns, int c){
	
		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 192.0, 160.0, 242.0, 40.0, 292.0, 80.0, 342.0, 160.0, 392.0, 160.0, 442.0, 120.0, 492.0, 120.0, 542.0, 120.0, 1755.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 393.0, 160.0, 42.0, 40.0, 92.0, 80.0, 142.0, 160.0, 192.0, 160.0, 242.0, 120.0, 292.0, 120.0, 342.0, 120.0, 6355.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 106.0, 397.0, 80.0, 46.0, 80.0, 96.0, 40.0, 146.0, 160.0, 196.0, 240.0, 246.0, 240.0, 296.0, 120.0, 346.0, 240.0, 3155.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 118.0, 198.0, 80.0, 248.0, 160.0, 298.0, 80.0, 348.0, 120.0, 398.0, 200.0, 47.0, 80.0, 97.0, 200.0, 147.0, 80.0, 1955.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 244.0, 200.0, 200.0, 250.0, 160.0, 300.0, 200.0, 350.0, 200.0, -1.0, 200.0, 49.0, 40.0, 99.0, 120.0, 149.0, 120.0, 355.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 187.0, 200.0, 237.0, 200.0, 287.0, 200.0, 337.0, 120.0, 387.0, 160.0, 437.0, 240.0, 487.0, 120.0, 537.0, 160.0, 1224.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 102.0, 388.0, 40.0, 37.0, 200.0, 87.0, 200.0, 137.0, 120.0, 187.0, 160.0, 237.0, 240.0, 287.0, 120.0, 337.0, 160.0, 824.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 102.0, 389.0, 80.0, 38.0, 160.0, 88.0, 40.0, 138.0, 160.0, 188.0, 240.0, 238.0, 160.0, 288.0, 120.0, 338.0, 80.0, 24.0, 200.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 72.0, 190.0, 120.0, 240.0, 240.0, 290.0, 240.0, 340.0, 160.0, 390.0, 160.0, 39.0, 200.0, 89.0, 120.0, 139.0, 200.0, 1552.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 392.0, 40.0, 41.0, 240.0, 91.0, 160.0, 141.0, 160.0, 191.0, 240.0, 241.0, 120.0, 291.0, 160.0, 341.0, 40.0, 352.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 100.0, 393.0, 240.0, 42.0, 160.0, 92.0, 240.0, 142.0, 120.0, 192.0, 120.0, 242.0, 160.0, 292.0, 240.0, 342.0, 120.0, 1460.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 90.0, 395.0, 240.0, 44.0, 40.0, 94.0, 160.0, 144.0, 160.0, 194.0, 80.0, 244.0, 80.0, 294.0, 160.0, 344.0, 240.0, 1768.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 195.0, 240.0, 245.0, 160.0, 295.0, 40.0, 345.0, 160.0, 395.0, 240.0, 44.0, 80.0, 94.0, 160.0, 144.0, 240.0, 1368.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 74.0, 197.0, 80.0, 247.0, 80.0, 297.0, 240.0, 347.0, 80.0, 397.0, 40.0, 46.0, 120.0, 96.0, 240.0, 146.0, 160.0, 1756.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 398.0, 160.0, 47.0, 80.0, 97.0, 240.0, 147.0, 80.0, 197.0, 40.0, 247.0, 160.0, 297.0, 160.0, 347.0, 160.0, 1356.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 210.0, 198.0, 160.0, 248.0, 80.0, 298.0, 160.0, 348.0, 160.0, 398.0, 240.0, 47.0, 160.0, 97.0, 160.0, 147.0, 160.0, 956.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 200.0, -1.0, 120.0, 49.0, 160.0, 99.0, 160.0, 149.0, 160.0, 199.0, 120.0, 249.0, 80.0, 299.0, 80.0, 349.0, 120.0, 419.0, 109.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 236.0, 1.0, 40.0, 51.0, 160.0, 101.0, 200.0, 151.0, 80.0, 201.0, 200.0, 251.0, 160.0, 301.0, 200.0, 351.0, 200.0, 654.0, 161.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 158.0, 205.0, 80.0, 255.0, 120.0, 305.0, 200.0, 355.0, 160.0, 4.0, 200.0, 54.0, 80.0, 104.0, 40.0, 154.0, 240.0, 1576.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 164.0, 7.0, 80.0, 57.0, 120.0, 107.0, 80.0, 208.0, 160.0, 257.0, 160.0, 307.0, 160.0, 357.0, 160.0, 1484.0, 70.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 146.0, 8.0, 80.0, 58.0, 240.0, 108.0, 120.0, 209.0, 80.0, 258.0, 240.0, 308.0, 200.0, 358.0, 80.0, 684.0, 70.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 150.0, 9.0, 120.0, 59.0, 200.0, 109.0, 80.0, 210.0, 80.0, 259.0, 120.0, 309.0, 80.0, 359.0, 240.0, 739.0, 91.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 168.0, 210.0, 120.0, 260.0, 120.0, 310.0, 160.0, 10.0, 80.0, 59.0, 120.0, 109.0, 80.0, 159.0, 240.0, 339.0, 91.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 208.0, 10.0, 120.0, 60.0, 120.0, 110.0, 160.0, 211.0, 120.0, 260.0, 240.0, 310.0, 120.0, 360.0, 120.0, 887.0, 59.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 250.0, 11.0, 160.0, 61.0, 200.0, 111.0, 80.0, 212.0, 120.0, 261.0, 40.0, 311.0, 40.0, 361.0, 160.0, 87.0, 59.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 213.0, 200.0, 263.0, 160.0, 313.0, 120.0, 13.0, 200.0, 62.0, 80.0, 112.0, 80.0, 162.0, 160.0, 501.0, 231.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 200.0, 214.0, 160.0, 264.0, 200.0, 314.0, 200.0, 14.0, 40.0, 63.0, 120.0, 113.0, 80.0, 163.0, 120.0, 232.0, 213.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 246.0, 14.0, 160.0, 64.0, 200.0, 114.0, 200.0, 215.0, 160.0, 264.0, 160.0, 314.0, 160.0, 364.0, 40.0, 1740.0, 70.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 108.0, 217.0, 200.0, 267.0, 120.0, 317.0, 240.0, 17.0, 160.0, 66.0, 40.0, 116.0, 40.0, 166.0, 200.0, 1648.0, 70.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 110.0, 219.0, 80.0, 269.0, 240.0, 319.0, 160.0, 19.0, 240.0, 68.0, 200.0, 118.0, 200.0, 168.0, 240.0, 48.0, 70.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 112.0, 19.0, 80.0, 69.0, 240.0, 119.0, 160.0, 220.0, 40.0, 269.0, 80.0, 319.0, 80.0, 369.0, 200.0, 458.0, 35.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 144.0, 220.0, 200.0, 270.0, 120.0, 320.0, 120.0, 20.0, 40.0, 69.0, 80.0, 119.0, 80.0, 169.0, 200.0, 58.0, 35.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 156.0, 20.0, 200.0, 70.0, 120.0, 120.0, 120.0, 221.0, 200.0, 270.0, 200.0, 320.0, 80.0, 370.0, 160.0, 357.0, 7.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 88.0, 72.0, 240.0, 121.0, 40.0, 222.0, 160.0, 271.0, 120.0, 321.0, 200.0, 371.0, 40.0, 1556.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 114.0, 74.0, 200.0, 123.0, 40.0, 224.0, 240.0, 273.0, 80.0, 323.0, 240.0, 373.0, 200.0, 1890.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 74.0, 277.0, 120.0, 326.0, 80.0, 26.0, 160.0, 75.0, 120.0, 125.0, 160.0, 175.0, 240.0, 1798.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 220.0, 77.0, 120.0, 126.0, 80.0, 227.0, 120.0, 276.0, 160.0, 326.0, 200.0, 376.0, 120.0, 1398.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 250.0, 278.0, 40.0, 327.0, 40.0, 27.0, 120.0, 76.0, 160.0, 126.0, 200.0, 176.0, 120.0, 998.0, 70.0, 0.0, 0.0, 0.0, 0.0}, new double[] {0.0, 0.0, 1.0}));
	
	}

}
