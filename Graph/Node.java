package week4;

public class Node {
	private String id;
	private boolean isVisited;
	
	
	public Node(String id, boolean isVisited){
		this.id = id;
		this.isVisited = isVisited;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}


	
}
