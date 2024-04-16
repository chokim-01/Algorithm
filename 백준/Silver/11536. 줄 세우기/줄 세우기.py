import sys

N = int(input())

lst = []
for _ in range(N):
    lst.append(input())


def find_match(cmp_lst):
    for i in range(N):
        if lst[i] != cmp_lst[i]:
            return False
    return True


sorted_list = sorted(lst)
sorted_reverse_list = sorted(lst, reverse=True)

flag = find_match(sorted_list)
if flag:
    print("INCREASING")
    sys.exit(0)
flag = find_match(sorted_reverse_list)
if flag:
    print("DECREASING")
    sys.exit(0)
print("NEITHER")