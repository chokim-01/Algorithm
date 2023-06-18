import math
n = 1000001
p = [True for _ in range(n)] 
p[1] = False
for i in range(2,int(math.sqrt(n))):
	if not p[i]:
		continue
	for k in range(i*i,n,i):
		p[k] = False
a,b = list(map(int,input().split()))

for c in range(a,b+1):
	if not p[c]:
		continue
	print(c)