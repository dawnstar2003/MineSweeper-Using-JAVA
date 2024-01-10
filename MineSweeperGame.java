package GameMineSweeper;

import java.util.*;

public class MineSweeperGame {

	//General instruction about the game
	private static void greetings() {
		System.out.println(
				"Welcome to the MineSweeper game! When you spot an 'x' in a cell, it means there are no mines nearby."
						+ " But if you see a 'number' there, it shows how many mines are around that cell. "
						+ "Your goal is to uncover all cells except the ones with mines to win. Be careful though! "
						+ "If you accidentally open a cell with a mine, game over!");
	}

	//board structure on the beginning of the game
	private static void printBoard(int board[][]) {
		System.out.println("      0    1    2    3    4    5    6    7    8    9");
		System.out.print("   ---------------------------------------------------");
		System.out.println();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0)
					System.out.print(" " + i + " |");
				if (board[i][j] == 0)
					System.out.print("    |");
			}
			System.out.println();
			System.out.print("   ---------------------------------------------------");
			System.out.println();
		}
	}

	//board structure after the game over
	private static void printBoardFinal(int board[][]) {
		System.out.println("      0    1    2    3    4    5    6    7    8    9");
		System.out.print("   ---------------------------------------------------");
		System.out.println();
		for (int i = 0; i < 10; i++) {
			// System.out.print("|");
			for (int j = 0; j < 10; j++) {
				if (j == 0)
					System.out.print(" " + i + " |");
				if (board[i][j] == 0)
					System.out.print("    |");
				else {
					if (board[i][j] == -1)
						System.out.print(" 💣 |");
					else
						System.out.print(" " + board[i][j] + "  |");
				}
			}
			System.out.println();
			System.out.print("   ---------------------------------------------------");
			System.out.println();
		}
	}
	
	//print only user opened mines
	private static void printBoardAndHideMines(int board[][], List<List<Integer>> opened) {
		System.out.println("      0    1    2    3    4    5    6    7    8    9");
		System.out.print("   ---------------------------------------------------");
		System.out.println();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0)
					System.out.print(" " + i + " |");
				boolean isOpened = checkIfOpen(opened, i, j);
				if (isOpened) {
					if (board[i][j] != 0)
						System.out.print("  " + board[i][j] + " |");
					else
						System.out.print("  X |");
				}

				else
					System.out.print("    |");

			}
			System.out.println();
			System.out.print("   ---------------------------------------------------");
			System.out.println();
		}
	}


	//check whether the cell already been opened or not
	private static boolean checkIfOpen(List<List<Integer>> opened, int i, int j) {
		for (List<Integer> innerList : opened) {
			if (i == innerList.get(0) && j == innerList.get(1))
				return true;
		}
		return false;
	}

	//Generate the board by setting bomb on the random cells
	private static int generateTheBoard(int[][] board, int row, int col) {
		int noOfMines = 0;
		for (int i = 0; i < 20; i++) {
			Random random = new Random();
			int MinesRow = random.nextInt(10);
			int MinesCol = random.nextInt(10);

			if (MinesRow != row && MinesCol != col) {
				board[MinesRow][MinesCol] = -1;
				noOfMines++;
			}
		}
		return noOfMines;
	}

	//fill the number of bombs on the surrounding cells
	private static int[][] fillWithNumberOfMines(int[][] board) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (board[i][j] != -1) {
					int cnt = checkSurrondings(board, i, j);
					board[i][j] = cnt;
				}
			}
		}
		return board;
	}

	//count the bomb on the surrounding cells and return the cnt to the fillWithNumberOfMines method
	private static int checkSurrondings(int[][] board, int row, int col) {
		int cnt = 0;
		for (int i = row - 1; i <= row - 1 + 2; i++) {
			for (int j = col - 1; j <= col - 1 + 2; j++) {
				if (i >= 0 && i <= 9 && j >= 0 && j <= 9)
					if (board[i][j] == -1)
						cnt++;
			}
		}
		return cnt;
	}

	//when user open the empty cells, it open cells on all direction until it reaches the number
	private static List<List<Integer>> addSomeMines(int[][] board, List<List<Integer>> alreadyOpened, int row,
			int col) {
		int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

		for (int[] dir : directions) {
			int dirRow = dir[0];
			int dirCol = dir[1];
			boolean loop = true;
			System.out.println(dir[0] + " " + dir[1] + " dirr");
			while (loop) {
				int newRow = row + dir[0];
				int newCol = col + dir[1];
				System.out.println(newRow + " " + newCol + " new");
				if (newRow < 0 || newRow > 9 || newCol < 0 || newCol > 9 || board[newRow][newCol] == -1) {
					break;
				} else {
					boolean isAClosedOne = checkIfOpen(alreadyOpened, newRow, newCol);
					if (!isAClosedOne)
						alreadyOpened.add(List.of(newRow, newCol));
					if (isAClosedOne || board[newRow][newCol] > 0)
						break;
				}
				dir[0] += dirRow;
				dir[1] += dirCol;
			}
		}
		return alreadyOpened;
	}

	//get and validate the user Input
	private static int getAndValidateInput(Scanner sc, boolean rowOrCol) {
		if (rowOrCol)
			System.out.println("Enter the Row value: ");
		else
			System.out.println("Enter the Column value : ");
		int n = sc.nextInt();
		while (n < 0 || n > 9) {
			System.out.println("Enter value between 0 to 9");
			n = sc.nextInt();
		}
		return n;
	}

	//controls the game flow
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		greetings();
		int board[][] = new int[10][10];
		int row = 0, col = 0, totalMines = 0;
		List<List<Integer>> alreadyOpened = new ArrayList<>();

		printBoard(board);

		row = getAndValidateInput(sc, true);
		col = getAndValidateInput(sc, false);

		alreadyOpened.add(List.of(row, col));

		totalMines = generateTheBoard(board, row, col);
		fillWithNumberOfMines(board);

		if (board[row][col] == 0) {
			addSomeMines(board, alreadyOpened, row, col);
		}

		printBoardAndHideMines(board, alreadyOpened);

		while (alreadyOpened.size() + totalMines < 100) {
			row = getAndValidateInput(sc, true);
			col = getAndValidateInput(sc, false);

			boolean isAClosedOne = checkIfOpen(alreadyOpened, row, col);

			if (!isAClosedOne) {
				alreadyOpened.add(List.of(row, col));
				if (board[row][col] == -1) {
					System.out.println("Opps, You Opened a Mine And Lost the Game...(^◕.◕^)");
					printBoardFinal(board);
					System.exit(0);
				} else {
					if (board[row][col] == 0) {
						addSomeMines(board, alreadyOpened, row, col);
					}
					printBoardAndHideMines(board, alreadyOpened);
				}
			}
		}
		printBoardFinal(board);
		System.out.println("YOU WON THE GAME....(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧");
	}
}
