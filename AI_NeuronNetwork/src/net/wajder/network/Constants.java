package net.wajder.network;

/**
 * Class with constants used in AI project
 * 
 * @author vyder
 *
 */
public class Constants {
	
	/** value of error to check if the neuron is learned or not */
	public static final double ERROR_VALUE = 0.01;
	
	/** small number needs to backward error propagation learning */
	public static final double EPSILON = 0.005;
	
	/** Additional BIAS neuron */
	public static final int BIAS = 1;
	
	/** number of input double values in trening pattern - number of neuron inputs */
	public static final int HOW_MANY_PATTERN_ELEMENTS = 64;
	
	/** number of trening patterns to generate in NeuronTreningList class */
	public static final int HOW_MANY_TRENING_PATTERNS = 30;

}
