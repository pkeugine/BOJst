#include <stdio.h>

int main() {
  int n, cnt=0;

  while(n>0) {
    cnt++;
    n-=cnt;
  }
  
  if(cnt%2) {
    printf("%d/%d", 1+(-n), cnt+n);
  }
  else {
    printf("%d/%d", cnt+n, 1+(-n));
  }
  
  return 0;
}
