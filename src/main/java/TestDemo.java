import java.util.Random;

public class TestDemo {
	public int addPositive(int a, int b) {
		if (a < 1 || b < 1) {
			throw new IllegalArgumentException("Both parameters must be positive!");
		}
		
		return a + b;
	}
	
	// A simple method to multiply integers
	public int multiplyIntegers(int a, int b) {
		return a*b;
	}
	
	public int randomNumberSquared() {
		int number = getRandomInt();
		
		return number * number;
	}

	public int getRandomInt() {
		Random random = new Random();
		return random.nextInt(10) + 1;
	}
}
