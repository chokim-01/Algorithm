import sys
input = sys.stdin.readline
N, M = map(int, input().split())
d = { x[0]: x[1] for _ in range(N) for x in [input().split()]}
print('\n'.join(d[input().rstrip()] for _ in range(M)))
