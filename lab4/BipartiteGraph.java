//AGNI PAILA , AM:4753
//MARINA PAPAGEORGIOU , AM:4757

//ston kodika stin BFS methode se periptosi pou to grafima den einai dimeres tiponei ton kyklo poy periexei oura

import java.io.*;

public class BipartiteGraph {

    private final int N;                // number of vertices, komvo
    private int K;                      // number of edges , akmes
    private Collection<Integer>[] adj;  // adjacency lists
	private boolean[] marked;			//true or false if visit komvo
	private int[] parent;		
	private String[] color;
	private int[] level;
	private boolean isBipartite;

    // construct graph with N nodes and no edges
    public BipartiteGraph(int N) {
        this.N = N;
        this.K = 0;
		marked=new boolean[N];
		parent=new int[N];
		color=new String[N];
		level=new int[N];
		isBipartite=true;
        adj = (Collection<Integer>[]) new Collection[N];  // array of references to collections
        for (int i = 0; i < N; i++) {
            adj[i] = new Collection<Integer>();   // initialize collections to be empty
        }
    }

    public int nodes() // return the number of nodes
    {
        return N;
    }

    public int edges() // return the number of edges
    {
        return K;
    }

    // add edge {v,w}
    public void addEdge(int v, int w) {
        adj[v].insert(w);
        adj[w].insert(v);
        K++;
    }

    // nodes adjacent to node v
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // print adjacency lists
    public void printGraph() {
        System.out.println("adjacency lists");
        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            for (int w : adj(v)) {
                System.out.print(w + " ");
            }
            System.out.println("");
        }
    }

    // breadth-first search from vertex s
    void bfs(int s) {
        /* enter your code */
		Queue<Integer> Q = new Queue<Integer>(N);   
		marked[s] = true;
		color[s]="White";		
		Q.put(s);
		int k;
		level[s]=0;
		while (!Q.isEmpty()){
			//color[s]="White";
			k = Q.get(); 
			for ( int v : adj(k) ){		
				if (!marked[v]) {
					marked[v] = true;
					level[v]=level[k]+1;
					parent[v] = k;
					Q.put(v);
					if(level[k]%2==0){
						color[v]="Black";
					}else{
						color[v]="White";
					}
				}
				if(color[v]==color[k]){
					isBipartite = false;
					//findOddCycle(v,k);
					printCycle(findOddCycle(v,k));
				}
			}
		}
		
		if(isBipartite==true){
			System.out.println("Input graph is Bipartite!");
		}
		
		/*for(int i=0;i<N;i++){
			System.out.println(marked[i]);
			System.out.println(parent[i]);
			System.out.println(color[i]);
			System.out.println(level[i]);
			System.out.println("-----------------");
		}*/
	}

	public boolean isBipartite() {
        return isBipartite;
    }

    // find odd-length cycle containing edge (u,v)
    Queue<Integer> findOddCycle(int u, int v) {
        Queue<Integer> C = new Queue<Integer>(N);
        /* enter your code */
		if(isBipartite()==false){
			while(v>=0){
				C.put(v);
				v--;
			}
		}
       return C;
    }

    // print cycle stored in queue C
    void printCycle(Queue<Integer> C) {
        /* enter your code */
		System.out.println("Input Graph is NOT Bipartite!");
		System.out.println("Odd cycle found");
		String x="";
		while(!C.isEmpty()){
			x+=C.get()+" ";
		}
		System.out.println("Cycle:( "+x+" )");
    }

    public static void main(String[] args) {
        In.init();
        int N = In.getInt();
        BipartiteGraph G = (BipartiteGraph) new BipartiteGraph(N);
        int K = In.getInt();
        for (int i = 0; i < K; i++) {
            int v = In.getInt();
            int w = In.getInt();
            G.addEdge(v, w);
        }
        //G.printGraph();
        G.bfs(0);
    }
}
