//Marina Papageorgiou AM:4757
//Agni Paila AM:4753

import java.io.*;
import java.util.Random;

public class RedBlackTree<Key extends Comparable<Key>, Item> {

    BSTreeNode root;        // root of binary search tree

    class BSTreeNode {

        Key key;            // key associated with the item stored at node
        Item item;          // item stored at node
        BSTreeNode left;    // left child
        BSTreeNode right;   // right child
        BSTreeNode parent;  // node's parent
        int height;         // node's height
        int N;              // number of descendants
		boolean isRed;      //true einai kokkino ,false einai mauro

        // create new node
        BSTreeNode(Key key, Item item, BSTreeNode parent) {
            this.key = key;
            this.item = item;
            this.parent = parent;
            this.height = 1;
            this.N = 1;
			isRed=true;
			//root=null;
        }
    }

    // search for item with key; returns the last node on the search path 
    BSTreeNode searchNode(Key key) {
        BSTreeNode v = root;
        BSTreeNode pv = null; // parent of v
        while (v != null) {
            int c = key.compareTo(v.key);  // compare with the key of node v
            pv = v;
            if (c < 0) {
                v = v.left;
            } else if (c > 0) {
                v = v.right;
            } else {
                return v; // item found; return node that contains it
            }
        }
        return pv; // item not found; return last node on the search path
    }

    // search for item with key
    public Item search(Key key) {
        if (root == null) {
            return null; // tree is empty
        }
        BSTreeNode v = searchNode(key);
        int c = key.compareTo(v.key);
        if (c == 0) {
            return v.item;    // item found
        } else {
            return null;      // item not found
        }
    }

    // return the height of a node x; if x is null return 0
    private int getHeight(BSTreeNode x) {
        if (x == null) {
            return 0;
        } else {
            return x.height;
        }
    }

    // return the number of descendants of a node x; if x is null return 0
    private int getN(BSTreeNode x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }
    
    // update the height and the number of descendants of a node
    private void updateNode(BSTreeNode x) {
        int leftHeight = getHeight(x.left);
        int rightHeight = getHeight(x.right);
        int bf = leftHeight - rightHeight; // balance factor
        if (bf < 0) {
            x.height = rightHeight + 1;
        } else {
            x.height = leftHeight + 1;
        }
        
        int leftN = getN(x.left);
        int rightN = getN(x.right);
        x.N = leftN + rightN + 1;
    }
    
    // update the height v's ancestors
    private void updatePath(BSTreeNode v) {
        BSTreeNode u = v;
        while (u != null) {
            updateNode(u);
            u = u.parent;
        }
    }
    
    // return the height of the binary search tree
    int getTreeHeight() {
        return getHeight(root);
    }
	
