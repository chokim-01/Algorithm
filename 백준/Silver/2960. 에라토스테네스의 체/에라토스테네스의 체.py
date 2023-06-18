import math
n,k = list(map(int,input().split()))
n +=1 
p = [True for _ in range(n)] 

for i in range(2,n):
	if not p[i]:
		continue

	for j in range(i,n,i):
		if not p[j]:
			continue
		p[j] = False
		k-=1
		if k == 0:
			print(j)
			exit(0)
