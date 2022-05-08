import java.io.IOException;
import java.util.Scanner;


public class Main {
	
	static int money;

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int date[][] = new int[N+1][2];
		for(int i = 1;i<N+1;i++) {
			date[i][0] = sc.nextInt();
			date[i][1] = sc.nextInt();
		}
		money = 0;
		
		rec(date,1, 0);
		
		System.out.println(money);
		
	}
	static void rec(int[][] date,int idx,int val) {
		if(idx > date.length) {
			
			return;
		}
		if(idx == date.length) {
		if(money < val)
			money = val;
		return;
		}
		if(money<val)
			money = val;
		
		rec(date, idx+date[idx][0], val+date[idx][1]);
		rec(date, idx+1, val);
		
		
	}
}
