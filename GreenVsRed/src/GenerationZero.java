import java.util.Arrays;
import java.util.Scanner;

public class GenerationZero extends Grid {

	Scanner input = new Scanner(System.in); 
	int x1, y1, N;
	
	public void userInput() {
		int[] sizeArray = gridWidthAndHeightValues();//initialize the array invoking the method gridWidthAndHeightValues
		int x = sizeArray[0];
		int y = sizeArray[1];
		this.y = y;
		this.x = x;
		this.grid = new int[y][x];//y-height = rows, x-width = columns
		
		int height = y;
		StringBuilder stringBuilder = new StringBuilder();
		
        while(height!=0){
             String cellValues;
             do
             {
            	cellValues = input.nextLine();//read from the console
             } while (cellValues.length()!=x || !checkString(cellValues));
             stringBuilder.append(cellValues);
             cellValues = null;
             height--;
        }
        fillGrid(stringBuilder);
        
        int[] coordinatesAndNArr = coordinatesAndNValues();
		this.x1 = coordinatesAndNArr[0];
		this.y1 = coordinatesAndNArr[1];
		this.N = coordinatesAndNArr[2];
	}
	
	//returns an array whose elements are the size of the grid
	public int[] gridWidthAndHeightValues() {
		String gridSize;
		String[] parts = null;
		do {
			gridSize = input.nextLine();
			gridSize = gridSize.replaceAll(", ", ",");//replace delimiter ", " with delimiter "," in the String
			parts = gridSize.split(",");
		}while(parts.length!=2 || !isNumeric(parts[0]) || !isNumeric(parts[1])
				|| (Integer.parseInt(parts[0]))>=1000 || (Integer.parseInt(parts[1]))>=1000
				|| (Integer.parseInt(parts[0]))>(Integer.parseInt(parts[1])));
		int[] array = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray(); //convert the String array to int array
		return array;
	}
	
	//returns an array whose elements are the last arguments(coordinates of a cell (x1,y1) and N (the last generation)
	public int[] coordinatesAndNValues() {
		String cellCoordinatesAndNString;
		String[] lastInputParts = null;
		do {
			cellCoordinatesAndNString = input.nextLine();
			cellCoordinatesAndNString = cellCoordinatesAndNString.replaceAll(", ", ",");//replace delimiter ", " with delimiter "," in the String
			lastInputParts = cellCoordinatesAndNString.split(",");
		}while(lastInputParts.length!=3 || !isNumeric(lastInputParts[0]) || !isNumeric(lastInputParts[1]) || !isNumeric(lastInputParts[2])
			|| (Integer.parseInt(lastInputParts[0])<0 || Integer.parseInt(lastInputParts[0])>=x)
			|| (Integer.parseInt(lastInputParts[1])<0 || Integer.parseInt(lastInputParts[1])>=y));
		int[] array = Arrays.stream(lastInputParts).mapToInt(Integer::parseInt).toArray(); //convert the String array to int array
		return array;
	}
	
	//method to check if string contains only numbers
	public boolean isNumeric(String number) {
		return number.chars().allMatch(Character::isDigit);
	}
	
	public void fillGrid(StringBuilder stringBuilder)
     {
		String cellValues = stringBuilder.toString(); // declare a variable from type String and initialize it with the string value of the stringBuilder
        int[] num = new int[cellValues.length()]; //create array with size = size of the cellValues
        for (int i = 0; i < cellValues.length(); i++){
	        num[i] = cellValues.charAt(i) - '0'; //  get the char at position i in the String and set the value to the array element at index i
        }
        for(int i = 0; i < y; i++)
        {  
	       for(int j = 0; j < x; j++)
	       {
	          this.grid[i][j] = num[i * x + j];//set state to a cell
	       }
	    }
     }
	
	//check if string contains only 0s and 1s
	public static boolean checkString(String string) {
		char[] arr = string.toCharArray();
		for(int i=0;i<arr.length;i++) {
			if(arr[i]!='0' && arr[i]!='1') { //check if the elements is 0 or 1
			 return false;
			}
		}
		return true;
	}

}
