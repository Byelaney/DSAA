package week4;

import java.util.LinkedList;

public class DirectedGraph {
	private int NodesNum;
	private Node[] nodes;
	private LinkedList<Edge> edges = new LinkedList<Edge>();
	
	
	public static void main(String []args){
		DirectedGraph udg = new DirectedGraph(6);
		udg.testRecDFS();
		
	}
	
	public DirectedGraph(int num){
		this.NodesNum = num;
		this.nodes = new Node[num];
	}
	
	public void addNode(int idx,Node e){
		if(idx > this.NodesNum -1)
			return;
		
		this.nodes[idx] = e;
	}
	
	public void addEdge(Edge e){
		this.edges.add(e);
	}
	
	public void RecDFS(DirectedGraph graph,Node start){
		start.setVisited(true);
		System.out.println(start.getId());
		LinkedList<Edge> edges = graph.getNodeEdge(start);
		for(Edge e:edges){
			Node p1 = e.getP1(); 
			Node p2 = e.getP2(); 
			Node v = null;
			if(p1.getId().equals(start.getId())){
				v = p2;
			}else{
				continue;
			}
			if(!v.isVisited())
				RecDFS(graph,v);
		}
	}

	public LinkedList<Edge> getNodeEdge(Node node){
		LinkedList<Edge> edge = new LinkedList<Edge>();		
		for(int i = 0;i<this.edges.size();i++){
			if(this.edges.get(i).NodeExist(node))
				edge.add(edges.get(i));
		}
		
		return edge;
	}
	
	
	public void testRecDFS(){
		DirectedGraph udg = new DirectedGraph(6);
		
		Node s = new Node("s",false);
		Node a = new Node("a",false);
		Node b = new Node("b",false);
		Node c = new Node("c",false);
		Node d = new Node("d",false);
		Node e = new Node("e",false);
		
		udg.addEdge(new Edge(s,a));
		udg.addEdge(new Edge(a,c));
		udg.addEdge(new Edge(c,e));
		udg.addEdge(new Edge(s,b));
		udg.addEdge(new Edge(a,b));
		udg.addEdge(new Edge(d,b));
		udg.addEdge(new Edge(d,c));
		udg.addEdge(new Edge(e,d));
				
		udg.addNode(0, s);
		udg.addNode(1, a);
		udg.addNode(2, b);
		udg.addNode(3, c);
		udg.addNode(4, d);
		udg.addNode(5, e);
		
		udg.RecDFS(udg, s);
		
	}
	
	
}
