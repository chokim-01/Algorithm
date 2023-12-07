lst = []
while True:
    try:
        for num in input().split():
            lst.append(int(num[::-1]))

    except EOFError:
        lst.remove(lst[0])
        lst.sort()
        print('\n'.join(str(x) for x in lst))
        break