//AGNI PAILA , AM: 4753
//PAPAGEORGIOU MARINA , AM: 4757

import java.io.*;

public class LinearProbingHT<Key,Value> {

    private class Pair {
        Key key;
        Value value;
        
        Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private int m;      // hash table size
    private Pair[] T;   // hash table
    private int n;      // number of distinct items inserted
    
    int probes;

    private int totalComparisons; // number of item comparisons for all insertions

    public int totalComparisons() {
        return totalComparisons;
    }
    
    // hash function
    private int hash(Key key) {
        return ( (key.hashCode() & 0x7fffffff) % m);
    }

    // linear probing
    private int hashL(int k, int i)
    {
        return ( (k + i) % m );
    }
    
    // constructor: initialize empty hash table of size M
    LinearProbingHT(int M)
    {
        m = M;
        n = 0;
        T = new LinearProbingHT.Pair[m]; 
    }

    public double loadFactor()
    {
        return (double) 100*n/m;
    }
    
    // insert key with associated value
    public void insert(Key key, Value value) {
        /*  enter your code! */
		int k = hash(key);
		int i=0;
		int j = hashL(k,i);
		while(T[j]!= null && T[j].key!=null){
			if(T[j].key.equals(key)){
				T[j].value=value;
				return;
			}
			totalComparisons++;
			i++;
			j = hashL(k,i);
		}
		n++;
		T[j]=new Pair(key,value);
		
		if(loadFactor() >80){
			m=2*m;
			resize(m);
		}
    }
	
	private void resize(int M){

		System.out.println("resize "+M);
		
		Pair[] oldT = T;
        T = new LinearProbingHT.Pair[M];
		
		for (Pair p : oldT) { 
            while(p != null){ 
				Key k=p.key;
				Value v=p.value;
				int i=hash(k);
				T[i]=new Pair(k,v);
				break;
            } 
        }
		
		/*for (int l = 0; l < oldT.length; l++) {
			Pair p = oldT[l];
			//int c = count[l];
			if (p != null) {
				int k = hash(p.key);
				int i = 0;
				int j = hashL(k,i);
				while (oldT[j] != null){
					totalComparisons++;
					i++;
					j = hashL(k,i);
				}
				oldT[j] = p;
				//tempCount[j] = c;
			}
		}
		T = oldT;*/		
	}
       
    // return the value associated with key
    public Value contains(Key key){
        /*  enter your code! */
		int k =hash(key);
		int i=0;
		int j= hashL(k,i);
		while(T[j]!=null){
			if(T[j].key.equals(key)){
				return T[j].value;
			}
			i++;
			j = hashL(k,i);
		}
        return null; // change appropriately
    }

    // print hash table 
    void print()
    {
        for (int j=0; j<m; j++) {
            if (T[j] == null) {
                System.out.println("T["+j+"]=");
                continue;
            }
            System.out.println("T["+j+"]=" + "(" + T[j].key + "," + T[j].value + ") ");
        }
    }

    public static void main(String[] args) {
        System.out.println("Test Hash Table with Linear Probing");

        int M = 11; // initial hash table size
        LinearProbingHT T = new LinearProbingHT<String,Integer>(M);
        
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