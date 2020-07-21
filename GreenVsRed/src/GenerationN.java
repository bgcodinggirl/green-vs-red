
public class GenerationN extends Grid {
	
	public GenerationN(int x,int y) {
		this.x=x;
		this.y=y;
		this.grid = new int[y][x];
	}
	
	public void ifCellIsGreenInNextGenerations(int[][] gridArray,int x1,int y1,int N) {
		int result = 0;
		if(gridArray[y1][x1]==1)result++;//check if the cell is green in genZero (including the generation Zero)
		int[][] temp=new int[this.x][this.y];
		for(int i=1; i<=N; i++) {
			temp = createNextGeneration(gridArray); 
			gridArray=temp;//the initial array becomes the next generation
			this.grid = new int[y][x]; //the next generation grid is reset
			if(temp[y1][x1]==1)result++; //check if the cell is green in the next generations, if yes -> increment result
			
		}
		System.out.println("# expected result: "+result);
	}
	
	public int[][] createNextGeneration(int[][] gridArray) {
		for(int i=0;i<grid.length;i++) 
			for(int j=0;j<grid[i].length;j++) {
				this.grid[i][j]=applyRulesToACell(gridArray, i, j); //fill the GenerationN array with new states according to the rules; 
		}
		return this.grid;
	}
	
	public int applyRulesToACell(int[][] gridArray, int row,int col) {
		int temp = -1; //define a temporary variable
		int greenNeighbours=findGreenNeighboursCount(gridArray, row, col); // initialize the variable with the green neighbours count
		if(gridArray[row][col]==0) { //check if the cell is red
			switch(greenNeighbours) { //check the count of the green neighbours
			case 3: temp=1; break; //if the count is 3 the cell will become green
			case 6: temp=1; break; //if the count is 6 the cell will become green
			default: temp=0; break; //if the count is 0,1,2,4,5,7,8 the cell will stay red
			}
		}
		if(gridArray[row][col]==1) { //check if the cell is green
			switch(greenNeighbours) {
			case 2: temp=1; break; //if the count is 3 the cell will stay green
			case 3: temp=1; break; //if the count is 3 the cell will stay green
			case 6: temp=1; break; //if the count is 3 the cell will stay green
			default: temp=0; break; //if the count is 0,1,4,5,7,8 the cell will become red
			}
		}
		return temp; //return the cell's new state
	}
	
	public int findGreenNeighboursCount(int[][] gridArray,int y,int x) {
		int greenCount=0;
		int topLeftCorner,topCenter,topRightCorner,leftSide,rightSide,bottomLeftCorner,bottomCenter,bottomRightCorner;//declare side and corner neighbours variables
		//try-catch top neighbours
		try {
			topLeftCorner = gridArray[y-1][x-1];//if the indexes not out of bounds (if exists topLeftCorner) initialize the variable
			if(topLeftCorner==1)greenCount++; //if topLeftCorner is green increment the count
		}
		catch(ArrayIndexOutOfBoundsException exception) {
		    topLeftCorner = -1; //if topLeftCorner does not exist, then initialize it with -1
		}
		
		try {
			topCenter = gridArray[y-1][x];
			if(topCenter==1)greenCount++;
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			topCenter = -1;
		}
		
		try {
			topRightCorner = gridArray[y-1][x+1];
			if(topRightCorner==1)greenCount++;
		}
		catch(ArrayIndexOutOfBoundsException exception) {
		    topRightCorner = -1;
		}
		
		//try-catch bottom neighbours
		try {
			bottomLeftCorner =  gridArray[y+1][x-1];
			if(bottomLeftCorner==1)greenCount++;
		}
		catch(ArrayIndexOutOfBoundsException exception) {
		    bottomLeftCorner = -1;
		}
		
		try {
			bottomCenter = gridArray[y+1][x];
			if(bottomCenter==1)greenCount++;
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			bottomCenter = -1;
		}
		
		//try-catch side neighbours
		try {
			bottomRightCorner = gridArray[y+1][x+1];
			if(bottomRightCorner==1)greenCount++;
		}
		catch(ArrayIndexOutOfBoundsException exception) {
		    bottomRightCorner = -1;
		}
		
		//side
		try {
			leftSide = gridArray[y][x-1];
			if(leftSide==1)greenCount++;
		}
		catch(ArrayIndexOutOfBoundsException exception) {
		    leftSide = -1;
		}
		try {
			rightSide = gridArray[y][x+1];
			if(rightSide==1)greenCount++;
		}
		catch(ArrayIndexOutOfBoundsException exception) {
		    rightSide = -1;
		}
		return greenCount; //returns the count of the green neighbours
	}
	
}
