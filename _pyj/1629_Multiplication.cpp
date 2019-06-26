#include <iostream>
using namespace std;
long long cal(long long a, long long b, long long c){
  if(b==0){
    if(c==1)
      return 0;
    else
      return 1;
  }
  else if(b==1)
    return a % c;
  else if(b%2 == 0){
    long long tmp = cal(a,b/2,c);
    return (tmp*tmp)%c;
  }
  else
    return (a*cal(a,b-1,c))%c;
}
int main(){
  long long a,b,c;
  cin >> a >> b >> c;
  cout << cal(a,b,c) << '\n';
  return 0;
}
