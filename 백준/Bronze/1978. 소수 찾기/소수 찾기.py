import math
n = 1001
p = [True for _ in range(n)] 
p[1] = False
for i in range(2,int(math.sqrt(n))):
	if not p[i]:
		continue
	for k in range(i*i,n,i):
		p[k] = False
input()
lst = list(map(int,input().split()))
c = 0
for l in lst:
	c += 1 if p[l] else 0
print(c)