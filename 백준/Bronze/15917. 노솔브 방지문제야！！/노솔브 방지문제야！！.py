import sys
input = sys.stdin.readline
print('\n'.join('1' if x > 0 and (x & (x-1)) == 0 else '0' for x in [int(input()) for _ in range(int(input()))]))