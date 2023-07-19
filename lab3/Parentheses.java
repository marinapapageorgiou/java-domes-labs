import java.io.*;

public class Parentheses {

    private static void PrintOutput(Queue<Integer> Q) {
        /* enter your code! */
		
		for(Integer s: Q){
			System.out.println(s.toString());
			//System.out.println("i="+i+", "+p.getC());
		}
		
    }

    private static void ParseInput(String str) {
        Stack<Pair> S = new Stack<Pair>(8);
        Queue<Integer> Q = new Queue<Integer>(8);
		
		Pair p;

        int N = str.length(); // number of characters in str
        for (int i = 0; i < N; i++) {
            char c = str.charAt(i); // character at position i of str

            /* enter your code! */
			switch (c){
				case '(' :
				case '{' :
				case '[' :	p = new Pair(c,i);
							S.push(p);
							break;
				case ')' : 	if ( S.isEmpty() ) {
								System.out.println("Syntax error! ");
								return;
							}
							p = S.pop();
							if ( p.getC() != '(') {
								System.out.println("Syntax error!");
								return;
							}
							Q.put(p.getP());
							Q.put(i);
							break;
				case ']' : 	if ( S.isEmpty() ) {
								System.out.println("Syntax error!");
								return;
							}
							p = S.pop();
							if ( p.getC() != '[') {
								System.out.println("Syntax error!");
								return;
							}
							Q.put(p.getP());
							Q.put(i);
							break;
				case '}' : 	if ( S.isEmpty() ) {
								System.out.println("Syntax error!");
								return;
							}
							p = S.pop();
							if ( p.getC() != '{') {
								System.out.println("Syntax error!");
								return;
							}
							Q.put(p.getP());
							Q.put(i);
							break;
				default : 	break;
			}
        }
		/*if ( !S.isEmpty() ) {
			System.out.println("Syntax error!");
			return;
		}else{*/
		PrintOutput(Q);
		
    }

    public static void main(String[] args) {
		
        System.out.print("Enter input string > ");
        In.init();
        String str = In.getString();   // read next character
        System.out.println("Input string = " + str + " , length = " + str.length());

        ParseInput(str);
    }
}