a,b,c = map(int,input().split())
print("Subway") if max(a, c) < b else (print("Anything") if max(a, c) == b else print("Bus"))
