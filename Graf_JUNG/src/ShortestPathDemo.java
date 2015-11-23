import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import java.util.*;
import javax.swing.*;
import org.apache.commons.collections15.*;

import edu.uci.ics.jung.algorithms.generators.random.*;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.algorithms.shortestpath.*;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.graph.util.*;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.control.*;
import edu.uci.ics.jung.visualization.decorators.*;
import edu.uci.ics.jung.visualization.renderers.*;
import edu.uci.ics.jung.visualization.renderers.Renderer;

/**
 * @author danyelf
 */
public class ShortestPathDemo extends JApplet {

	private static final long serialVersionUID = 7526217664458188502L;

	private String mFrom;
	private String mTo;
	private Graph<String,Number> mGraph;
	private Set<String> mPred;
	
	public void init(){
		this.mGraph = getGraph();
		setBackground(Color.WHITE);
		// show graph
        final Layout<String,Number> layout = new FRLayout<String,Number>(mGraph);
        final VisualizationViewer<String,Number> vv = new VisualizationViewer<String,Number>(layout);
        vv.setBackground(Color.WHITE);

        vv.getRenderContext().setVertexDrawPaintTransformer(new MyVertexDrawPaintFunction<String>());
        vv.getRenderContext().setVertexFillPaintTransformer(new MyVertexFillPaintFunction<String>());
        vv.getRenderContext().setEdgeDrawPaintTransformer(new MyEdgePaintFunction());
        vv.getRenderContext().setEdgeStrokeTransformer(new MyEdgeStrokeFunction());
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
        vv.setGraphMouse(new DefaultModalGraphMouse<String, Number>());
        vv.addPostRenderPaintable(new VisualizationViewer.Paintable(){
            
            public boolean useTransform() {
                return true;
            }
            public void paint(Graphics g) {
                if(mPred == null) return;
                
                for (Number e : layout.getGraph().getEdges()) {
                    
                    if(isBlessed(e)) {
                        String v1 = mGraph.getEndpoints(e).getFirst();
                        String v2 = mGraph.getEndpoints(e).getSecond();
                        Point2D p1 = layout.transform(v1);
                        Point2D p2 = layout.transform(v2);
                        p1 = vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, p1);
                        p2 = vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, p2);
                        Renderer<String,Number> renderer = vv.getRenderer();
                        renderer.renderEdge(
                                vv.getRenderContext(),
                                layout,
                                e);
                    }
                }
            }
        });
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(vv, BorderLayout.CENTER);
        getContentPane().add(setUpControls(), BorderLayout.SOUTH);
	}

    boolean isBlessed( Number e ) {
    	Pair<String> endpoints = mGraph.getEndpoints(e);
		String v1= endpoints.getFirst()	;
		String v2= endpoints.getSecond() ;
		return v1.equals(v2) == false && mPred.contains(v1) && mPred.contains(v2);
    }
    
	public class MyEdgePaintFunction implements Transformer<Number,Paint> {
	    
		public Paint transform(Number e) {
			if ( mPred == null || mPred.size() == 0) return Color.BLACK;
			if( isBlessed( e )) {
				return new Color(0.0f, 0.0f, 1.0f, 0.5f);
			} else {
				return Color.LIGHT_GRAY;
			}
		}
	}
	
	public class MyEdgeStrokeFunction implements Transformer<Number,Stroke> {
        protected final Stroke THIN = new BasicStroke(1);
        protected final Stroke THICK = new BasicStroke(1);

        public Stroke transform(Number e) {
			if ( mPred == null || mPred.size() == 0) return THIN;
			if (isBlessed( e ) ) {
			    return THICK;
			} else 
			    return THIN;
        }
	    
	}
	
	public class MyVertexDrawPaintFunction<V> implements Transformer<V,Paint> {

		public Paint transform(V v) {
			return Color.black;
		}

	}

	public class MyVertexFillPaintFunction<V> implements Transformer<V,Paint> {

		public Paint transform( V v ) {
			if ( v == mFrom) {
				return Color.BLUE;
			}
			if ( v == mTo ) {
				return Color.BLUE;
			}
			if ( mPred == null ) {
				return Color.LIGHT_GRAY;
			} else {
				if ( mPred.contains(v)) {
					return Color.RED;
				} else {
					return Color.LIGHT_GRAY;
				}
			}
		}

	}

	private JPanel setUpControls() {
		JPanel jp = new JPanel();
		jp.setBackground(Color.WHITE);
		jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
		jp.setBorder(BorderFactory.createLineBorder(Color.black, 3));		
		jp.add(new JLabel("        Wybierz dwa wierzchołki, aby zobaczyć najkrótszš cieżkę"));
		JPanel jp2 = new JPanel();
		jp2.add(new JLabel("od", SwingConstants.LEFT));
		jp2.add(getSelectionBox(true));
		jp2.setBackground(Color.white);
		JPanel jp3 = new JPanel();
		jp3.add(new JLabel("do", SwingConstants.LEFT));
		jp3.add(getSelectionBox(false));
		jp3.setBackground(Color.white);
		jp.add( jp2 );
		jp.add( jp3 );
		return jp;
	}

	private Component getSelectionBox(final boolean from) {

		Set<String> s = new TreeSet<String>();
		
		for (String v : mGraph.getVertices()) {
			s.add(v);
		}
		final JComboBox choices = new JComboBox(s.toArray());
		choices.setSelectedIndex(-1);
		choices.setBackground(Color.WHITE);
		choices.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String v = (String)choices.getSelectedItem();
					
				if (from) {
					mFrom = v;
				} else {
					mTo = v;
				}
				drawShortest();
				repaint();				
			}
		});
		return choices;
	}

	protected void drawShortest() {
		if (mFrom == null || mTo == null) {
			return;
		}
		BFSDistanceLabeler<String,Number> bdl = new BFSDistanceLabeler<String,Number>();
		bdl.labelDistances(mGraph, mFrom);
		mPred = new HashSet<String>();
		
		String v = mTo;
		Set<String> prd = bdl.getPredecessors(v);
		mPred.add( mTo );
		while( prd != null && prd.size() > 0) {
			v = prd.iterator().next();
			mPred.add( v );
			if ( v == mFrom ) return;
			prd = bdl.getPredecessors(v);
		}
	}

	public static void main(String[] s) {
		JFrame jf = new JFrame();
		ShortestPathDemo sp = new ShortestPathDemo();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().add(sp);
		
		sp.init();
		
		jf.pack();
		jf.setVisible(true);
	}

	Graph<String,Number> getGraph() {

		Graph<String,Number> g =
			new EppsteinPowerLawGenerator<String,Number>(
					new GraphFactory(), new VertexFactory(), new EdgeFactory(), 26, 50, 50).create();
		Set<String> removeMe = new HashSet<String>();
		for (String v : g.getVertices()) {
            if ( g.degree(v) == 0 ) {
                removeMe.add( v );
            }
        }
		for(String v : removeMe) {
			g.removeVertex(v);
		}
		return g;
	}
	
	static class GraphFactory implements Factory<Graph<String,Number>> {
		public Graph<String,Number> create() {
			return new SparseMultigraph<String,Number>();
		}
	}
	
	static class VertexFactory implements Factory<String> {
		char a = 'a';
		public String create() {
			return Character.toString(a++);
		}
		
	}
	static class EdgeFactory implements Factory<Number> {
		int count;
		public Number create() {
			return count++;
		}
		
	}

}