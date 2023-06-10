import sys
from collections import deque

if __name__ == '__main__':
    input = sys.stdin.readline
    T = int(input())
    q = deque()
    for tc in range(T):
        inp = list(input().split())
        com = inp[0]

        if com == "push_front":
            q.appendleft(inp[1])

        if com == "push_back":
            q.append(inp[1])

        if com == "pop_front":
            if not q:
                print(-1)
                continue
            print(q.popleft())

        if com == "pop_back":
            if not q:
                print(-1)
                continue
            print(q.pop())

        if com == "size":
            print(len(q))

        if com == "empty":
            if not q:
                print(1)
                continue
            print(0)

        if com == "front":
            if not q:
                print(-1)
                continue
            print(q[0])

        if com == "back":
            if not q:
                print(-1)
                continue
            print(q[len(q)-1])


