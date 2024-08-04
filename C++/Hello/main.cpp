#include <chrono>
#include <ctime>
#include <iostream>
#include <unistd.h>
#include <cmath>
#include <random>
#include <bit>

unsigned possible(int row, int column, int i) {

	std::cout << column +  row - i<< std::endl;
	std::cout << column -  (row - i)<< std::endl;

	unsigned temp = (1 << column) << (row - i);
	temp |= (1 << column) >> (row - i);

	return temp;
}

void printBits(unsigned num) {
	// Get the size of the integer in bits
	const int bitSize = sizeof(num) * 8;

	// Print the bits using a bitset
	std::bitset<bitSize> bits(num);
	std::cout << bits << std::endl;
}

int main() {
	int row = 10;
	int column = 30;

	for (int i = 0; i < row; ++i) {
		printBits(possible(row,column,i));
	}

	usleep(100);

    return 0;
}

