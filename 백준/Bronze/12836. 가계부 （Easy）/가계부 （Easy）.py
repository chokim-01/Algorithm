if __name__ == '__main__':
    life, testcases = map(int, input().split())

    queries = [list(map(int, input().split())) for i in range(testcases)]
    ledger = [0 for _ in range(life + 1)]
    for query in queries:
        if query[0] == 1:
            ledger[query[1]] += query[2]
        else:
            calc = 0
            for day in range(query[1], query[2] + 1):
                calc += ledger[day]
            print(calc)