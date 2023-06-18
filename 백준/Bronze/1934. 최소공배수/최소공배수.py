import math
t = int(input())

for _ in range(t):
	a,b = list(map(int,input().split()))
	print(math.lcm(a,b))