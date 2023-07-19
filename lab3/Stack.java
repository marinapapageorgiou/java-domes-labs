
public class Stack<Item> {

	private int n;      //plithos stoixeion sth stoiba
	private Item[] A;   //pinakas poy ilopoiei thn stoiva
	
    Stack(int N) {
        /* enter your code! */
		A=(Item[]) new Object[N];
		this.n=0;
    }
	
	private void resize(int M) {
		Item[] temp;
		temp =(Item[]) new Object[M];
		//for(int i=0; i<n;i++) {
		for(int i=0;i<n;i++){
			temp[i] = A[i];
		}
		A = temp;
	}

    boolean isEmpty() {
		return n== 0;
        //return true; // change appropriately
    }

    // insert new item on top of the stack
    void push(Item item) {
        /* enter your code! */
		if (n == A.length) {
			resize(2*A.length);
		}
		A[n++] = item;
    }

    // extract most recent item from the top of the stack
    Item pop() {
        /* enter your code */
		Item item = A[--n];
		A[n] = null; 
		if (n>0 && n==(A.length/4)) {
			resize(A.length/2);
		}
		return item;
        //return null; // change appropriately
    }
}
