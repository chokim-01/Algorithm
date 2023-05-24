import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int count = 0;
		st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken()); // 사로의 수
		int N = Integer.parseInt(st.nextToken()); // 동물의 수
		int L = Integer.parseInt(st.nextToken()); // 사정거리
		int Sa[] = new int[M]; // 사로의 수
		int[] An[] = new int[N][2];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++)
			Sa[i] = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			An[i][0] = Integer.parseInt(st.nextToken());
			An[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(Sa);
		Arrays.sort(An, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return  o1[0] - o2[0];
			}
		});

		for (int i = 0; i < N; i++) {

			for (int j = 0; j < M; j++) {
				if(An[i][1] >L)
					continue;
				
				
				
				int a = Math.abs(Sa[j] - An[i][0]);
				
				if (a + An[i][1] <= L) {
					An[i][0] = Integer.MAX_VALUE;
					count++;
				}
				if(An[i][0] < Sa[j]) 
					break;
			}
		}

		System.out.println(count);
	}
}