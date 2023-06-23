a = [1,1,2,2,2,8]
b = list(map(int,input().split()))
for i,c in enumerate(b):
    print(a[i] - c,end=" ")