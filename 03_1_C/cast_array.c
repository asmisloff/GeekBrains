#include <stdlib.h>
#include <stdio.h>

// Проход по массиву шагами по 2 байта. На процессоре с другим порядком байт результат будет другим.
void print_as_char(unsigned int *arr, int len) {
    unsigned char *sarr = arr;
    for (int i = 0; i < 4 * len; i++) {
        printf("%#0x, ", sarr[i]);
    }
    printf("\b\b\n");
}

int main(int argc, char const *argv[])
{
    const int LEN = 10;
    unsigned int arr[] = {0x11223344, 0x55667788, 0x99aabbcc, 0xddeeff00,
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

    printf("------------ Second way ------------\n");
    print_as_char(arr, LEN);

    return 0;
}
