package net.wajder.network;

import java.io.Serializable;

/**
 * Class with constants used in AI project
 * 
 * @author vyder
 *
 */
public class Constants implements Serializable {
	
	// when TRUE, additional data is shown when computing weights
	final static boolean DEBUG = true;
	
	/** Structure of the neural network */
	public static final int[] STRUCTURE = new int[] {20, 40, 20, 3};
	
	/** value of error to check if the neuron is learned or not */
	public static final double ERROR_VALUE = 0.02;
	
	/** small number needs to backward error propagation learning */
	public static final double EPSILON = 0.1;
	
	/** Additional BIAS neuron */
	public static final int BIAS = 1;
	
	/** Beta parameter at sigmoidal activation function */
	public static final double BETA = 0.1;
	
	/** value below this one is display as 0 */
	public static final double DISPLAY_AS_ZERO = 0.1;
	
	/** value greater than this one display as 1 */
	public static final double DISPLAY_AS_ONE = 0.9;
	
	
	
	/** number of input double values in trening pattern - number of neuron inputs */
	public static final int HOW_MANY_PATTERN_ELEMENTS = 64;
	
	/** number of trening patterns to generate in NeuronTreningList class */
	public static final int HOW_MANY_TRENING_PATTERNS = 30;
	
	
	
	/** czy uczenie sieci czy wczytanie zapisanej wyuczonej z pliku */
	public static final boolean LEARN = false;
	
	/** czy zapisanie nauczonej sieci do pliku */
	public static final boolean SAVE = true;
	
	
	
	/** œcie¿ka zapisu/odczytu */
	public static final String COMMON_PATH = "D:\\NeuronNetworkFiles\\network";
	
	/** rozszerzenie pliku */
	public static final String EXTENSION = ".dat";
	
	/** œcie¿ka zapisu/odczytu sieci neuronowej */
	public static final String PATH_TO_NETWORK_FILE = COMMON_PATH + EXTENSION;
	
	/** œcie¿ka zapisu/odczytu sieci neuronowej jako historia */
	public static final String PATH_TO_NETWORK_FILE_WITH_TIME = COMMON_PATH + System.currentTimeMillis() + EXTENSION;
	
	
	/** œcie¿ka dla obrazków */
	public static final String IMAGE_PATH = "C:\\Users\\vyder\\git\\MyRepo\\AI_NeuronNetwork\\src\\net\\wajder\\game\\images";
	
	
	
	
	public static final int X_DISTANCE = 50;
	
	public static final int Y_DISTANCE = 40;
	
	
	
}
