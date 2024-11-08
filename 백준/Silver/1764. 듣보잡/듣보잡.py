import sys
input = sys.stdin.readline
n,m = map(int,input().split())

s = set()
ans = []

for _ in range(n):
    s.add(input())

for _ in range(m):
    inp = input()
    if inp in s:
        ans.append(inp)
print(len(ans))
print(''.join(sorted(ans)))