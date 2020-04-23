#include <stdio.h>

int main(void) {
    double write = 0;
    double in = 2.54;
    double ft = 12;

    int nat = 0;
    int ans_ft = 0;
    double ans_in = 0;

    printf("insert your number: ");
    scanf("%lf", &write);

    // change value to inch
    write = write / in;
    nat = (int)write;

    // get answer feet & inch
    ans_ft = nat / ft;
    ans_in = write - ans_ft * ft;

    printf("%d %lf \n", ans_ft, ans_in);
    return 0;
}
