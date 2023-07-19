//AGNI PAILA , AM:4753
//MARINA PAPAGEORGIOU , AM:4757

class Queue<Item> { 
    
    /* enter your code */
	private Node head;
	private Node tail;
	private int n;
	
	private class Node {  
		Item item; 
		Node next;
		Node(Item item) { 
			this.item = item; 
			next = null; 
		}
	}
	
	Queue(int maxN) {
		head = null; 
		tail = null; 
		n=0;
	}
	
	boolean isEmpty() { 
		return (head == null); 
	}
	
	void put(Item item) { 
		Node t = tail; 
		tail = new Node(item);
		if (isEmpty()){
			head = tail; 
		}else{
			t.next=tail; 
		}
		n++;
	}
	
	Item get() {  
		Item item = head.item; 
		Node t = head.next;
		head = t; 
		n--;
		return item;  
	}

    
}
