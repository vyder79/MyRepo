import edu.uci.ics.jung.algorithms.flows.EdmondsKarpMaxFlow;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

import org.apache.commons.collections15.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
 
public class SimpleGraph extends JFrame {
	
	private double maxWeight = 0;
	
  public SimpleGraph (){
    super("Zad.4 - programowanie sieciowe (Wydra[199355], KawÄ™czyĹ„ski[199340])");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Transformer<MyLink, Double> wtTransformer = new Transformer<MyLink, Double>() {
			public Double transform(MyLink link) {
				return link.weight;
			}
		};
    
    Graph g = getGraphForShortestPath();
    DijkstraShortestPath<Integer,String> alg = new DijkstraShortestPath(g, wtTransformer);
    List<String> lista = alg.getPath(1, 9);
    Number dist = alg.getDistance(1, 9);
    String result = " KrawÄ™dzie dla czasu minimalnego: \r\n";
    for (Object s : lista){
    	System.out.println(""+s);
    	result += s + "\r\n";
    }
    System.out.println("minimalny czas wykonania: " + dist);
    result += " minimalny czas wykonania: " + dist + " (dni)";
    
    // zielone okno z danymi najkrĂłtszej drogi w grafie
    JTextArea jta = new JTextArea();
    jta.setText(result);
    jta.setBounds(200, 450, 299, 200);
    jta.setBackground(new Color(0,255,0));
    getContentPane().add(jta, 0);
    jta.setVisible(true);
    
    //algorytm dla Ĺ›cieĹĽki krytycznej:
    Graph gmax = getGraphForCriticalPath();
    DijkstraShortestPath<Integer,MyLink> alg2 = new DijkstraShortestPath(gmax, wtTransformer);
    List<MyLink> lista2 = alg2.getPath(1, 9);
    //double dist2 = (double) alg2.getDistance(1, 9);
    
    double dist2 = 0.0;
    for(MyLink l : lista2){
    	dist2 += l.getCapacity();
    }
    
    String result2 = " KrawÄ™dzie dla Ĺ›cieĹĽki krytycznej: \r\n";
    for (MyLink s : lista2){
    	System.out.println(""+s.toStringWithCapacity());
    	result2 += s + "\r\n";
    }
    System.out.println("Ĺ›cieĹĽka krytyczna: " + dist2);
    result2 += " Ĺ›cieĹĽka krytyczna: " + dist2 + " (dni)";
    
    // czerwone okno z danymi dla Ĺ›cieĹĽki krytycznej
    JTextArea jta2 = new JTextArea();
    jta2.setText(result2);
    jta2.setBounds(500, 450, 301, 200);
    jta2.setBackground(new Color(255,0,0));
    jta2.setForeground(new Color(255,255,255));
    getContentPane().add(jta2, 1);
    jta2.setVisible(true);
    
    VisualizationViewer<Integer,String> vv = 
     new VisualizationViewer<Integer,String>(new FRLayout(g), new Dimension (800,400));
    getContentPane().add(vv);
 
    vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Integer>());
    vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
    vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

    pack();
    
    setSize (new Dimension (800, 700));
    setVisible(true);
  }
 
  
  /**
   * tworzenie grafu do wyszukiwania najkrĂłtszej drogi
   * 
   * @return Graph
   */
public Graph getGraphForShortestPath() {
//    Graph<Integer, String> g = new DirectedSparseGraph<Integer, String>();
	  Graph<Integer, MyLink> g = new DirectedSparseMultigraph<Integer, MyLink>();
    g.addVertex((Integer)1);
    g.addVertex((Integer)2);
    g.addVertex((Integer)3);
    g.addVertex((Integer)4);
    g.addVertex((Integer)5);
    g.addVertex((Integer)6);
    g.addVertex((Integer)7);
    g.addVertex((Integer)8);
    g.addVertex((Integer)9);
    //dla Ĺ›cieĹĽki min 2 razy ta sama wartoĹ›Ä‡ w MyLink
    g.addEdge(new MyLink(7, 7, "E"), 1, 2);
    g.addEdge(new MyLink(20, 20, "F"), 1, 3);
    g.addEdge(new MyLink(8, 8, "M"), 2, 3);
    g.addEdge(new MyLink(10, 10, "B"), 2, 4);
    g.addEdge(new MyLink(5, 5, "D"), 2, 4);
    g.addEdge(new MyLink(12, 12, "G"), 3, 4);
    g.addEdge(new MyLink(8, 8, "C"), 4, 5);
    g.addEdge(new MyLink(12, 12, "H"), 5, 6);
    g.addEdge(new MyLink(7, 7, "J"), 4, 6);
    g.addEdge(new MyLink(13, 13, "K"), 6, 7);
    g.addEdge(new MyLink(10, 10, "L"), 4, 7);
    g.addEdge(new MyLink(7, 7, "A"), 3, 7);
    g.addEdge(new MyLink(10, 10, "Ĺ�"), 7, 8);
    g.addEdge(new MyLink(10, 10, "I"), 5, 8);
    g.addEdge(new MyLink(3, 3, "N"), 8, 9);
    return g;
  }
  
  /**
   * tworzenie grafu dla algorytmu wyszukiwania Ĺ›cieĹĽki krytycznej
   * 
   * @return Graph
   */
