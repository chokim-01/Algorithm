from collections import deque

if __name__ == '__main__':
    que = deque()

    C = int(input())
    N = int(input())

    rn = [[0,0] for i in range(N*2)]

    for i in range(N):
        x,y = list(map(int,input().split()))
        rn[i][0] = x-1
        rn[i][1] = y-1
        rn[i+N][0] = y-1
        rn[i+N][1] = x-1

    flags = [False for i in range(C)]
    flags[0] = True
    que.append(0)
    cnt = 0
    while que:
        n = que.popleft()
        for i in range(len(rn)):
            if rn[i][0] == n and not flags[rn[i][1]]:
                cnt +=1
                flags[rn[i][1]] = True
                que.append(rn[i][1])
    print(cnt)