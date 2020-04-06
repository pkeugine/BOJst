#include <iostream>
#include <queue>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    int N, M;
    queue<int> q;
    
    cin >> N >> M;
    
    //initial queue
    for(int i=1; i<=N; i++) q.push(i);
    
    //operation and print
    cout << "<";
    while(q.size()) {
        if(q.size() == 1) { //the last one
            cout << q.front() << ">";
            q.pop();
            break;
        }
        for(int i=1; i<M; i++) {
            q.push(q.front());
            q.pop();
        }
        cout << q.front() << ", ";
        q.pop();
    }
    cout << endl;
    return 0;
}
