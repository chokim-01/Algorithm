r=[list(map(int,input().split()))for i in range(3)];f=0
def v():
    a,b,c=sorted(r);return a[0]+b[0]==c[0]and a[1]==b[1]and a[1]+c[1]==c[0]
def s(k):
    if k==3:return v()
    ret=s(k+1)
    r[k][0],r[k][1]=r[k][1],r[k][0]
    ret|=s(k+1)
    return ret
print(int(s(0)))