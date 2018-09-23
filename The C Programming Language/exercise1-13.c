#include <stdio.h>

void histogram(int data[], int n)
{
    int max_count = 0;
    for (int i = 0; i < n; ++i)
        max_count = (data[i] > max_count) ? data[i] : max_count;
    int outer = max_count;
    for (int y = 0; y < outer; ++y)
    {
        for (int x = 0; x < 2 * n; ++x)
        {
            if (x%2 == 0)
                printf(" ");
            else
            {
                if (data[x/2] == max_count)
                {
                    printf("|");
                    --data[x/2];
                }
                else
                    printf(" ");
            }
        }
        printf("\n");
        --max_count;
    }
}

int main(int argc, char const *argv[])
{
    int a[] = {1, 2, 3, 4};
    histogram(a, 4);
    return 0;
}