public Graph getGraphForCriticalPath() {
	Graph<Integer, MyLink> g = new DirectedSparseMultigraph<Integer, MyLink>();
    g.addVertex((Integer)1);
    g.addVertex((Integer)2);
    g.addVertex((Integer)3);
    g.addVertex((Integer)4);
    g.addVertex((Integer)5);
    g.addVertex((Integer)6);
    g.addVertex((Integer)7);
    g.addVertex((Integer)8);
    g.addVertex((Integer)9);
    // maxWeight to max_waga+1 w danym grafie
    //setMaxWeight(14.0);
    setMaxWeight(21.0);
    // dla Ĺ›cieĹĽki krytycznej pierwsza wartoĹ›Ä‡ Myink dla algorytmu, druga do prezentacji
    g.addEdge(new MyLink(getMaxWeight() - 7, 7, "E"), 1, 2);
    g.addEdge(new MyLink(getMaxWeight() - 20, 20, "F"), 1, 3);
    g.addEdge(new MyLink(getMaxWeight() - 8, 8, "M"), 2, 3);
    g.addEdge(new MyLink(getMaxWeight() - 10, 10, "B"), 2, 4);
    g.addEdge(new MyLink(getMaxWeight() - 5, 5, "D"), 2, 4);
    g.addEdge(new MyLink(getMaxWeight() - 12, 12, "G"), 3, 4);
    g.addEdge(new MyLink(getMaxWeight() - 8, 8, "C"), 4, 5);
    g.addEdge(new MyLink(getMaxWeight() - 12, 12, "H"), 5, 6);
    g.addEdge(new MyLink(getMaxWeight() - 7, 7, "J"), 4, 6);
    g.addEdge(new MyLink(getMaxWeight() - 13, 13, "K"), 6, 7);
    g.addEdge(new MyLink(getMaxWeight() - 10, 10, "L"), 4, 7);
    g.addEdge(new MyLink(getMaxWeight() - 7, 7, "A"), 3, 7);
    g.addEdge(new MyLink(getMaxWeight() - 10, 10, "Ł"), 7, 8);
    g.addEdge(new MyLink(getMaxWeight() - 10, 10, "I"), 5, 8);
    g.addEdge(new MyLink(getMaxWeight() - 3, 3, "N"), 8, 9);
    return g;
  }
  
  
  public static void main(String[] args) {
	  
	  // TODO
	  // Task powstaĹ‚ by moĹĽna go byĹ‚o zamieniÄ‡ na automatyczne tworzenie grafu
	  LinkedList<Task> tasks = new LinkedList<Task>();
	  Task A = new Task("A", 7);
	  Task B = new Task("B", 10);
	  Task C = new Task("C", 8);
	  Task D = new Task("D", 5);
	  Task E = new Task("E", 7);
	  Task F = new Task("F", 2);
	  Task G = new Task("G", 12);
	  Task H = new Task("H", 12);
	  Task I = new Task("I", 10);
	  Task J = new Task("J", 7);
	  Task K = new Task("K", 13);
	  Task L = new Task("L", 10);
	  Task Ł = new Task("Ł", 10);
	  Task M = new Task("M", 8);
	  Task N = new Task("N", 3);
	  
	  A.addTaskToDoBefore(F);
	  A.addTaskToDoBefore(M);
	  
	  B.addTaskToDoBefore(E);
	  
	  C.addTaskToDoBefore(D);
	  
	  D.addTaskToDoBefore(E);
	  
	  //E.addPreviousTask(null);
	  
	  //F.addPreviousTask(null);
	  
	  G.addTaskToDoBefore(F);
	  G.addTaskToDoBefore(M);
	  
	  H.addTaskToDoBefore(C);
	  
	  I.addTaskToDoBefore(C);

	  J.addTaskToDoBefore(D);
	  J.addTaskToDoBefore(G);
	  J.addTaskToDoBefore(B);
	  
	  K.addTaskToDoBefore(J);
	  K.addTaskToDoBefore(H);
	  
	  L.addTaskToDoBefore(D);
	  L.addTaskToDoBefore(G);
	  L.addTaskToDoBefore(B);
	  
	  Ł.addTaskToDoBefore(A);
	  Ł.addTaskToDoBefore(L);
	  Ł.addTaskToDoBefore(K);
	  
	  M.addTaskToDoBefore(E);
	  
	  N.addTaskToDoBefore(I);
	  N.addTaskToDoBefore(Ł);
	  
	  tasks.add(A);
	  tasks.add(B);
	  tasks.add(C);
	  tasks.add(D);
	  tasks.add(E);
	  tasks.add(F);
	  tasks.add(G);
	  tasks.add(H);
	  tasks.add(I);
	  tasks.add(J);
	  tasks.add(K);
	  tasks.add(L);
	  tasks.add(Ł);
	  tasks.add(M);
	  tasks.add(N);
	  
	  new SimpleGraph();
  }


public double getMaxWeight() {
	return maxWeight;
}


public void setMaxWeight(double maxWeight) {
	this.maxWeight = maxWeight;
}
  
//public Graph getGraph() { // original version
////Graph<Integer, String> g = new DirectedSparseGraph<Integer, String>();
//Graph<Integer, String> g = new DirectedSparseMultigraph<Integer, String>();
//g.addVertex((Integer)1);
//g.addVertex((Integer)2);
//g.addVertex((Integer)3);
//g.addVertex((Integer)4);
//g.addVertex((Integer)5);
//g.addVertex((Integer)6);
//g.addVertex((Integer)7);
//g.addVertex((Integer)8);
//g.addVertex((Integer)9);
//g.addEdge("E", 1, 2);
//g.addEdge("F", 1, 3);
//g.addEdge("M", 2, 3);
//g.addEdge("B", 2, 4);
//g.addEdge("D", 2, 4);
//g.addEdge("G", 3, 4);
//g.addEdge("C", 4, 5);
//g.addEdge("H", 5, 6);
//g.addEdge("J", 4, 6);
//g.addEdge("K", 6, 7);
//g.addEdge("L", 4, 7);
//g.addEdge("A", 3, 7);
//g.addEdge("Ĺ�", 7, 8);
//g.addEdge("I", 5, 8);
//g.addEdge("N", 8, 9);
//return g;
//}
}