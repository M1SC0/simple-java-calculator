package service;

public class CalculatorService {

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

    public double add() {
        return (num1 + num2 == -0.0) ? 0.0 : (num1 + num2);    }

    public double subtract() {
        return (num1 - num2 == -0.0) ? 0.0 : (num1 - num2);    }

    public double multiply() {
        return (num1 * num2 == -0.0) ? 0.0 : (num1 * num2);    }

    public double divide() {
        return (num1 / num2 == -0.0) ? 0.0 : (num1 / num2);
    }

    public double squareRootNum1() {
        return Math.sqrt(this.num1);
    }

    public double squareRootNum2() {
        return Math.sqrt(this.num2);
    }

}