	// rotate left at node x
	public BSTreeNode rotateLeft(BSTreeNode x) {
		BSTreeNode y = x.right;
		x.right = y.left;
		if (y.left != null) {
			y.left.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			this.root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
		updateNode(x);
		updateNode(y);
		return y;
	}
	
	// rotate right at node x
	public BSTreeNode rotateRight(BSTreeNode x) {
		BSTreeNode y = x.left;
		x.left = y.right;
		if (y.right != null) {
			y.right.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			this.root = y;
		} else if (x == x.parent.right) {
			x.parent.right = y;
		} else {
			x.parent.left = y;
		}
		y.right = x;
		x.parent = y;
		updateNode(x);
		updateNode(y);
		return y;
	}
	
	//voithitikh routina sibling gia thn euresh toy theiou
	private BSTreeNode sibling(BSTreeNode y){
		if(y==y.parent.left){
			return y.parent.right;  //theios sta deksia
		}else if(y==y.parent.right){
			return y.parent.left;   //theios sta aristera
		}
		return null;
	}
	
	//apokatastash RBT meta thn eisagwgh 
	private void fix (BSTreeNode x) {
		if(x == root ){
			return;
		}
		BSTreeNode y, z, uncle;
		
		y = x.parent;
		if(!y.isRed ) {
			updatePath(y); 
			return;
		}
		z = y.parent;
		uncle = sibling (y);//epistrefei aderfo toy y=ueios toy x
		//System.out.println(uncle);
		while(x.parent.isRed==true){
			//o theios einai deksia
			if(uncle==x.parent.parent.right){
				
				//o theios einai null h mayros
				if(uncle==null || uncle.isRed==false){
					if(x==x.parent.left){
						x=x.parent;     //AA
						rotateRight(x);
						updatePath(x);
					}
					x.parent.isRed=false;  //AD
					x.parent.parent.isRed=true;
					//rotateLeft(x.parent.parent);
					rotateLeft(x);
					updatePath(x);
					rotateRight(x);
					updatePath(x);
				}else{ //o theios einai kokkinos
					x.parent.isRed=false;
					x.parent.parent.isRed=true;
					uncle.isRed=false;
					x=x.parent.parent;
					updatePath(x);
				}
			}else{ //(uncle==x.parent.parent.left)//o theios einai aristera
				//o theios einai null h mayros
				if(uncle==null || uncle.isRed==false){
					if(x==x.parent.right){
						x=x.parent;     //DD
						rotateLeft(x);
						updatePath(x);
					}
					x.parent.isRed=false;  //DA
					x.parent.parent.isRed=true;
					//rotateRight(x.parent.parent);
					rotateRight(x);
					updatePath(x);
					rotateLeft(x);
					updatePath(x);
				}else{ //o theios einai kokkinos
					x.parent.isRed=false;
					x.parent.parent.isRed=true;
					uncle.isRed=false;
					x=x.parent.parent;
					updatePath(x);
				}
			}
			if(x==root){
				break;
			}
		}
		root.isRed=false;
		updatePath(x);
	}

    // insert item with key and return inserted node
    BSTreeNode insertNode(Key key, Item item) {
        if (root == null) { // tree is empty
            root = new BSTreeNode(key, item, null);
			root.isRed=false;  //root= mauros
            return root;
        }
		
		BSTreeNode v = searchNode(key); // v is the last node on the search path
        int c = key.compareTo(v.key);
        if (c == 0) { // key already exists in v; overwrite node's item with new item
            v.item = item;
			updatePath(v);
            return v;
        }

        BSTreeNode u = new BSTreeNode(key, item, v); // new node becomes child of v
		
		//edv elegxos gia kokkiko kombo me kokkiko paidi
		//kaloume thn fix
        if (c < 0) {
            v.left = u;
			//u.isRed=true;
			//u.left=null;
			//u.right=null;
			updatePath(u);
        } else{
            v.right = u;
			//u.isRed=true;
			//u.left=null;
			//u.right=null;
			updatePath(u);
        }

       
		if(v.isRed==true && u.isRed==true){
			fix(u);
		}
        return u;
    }

    // insert item with key
    public void insert(Key key, Item item) {
        BSTreeNode v = insertNode(key, item);
        updatePath(v); 
    }
	
	private String printColor(BSTreeNode v){
		String s="";
		for(int i=0; i<getN(root); i++){
			if(v.isRed==true){
				return "RED";
			}else{
				return "BLACK";
			}
		}
		return s;
	}
	
    // inorder traversal: prints the key of each node
    void printNode(BSTreeNode v, int level) {
        if (v == null) {
            return;
        }
        printNode(v.right, level + 1);
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
		
		String temp=printColor(v);
		
        System.out.println("" + v.key + "[" + v.height + "," + v.N + "]"+"("+temp+")");
        printNode(v.left, level + 1);
    }

    // print binary tree
    public void print() {
        System.out.println("Printing binary search tree");
        System.out.println("");
        printNode(root, 0);
        System.out.println("");
    }
	
	//posa kleidia einai mikrotera toy m
	private int rankm(Key m,BSTreeNode x){
		if(x==null){
			return 0;
		}
		int c = m.compareTo(x.key);
		if(c<0){
			return rankm(m, x.left);
		}else if(c>0){
			return 1+getN(x.left)+rankm(m,x.right);
		}else{
			return getN(x.left);
		}
	}
	
	public int rankm(Key m){
		return rankm(m,root);
	}
	
	//posa kleidia einai megalytera toy k
	private int rankk(Key k,BSTreeNode x){
		if(x==null){
			return 0;
		}
		int c = k.compareTo(x.key);
		if(c>0){
			return rankk(k, x.right);
		}else if(c<0){
			return 1+getN(x.right)+rankk(k,x.left);
		}else{
			return getN(x.right);
		}
	}
	
	public int rankk(Key k){
		return rankk(k,root);
	}
	
	public int range(Key k,Key m){
		if(rankk(k)-rankm(m)>0){
			return rankk(k)-rankm(m);
		}else{
			return rankm(m)-rankk(k);
		}
	}
	
	/*public int range(Key k,Key m){
		int q=0;
			BSTreeNode c=root;
			while(c!=null){
				if (c.left == null){  
					// check if current node
					// lies between n1 and n2  
					if (c.key.compareTo(k)>0 && c.key.compareTo(m)<0){  
						q=q+1; 
					}
					c= c.right; 
					
				}else {  
					BSTreeNode pre = c.left;   
					while (pre.right != null && pre.right != c) { 
						pre = pre.right;  
		  
						if (pre.right == null){  
							pre.right = c;  
							c = c.left;  
						}else {  
							pre.right = null;   
							if (c.key.compareTo(k)>0 && c.key.compareTo(m)<0) {  
								q=q+1; 
							}  
							c = c.right;  
						}  
					}
				}  
			}
			return q;
		}
	}*/

    public static void main(String[] args) {
        System.out.println("Test Binary Search Tree");
        int n = Integer.parseInt(args[0]);
        System.out.println("number of keys n = " + n);

        RedBlackTree T = new RedBlackTree<Integer, String>();

        Random rand = new Random(0);
        int[] keys = new int[n];
        for (int i = 0; i < n; i++) { // store n random numbers in [0,2n)
        	keys[i] = rand.nextInt(2*n);
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            String item = "item" + i;
            T.insert(keys[i], item);
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("construction time = " + totalTime);
        //T.print();
        System.out.println("tree height = " + T.getTreeHeight());
        
        startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            if (T.search(keys[i]) == null) {
                System.out.println("key " + keys[i] + " not found!");
            }
        }
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("search time = " + totalTime);
    }
}