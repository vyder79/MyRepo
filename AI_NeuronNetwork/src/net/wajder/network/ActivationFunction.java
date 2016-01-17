package net.wajder.network;
/**
 * @author vyder
 */
public class ActivationFunction {
	
	private double beta = 2.0;
	private final double e = Math.E;
	private ActivFuncEnum activFunc = ActivFuncEnum.LINEAR;
	
	public ActivationFunction(){
		this.activFunc = ActivFuncEnum.LINEAR; // linear as default
	}
	
	public ActivationFunction(ActivFuncEnum func){
		this.activFunc = func;
	}
	
	/*
	 * uaktywnienie neuronu, obliczenie wyj�cia
	 */
	public double activate (double s) {
		return this.activFunc == ActivFuncEnum.SIGMOID ? sigmoidal(s) : linear(s);
	}
	
	/*
	 * funkcja liniowa, zwraca dok��dnie to, co podano na wej�cie
	 */
	private double linear(double s){
		return s;
	}
	
	/*
	 * funkcja unipolarna sigmoidalna, przyjmuje warto�ci od 0 do 1
	 */
	private double sigmoidal(double s){
		return 1 / (1 + Math.pow(e, (-1 * beta * s)));
	}
	
	/*
	 * do test�w
	 */
	public void test(){
		
		for (double i=-20; i<=20; i=i+0.1){
			System.out.println(sigmoidal(i));
		}
		
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
