N = int(input())
l = sorted(list(map(int,input().split())))


ans = [1e+10]*3
try:
 for i,v in enumerate(l[:N-2]):
    a = i+1
    b = N-1
    while a < b:
        t = [v,l[a],l[b]]
        s = sum(t)
        if abs(s) <= abs(sum(ans)):
            ans = t
        if s==0:
            raise error
        elif s<0:
            a+=1
        elif s>0:
            b-=1
except:
    N=1
print(*ans)