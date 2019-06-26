#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int MAX = 100000;

int N, M;
vector<int> A;

int binarySearch(int low, int high, int target){
  if(low>high)
    return 0;
  else{
    int mid = (low + high) / 2;
    if(A[mid] == target)
      return 1;
    else if(A[mid] > target)
      return binarySearch(low, mid -1, target);
    else
      return binarySearch(mid+1, high, target);
  }
}

int main(){
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cin >> N;

  for(int i=0; i<N; i++){
    int num;
    cin >> num;
    A.push_back(num);
  }
  sort(A.begin(), A.end());
 
  cin >> M;
  for(int i=0; i<M; i++){
    int num;
    cin >> num;
    cout << binarySearch(0, N-1, num) << '\n';
  }
  return 0;
}
