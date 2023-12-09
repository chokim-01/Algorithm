from sys import stdin as s
input = s.readline
print('\n'.join(str(i) for i in sorted([int(input()) for _ in range(int(input()))])))  