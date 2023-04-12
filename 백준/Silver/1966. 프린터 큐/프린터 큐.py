from collections import deque
import time

class Node:
    def __init__(self,x,flag):
        self.x = x
        self.flag = flag

    def setNode(self,x,flag):
        self.x = x
        self.flag = flag

    def getNode(self):
        return self.x, self.flag


def bfs(que):
    cnt = 1
    while que:
        n = que.popleft()

        if not que or n.x >= getMax(que):
            if n.flag:
                print(cnt)
                return
            cnt +=1
            continue

        que.append(n)

def getMax(que):
    m = [q.x for q in que]
    return max(m)

if __name__ == '__main__':
    T = int(input())

    for tc in range(T):
        que = deque()
        #문서의 수, 위치
        N, M = list(map(int,input().split()))
        s = list(map(int,input().split()))
        for i in range(len(s)):
            if i == M:
                que.append(Node(s[i],True))
                continue
            que.append(Node(s[i],False))

        getMax(que)

        bfs(que)

