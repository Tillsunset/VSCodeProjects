#include <iostream>

void func(int x = 1, int y = 2) {
	std::cout << x << " " << y << std::endl;
}

int main() {

   int *y;
   int x[2];
   x[0] = 3;
   x[1] = 5;
   y=x;
   y++;
   (*y)++;
   std::cout << x[0] <<" " << x[1] << std::endl;

	func(1,2);
	return 0;
}