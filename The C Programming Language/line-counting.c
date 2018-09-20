#include <stdio.h>

/* count lines in input */
int main(int argc, char const *argv[])
{
    int c, bl, tl, nl;
    bl = 0;
    tl = 0;
    nl = 0;
    while ((c = getchar()) != EOF) {
        if (c == '\n')
            ++nl;
        else if (c == ' ')
            ++bl;
        else if (c == '\t')
            ++tl;
    // printf("%d\n", nl);
    }
    return 0;
}
