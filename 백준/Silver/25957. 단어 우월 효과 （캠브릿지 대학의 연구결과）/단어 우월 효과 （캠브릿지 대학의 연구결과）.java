import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuffer sb;

		HashMap<String, String> map = new HashMap<>();
		N = Integer.parseInt(br.readLine());

		while (N-- > 0) {
			String value = br.readLine();
			map.put(cut(value), value);
		}
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		while (M-- > 0)
			bw.write(map.get(cut(st.nextToken()))+" ");
		
		bw.flush();
		bw.close();

	}

	static String cut(String value) {
		StringBuffer sb = new StringBuffer();
		sb.append(value.charAt(0));
		if (value.length() != 1) {
			sb.append(value.charAt(value.length() - 1));
			char[] c = value.substring(1, value.length()-1).toCharArray();
			Arrays.sort(c);
			sb.append(String.valueOf(c));
		}
		return sb.toString();
	}
}