// Name: Sebastian Lara
// VUnetID: Laraso
// Email: sebastian.o.lara@vanderbilt.edu
// Honor statement:“I pledge on my honor that I have neither given nor
//                  received unauthorized aid on this assignment.”

#include "Queens.h"
#include <fstream>
#include <string>
#include <vector>

Queens::Queens()
{
    node* topLeftNode = new node;
    node* firstInRow = topLeftNode;
    node* lastNode;
    node* currentNode;
    for (int i = 0; i < 8; ++i) {
        currentNode = firstInRow;
        currentNode->xPos = 0;
        currentNode->yPos = i;
        for (int j = 1; j < 8; ++j) {
            lastNode = currentNode;
            currentNode->right = new node;
            currentNode->right->left = currentNode;
            currentNode = currentNode->right;
            currentNode->xPos = j;
            currentNode->yPos = i;

            lastNode->right = currentNode;
            if (lastNode->above != nullptr) {
                if (lastNode->above->right != nullptr) {
                    currentNode->above = lastNode->above->right;
                    currentNode->above->below = currentNode;
                }
            }
        }

        firstInRow->below = new node;
        firstInRow->below->above = firstInRow;
        firstInRow = firstInRow->below;
    }

    firstInRow->above->below = nullptr;
    delete (firstInRow);

    this->initialPointer = topLeftNode;
}

Queens::Queens(const Queens& rhs)
{
    Queens temp;
    std::swap(this->initialPointer, temp.initialPointer);

    node* firstRowThis = this->initialPointer;
    node* currentThis;
    node* firstRowThat = rhs.initialPointer;
    node* currentThat;

    while (firstRowThis != nullptr) {
        currentThis = firstRowThis;
        currentThat = firstRowThat;
        firstRowThis = firstRowThis->below;
        firstRowThat = firstRowThat->below;
        while (currentThis != nullptr) {
            currentThis->val = currentThat->val;

            currentThis = currentThis->right;
            currentThat = currentThat->right;
        }
    }
}

Queens::~Queens()
{
    node* firstInRow = initialPointer;
    node* currentNode;
    node* nextNode;

    while (firstInRow != nullptr) {
        currentNode = firstInRow;
        firstInRow = firstInRow->below;
        while (currentNode != nullptr) {
            nextNode = currentNode->right;
            delete currentNode;
            currentNode = nextNode;
        }
    }
}

void Queens::loadFromFile(const std::string& filename)
{
    std::ifstream file;
    file.open(filename);

    node* firstInRow = initialPointer;
    node* current = firstInRow;
    std::string line;
    while (std::getline(file, line)) {
        for (char i : line) {
            if (i != ' ') {
                current->val = i == '1';
                current = current->right;
            }
        }
        firstInRow = firstInRow->below;
        current = firstInRow;
    }
    file.close();
}

bool Queens::solve()
{
    return solveHelper();
}

bool Queens::solveHelper()
{
    return true;
}

void Queens::setEmptyNodes()
{
}

std::string Queens::getPossible(node* nodeUsed) const
{
    if (nodeUsed == initialPointer){}
    return "";
}

node* Queens::get(size_t& x, size_t& y) const
{
    if (x == y){}
    return initialPointer;
}

bool Queens::equals(const Queens& other) const
{
    if (this == &other) {
        return true;
    }

    node* firstRowThis = this->initialPointer;
    node* currentThis;
    node* firstRowThat = other.initialPointer;
    node* currentThat;

    while (firstRowThis != nullptr) {
        currentThis = firstRowThis;
        currentThat = firstRowThat;

        firstRowThis = firstRowThis->below;
        firstRowThat = firstRowThat->below;
        while (currentThis != nullptr) {
            if (currentThis->val != currentThat->val) {
                return false;
            }
            currentThis = currentThis->right;
            currentThat = currentThat->right;
        }
    }

    return true;
}

std::ostream& operator<<(std::ostream& out, const Queens& sudoku)
{
    node* firstInRow = sudoku.initialPointer;
    node* current = firstInRow;
    std::string temp;

    for (int i = 0; i < 9; ++i) {

        if (i == 3 || i == 6) {
            out << "------+-------+------\n";
        }
        temp = "";
        for (int j = 0; j < 9; ++j) {
            temp.append(std::to_string(current->val) + " ");

            current = current->right;
        }
        firstInRow = firstInRow->below;
        current = firstInRow;

        for (char j : temp) {
            if (j == '0') {
                temp.replace(temp.find(j), 1, " ");
            }
        }
        temp.insert(6, "| ");
        temp.insert(14, "| ");

        out << temp << "\n";
    }
    return out;
}
