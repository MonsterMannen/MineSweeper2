public class Game {

	// variables
	private int[][] field;				// the game field with bombs and numbers
	private boolean[][] visibleField;	// what fields are visible after clicking (all false from start)
	private int width;					// nr of cols
	private int height;					// nr of rows
	private int bombs;					// number of bombs
	private boolean gameOver = false;	// sets to true if a bomb is clicked

	// constructor for rectangle field
	public Game(int width, int height, int bombs){
		field = new int[height][width];
		visibleField = new boolean[height][width];
		this.bombs = bombs;
		this.width = width;
		this.height = height;
		initField();
	}

	// constructor for square field
	public Game(int size, int bombs) {
		this(size, size, bombs);
	}

	// sets up the game field
	private void initField(){
		setBombs();
		setNumbers();
	}

	// places bombs
	private void setBombs(){
		int bombsToPlace = bombs;
		while(bombsToPlace > 0){
			int r1 = (int)Math.floor(Math.random() * height);	// int 0 to rows-1
			int r2 = (int)Math.floor(Math.random() * width);	// int 0 to cols-1
			if(field[r1][r2] != -1){
				field[r1][r2] = -1;
				bombsToPlace--;
			}
		}
	}

	// sets how many bombs are around each field
	private void setNumbers(){
		for(int row = 0; row < height; row++){
			for(int col = 0; col < width; col++){

				// skip bomb fields
				if(field[row][col] == -1){
					continue;
				}

				// check 3x3 square around current field
				for(int i = -1; i <= 1; i++){
					for(int j = -1; j <= 1; j++){
						// make sure we are not checking outside the field
						if(isInside(row+i, col+j)){
							// check if bomb
							if(field[row+i][col+j] == -1){
								// bomb found, increase number
								field[row][col]++;
							}
						}
					}
				}
			}
		}
	}

	// check if inside game field
	private boolean isInside(int row, int col){
		return (row >= 0 && row < height && col >= 0 && col < width);
	}

	// ****************************** public methods ******************************

	// returns true if game is over; a bomb has been clicked
	public boolean isGameOver(){
		return gameOver;
	}

	// return true if all fields except the bombs are visible
	public boolean isGameWon(){
		int nrVisible = 0;
		for(int row = 0; row < height; row++){
			for(int col = 0; col < width; col++){
				if(visibleField[row][col]){
					nrVisible++;
				}
			}
		}
		// all fields that are not bombs are visible and the game is not over
		return (nrVisible + bombs == width * height && !gameOver);
	}

	// returns the game field
	public int[][] getField(){
		return field;
	}

	// returns the visible fields
	public boolean[][] getVisibleField(){
		return visibleField;	// should probably return a copy
	}

	// return value on a specific spot
	public int getField(int row, int col){
		return field[row][col];
	}

	// run when user clicks a field
	public void clickField(int row, int col){
		// set the clicked field to visible
		visibleField[row][col] = true;

		// clicked on a bomb
		if(field[row][col] == -1){
			gameOver = true;
			// TODO reveal rest of bombs too
			return;
		}

		// clicked on a numbered field, don't need to do more
		if(field[row][col] > 0){
			return;
		}

		// clicked on a 0-field, need to scan surrounding fields
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				// make sure we dont try to look outside gamefield
				if(isInside(row+i, col+j)){
					// sets surrounding 0-fields to visible
					if(field[row+i][col+j] == 0 && visibleField[row+i][col+j] == false){
						clickField(row+i, col+j);
					// sets all numbered fields (>0) to visible if this was a 0
					}else if(field[row+i][col+j] > 0){
						visibleField[row+i][col+j] = true;
					}
				}
			}
		}
	}

	// debug method
	public void printField(){
		for(int row = 0; row < height; row++){
			for(int col = 0; col < width; col++){
				if(field[row][col] >= 0){
					System.out.print(field[row][col] + " ");
				}else{
					System.out.print("* ");
				}
			}
			System.out.print("\n");
		}
	}

	// debug method
	public void printVisible(){
		for(int row = 0; row < height; row++){
			for(int col = 0; col < width; col++){
				if(visibleField[row][col] == true){
					System.out.print(field[row][col] + " ");
				}else{
					System.out.print("_ ");
				}
			}
			System.out.print("\n");
		}
	}

	// test
	/*
	public static void main(String args[]){
		int bombs = 2;
		Game minesweeper = new Game(5, bombs);
		minesweeper.printField();
		minesweeper.clickField(0, 0);
		System.out.println("===========");
		minesweeper.printVisible();
	}
	*/
}
