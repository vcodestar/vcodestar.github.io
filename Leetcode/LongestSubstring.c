#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int alreadyInString(char ch, char *str);

int main() {

    char *strng = "bbbb";
    char c = 'V';

    char *cpy = malloc(strlen(strng) + 1);
    int i = 0; // Index variable for tmp array
    int count = 0;
    int maxCount = 0;
    char *tmp = malloc(strlen(strng) + 1);


    while(*strng != '\0')
    {
        strcpy(cpy, strng);
        printf("\ncpy: %s", cpy);
        while (!alreadyInString(*(cpy + 0), tmp)) {
            //printf("\nCurrent tmp : %s", tmp);
            tmp[i++] = *cpy++;
        }
        tmp[i] = '\0'; // Null-terminate the tmp array
        count = strlen(tmp);

        if(count > maxCount)
        {
            maxCount = count;
        }

        count = 0;

        printf("\ntmp: %s ", tmp);
        printf("\nCount : %d", maxCount);
        *strng++;
        printf("\nString reset ...");
        tmp[0] = '\0';
        i = 0;
    }


    int tf = alreadyInString(c, tmp);

    printf("\nIn string: %d ", tf);

    free(tmp); // Free the allocated memory

    return 0;
}

int alreadyInString(char ch, char *str) {
    while (*str != '\0') {
        if (ch == *str)
            return 1; // Character found, return true
        str++;
    }
    return 0; // Character not found, return false
}

int makeSub()
{

}
