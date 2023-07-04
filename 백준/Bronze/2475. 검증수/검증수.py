sum = 0
lst = list(map(int,input().split()))
for l in lst:
	sum += l*l
print(sum%10)