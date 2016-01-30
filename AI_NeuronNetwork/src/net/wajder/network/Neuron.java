package net.wajder.network;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class Neuron to compute output for given input signals
 * using list of weights for every input and activation function.
 * 
 * @author vyder
 *
 */
public class Neuron {
	
	/** list of neuron weights */
	private ArrayList<Double> weights = new ArrayList<>();
	
	/** neuron's activation function */
	private ActivationFunction aFunction = new ActivationFunction();
	
	/** neuron's description */
	private String description = "...";
	
	/** neuron's error */
	private double error = 0d;
	
	/** neuron's output */
	private double out = 0d;
	
	/** neuron's weighted sum */
	private double weightedSum = 0d;
	
	/** 
	 * constructors 
	 */
	public Neuron(){
		this.weights = new ArrayList<>();
		this.aFunction = new ActivationFunction();
	}
	
	public Neuron(ArrayList<Double> weights_list){
		this.weights = weights_list;	
	}
	
	public Neuron(ArrayList<Double> weights_list, String description){
		this.weights = weights_list;	
		this.description = description;
	}
	
	public Neuron(ArrayList<Double> weights_list, String description, ActivationFunction aFunction){
		this.weights = weights_list;	
		this.description = description;
		this.aFunction = aFunction;
	}
	
	/**
	 *  learned neuron
	 *  
	 * @param inputs
	 * @return
	 */
	public double activate(ArrayList<Double> inputs){
		double z = 0;
		for (int i = 0; i < inputs.size(); i++) {
			z += inputs.get(i) * this.weights.get(i);
		}
		this.weightedSum = z;
		this.out = aFunction.activate(z);
		return this.out;
	}
	
	/**
	 *  set random weights at the start of learning
	 *  
	 * @param tp - trening pattern
	 */
	public void setRandomWeights(TreningPattern tp) {
		setRandomWeights(tp.getInputList(), tp.getOutput());
	}
	
	private void setRandomWeights(ArrayList<Double> inputData, double output) {
		double weightsSum = 0d;
		int count = inputData.size() - 1;
		Random r = new Random();
		for (int i = 0; i < count; i++) {
			this.weights.add(r.nextDouble());
			if (Constants.DEBUG) {
				System.out.println("weight " + i + ": " + this.weights.get(i));
			}
		}
		for (int i = 0; i < count; i++) {
			weightsSum += inputData.get(i) * this.weights.get(i);
		}
		this.weights.add((output - weightsSum) / inputData.get(count));
	}
	
	/**
	 *  learning using backward error propagation
	 *  
	 * @param tp
	 * @param epsilon
	 * @return
	 */
	public double backwardErrorPropagation(TreningPattern tp, double epsilon) {
		return backwardErrorPropagation(tp.getInputList(), tp.getOutput(), epsilon);
	}
	
	private double backwardErrorPropagation(ArrayList<Double> inputs, double output, double epsilon) {
		double errorValue = output - activate(inputs);
		ArrayList<Double> newWeights = new ArrayList<>();
		for (int i = 0; i < this.weights.size(); i++) {
			newWeights.add(this.weights.get(i) + (epsilon * errorValue) * inputs.get(i));
		}
		this.weights.clear();
		this.weights = newWeights;
		if (Constants.DEBUG) {
			System.out.println("errorValue " + Math.abs(errorValue));
		}
		return errorValue;
	}
	
	public void toStringOut(){
		System.out.print(this.description + " " + this.weights.toString() + " = " + this.out + " / err = " + this.error);
	}
	
	public String toStringOutString(){
		return "\r\n " + this.description + " " + this.weights.toString() + " = " + this.out + " / err = " + this.error;
	}
	
	public String toStringOutNeuronOutputRounded(){
		double t = this.out < Constants.DISPLAY_AS_ZERO ? 0 : this.out > Constants.DISPLAY_AS_ONE ? 1 : this.out;
		return " " + t;
	}
	
	public String toStringOutNeuronOutput(){
		return " " + this.out;
	}

	/**
	 *  getters and setters
	 */
	public ArrayList<Double> getWeights() {
		return weights;
	}

	public void setWeights(ArrayList<Double> weights) {
		this.weights = weights;
	}
	
	public ActivationFunction getaFunction() {
		return aFunction;
	}

	public void setaFunction(ActivationFunction aFunction) {
		this.aFunction = aFunction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error < -999999999 ? -999999999 : (error > 999999999 ? 999999999 : error);
	}

	public double getOut() {
		return out;
	}

	public void setOut(double out) {
		this.out = out;
	}

	public double getWeightedSum() {
		return weightedSum;
	}

	public void setWeightedSum(double weightedSum) {
		this.weightedSum = weightedSum;
	}

}
