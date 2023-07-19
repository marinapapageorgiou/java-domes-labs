//AGNI PAILA , AM: 4753
//PAPAGEORGIOU MARINA , AM: 4757

import java.io.*;

public class ChainingHT<Key,Value> {

    private class Node {
        Key key;
        Value value;
        Node next;    // next node of linked list 
 
        Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int m;          // hash table size
    private Node[] T;       // hash table
    private int n;          // number of distinct items inserted

    private int totalComparisons; // number of item comparisons for all insertions

    public int totalComparisons() {
        return totalComparisons;
    }

    // hash function
    private int hash(Key key) {
        return ((key.hashCode() & 0x7fffffff) % m);
    }

    // constructor: initialize empty hash table of size M
    ChainingHT(int M) {
        m = M;
        n = 0;
        T = new ChainingHT.Node[m];
    }
    
    public double loadFactor() {
        return (double) 100 * n / m;
    }

    // insert key with associated value
    public void insert(Key key, Value value) {
        /*  enter your code! */
		// Find head of chain for given key
		int bucket=hash(key);
		Node head=T[bucket];
		//check if key is already present
		while(head!=null){
			if(head.key.equals(key)){
				head.value=value;
				return;
			}
			totalComparisons++;
			head=head.next;
		}
		//insert key in chain
		n++;
		T[bucket]=new Node(key,value,T[bucket]);
		if(loadFactor() > 80){
			m=2*m;
			resize(m);
		}
    }
	
	private void resize(int M) {
		System.out.println("resize "+M);
		
        Node[] oldT = T;
        T = new ChainingHT.Node[M];
		
        for (Node headNode : oldT) { 
            while(headNode != null){ 
				Key k=headNode.key;
				Value v=headNode.value;
				int i=hash(k);
				T[i]=new Node(k,v,T[i]);
                headNode = headNode.next; 
				totalComparisons++;
            } 
        }
    }
	
    // return the value associated with key
    public Value contains(Key key) {
        /*  enter your code! */
		// Find head of chain for given key
		int bucket=hash(key);
		Node head=T[bucket];
		//search for key in chain
		while(head!=null){
			if(head.key.equals(key)){
				return head.value;
			}
			head=head.next;
		}
		//if key not found
        return null; // change appropriately
    }

    // print hash table 
    void print() {
        System.out.println("");
        for (int j = 0; j < m; j++) {
            Node x = T[j];
            System.out.print("T[" + j + "] = " );
            while (x != null) {
                System.out.print("(" + x.key + "," + x.value + ") " );
                x = x.next;
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        System.out.println("Test Hash Table with Chaining");
        
        int M = 11; // initial hash table size
        ChainingHT T = new ChainingHT<String,Integer>(M);

        In.init();
        long startTime = System.currentTimeMillis();
        while (!In.empty()) {
            String s = In.getString();
            Integer count = (Integer) T.contains(s);
            if ( count != null ) {
                T.insert(s, count + 1);
            } else {
                T.insert(s, 1);
            }
        }
        //T.print();
        long endTime = System.currentTimeMillis();
        long chtTime = endTime - startTime;
        System.out.println("construction time = " + chtTime);
        System.out.println("total number of item comparisons  = " + T.totalComparisons());
        System.out.println("load factor = " + T.loadFactor());
        
        System.out.println("contains 'and' " + T.contains("and") + " times");
        System.out.println("contains 'astonished' " + T.contains("astonished") + " times");
        System.out.println("contains 'boat' " + T.contains("boat") + " times");
        System.out.println("contains 'path' " + T.contains("path") + " times");
        System.out.println("contains 'the' " + T.contains("the") + " times");
        System.out.println("contains 'train' " + T.contains("train") + " times");
        System.out.println("contains 'tom' " + T.contains("tom") + " times");
        System.out.println("contains 'wondered' " + T.contains("wondered") + " times");
        
        endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total running time = " + totalTime); 
    }
}
