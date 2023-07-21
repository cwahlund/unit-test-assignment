import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.params.provider.MethodSource;

class TestDemoJUnitTest {
	
	private TestDemo testDemo;
	
	@BeforeEach
	void setUp() {
		testDemo = new TestDemo();
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		
		assertThat(fiveSquared).isEqualTo(25);
	}

	// Tests to make sure multiplied integers are calculated correctly.  Tests were made for 0, and negative numbers.
	@Test
	void assertThatNumbersMultipliedAreCorrect() {
		assertThat(testDemo.multiplyIntegers(1, 5)).isEqualTo(5);
		assertThat(testDemo.multiplyIntegers(0, 5)).isEqualTo(0);
		assertThat(testDemo.multiplyIntegers(2, 10)).isEqualTo(20);
		assertThat(testDemo.multiplyIntegers(-1, 5)).isEqualTo(-5);
		assertThat(testDemo.multiplyIntegers(-1, -5)).isEqualTo(5);
	}
	
	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);
		assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);
		assertThatThrownBy(() -> testDemo.addPositive(-1, 2)).isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@MethodSource("TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
		}
	}
	
	static Stream<Arguments> argumentsForAddPositive(){
		//formatter:off
		return Stream.of(
				arguments(2, 4, 6, false),
				arguments(2, 5, 7, false),
				arguments(-1, 2, 1, true),
				arguments(0, 2, 2, true)
		);
				
		//formatter:on
	}

}
