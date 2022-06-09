import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		String s;

		public Node(String s) {
			// TODO Auto-generated constructor stub
			this.s = s;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			int oLen = o.s.length();
			int thisLen = this.s.length();
			String oS = o.s;
			String thisS = this.s;

			boolean flag = false;

			if (oLen > thisLen) {
				flag = true;
				String tmp = oS;
				oS = thisS;
				thisS = tmp;

				int tmpLen = oLen;
				oLen = thisLen;
				thisLen = tmpLen;
			}

			for (int i = 0; i < thisS.length(); i++) {
				if (i >= oS.length()) {
					char now = oS.charAt(i % oLen);
					char thisNow = thisS.charAt(i);

					if (i == thisS.length() - 1) {
						if(thisNow == now) {
							int nI = i%oLen;
							int nthisI = i;
							
							for(int n = 0;n<thisS.length();n++) {
								nI = (nI+1)%oLen;
								nthisI = (nthisI +1)%thisLen;
								
								int noS = oS.charAt(nI);
								int nthisS = thisS.charAt(nthisI);
								if(noS > nthisS)
									return 1 * (flag ? -1 : 1);
								else if(noS == nthisS)
									continue;
								else
									return -1 * (flag ? -1 : 1);
							}
							
							return -1 * (flag ? -1 : 1);
						}
						if (thisNow < now)
							return 1 * (flag ? -1 : 1);
						else
							return -1 * (flag ? -1 : 1);
					}
					if (thisNow == now)
						continue;

					else if (thisNow > now) {
						return -1 * (flag ? -1 : 1);
					} else {
						return 1 * (flag ? -1 : 1);
					}
				}

				if (oS.charAt(i) == thisS.charAt(i))
					continue;

				if (oS.charAt(i) > thisS.charAt(i))
					return 1 * (flag ? -1 : 1);
				else
					return -1 * (flag ? -1 : 1);
			}

			return thisS.compareTo(oS);
		}

		@Override
		public String toString() {
			return this.s;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		ArrayList<Node> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++)
			list.add(new Node(st.nextToken()));

		Collections.sort(list);

		StringBuffer sb = new StringBuffer();
		for (Node n : list)
			sb.append(n.s);

		String s = sb.toString();

		if (s.charAt(0) == '0')
			System.out.println(0);
		else
			System.out.println(s);

	}
}