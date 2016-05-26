package net.wajder.network;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TrainedNetwork {
	
	NeuronNetwork net = null;

	public TrainedNetwork() {
		
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

	public NeuronNetwork getNet() {
		return net;
	}

	public void setNet(NeuronNetwork net) {
		this.net = net;
	}

}
