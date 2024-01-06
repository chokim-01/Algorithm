import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int c = 0;
		while (N-- > 0) {
			BigInteger num = new BigInteger(br.readLine());
			num = num.multiply(BigInteger.TWO).add(BigInteger.ONE);
			if (num.isProbablePrime(10))
				c++;
		}
		System.out.println(c);
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= 5 || y >= 5)
			return false;
		return true;
	}
}
