import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    static class CustomList {
        int[] arr;
        int startPointer, endPointer;

        CustomList(int len) {
            this.arr = new int[len<<1];
            IntStream.rangeClosed(1, len).forEach(x -> this.arr[x - 1] = x);
            this.startPointer = 0;
            this.endPointer = len;
        }

        StringBuilder go() {
            StringBuilder ans = new StringBuilder();
            while (startPointer < endPointer) {
                ans.append(arr[startPointer++]).append(" ");
                arr[endPointer++] = arr[startPointer++];
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        CustomList customList = new CustomList(N);
        System.out.println(customList.go());
    }
}
