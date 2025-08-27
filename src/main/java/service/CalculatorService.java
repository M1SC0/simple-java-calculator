package service;

public class CalculatorService {
    private double result;
    private double num1, num2;
    private char mathSymbol;
    private String cancelSymbol;

    public char getMathSymbol() {
        return mathSymbol;
    }

    public void setMathSymbol(char mathSymbol) {
        this.mathSymbol = mathSymbol;
    }

    public String getCancelSymbol() {
        return cancelSymbol;
    }

    public void setCancelSymbol(String cancelSymbol) {
        this.cancelSymbol = cancelSymbol;
    }

    public double getResult() {
        return result;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public double getNum1() {
        return num1;
    }

    public double getNum2() {
        return num2;
    }

    public double add(double number) {
        result += number;
        return result;
    }

    public double subtract(double number) {
        result -= number;
        return result;
    }

    public double multiply(double number) {
        result *= number;
        return result;
    }

    public double divide(double number) {
        result /= number;
        return result;
    }

    public double squareRoot(double number) {
        return Math.sqrt(number);
    }

    public void setResult(double result) {
        this.result = result;
    }
}
