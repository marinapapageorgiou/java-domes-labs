//Marina Papageorgiou AM:4757
//Agni Paila AM:4753

import java.io.*;

public class WordList {

    private class Node {
        String str;
        int count;   // occurrences of str in input file
        Node next;   // next node in linked list

        Node(String str) {
            this.str = str;
            this.next = null;
            this.count = 1;
        }
    }

    private Node first;     // first node of the linked list
    private int nodeCount;  // number of nodes

    public WordList() {
        this.nodeCount = 0;
    }

    int nodeCount() {
        return nodeCount;
    }

    // return the number of occurrences of word s in the input file
    public int contains(String s) {
        /* enter you code! */
		Node x=first;
		while(x!=null){
			//System.out.println("------aaaaaa");
			if(x.str.equals(s)==true){
				//System.out.println("------bbbbbb");
				return x.count;
			}
			x=x.next;
			//System.out.println("------cccccc");
		}
        // s not found
        return 0;
    }

    // add one more occurence of word s; insert new node if s is not in the linked list
    public void insert(String s) {
        /* enter you code! */
		Node x=first;
		//System.out.println("======");
		while(x!=null){
			//System.out.println("bbbbbb");
			if(x.str.equals(s)==true){
				//System.out.println("cccccc");
				x.count=x.count+1;
				return;
			}
			x=x.next;
			//System.out.println("eeeeee");
		}
		Node newNode=new Node(s);
		newNode.next=first;
		first=newNode;
		nodeCount+=1;
		//System.out.println("dddddd");
    }

    // delete word s from the linked list
    public void delete(String s) {
        /* enter you code! */
		Node x =first;
		while(x!=null){
			//System.out.println("11111111111");
			if(x.next.str.equals(s)==true){
				//System.out.println("2222222222");
				Node y = new Node(s);
				y = x.next; 
				x.next = y.next;
				break;
			}
			x=x.next;
			//System.out.println("33333333333");
		}
		
    }
	
    // sort linked list in decreasing word frequencies 
    public void freqOrder() { 
        /* enter you code! */
		Node a=first;
		Node t, u, x, b = new Node(null);     //a kyrioy=x diko mas + w diko mas=x kyrioy
		while (a!= null) {
			t = a.next;
			u = t.next; 
			a.next = u;
			for (x = b; x.next != null; x = x.next){
				if (x.count>t.count){
					break;
				}else{
					a.next = t.next;
					t.next = a;
				}
			}
		}
		
		// allos pithanos tropos
		
		/*Node x=first;
	    Node y=first.next;
	    for(x=first;x.next != null;x = x.next){
		    if(x.count>y.count){
				continue;
		    }else{
				y.next=x.next;
				x.next=y;
		    }
	    }*/
    }
    
    // sort linked list in lexicographic order of words 
	public void lexOrder() {
		Node x=first;
		Node y=first.next;
		for (x = first; x != null; x = x.next){
			for(y = first.next; y != null; y = y.next){  
				if (x.str.compareTo(y.str) > 0) {    
					break;
				}else{
					y.next=x.next;
					x.next=y;
					return;
				}
			}
		}
		
		/*Node a=first;
		Node t, u, x, b = new Node(null); 
		System.out.println("======");		
		while (a.next!= null) {
			t = a.next;
			u = t.next; 
			a.next = u;
			System.out.println("aaaaaaaaaa");
			for (x = b; x.next != null; x = x.next){
				System.out.println("bbbbbbbbbbbb");
				if (x.next.str.compareTo(t.str)>0){
					System.out.println("ccccccccccc");
					break;
				}else{
					t.next = x.next; 
					x.next = t;
					System.out.println("ddddddddddd");
				}
			}
		}	*/
		
	}

    
    // find the k-th word in the linked list
    public String select(int k) {
        /* enter you code! */
		System.out.println("aaaaaaaaaa");
		Node x=first;
		String element="";
		while(x!=null){
			System.out.println("bbbbbbbbbbbb");
			for(int i=0; i<k; i++){
				System.out.println("ccccccccc");
				element=x.str+": "+x.count+"   ";
				//break;
			}
			x=x.next;
			System.out.println("ddddddddddd");
			return element;
		}
		return null; // change appropriately
    }
    
    // print first k strings of linked list
    public void print(int k){
        /* enter you code! */
		//System.out.println("aaaaaaaaaa");
		Node x=first;
		String all="";
		int counter =0;
		while(x!=null){
			//System.out.println("bbbbbbbbbbbb");
			while(counter<k){
				//System.out.println("ccccccccc");
				all+=x.str+": "+x.count+"   ";
				counter++;
				break;
			}
			x=x.next;
			//System.out.println("ddddddddddd");
		}
		System.out.println(all);
    }
    
    public static void main(String[] args) {
        System.out.println("Test WordList");

        WordList L = new WordList();

        In.init();
        long startTime = System.currentTimeMillis();
        while (!In.empty()) {
            String s = In.getString();
            if (s.isEmpty()) continue;
            L.insert(s);
        }
        long endTime = System.currentTimeMillis();
        long listTime = endTime - startTime;
        System.out.println("linked list construction time = " + listTime);
        System.out.println("number of linked list nodes = " + L.nodeCount());
        System.out.println("");

        System.out.println("contains 'and' " + L.contains("and") + " times");
        System.out.println("contains 'astonished' " + L.contains("astonished") + " times");
        System.out.println("contains 'boat' " + L.contains("boat") + " times");
        System.out.println("contains 'path' " + L.contains("path") + " times");
        System.out.println("contains 'the' " + L.contains("the") + " times");
        System.out.println("contains 'train' " + L.contains("train") + " times");
        System.out.println("contains 'tom' " + L.contains("tom") + " times");
        System.out.println("contains 'wondered' " + L.contains("wondered") + " times");
        System.out.println("");

        System.out.println("sorting words in lexicographical order");
        L.lexOrder();
        System.out.println("first 10 words in lexicographical order are:");
        L.print(10);
        String s = L.select(100);
        System.out.println("100th word in lexicographical order = " + s + "(" + L.contains(s) + ")");
        System.out.println("");
        
        System.out.println("sorting list in decreasing word frequency order");
        L.freqOrder();
        System.out.println("the 10 most frequent words are:");
        L.print(10);
        s = L.select(100);
        System.out.println("100th most frequent word = " + s + "(" + L.contains(s) + ")");
        System.out.println("");
        
        System.out.println("deleting '" + s +"'");
        L.delete(s);
        String t = L.select(100);
        System.out.println("next most frequent word after '" + s + "' = " + t + "(" + L.contains(t) + ")");
        System.out.println("");
        
        endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total running time = " + totalTime);
    }
}
