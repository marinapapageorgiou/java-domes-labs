import java.io.*;
import java.util.Random;

// minimum priority queue implemented with a binary heap
public class MinPQ<Item, Key extends Comparable<Key>> {

    private int N;    // number of items on priority queue
    private Key  pqK[]; // binary heap of keys
    private Item pqI[]; // binary heap of items

    public MinPQ(int maxN) {
        pqK = (Key[]) new Comparable[maxN + 1];
        pqI = (Item[]) new Object[maxN + 1];
        N = 0;
    }
	
	private boolean less(int i, int j) {
		return pqK[i].compareTo(pqK[j]) < 0;
	}
	
	private void exch(int i, int j) {
		Key t = pqK[i];
		pqK[i] = pqK[j];
		pqK[j] = t;
	}
	
	private void fixUp(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}
	
	private void fixDown(int k) {
		int j;
		while (2 * k <= N) {
			j = 2 * k;
			if (j < N && less(j, j + 1)) {
				j++;
			}
			if (!less(k, j)) {
				break;
			}
			exch(k, j);
			k = j;
		}
	}

    // return the number of elements in the priority queue
    public int size() {
        return N;
    }

    // insert item v with key k
    public void insert(Item v, Key k) {
        /* enter your code! */
		pqK[++N]=k; 
		fixUp (N);
    }

    // return the item with minimum key
    public Item minItem() {
        return pqI[1];
    }
    
    // return the minimum key
    public Key minKey() {
        return pqK[1];
    }
    
    // delete the item with minimum key
    public void delMin() {
        /* enter your code! */
		Key min =pqK[N];
		//Key min =pqK[minItem()];
		
		exch(N,1);
		pqK[N--] = null;//////////////////////
		fixDown(N);
		return ;
		
		//return min;
    }

    private void printPQ() {
        System.out.println("");
        for (int i = 1; i <= N; i++) {
            System.out.println("" + i + ": item = " + pqI[i] + ", key = " + pqK[i]);
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        System.out.println("Test Min Priority Queue implemented with a Binary Heap");

        long startTime = System.currentTimeMillis();
        MinPQ<Character,Integer> PQ = new MinPQ<Character,Integer>(26);
        Random rand = new Random(0);

        System.out.println("");
        System.out.println("Inserting keys");
        System.out.println("");
        for (int i = 1; i <= 26; i++) {
            int key = rand.nextInt(100); // assign random keys
            char c = (char) (96 + i);
            System.out.println("insert item " + c + " key " + key);
            PQ.insert(c, key);
        }
        //PQ.printPQ();
        
        System.out.println("");
        System.out.println("Deleting minimum keys");
        System.out.println("");
        while (PQ.size()>0) {
            char c = (char) PQ.minItem();
            int k = PQ.minKey();
            PQ.delMin();
            System.out.println("item " + c + " key " + k);
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total time = " + totalTime);
    }
}
