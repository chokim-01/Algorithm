import sys
input = sys.stdin.readline
n = int(input())
ar = [0 for i in range(10001)]

for _ in range(n):
    ar[int(input())] +=1

for i in range(1,10001):
    for j in range(ar[i]):
        print(i)