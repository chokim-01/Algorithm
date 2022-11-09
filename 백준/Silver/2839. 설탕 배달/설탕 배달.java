import java.util.Arrays;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int cnt1 = 0;
		int cnt2 = N/5;
		N%=5;
		while(cnt2>=0) {
			if(N%3==0) {
				cnt1 = N/3;
				N%=3;
				break;
			}
			cnt2--;
			N+=5;
		}
		System.out.println(N==0?cnt1+cnt2:-1);
		
	}
}