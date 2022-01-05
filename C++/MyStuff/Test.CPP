#include <iostream>
using namespace std;

int main() {

   int *y;
   int x[2];
   x[0] = 3;
   x[1] = 5;
   y=x;
   y++;
   (*y)++;
   std::cout << x[0] <<" " << x[1] << endl;
   return 0;

}