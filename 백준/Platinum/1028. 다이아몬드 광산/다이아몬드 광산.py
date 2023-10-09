import sys

N, M = 0, 0
m = []
count_m = []

class Node:
    def __init__(self, x, y):
        self.x = x
        self.y = y

def find_dia():
    ret = 0
    for i in range(N):
        for j in range(M):
            min_val = min(count_m[i][j][0], count_m[i][j][1])
            if min_val <= ret:
                continue
            while min_val > ret:
                X = i + (min_val - 1)
                leftY = j - (min_val - 1)
                rightY = j + (min_val - 1)
                min_val -= 1
                if not m_chk(X, leftY) or not m_chk(X, rightY):
                    continue
                if count_m[X][rightY][0] < min_val + 1 or count_m[X][leftY][1] < min_val + 1:
                    continue
                min2 = min(count_m[X][leftY][1], count_m[X][rightY][0])
                m = min(min_val + 1, min2)
                if ret < m:
                    ret = m
    return ret

def make_count_m():
    q = []
    for i in range(N):
        q.append(Node(i, 0))
    for j in range(1, M):
        q.append(Node(N - 1, j))
    make(q, True)
    q = []
    for i in range(N):
        q.append(Node(i, M - 1))
    for j in range(M - 1):
        q.append(Node(N - 1, j))
    make(q, False)

def make(q, flag):
    bef = 0
    while q:
        bef = 0
        n = q.pop(0)
        ni = n.x
        nj = n.y
        while True:
            if not m_chk(ni, nj):
                break
            if m[ni][nj]:
                bef += 1
            else:
                bef = 0
            count_m[ni][nj][0 if flag else 1] = bef
            ni -= 1
            nj = nj + 1 if flag else nj - 1

def m_chk(x, y):
    return 0 <= x < N and 0 <= y < M

def input_data():
    global N, M, m, count_m
    N, M = map(int, input().split())
    m = [[False] * M for _ in range(N)]
    count_m = [[[0, 0] for _ in range(M)] for _ in range(N)]

    for i in range(N):
        s = input()
        for j in range(M):
            m[i][j] = s[j] == '1'

def main():
    input_data()
    make_count_m()
    ans = find_dia()
    print(ans)

if __name__ == "__main__":
    main()