d = [input().split() for _ in range(3)]
print(*[int(x[1])%100 for x in sorted(d,key=lambda x:int(x[1]))],sep="")
print("".join([x[2][0] for x in sorted(d,key=lambda x:-int(x[0]))]))