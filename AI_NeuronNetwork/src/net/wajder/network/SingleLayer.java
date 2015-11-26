package net.wajder.network;
import java.util.ArrayList;

/**
 * Single layer of neurons
 * 
 * @author vyder
 *
 */
public class SingleLayer {
	
	private ArrayList<Neuron> singleLayer;
	
	private String description;

	public SingleLayer(ArrayList<Neuron> neurons, String description) {
		this.description = description;
		this.singleLayer = neurons;		
	}
	
	public void toStringOut(){
		System.out.print(this.description + "[");
		for (Neuron n : this.singleLayer) {
			n.toStringOut();
		}
		System.out.print("]");
		System.out.println("");
	}
	
	public String toStringOutString(){
		String out = "\r\n\r\n";
		out += this.description + "[";
		for (Neuron n : this.singleLayer) {
			out += n.toStringOutString();
		}
		out += "]";
		return out;
	}

	public ArrayList<Neuron> getSingleLayer() {
		return singleLayer;
	}

	public void setSingleLayer(ArrayList<Neuron> singleLayer) {
		this.singleLayer = singleLayer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
