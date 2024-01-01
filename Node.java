public class Node<T> {

	private T data;
	private Node<T> nextNode;

	
	//constructors
	public Node(T data) {
		this.data = data;
	} 
	
	public Node(T data, Node<T> nextNode) {
		this.data = data;
		this.nextNode = nextNode;
	}

	//getters
	public T getData() {
		return this.data;
	}
	
	public Node<T> getNext() {
		return this.nextNode;
	}
	
	//setters
	public void setData(T data) {
		this.data = data;
	}
	
	public void setNext(Node<T> nextNode) {
		this.nextNode = nextNode;
	}
	
	//toString
	public String toString() {
		return this.data.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return this.toString() == obj.toString();
	}


}
