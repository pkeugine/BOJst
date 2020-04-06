#include <stdio.h>
#include <string.h>
#include <ctype.h>

int main() {
  char str[1000001];
  int spaceNum = 0;
  int wordNum = 0;
  int len;

  gets(str);
  len = strlen(str);

  for(int i=0; i<len; i++) {
    if(str[i] == ' ') spaceNum++;
  }

  wordNum = spaceNum + 1;
  if(isspace(str[0])) wordNum--;
  if(isspace(str[len-1])) wordNum--;
  printf("%d", wordNum);

  return 0;
}
