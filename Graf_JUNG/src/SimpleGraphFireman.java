import edu.uci.ics.jung.algorithms.flows.EdmondsKarpMaxFlow;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
//import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;

import org.apache.commons.collections15.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

public class SimpleGraphFireman extends JFrame {

	private double maxWeight = 0;

	public SimpleGraphFireman() {
		super("(Wydra[199355], Kawęczyński[199340])");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Transformer<MyLink, Double> wtTransformer = new Transformer<MyLink, Double>() {
			public Double transform(MyLink link) {
				return link.weight;
			}
		};

		Graph g = getGraphForShortestPath();
		DijkstraShortestPath<Integer, String> alg = new DijkstraShortestPath(g, wtTransformer);
		String result = "";
		
		for (int i = 2; i <= 12; i++) {
			List<String> lista = alg.getPath(1, i);
			Number dist = alg.getDistance(1, i);
			result += " \r\n-----------------------\r\n Krawędzie dla czasu minimalnego: punkty [1] -> ["+i+"] \r\n";
			for (Object s : lista) {
				//System.out.println("" + s);
				result += s + "\r\n";
			}
			//System.out.println("minimalny czas dotarcia: " + dist);
			result += " minimalny czas dotarcia: " + dist + " (minut)";
		}
		
		System.out.println(result);
		
			
		
		// zielone okno z danymi najkrótszej drogi w grafie
//		JTextArea jta = new JTextArea();
//		jta.setText(result);
//		jta.setBounds(200, 450, 299, 200);
//		jta.setBackground(new Color(0, 255, 0));
//		getContentPane().add(jta, 0);
//		jta.setVisible(true);

		VisualizationViewer<Integer, String> vv = new VisualizationViewer<Integer, String>(new FRLayout(g),
				new Dimension(990, 680));
		getContentPane().add(vv);

		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Integer>());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		pack();

		setSize(new Dimension(999, 700));
		setVisible(true);
	}

	/**
	 * tworzenie grafu do wyszukiwania najkrótszej drogi
	 * 
	 * @return Graph
	 */
	public Graph getGraphForShortestPath() {
		Graph<Integer, MyLink> g = new DirectedSparseMultigraph<Integer, MyLink>(); // UndirectedSparseMultigraph
																						// /
																						// DirectedSparseMultigraph
		g.addVertex((Integer) 1);
		g.addVertex((Integer) 2);
		g.addVertex((Integer) 3);
		g.addVertex((Integer) 4);
		g.addVertex((Integer) 5);
		g.addVertex((Integer) 6);
		g.addVertex((Integer) 7);
		g.addVertex((Integer) 8);
		g.addVertex((Integer) 9);
		g.addVertex((Integer) 10);
		g.addVertex((Integer) 11);
		g.addVertex((Integer) 12);

		// dla ścieżki minimalnej 2 razy ta sama wartość w MyLink
		g.addEdge(new MyLink(17, 17, "A"), 1, 2);
		g.addEdge(new MyLink(21, 21, "B"), 1, 3);
		g.addEdge(new MyLink(13, 13, "C"), 1, 4);
		g.addEdge(new MyLink(25, 25, "D"), 2, 3);
		g.addEdge(new MyLink(16, 16, "E"), 2, 5);
		g.addEdge(new MyLink(10, 10, "F"), 2, 7);
		g.addEdge(new MyLink(20, 20, "G"), 3, 6);
		g.addEdge(new MyLink(10, 10, "H"), 3, 8);
		g.addEdge(new MyLink(15, 15, "I"), 4, 2);
		g.addEdge(new MyLink(12, 12, "J"), 4, 3);
		g.addEdge(new MyLink(19, 19, "K"), 4, 6);
		g.addEdge(new MyLink(10, 10, "L"), 4, 9);
		g.addEdge(new MyLink(9, 9, "M"), 5, 7);
		g.addEdge(new MyLink(18, 18, "N"), 5, 8);
		g.addEdge(new MyLink(17, 17, "O"), 6, 8);
		g.addEdge(new MyLink(21, 21, "P"), 6, 9);
		g.addEdge(new MyLink(14, 14, "Q"), 7, 8);
		g.addEdge(new MyLink(15, 15, "R"), 7, 11);
		g.addEdge(new MyLink(10, 10, "S"), 8, 11);
		g.addEdge(new MyLink(11, 11, "T"), 8, 12);
		g.addEdge(new MyLink(20, 20, "U"), 9, 10);
		g.addEdge(new MyLink(13, 13, "V"), 9, 12);
		g.addEdge(new MyLink(9, 9, "W"), 10, 12);
		g.addEdge(new MyLink(9, 9, "X"), 11, 12);
		
		//dla grafu skierowanego drogi powrotne (z górki, -5))
		g.addEdge(new MyLink(12, 12, "AA"), 2, 1);
		g.addEdge(new MyLink(16, 16, "BB"), 3, 1);
		g.addEdge(new MyLink(8, 8, "CC"), 4, 1);
		g.addEdge(new MyLink(20, 20, "DD"), 3, 2);
		g.addEdge(new MyLink(11, 11, "EE"), 5, 2);
		g.addEdge(new MyLink(5, 5, "FF"), 7, 2);
		g.addEdge(new MyLink(15, 15, "GG"), 6, 3);
		g.addEdge(new MyLink(5, 5, "HH"), 8, 3);
		g.addEdge(new MyLink(10, 10, "II"), 2, 4);
		g.addEdge(new MyLink(7, 7, "JJ"), 3, 4);
		g.addEdge(new MyLink(14, 14, "KK"), 6, 4);
		g.addEdge(new MyLink(5, 5, "LL"), 9, 4);
		g.addEdge(new MyLink(4, 4, "MM"), 7, 5);
		g.addEdge(new MyLink(13, 13, "NN"), 8, 5);
		g.addEdge(new MyLink(12, 12, "OO"), 8, 6);
		g.addEdge(new MyLink(16, 16, "PP"), 9, 6);
		g.addEdge(new MyLink(9, 9, "QQ"), 8, 7);
		g.addEdge(new MyLink(10, 10, "RR"), 11, 7);
		g.addEdge(new MyLink(5, 5, "SS"), 11, 8);
		g.addEdge(new MyLink(6, 6, "TT"), 12, 8);
		g.addEdge(new MyLink(15, 15, "UU"), 10, 9);
		g.addEdge(new MyLink(8, 8, "VV"), 12, 9);
		g.addEdge(new MyLink(4, 4, "WW"), 12, 10);
		g.addEdge(new MyLink(4, 4, "XX"), 12, 11);
		return g;
	}

	public static void main(String[] args) {

		new SimpleGraphFireman();

	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

}