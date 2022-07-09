// Name: Sebastian Lara
// VUnetID: Laraso
// Email: sebastian.o.lara@vanderbilt.edu
// Honor statement:“I pledge on my honor that I have neither given nor
//                  received unauthorized aid on this assignment.”

#ifndef SUDOKU_H
#define SUDOKU_H

#include <string>
#include <vector>

struct node {
    node* right = nullptr;
    node* left = nullptr;
    node* above = nullptr;
    node* below = nullptr;
    bool val = false;
    size_t xPos = 0;
    size_t yPos = 0;
};

class Queens {
public:
    /**
     * default constructor
     */
    Queens();

    /**
     * Copy constructor
     *
     * @param rhs   Sudoku object to copy from
     */
    Queens(const Queens& rhs);

    /**
     * Copy assignment, set to delete
     *
     * @param rhs   Sudoku object to copy from
     * @return      this
     */
    Queens& operator=(Queens rhs) = delete;

    /**
     * destructor for Sudoku object
     */
    ~Queens();

    /**
     * loads a Sudoku puzzle from txt file
     *
     * @param filename  the txt file, which contains the Sudoku file to load
     */
    void loadFromFile(const std::string& filename);

    /**
     * attempts to solve the Sudoku puzzle
     *
     * @return  true if solution has been reached, false if unsolved and no
     * possible options left
     */
    bool solve();

    /**
     * checks if this and the Sudoku puzzle provide are equivalent
     *
     * @param other the Sudoku puzzle to compare to
     * @return      true if all values are equivalent, otherwise false
     */
    bool equals(const Queens& other) const;

    /**
     * Prints out the current state of the Sudoku Puzzle
     *
     * @param out       the ostream to print to
     * @param sudoku    the Puzzle to be printed
     * @return          The puzzle formatted
     */
    friend std::ostream& operator<<(std::ostream& out, const Queens& sudoku);

private:
    /**
     * Returns the node with indexes provided
     *
     * @param x the x index
     * @param y the y index
     * @return  the node at the indexes
     */
    node* get(size_t& x, size_t& y) const;

    /**
     * Returns all possible values the provided node can take
     *
     * @param nodeUsed  the node to be checked
     * @return          A string containing all the possible values for nodeUsed
     */
    std::string getPossible(node* nodeUsed) const;

    void setEmptyNodes();

    bool solveHelper();

    std::vector<node*> emptyNodes;

    node* initialPointer = nullptr;
};

#endif // SUDOKU_H