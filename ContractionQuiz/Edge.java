package graph;

public class Edge {
	private int endpoint1;
	private int endpoint2;
	public Edge(int endpoint1, int endpoint2) {
		super();
		this.endpoint1 = endpoint1;
		this.endpoint2 = endpoint2;
	}
	public int getEndpoint1() {
		return endpoint1;
	}
	public void setEndpoint1(int endpoint1) {
		this.endpoint1 = endpoint1;
	}
	public int getEndpoint2() {
		return endpoint2;
	}
	public void setEndpoint2(int endpoint2) {
		this.endpoint2 = endpoint2;
	}
	
	public boolean compare(Edge e){
		if(endpoint1 == e.getEndpoint1() && endpoint2 == e.getEndpoint2())
			return true;
		
		if(endpoint1 == e.getEndpoint2() && endpoint2 == e.getEndpoint1())
			return true;
		
		return false;
	}
	
}
