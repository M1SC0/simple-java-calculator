package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    CalculatorService calc = new CalculatorService();

    @Test
    void testAdd() {
        calc.setNum1(2.5);
        calc.setNum2(3.5);
        assertEquals(6.0, calc.add());

        calc.setNum1(-2.5);
        calc.setNum2(3.5);
        assertEquals(1.0, calc.add());

        calc.setNum1(2.5);
        calc.setNum2(-3.5);
        assertEquals(-1.0, calc.add());

        calc.setNum1(-2.5);
        calc.setNum2(-3.5);
        assertEquals(-6.0, calc.add());

        calc.setNum1(2.5);
        calc.setNum2(0);
        assertEquals(2.5, calc.add());

        calc.setNum1(0);
        calc.setNum2(3.5);
        assertEquals(3.5, calc.add());

        calc.setNum1(-2.5);
        calc.setNum2(0);
        assertEquals(-2.5, calc.add());

        calc.setNum1(0);
        calc.setNum2(-3.5);
        assertEquals(-3.5, calc.add());

        calc.setNum1(0);
        calc.setNum2(0);
        assertEquals(0, calc.add());
    }

    @Test
    void testSubtract() {
        calc.setNum1(5.0);
        calc.setNum2(2.0);
        assertEquals(3.0, calc.subtract());

        calc.setNum1(-5.0);
        calc.setNum2(2.0);
        assertEquals(-7.0, calc.subtract());

        calc.setNum1(5.0);
        calc.setNum2(-2.0);
        assertEquals(7.0, calc.subtract());

        calc.setNum1(-5.0);
        calc.setNum2(-2.0);
        assertEquals(-3.0, calc.subtract());

        calc.setNum1(5.0);
        calc.setNum2(0);
        assertEquals(5.0, calc.subtract());

        calc.setNum1(0);
        calc.setNum2(2.0);
        assertEquals(-2.0, calc.subtract());

        calc.setNum1(-5.0);
        calc.setNum2(0);
        assertEquals(-5.0, calc.subtract());

        calc.setNum1(0);
        calc.setNum2(-2.0);
        assertEquals(2.0, calc.subtract());

        calc.setNum1(0);
        calc.setNum2(0);
        assertEquals(0, calc.subtract());
    }

    @Test
    void testMultiply() {
        calc.setNum1(4.0);
        calc.setNum2(2.5);
        assertEquals(10.0, calc.multiply());

        calc.setNum1(-4.0);
        calc.setNum2(2.5);
        assertEquals(-10.0, calc.multiply());

        calc.setNum1(4.0);
        calc.setNum2(-2.5);
        assertEquals(-10.0, calc.multiply());

        calc.setNum1(-4.0);
        calc.setNum2(-2.5);
        assertEquals(10.0, calc.multiply());

        calc.setNum1(4.0);
        calc.setNum2(0);
        assertEquals(0, calc.multiply());

        calc.setNum1(0);
        calc.setNum2(2.5);
        assertEquals(0, calc.multiply());

        calc.setNum1(-4.0);
        calc.setNum2(0);
        assertEquals(0, calc.multiply());

        calc.setNum1(0);
        calc.setNum2(-2.5);
        assertEquals(0, calc.multiply());

        calc.setNum1(0);
        calc.setNum2(0);
        assertEquals(0, calc.multiply());
    }

    @Test
    void testDivide() {
        calc.setNum1(10.0);
        calc.setNum2(2.0);
        assertEquals(5.0, calc.divide());

        calc.setNum1(-10.0);
        calc.setNum2(2.0);
        assertEquals(-5.0, calc.divide());

        calc.setNum1(10.0);
        calc.setNum2(-2.0);
        assertEquals(-5.0, calc.divide());

        calc.setNum1(-10.0);
        calc.setNum2(-2.0);
        assertEquals(5.0, calc.divide());

        calc.setNum1(0);
        calc.setNum2(5.0);
        assertEquals(0, calc.divide());

        calc.setNum1(0);
        calc.setNum2(-5.0);
        assertEquals(0, calc.divide());

        calc.setNum1(0);
        calc.setNum2(0);
        assertTrue(Double.isNaN(calc.divide()));
    }

    @Test
    void testSquareRoot() {
        calc.setNum1(16.0);
        assertEquals(4.0, calc.squareRootNum1());

        calc.setNum1(0);
        assertEquals(0.0, calc.squareRootNum1());

        calc.setNum1(2.0);
        assertEquals(Math.sqrt(2.0), calc.squareRootNum1());

        calc.setNum1(-2.0);
        assertTrue(Double.isNaN(calc.squareRootNum1()));
    }
}