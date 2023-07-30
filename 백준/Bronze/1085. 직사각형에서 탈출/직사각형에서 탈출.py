l = list(map(int,input().split()))
print(min(min(l[0],l[2]-l[0]),min(l[1],l[3]-l[1])))