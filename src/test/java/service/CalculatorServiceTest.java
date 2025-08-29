package service;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    CalculatorService calc = new CalculatorService();
    double result;

    // --- ADDITION TESTS ---

    @Test
    void testSimpleAddition() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("2", "+", "3"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(5.0, result);
    }

    @Test
    void testSimpleAdditionWithNegative1() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("0", "-", "2", "+", "3"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(1.0, result);
    }

    @Test
    void testSimpleAdditionWithNegative2() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-2", "+", "3"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(1.0, result);
    }

    @Test
    void testSimpleAdditionWithNegative3() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("2", "+", "-3"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-1.0, result);
    }

    @Test
    void testSimpleAdditionWithNegative4() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-2", "+", "-3"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-5.0, result);
    }

    @Test
    void testSimpleAdditionWithZero1() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("0", "+", "3"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(3.0, result);
    }

    @Test
    void testSimpleAdditionWithZero2() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("2", "+", "0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(2.0, result);
    }

    @Test
    void testSimpleAdditionWithZero3() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("0", "+", "0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(0, result);
    }

    @Test
    void testSimpleAdditionWithZeroAndNegative1() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-2", "+", "0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-2.0, result);
    }

    @Test
    void testAdditionWithIntegerMax() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                Integer.toString(Integer.MAX_VALUE), "+", "1"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals((double)Integer.MAX_VALUE + 1, result);
    }

    @Test
    void testAdditionWithIntegerMin() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                Integer.toString(Integer.MIN_VALUE), "+", "-1"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals((double)Integer.MIN_VALUE - 1, result);
    }

    @Test
    void testAdditionWithDecimals() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("1.2", "+", "3.4"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(4.6, result);
    }

    @Test
    void testNegativeDecimalAddition() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-1.1", "+", "2.2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(1.1, result);
    }

    @Test
    void testAdditionWithLargeNumbers() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                Double.toString(Double.MAX_VALUE), "+", "1"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(Double.MAX_VALUE, result);
    }

    @Test void testAdditionWithZeroAndDecimal() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("0", "+", "0.0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(0.0, result);
    }
    @Test void testAdditionNegativeDecimalAndZero() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-0.5", "+", "0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-0.5, result);
    }
    @Test void testAdditionMultipleDecimals() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("1.1", "+", "2.2", "+", "3.3", "+", "4.4"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(11.0, result);
    }
    @Test void testAdditionAlternatingSigns() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("5", "+", "-3", "+", "2", "+", "-1"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(3.0, result);
    }

    // --- SUBTRACTION TESTS ---

    @Test
    void testSimpleSubtraction() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("5", "-", "2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(3.0, result);
    }

    @Test
    void testSimpleSubtractionWithNegative1() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("0", "-", "5", "-", "2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-7.0, result);
    }

    @Test
    void testSimpleSubtractionWithNegative2() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-5", "-", "2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-7.0, result);
    }

    @Test
    void testSimpleSubtractionWithNegative3() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("5", "-", "-2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(7.0, result);
    }

    @Test
    void testSimpleSubtractionWithNegative4() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-5", "-", "-2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-3.0, result);
    }

    @Test
    void testSubtractionToZero() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("7", "-", "7"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(0.0, result);
    }

    @Test
    void testSubtractionWithIntegerMax() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                Integer.toString(Integer.MAX_VALUE), "-", "1"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals((double)Integer.MAX_VALUE - 1, result);
    }

    @Test
    void testSubtractionWithIntegerMin() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                Integer.toString(Integer.MIN_VALUE), "-", "-1"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals((double)Integer.MIN_VALUE + 1, result);
    }

    @Test
    void testSubtractionWithDecimals() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("5.5", "-", "2.2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(3.3, result);
    }

    @Test
    void testNegativeDecimalSubtraction() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-3.3", "-", "1.1"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-4.4, result);
    }

    @Test void testSubtractionWithZeroAndDecimal() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("0", "-", "0.0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(0.0, result);
    }

    @Test void testSubtractionNegativeDecimalAndZero() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-0.5", "-", "0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-0.5, result);
    }

    // TODO: rounding the floating point result to avoid precision issues
    @Test void testSubtractionMultipleDecimals() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("10.5", "-", "2.2", "-", "3.3", "-", "1.0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(4.0, result);
    }

    @Test void testSubtractionAlternatingSigns() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("5", "-", "-3", "-", "2", "-", "-1"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(7.0, result);
    }

    // --- MULTIPLICATION TESTS ---

    @Test
    void testSimpleMultiplication() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("4", "x", "3"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(12.0, result);
    }

    @Test
    void testSimpleMultiplicationWithNegative1() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("0", "-",  "4", "x", "3"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-12.0, result);
    }

    @Test
    void testSimpleMultiplicationWithTwoNegative2() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-4", "x", "3"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-12.0, result);
    }

    @Test
    void testSimpleMultiplicationWithTwoNegative3() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("4", "x", "-3"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-12.0, result);
    }

    @Test
    void testSimpleMultiplicationWithNegative4() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-4", "x", "-3"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(12.0, result);
    }

    @Test
    void testSimpleMultiplicationWithNegative5() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("0", "-", "4", "x", "-3"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(12.0, result);
    }

    @Test
    void testMultiplicationByZero() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("12345", "x", "0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(0.0, result);
    }

    @Test
    void testMultiplicationWithDecimals() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("1.5", "x", "2.0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(3.0, result);
    }

    @Test
    void testDecimalMultiplicationWithNegative() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("2.5", "x", "-4.0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-10.0, result);
    }

    @Test void testMultiplicationWithZeroAndDecimal() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("0", "x", "0.0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(0.0, result);
    }
    @Test void testMultiplicationNegativeDecimalAndZero() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-0.5", "x", "0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(0.0, result);
    }
    @Test void testMultiplicationMultipleDecimals() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("1.1", "x", "2.2", "x", "3.3"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(7.986, result, 1e-9);
    }
    @Test void testMultiplicationAlternatingSigns() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("5", "x", "-3", "x", "2", "x", "-1"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(30.0, result);
    }

    // --- DIVISION TESTS ---

    @Test
    void testSimpleDivision() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("10", "/", "2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(5.0, result);
    }

    @Test
    void testSimpleDivisionWithNegative1() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("0", "-", "10", "/", "2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-5.0, result);
    }

    @Test
    void testSimpleDivisionWithNegative2() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-10", "/", "2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-5.0, result, 1e-9);
    }

    @Test
    void testSimpleDivisionWithNegative3() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("10", "/", "-2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-5.0, result);
    }

    @Test
    void testSimpleDivisionWithNegative4() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-10", "/", "-2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(5.0, result);
    }

    @Test
    void testSimpleDivisionWithNegative5() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("0", "-", "10", "/", "-2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(5.0, result);
    }

    @Test
    void testDivisionWithDecimals() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("7.5", "/", "2.5"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(3.0, result);
    }

    @Test
    void testDecimalDivisionWithNegative() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-9.0", "/", "3.0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-3.0, result);
    }

    @Test void testDivisionWithZeroNumerator() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("0", "/", "5"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(0.0, result);
    }
    @Test void testDivisionWithZeroDenominator() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("5", "/", "0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertTrue(Double.isInfinite(result));
    }
    @Test void testDivisionZeroByZero() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("0", "/", "0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertTrue(Double.isNaN(result));
    }
    @Test void testDivisionMultipleDecimals() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("8.8", "/", "2.2", "/", "2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(2.0, result);
    }
    @Test void testDivisionAlternatingSigns() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-8", "/", "-2", "/", "2"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(2.0, result);
    }

    // --- EDGE & COMPLEX CASES ---

    @Test
    void testSingleNumber() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("42"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(42.0, result);
    }

    @Test
    void testLeadingNegativeNumber() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("-5", "+", "10"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(5.0, result);
    }

    @Test
    void testLongDecimalExpression() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "0.1", "+", "0.2", "+", "0.3", "-", "0.4", "x", "10"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-3.4, result, 1e-9);
    }

    @Test
    void testAlternatingSigns() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "10", "-", "5", "+", "2", "-", "3", "+", "1"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(5.0, result);
    }

    @Test
    void testExpressionWithDecimals() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("2.5", "x", "4", "+", "1.5"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(11.5, result);
    }

    @Test
    void testComplexDecimalExpression() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("1.5", "+", "2.5", "x", "3.0", "-", "4.0", "/", "2.0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(7.0, result);
    }

    @Test
    void testMultipleDecimalOperations() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("10.5", "-", "2.5", "+", "1.0", "x", "2.0", "/", "2.0"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(9.0, result);
    }

    @Test
    void testComplexExpression() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList("3", "+", "6", "-", "9", "x", "8", "/", "4"));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-9.0, result, 1e-9);
    }

    @Test
    void testVeryComplexExpression1() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "5", "+", "3", "x", "2", "-", "8", "/", "4", "+", "7", "x", "2.5", "-", "1.5", "+", "6", "/", "3", "x", "2", "-", "4"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(25.0, result, 1e-9);
    }

    @Test
    void testVeryComplexExpression2() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "12", "/", "3", "x", "2.5", "-", "7", "+", "8", "x", "2", "-", "3.5", "/", "0.5", "+", "6", "-", "4", "x", "2", "+", "1.25"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(11.25, result, 1e-9);
    }

    @Test
    void testVeryComplexExpression3() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "-5", "+", "3", "x", "-2", "-", "8", "/", "-4", "+", "7", "x", "-2.5", "-", "1.5", "+", "6", "/", "-3", "x", "2", "-", "4"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-36.0, result, 1e-9);
    }

    @Test
    void testVeryComplexExpression4() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "100", "/", "2", "x", "3.1415", "-", "42", "+", "7", "x", "8.2", "-", "3.14", "/", "2.71", "+", "6.28", "-", "4", "x", "2.5", "+", "1.618"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(169.2143284133, result, 1e-6);
    }

    @Test
    void testVeryComplexExpression5() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "1.1", "+", "2.2", "x", "3.3", "-", "4.4", "/", "5.5", "+", "6.6", "x", "7.7", "-", "8.8", "+", "9.9", "/", "10.1", "x", "11.2", "-", "12.3"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(48.25821782178218, result, 1e-6);
    }

    @Test
    void testLongExpressionWithAllOperators() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "2", "+", "3", "x", "4", "-", "5", "/", "2", "+", "6", "x", "7", "-", "8", "/", "4", "+", "9"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(60.5, result, 1e-9);
    }

    @Test
    void testExpressionWithManyDecimals() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "0.1", "+", "0.2", "x", "0.3", "-", "0.4", "/", "0.5", "+", "0.6", "x", "0.7", "-", "0.8", "+", "0.9"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-0.12, result, 1e-9);
    }

    @Test
    void testExpressionWithManyNegatives() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "-1", "+", "-2", "x", "-3", "-", "-4", "/", "-2", "+", "-5"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-2.0, result, 1e-9);
    }

    @Test
    void testVeryLongExpression() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "1", "+", "2", "+", "3", "+", "4", "+", "5", "+", "6", "+", "7", "+", "8", "+", "9", "+", "10",
                "-", "5", "x", "2", "/", "2", "+", "3", "x", "4", "-", "6", "/", "3", "+", "7", "x", "2"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(74.0, result, 1e-9);
    }

    @Test
    void testExpressionWithAllZeros() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "0", "+", "0", "-", "0", "x", "0", "/", "1"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(0.0, result);
    }

    @Test
    void testExpressionWithLargeAndSmallNumbers() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "1000000", "+", "0.0001", "-", "999999", "x", "2", "/", "4"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(500000.50009999995, result, 1e-6);
    }

    @Test
    void testExpressionWithAlternatingZeroAndDecimal() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "0", "+", "1.1", "-", "0", "x", "2.2", "/", "1.1"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(1.1, result, 1e-9);
    }

    @Test
    void testExpressionWithManyOperators() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "1", "+", "2", "x", "3", "-", "4", "/", "5", "+", "6", "x", "7", "-", "8", "/", "9", "+", "10", "x", "11"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(157.31111111111113, result);
    }

    @Test
    void testExpressionWithNegativeAndZero() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "-5", "+", "0", "x", "-2", "/", "1"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(-5.0, result, 1e-9);
    }

    @Test
    void testExpressionWithAllNegativeDecimals() {
        ArrayList<String> expr = new ArrayList<>(Arrays.asList(
                "-1.1", "+", "-2.2", "x", "-3.3", "-", "-4.4", "/", "-5.5"
        ));
        calc.setEspression(expr);
        result = calc.calculateResult();
        assertEquals(5.36, result, 1e-9);
    }
}