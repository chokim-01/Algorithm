#include <stdio.h>
#include <string.h>

int main() {
    char str[101][300] = {0};
    int i;

    for(int i = 0;i<101;++i){
        if(fgets(str[i],300,stdin) == NULL)
            break;
        printf("%s", str[i]);
    }
    
    
    return 0;
}