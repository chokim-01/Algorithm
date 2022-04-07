import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Node {
		int num;
		Node left, right;
		public Node(int num) {
			// TODO Auto-generated constructor stub
			this.num = num;
		}
		void insert(int num) {
			if(num < this.num) {
				if(this.left == null)
					this.left = new Node(num);
				else
					this.left.insert(num);
			} else {
				if(this.right == null)
					this.right = new Node(num);
				else
					this.right.insert(num);
			}
		}
		
	}
	static StringBuffer sb;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer();
		
		String num = br.readLine();
		Node root = new Node(Integer.parseInt(num));
		while(true) {
			num = br.readLine();
			if(num == null)
				break;
			root.insert(Integer.parseInt(num));
			
		}
		
		dfs(root);
		System.out.println(sb.toString());
	}		
	static void dfs(Node now) {
		if(now == null)
			return;
		dfs(now.left);
		dfs(now.right);
		sb.append(now.num + "\n");
		
	}
}
