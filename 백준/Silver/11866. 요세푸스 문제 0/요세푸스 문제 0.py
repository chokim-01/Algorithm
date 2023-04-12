from collections import deque

if __name__ == '__main__':
    N, K = list(map(int,input().split()))

    que = deque()

    for i in range(N):
        que.append(i+1)
    cnt = 0
    print("<",end="")
    while que:
        cnt +=1
        if cnt %K == 0:
            if len(que) == 1:
                print(que.popleft(),end="")
                continue
            print(que.popleft(),", ",sep="",end="")
        else:
            que.append(que.popleft())
    print(">")



