
class Queue<Item> {
    
	private int n;     //plithos stoixeion sth oyra
	private Item[] A;   //pinakas poy ilopoiei thn oyra
	private int head;
	private int tail;
	
    Queue(int maxN) {
        /* enter your code! */
		//A = new Object[maxN+1];
		A=(Item[]) new Object[maxN+1];
		n=maxN+1;
		head=n;
		tail=0;
    }
    
    boolean isEmpty() {
		return (head%n==tail);
        //return true; // change appropriately
    }

    // insert new item in the queue
    void put(Item item) {
        /* enter your code! */
		A[tail++]=item;
		tail=tail%n;
		/*last = (last +1)%A.length;
		A[last] = item; 
		n++;*/
    }

    // extract least recent item from the queue
    Item get() {
        /* enter your code */
		head=head&n;
		return A[head++];
		/*Item item = A[first];
		A[first] = null;
		--n;
		if (n > 0){
			first = (first + 1)% A.length; 
		}
		return item;*/
        //return null; // change appropriately
    }
}

