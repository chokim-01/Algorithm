n = input()
ans = ""
for c in n:
    if c.isupper():
        ans += c.lower()
    else:
        ans += c.upper()
print(ans)