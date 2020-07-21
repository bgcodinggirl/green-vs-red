public class MainClass {
	
	public static void main(String[] args) {
		GenerationZero generationZero = new GenerationZero();
		generationZero.userInput();
		GenerationN generationN = new GenerationN(generationZero.x,generationZero.y);
		generationN.ifCellIsGreenInNextGenerations(generationZero.grid, generationZero.x1, generationZero.y1, generationZero.N);
	}

}
