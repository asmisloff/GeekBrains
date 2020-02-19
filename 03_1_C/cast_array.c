#include <stdlib.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
    const int LEN = 10;
    unsigned int arr[] = {0x11112222, 0x22223333, 0x33334444, 0x44445555,
                 0x55556666, 0x66667777, 0x77778888, 0x88889999,
                 0x9999AAAA, 0x1111BBBB};

    printf("Source array: ");
    for (int i = 0; i < LEN; i++) {
        printf("%#0X, ", arr[i]);
    }
    printf("\b\b\n");

    printf("Array of short pairs: ");
    for (int i = 0; i < LEN; i++) {
        unsigned short left = arr[i] >> 16;
        unsigned short right = arr[i];
        printf("%#0X:%#0X, ", left, right);
        // printf("%d:%d, ", left, right);
    }
    printf("\b\b\n");

    return 0;
}
