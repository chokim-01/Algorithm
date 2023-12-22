import sys
input = sys.stdin.readline
n, k = map(int, input().split())
l = sorted(map(int, input().split()))
print(l[k-1])