import math
N = int(input())
for _ in range(N):
    a,b = list(map(int,input().split()))
    print((int)(math.factorial(b)/(math.factorial(a)*math.factorial(b-a))))