import java.io.*;

public class UnionFind {

    private int[] Pi;     // parent array
    private int[] size;   // size of each set 
    private int N;        // number of items
	private int sinola;

    UnionFind(int N) {
        this.N = N;
        Pi = new int[N + 1];
        size = new int[N + 1];
		this.sinola=N;
        for (int k = 0; k <= N; k++) {
            Pi[k] = k;
            size[k] = 1;
        }
    }
	
	private void compress(int v) {
		int m;
		if ((m = Pi[v]) != v) {
			compress(m);
			Pi[v] = Pi[m];
		}
	}
	
	int find(int v) {
		compress(v);
		return Pi[v];
	}

	void unite(int v, int u) {
		int k = find(v); 
		int l = find(u); 
		if (k == l) { 
			return;
		}
		if (size[k] > size[l]) {
			int t = k;
			k = l;
			l = t;
		}
		size[l] += size[k]++;
		Pi[k] = l;
		sinola=sinola-1;
	}

    int setCount() {
		return sinola;
    }
    
    void print() {
        System.out.println("Set Union Data Structure");
        for (int k=1; k<=N; k++) {
            System.out.println("Pi["+k+"]="+Pi[k]);
            if (Pi[k]==k) System.out.println("size["+k+"]="+size[k]);
        }
    }

    public static void main(String[] args) {
        System.out.println("Test UnionFind");

        int N = 16; 
        UnionFind UF = new UnionFind(N);
        
        for (int k=1; k<=3; k++){
            UF.unite(k+1,k);
            UF.unite(k+5,k+4);
            UF.unite(k+9,k+8);
            UF.unite(k+13,k+12);
        }
		
		//UF.print();
        UF.unite(1,5);
        UF.unite(9,13);
        UF.print();
        UF.unite(1,13);
        UF.find(2);
        UF.print();
    }
}
