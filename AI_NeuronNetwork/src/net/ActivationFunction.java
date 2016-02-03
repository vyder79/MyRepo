package net;

public class ActivationFunction {
	
	private double beta = 2.0;
	private final double e = Math.E;
	private ActivFuncEnum activFunc = ActivFuncEnum.LINEAR;
	
	public ActivationFunction(){
		this.activFunc = ActivFuncEnum.LINEAR;
	}
	
	public ActivationFunction(ActivFuncEnum func){
		this.activFunc = func;
	}

	public double activate (double s) {
		return this.activFunc == ActivFuncEnum.SIGMOID ? sigmoidal(s) : linear(s);
	}

	private double linear(double s){
		return s;
	}

	private double sigmoidal(double s){
		return 1 / (1 + Math.pow(e, (-1 * beta * s)));
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public ActivFuncEnum getActivFunc() {
		return activFunc;
	}

	public void setActivFunc(ActivFuncEnum activFunc) {
		this.activFunc = activFunc;
	}
	
}
