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
			buildTrenningPatternList(treningPatterns);
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
		System.out.println("traininglist: " + treningPatterns.size());
		System.out.println("ERROR_VALUE (stop): " + Constants.ERROR_VALUE);
		System.out.println("EPSILON : " + Constants.EPSILON);
		System.out.println("BIAS: " + Constants.BIAS);
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
		
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 188.0, 275.0, 120.0, 325.0, 120.0, 375.0, 40.0, 425.0, 80.0, 475.0, 40.0, 525.0, 120.0, 575.0, 200.0, 625.0, 200.0, 3204.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 190.0, 274.0, 120.0, 324.0, 120.0, 374.0, 40.0, 424.0, 80.0, 474.0, 40.0, 524.0, 120.0, 574.0, 200.0, 624.0, 200.0, 3202.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 192.0, 273.0, 120.0, 323.0, 120.0, 373.0, 40.0, 423.0, 80.0, 473.0, 40.0, 523.0, 120.0, 573.0, 200.0, 623.0, 200.0, 3200.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 194.0, 272.0, 120.0, 322.0, 120.0, 372.0, 40.0, 422.0, 80.0, 472.0, 40.0, 522.0, 120.0, 572.0, 200.0, 622.0, 200.0, 3198.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 214.0, 257.0, 120.0, 307.0, 120.0, 357.0, 40.0, 407.0, 80.0, 457.0, 40.0, 507.0, 120.0, 557.0, 200.0, 607.0, 200.0, 3168.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 212.0, 256.0, 120.0, 306.0, 120.0, 356.0, 40.0, 406.0, 80.0, 456.0, 40.0, 506.0, 120.0, 556.0, 200.0, 606.0, 200.0, 3166.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 210.0, 255.0, 120.0, 305.0, 120.0, 355.0, 40.0, 405.0, 80.0, 455.0, 40.0, 505.0, 120.0, 555.0, 200.0, 605.0, 200.0, 3164.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 208.0, 254.0, 120.0, 304.0, 120.0, 354.0, 40.0, 404.0, 80.0, 454.0, 40.0, 504.0, 120.0, 554.0, 200.0, 604.0, 200.0, 3162.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 194.0, 169.0, 120.0, 219.0, 120.0, 269.0, 40.0, 319.0, 80.0, 369.0, 40.0, 419.0, 120.0, 469.0, 200.0, 519.0, 200.0, 2992.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 196.0, 168.0, 120.0, 218.0, 120.0, 268.0, 40.0, 318.0, 80.0, 368.0, 40.0, 418.0, 120.0, 468.0, 200.0, 518.0, 200.0, 2990.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 198.0, 167.0, 120.0, 217.0, 120.0, 267.0, 40.0, 317.0, 80.0, 367.0, 40.0, 417.0, 120.0, 467.0, 200.0, 517.0, 200.0, 2988.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 200.0, 166.0, 120.0, 216.0, 120.0, 266.0, 40.0, 316.0, 80.0, 366.0, 40.0, 416.0, 120.0, 466.0, 200.0, 516.0, 200.0, 2986.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 202.0, 165.0, 120.0, 215.0, 120.0, 265.0, 40.0, 315.0, 80.0, 365.0, 40.0, 415.0, 120.0, 465.0, 200.0, 515.0, 200.0, 2984.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 204.0, 164.0, 120.0, 214.0, 120.0, 264.0, 40.0, 314.0, 80.0, 364.0, 40.0, 414.0, 120.0, 464.0, 200.0, 514.0, 200.0, 2982.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 206.0, 163.0, 120.0, 213.0, 120.0, 263.0, 40.0, 313.0, 80.0, 363.0, 40.0, 413.0, 120.0, 463.0, 200.0, 513.0, 200.0, 2980.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 208.0, 162.0, 120.0, 212.0, 120.0, 262.0, 40.0, 312.0, 80.0, 362.0, 40.0, 412.0, 120.0, 462.0, 200.0, 512.0, 200.0, 2978.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 210.0, 161.0, 120.0, 211.0, 120.0, 261.0, 40.0, 311.0, 80.0, 361.0, 40.0, 411.0, 120.0, 461.0, 200.0, 511.0, 200.0, 2976.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 208.0, 140.0, 120.0, 190.0, 120.0, 240.0, 40.0, 290.0, 80.0, 340.0, 40.0, 390.0, 120.0, 440.0, 200.0, 490.0, 200.0, 2934.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 192.0, 132.0, 120.0, 182.0, 120.0, 232.0, 40.0, 282.0, 80.0, 332.0, 40.0, 382.0, 120.0, 432.0, 200.0, 482.0, 200.0, 2918.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 190.0, 131.0, 120.0, 181.0, 120.0, 231.0, 40.0, 281.0, 80.0, 331.0, 40.0, 381.0, 120.0, 431.0, 200.0, 481.0, 200.0, 2916.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 188.0, 130.0, 120.0, 180.0, 120.0, 230.0, 40.0, 280.0, 80.0, 330.0, 40.0, 380.0, 120.0, 430.0, 200.0, 480.0, 200.0, 2914.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 186.0, 129.0, 120.0, 179.0, 120.0, 229.0, 40.0, 279.0, 80.0, 329.0, 40.0, 379.0, 120.0, 429.0, 200.0, 479.0, 200.0, 2912.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 184.0, 106.0, 120.0, 156.0, 120.0, 206.0, 40.0, 256.0, 80.0, 306.0, 40.0, 356.0, 120.0, 406.0, 200.0, 456.0, 200.0, 2866.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 182.0, 105.0, 120.0, 155.0, 120.0, 205.0, 40.0, 255.0, 80.0, 305.0, 40.0, 355.0, 120.0, 405.0, 200.0, 455.0, 200.0, 2864.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 180.0, 104.0, 120.0, 154.0, 120.0, 204.0, 40.0, 254.0, 80.0, 304.0, 40.0, 354.0, 120.0, 404.0, 200.0, 454.0, 200.0, 2862.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 178.0, 103.0, 120.0, 153.0, 120.0, 203.0, 40.0, 253.0, 80.0, 303.0, 40.0, 353.0, 120.0, 403.0, 200.0, 453.0, 200.0, 2860.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 176.0, 102.0, 120.0, 152.0, 120.0, 202.0, 40.0, 252.0, 80.0, 302.0, 40.0, 352.0, 120.0, 402.0, 200.0, 452.0, 200.0, 2858.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 174.0, 101.0, 120.0, 151.0, 120.0, 201.0, 40.0, 251.0, 80.0, 301.0, 40.0, 351.0, 120.0, 401.0, 200.0, 451.0, 200.0, 2856.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 172.0, 100.0, 120.0, 150.0, 120.0, 200.0, 40.0, 250.0, 80.0, 300.0, 40.0, 350.0, 120.0, 400.0, 200.0, 450.0, 200.0, 2854.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 174.0, 83.0, 120.0, 133.0, 120.0, 183.0, 40.0, 233.0, 80.0, 283.0, 40.0, 333.0, 120.0, 383.0, 200.0, 433.0, 200.0, 2820.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 184.0, 78.0, 120.0, 128.0, 120.0, 178.0, 40.0, 228.0, 80.0, 278.0, 40.0, 328.0, 120.0, 378.0, 200.0, 428.0, 200.0, 2810.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 186.0, 77.0, 120.0, 127.0, 120.0, 177.0, 40.0, 227.0, 80.0, 277.0, 40.0, 327.0, 120.0, 377.0, 200.0, 427.0, 200.0, 2808.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 196.0, 64.0, 120.0, 114.0, 120.0, 164.0, 40.0, 214.0, 80.0, 264.0, 40.0, 314.0, 120.0, 364.0, 200.0, 414.0, 200.0, 2782.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 234.0, 11.0, 120.0, 61.0, 120.0, 111.0, 40.0, 161.0, 80.0, 211.0, 40.0, 261.0, 120.0, 311.0, 200.0, 361.0, 200.0, 2676.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 236.0, 10.0, 120.0, 60.0, 120.0, 110.0, 40.0, 160.0, 80.0, 210.0, 40.0, 260.0, 120.0, 310.0, 200.0, 360.0, 200.0, 2674.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 238.0, 9.0, 120.0, 59.0, 120.0, 109.0, 40.0, 159.0, 80.0, 209.0, 40.0, 259.0, 120.0, 309.0, 200.0, 359.0, 200.0, 2672.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 242.0, 397.0, 40.0, 46.0, 120.0, 96.0, 40.0, 146.0, 80.0, 196.0, 40.0, 246.0, 120.0, 296.0, 200.0, 346.0, 200.0, 2646.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 240.0, 396.0, 40.0, 45.0, 120.0, 95.0, 40.0, 145.0, 80.0, 195.0, 40.0, 245.0, 120.0, 295.0, 200.0, 345.0, 200.0, 2644.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
//
//		treningPatterns.add(new TreningPattern(new double[] {40.0, 234.0, 393.0, 40.0, 42.0, 120.0, 92.0, 40.0, 142.0, 80.0, 192.0, 40.0, 242.0, 120.0, 292.0, 200.0, 342.0, 200.0, 2638.0, 160.0}, new double[] {1.0, 0.0, 0.0}));

		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 491.0, 120.0, 541.0, 40.0, 591.0, 200.0, 641.0, 80.0, 691.0, 160.0, 741.0, 160.0, 791.0, 40.0, 841.0, 120.0, 2449.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 60.0, 391.0, 120.0, 441.0, 40.0, 491.0, 200.0, 541.0, 80.0, 591.0, 160.0, 641.0, 160.0, 691.0, 40.0, 741.0, 120.0, 2249.0, 160.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 178.0, 291.0, 120.0, 341.0, 40.0, 391.0, 200.0, 441.0, 80.0, 491.0, 160.0, 541.0, 160.0, 591.0, 40.0, 641.0, 120.0, 2049.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 174.0, 191.0, 120.0, 241.0, 40.0, 291.0, 200.0, 341.0, 80.0, 391.0, 160.0, 441.0, 160.0, 491.0, 40.0, 541.0, 120.0, 1849.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 250.0, 91.0, 120.0, 141.0, 40.0, 191.0, 200.0, 241.0, 80.0, 291.0, 160.0, 341.0, 160.0, 391.0, 40.0, 441.0, 120.0, 1649.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 250.0, 392.0, 160.0, 41.0, 40.0, 91.0, 200.0, 141.0, 80.0, 191.0, 160.0, 241.0, 160.0, 291.0, 40.0, 341.0, 120.0, 1449.0, 160.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 238.0, 292.0, 160.0, 342.0, 200.0, 392.0, 200.0, 41.0, 80.0, 91.0, 160.0, 141.0, 160.0, 191.0, 40.0, 241.0, 120.0, 1249.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 212.0, 192.0, 160.0, 242.0, 200.0, 292.0, 200.0, 342.0, 160.0, 392.0, 80.0, 41.0, 160.0, 91.0, 40.0, 141.0, 120.0, 1049.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 228.0, 92.0, 160.0, 142.0, 200.0, 192.0, 200.0, 242.0, 160.0, 292.0, 80.0, 342.0, 200.0, 392.0, 40.0, 41.0, 120.0, 849.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 250.0, 393.0, 40.0, 42.0, 200.0, 92.0, 200.0, 142.0, 160.0, 192.0, 80.0, 242.0, 200.0, 292.0, 40.0, 342.0, 80.0, 649.0, 160.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 236.0, 293.0, 40.0, 343.0, 160.0, 393.0, 200.0, 42.0, 160.0, 92.0, 80.0, 142.0, 200.0, 192.0, 40.0, 242.0, 80.0, 449.0, 160.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 236.0, 193.0, 40.0, 243.0, 160.0, 293.0, 200.0, 343.0, 120.0, 393.0, 80.0, 42.0, 200.0, 92.0, 40.0, 142.0, 80.0, 249.0, 160.0}, new double[] {0.0, 1.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 152.0, 93.0, 40.0, 143.0, 160.0, 193.0, 200.0, 243.0, 120.0, 293.0, 80.0, 343.0, 120.0, 393.0, 120.0, 42.0, 80.0, 1956.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 66.0, 394.0, 160.0, 43.0, 160.0, 93.0, 200.0, 143.0, 120.0, 193.0, 80.0, 243.0, 120.0, 293.0, 120.0, 343.0, 240.0, 1756.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 294.0, 160.0, 344.0, 40.0, 394.0, 120.0, 43.0, 120.0, 93.0, 80.0, 143.0, 120.0, 193.0, 120.0, 243.0, 240.0, 1556.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 44.0, 194.0, 160.0, 244.0, 40.0, 294.0, 120.0, 344.0, 160.0, 394.0, 120.0, 43.0, 120.0, 93.0, 120.0, 143.0, 240.0, 1356.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 82.0, 94.0, 160.0, 144.0, 40.0, 194.0, 120.0, 244.0, 160.0, 294.0, 120.0, 344.0, 80.0, 394.0, 80.0, 43.0, 240.0, 1156.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 70.0, 395.0, 200.0, 44.0, 40.0, 94.0, 120.0, 144.0, 160.0, 194.0, 120.0, 244.0, 80.0, 294.0, 80.0, 344.0, 200.0, 956.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 32.0, 295.0, 200.0, 345.0, 200.0, 395.0, 40.0, 44.0, 160.0, 94.0, 120.0, 144.0, 80.0, 194.0, 80.0, 244.0, 200.0, 756.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 30.0, 195.0, 200.0, 245.0, 200.0, 295.0, 40.0, 345.0, 80.0, 395.0, 240.0, 44.0, 80.0, 94.0, 80.0, 144.0, 200.0, 556.0, 70.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 36.0, 95.0, 200.0, 145.0, 200.0, 195.0, 40.0, 245.0, 80.0, 295.0, 240.0, 345.0, 40.0, 395.0, 200.0, 44.0, 200.0, 356.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 102.0, 396.0, 200.0, 45.0, 200.0, 95.0, 40.0, 145.0, 80.0, 195.0, 240.0, 245.0, 40.0, 295.0, 200.0, 345.0, 80.0, 156.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 134.0, 296.0, 200.0, 346.0, 40.0, 396.0, 160.0, 45.0, 80.0, 95.0, 240.0, 145.0, 40.0, 195.0, 200.0, 245.0, 80.0, 748.0, 125.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 118.0, 196.0, 200.0, 246.0, 40.0, 296.0, 160.0, 346.0, 80.0, 396.0, 200.0, 45.0, 40.0, 95.0, 200.0, 145.0, 80.0, 548.0, 125.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 124.0, 96.0, 200.0, 146.0, 40.0, 196.0, 160.0, 246.0, 80.0, 296.0, 200.0, 346.0, 40.0, 396.0, 160.0, 45.0, 80.0, 348.0, 125.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 106.0, 397.0, 80.0, 46.0, 40.0, 96.0, 160.0, 146.0, 80.0, 196.0, 200.0, 246.0, 40.0, 296.0, 160.0, 346.0, 240.0, 148.0, 125.0}, new double[] {1.0, 0.0, 0.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 122.0, 297.0, 80.0, 347.0, 40.0, 397.0, 120.0, 46.0, 80.0, 96.0, 200.0, 146.0, 40.0, 196.0, 160.0, 246.0, 240.0, 1856.0, 70.0}, new double[] {0.0, 0.0, 1.0}));
		treningPatterns.add(new TreningPattern(new double[] {40.0, 100.0, 197.0, 80.0, 247.0, 40.0, 297.0, 120.0, 347.0, 240.0, 397.0, 200.0, 46.0, 40.0, 96.0, 160.0, 146.0, 240.0, 1656.0, 70.0}, new double[] {1.0, 0.0, 0.0}));

	}

}
