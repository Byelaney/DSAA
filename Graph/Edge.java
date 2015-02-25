package week4;

public class Edge {
	//if is directed,then from p1 to p2
	private Node p1;
	private Node p2;
	
	public Edge(Node p1, Node p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}

	public Node getP1() {
		return p1;
	}

	public void setP1(Node p1) {
		this.p1 = p1;
	}

	public Node getP2() {
		return p2;
	}

	public void setP2(Node p2) {
		this.p2 = p2;
	}
	
	public boolean NodeExist(Node e){
		if(this.p1.getId().equals(e.getId()))
			return true;
		if(this.p2.getId().equals(e.getId()))
			return true;
		
		return false;
	}
	
	
	
}
