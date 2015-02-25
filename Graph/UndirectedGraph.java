package week4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class UndirectedGraph {
	private int NodesNum;
	private Node[] nodes;
	private LinkedList<Edge> edges = new LinkedList<Edge>();
	private ArrayList<String> visited = new ArrayList<String>();
	
	public static void main(String []args){
		UndirectedGraph udg = new UndirectedGraph(6);
		udg.testRecDFS();
		
	}
	
	public UndirectedGraph(int num){
		this.NodesNum = num;
		this.nodes = new Node[num];
	}
	
	public void addNode(int idx,Node e){
		if(idx > this.NodesNum -1)
			return;
		
		this.nodes[idx] = e;
	}
	
	public void BFS(UndirectedGraph graph,Node start){
		start.setVisited(true);
		System.out.println(start.getId());
		//visited.add(start.getId());
		
		Queue<Node> Q = new LinkedList<Node>();
		Q.add(start);
		
		while(!Q.isEmpty()){
			Node v = Q.peek();
			Q.remove();
			if(v == null)
				break;
			
			LinkedList<Edge> edges = graph.getNodeEdge(v);
			for(Edge e:edges){
				Node p1 = e.getP1(); 
				Node p2 = e.getP2(); 
				Node w = null;
				if(p1.getId().equals(v.getId())){
					w = p2;
				}else{
					w = p1;
				}
				
				if(!w.isVisited()){
					w.setVisited(true);
					System.out.println(w.getId());
					Q.add(w);
				}
				
			}
			
		}
	}
	
	public void DFS(UndirectedGraph graph,Node start){
		Stack<Node> stack = new Stack<Node>();
		stack.push(start);
		
		while(!stack.isEmpty()){
			Node v = stack.pop();
			if(v == null)
				break;
				
			if(!v.isVisited()){
				v.setVisited(true);
				System.out.println(v.getId());
				LinkedList<Edge> edges = graph.getNodeEdge(v);
				for(Edge e:edges){
					Node p1 = e.getP1(); 
					Node p2 = e.getP2(); 
					Node w = null;
					if(p1.getId().equals(v.getId())){
						w = p2;
					}else{
						w = p1;
					}
					stack.push(w);
				}
			}
			
			
		}
	}
	
	public void RecDFS(UndirectedGraph graph,Node start){
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
				v = p1;
			}
			if(!v.isVisited())
				RecDFS(graph,v);
		}
	}
	
	public LinkedList<Edge> getEdges(){
		return this.edges;
	}
	
	public LinkedList<Edge> getNodeEdge(Node node){
		LinkedList<Edge> edge = new LinkedList<Edge>();
		
		for(int i = 0;i<this.edges.size();i++){
			if(this.edges.get(i).NodeExist(node))
				edge.add(edges.get(i));
		}
		
		return edge;
	}
	
	public void addEdge(Edge e){
		this.edges.add(e);
	}
	
	public ArrayList<String> getVisited(){
		return this.visited;
	}
	
	public void testBFS(){
		UndirectedGraph udg = new UndirectedGraph(6);
		
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
		udg.addEdge(new Edge(b,c));
		udg.addEdge(new Edge(c,d));
		udg.addEdge(new Edge(d,e));
		udg.addEdge(new Edge(b,d));
				
		udg.addNode(0, s);
		udg.addNode(1, a);
		udg.addNode(2, b);
		udg.addNode(3, c);
		udg.addNode(4, d);
		udg.addNode(5, e);
		
		udg.BFS(udg, s);
	}
	
	public void testDFS(){
		UndirectedGraph udg = new UndirectedGraph(6);
		
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
		udg.addEdge(new Edge(b,c));
		udg.addEdge(new Edge(c,d));
		udg.addEdge(new Edge(d,e));
		udg.addEdge(new Edge(b,d));
				
		udg.addNode(0, s);
		udg.addNode(1, a);
		udg.addNode(2, b);
		udg.addNode(3, c);
		udg.addNode(4, d);
		udg.addNode(5, e);
		
		udg.DFS(udg, s);
		
	}
	
	public void testRecDFS(){
		UndirectedGraph udg = new UndirectedGraph(6);
		
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
		udg.addEdge(new Edge(b,c));
		udg.addEdge(new Edge(c,d));
		udg.addEdge(new Edge(d,e));
		udg.addEdge(new Edge(b,d));
				
		udg.addNode(0, s);
		udg.addNode(1, a);
		udg.addNode(2, b);
		udg.addNode(3, c);
		udg.addNode(4, d);
		udg.addNode(5, e);
		
		udg.RecDFS(udg, s);
		
	}
}
