rectangles=[list(map(int, input().split())) for i in range(3)]
ans=0

def is_valid():
    r1,r2,r3 = sorted(rectangles)
    if r1[0]+r2[0]!=r3[0] or r1[1]!=r2[1] or r1[1]+r3[1]!=r3[0]:
        return 0
    return 1

def solve(k):
    if k==3:
        return is_valid()
    if solve(k+1):
        return 1
    rectangles[k][0],rectangles[k][1]=rectangles[k][1],rectangles[k][0]
    if solve(k+1):
        return 1
    return 0

print(solve(0))