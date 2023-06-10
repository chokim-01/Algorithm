n = int(input())

for i in range(n):
    a,b = list(map(int,input().split()))
    print("Case #",(i+1),": ",(a+b),sep="")