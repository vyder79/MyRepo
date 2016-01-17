package net.wajder.network;
import java.util.ArrayList;

/**
 * Class stored trening pattern (list double values for neuron inputs and 
 * value of output for specified input list)
 * 
 * @author vyder
 *
 */
public class TreningPattern {
	
	/** list of double values to set for inputs of neuron */
	ArrayList<Double> inputList = new ArrayList<Double>();
	
	/** list of the outputs for specified input list */
	ArrayList<Double> outputList = new ArrayList<Double>();
	
	/** value of the output for specified input list */
	double output = 0;
	
	/**
	 * constructors
	 */
	public TreningPattern() {
	}
	
	public TreningPattern(ArrayList<Double> inputList, double output){
		this.inputList = inputList;
		this.output = output;
	}

	public TreningPattern(ArrayList<Double> inputList, ArrayList<Double> outputList) {
		super();
		this.inputList = inputList;
		this.outputList = outputList;
	}

	// getters and seters
	public ArrayList<Double> getInputList() {
		return inputList;
	}

	public void setInputList(ArrayList<Double> inputList) {
		this.inputList = inputList;
	}

	public double getOutput() {
		return output;
	}

	public void setOutput(double output) {
		this.output = output;
	}

	public ArrayList<Double> getOutputList() {
		return outputList;
	}

	public void setOutputList(ArrayList<Double> outputList) {
		this.outputList = outputList;
	}
	

	
}
