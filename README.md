# MineSweeper-Using-JAVA

This is a console-based Minesweeper game developed in Java where players uncover cells on a grid, aiming to avoid hidden mines while revealing safe cells to win.

## Introduction

Minesweeper is a single-player puzzle game that involves strategically uncovering cells on a grid. The game board consists of a matrix of cells where some contain hidden mines. Players reveal cells by entering row and column coordinates to uncover them. The objective is to avoid clicking on cells containing mines and uncover all safe cells to win the game.

## How to Play

- **Starting the Game:** Run the Minesweeper game and follow the instructions displayed.
- **Game Display:** The game board is displayed as a grid. Each cell either contains a mine, a number indicating the number of adjacent mines, or is blank.
- **Game Flow:** Players input row and column values to uncover cells. Use the clues from numbers to identify safe cells and avoid mines.
- **Winning/Losing:** Win by uncovering all safe cells. Lose by clicking on a cell containing a mine.
- **Commands:** Use commands provided by the game interface to navigate the grid and reveal cells.

## Game Features

- **Grid Display:** Provides a visual grid representation of the game board.
- **Cell Uncovering:** Allows players to uncover cells using row and column coordinates.
- **Mine Indication:** Differentiates cells containing mines, numbers indicating nearby mines, and empty cells.
- **Game Logic:** Implements win and loss conditions, tracks progress, and manages the game flow.

## Methods and Functions

- **Greetings():** Displays a simple message explaining how to play the game.
- **PrintBoard():** Displays the game board with all cells closed.
- **PrintBoardFinal():** Displays all cells as opened after the game concludes (win/loss).
- **PrintBoardAndHideMines():** Prints cells opened by the user, opening surrounding empty cells if clicked cell is empty.
- **CheckIfOpen():** Checks if a cell is already opened by the user.
- **GenerateTheBoard():** Generates random row and column values, placing mines on the board.
- **FillWithNumberOfMines():** Counts surrounding mines and updates cells with the count.
- **AddSomeMines():** Opens surrounding empty cells until encountering a numbered cell when a user clicks an empty cell.
- **GetAndValidateUserInput():** Retrieves and validates user input for cell coordinates.
- **Main():** Manages game flow, including win/loss conditions, function calls, and game logic.

## How to Run

1. Compile the Java file containing the Minesweeper game.
2. Run the compiled Java file to start the game.
3. Follow on-screen instructions to play and enjoy the Minesweeper game.

## Contribution

Contributions, bug reports, and feature requests are welcome! If you have any improvements or new ideas, feel free to fork the repository and submit pull requests.

