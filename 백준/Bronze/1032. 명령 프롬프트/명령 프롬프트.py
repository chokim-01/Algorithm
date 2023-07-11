N = int(input())

lst = [input() for _ in range(N)]
lenL = len(lst[0])
f = [True for _ in range(lenL)]

for j in range(lenL):
	if not f[j]:
		continue
	for i in range(1,N):
		if lst[i][j] != lst[0][j]:
			f[j] = False
			break

for idx, v in enumerate(lst[0]):
	if f[idx]:
		print(v,end="")
	else:
		print("?",end="")
			