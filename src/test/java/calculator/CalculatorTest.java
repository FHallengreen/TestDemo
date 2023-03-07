package calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {

    @Mock
    private NumberSource numberSource;

    private Calculator cut;



    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        cut = new Calculator(numberSource);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 10L, 100L, Long.MAX_VALUE})
    public void calculator_Multiply_PositiveAndPositive_ReturnsPositive(Long value) {
        Mockito.when(numberSource.next()).thenReturn(value, value);
        assertTrue(cut.multiply() > 0);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 10L, 100L, Long.MAX_VALUE})
    public void calculator_Multiply_PositiveAndNegative_ReturnsNegative(long value){
        Mockito.when(numberSource.next()).thenReturn(value, -value);
        assertTrue(cut.multiply() < 0);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 10L, 100L, Long.MAX_VALUE})
    public void calculator_Multiply_NegativeAndPositive_ReturnsNegative(long value){
        Mockito.when(numberSource.next()).thenReturn(-value, value);
        assertTrue(cut.multiply() < 0);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 10L, 100L, Long.MAX_VALUE})
    public void calculator_Multiply_NegativeAndNegative_ReturnsPositive(long value){
        Mockito.when(numberSource.next()).thenReturn(-value, -value);
        assertTrue(cut.multiply() > 0);
    }



}