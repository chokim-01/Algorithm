s = list(input())
a=0
b=0
for i in range(len(s)-2):
    if s[i] == 'J' and s[i+1]=='O' and s[i+2]=='I':
        a+=1
    if s[i] == 'I' and s[i+1]=='O' and s[i+2]=='I':
        b+=1
print(a,b,sep="\n")