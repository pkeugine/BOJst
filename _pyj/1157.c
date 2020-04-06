#include <stdio.h>
#include <string.h>

int main() {
  char str[1000001];
  int count;

  int max_char_count;
  int max_count;
  char max_char;

  scanf("%s", str);

  //capitalize everything
  for(int i=0; str[i]; i++) {
    if('a' <= str[i] && str[i] <= 'z') {
      str[i] -= 32;
    }
  }

  max_count = -1;

  for(int i=0; str[i]; i++) {
    count = 0;
    for(int j=0; str[j]; j++) {
      if(j<i) {
        if(str[i] == str[j]) break;
      }
      else if(str[i] == str[j]) count++;
    }
    if(max_count == count) max_char_count++;
    if(max_count < count) {
      max_count = count;
      max_char = str[i];
      max_char_count = 1;
    }
  }
  if(max_char_count>1) max_char = '?';

  printf("%c", max_char);

  return 0;
}
