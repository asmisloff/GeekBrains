/*
 * Домашнее задание в Geek University по курсу "Основы языка C"
 * Используя заголовочный файл <math.h>, описать функцию, int calculateSquareEquality(int a, int b, int c, float* x1, float* x2),
 * которая будет решать квадратное уравнение вида a * x ^ 2 + b * x + c = 0 и записывать корни этого уравнения в переменные,
 * адреса которых переданы в качестве указателей х1 и х2.
 * Функция должна вернуть -1, если уравнение не имеет корней, 0, если у уравнения есть один корень, и 1, если у уравнения два корня.
 */

#include <math.h>
#include <stdlib.h>
#include <stdio.h>

int discriminant(int a, int b, int c) {
    return b * b - 4 * a * c;
}

int calculateSquareEquality(int a, int b, int c, float* x1, float* x2) {
    int discr = discriminant(a, b, c);

    if (discr < 0) {
        return -1;
    }
    else if (discr == 0) {
        *x1 = -b / (2 * (float)a);
        *x2 = *x1;
        return 0;
    }
    else {
        float dr = sqrtf(discr);
        *x1 = (-b + dr) / (2 * (float)a);
        *x2 = (-b - dr) / (2 * (float)a);
        return 1;
    }
}

//----------------------------------------------------------------------------------------------------------------------------------

void test(int a, int b, int c) {
    float x1, x2;
    int nor = calculateSquareEquality(a, b, c, &x1, &x2); // number of roots

    printf("Уравнение: %dx**2 %s%dx %s%d = 0 ",
            a,
            (b >= 0 ? "+ " : "- "), abs(b),
            (c >= 0 ? "+ " : "- "), abs(c));

    if (nor == -1) {
        printf("не имеет действительных корней\n"); 
    }
    else if (nor == 0) {
        printf("имеет один корень: x = %.4f\n", x1);
    }
    else {
        printf("имеет 2 дейтсвительных корня: x1 = %.4f, x2 = %.4f\n", x1, x2);
    }
}

int main() {
    test(1, 0, 0);
    test(1, 1, 1);
    test(5, 1, -12);

    return 0;
}
