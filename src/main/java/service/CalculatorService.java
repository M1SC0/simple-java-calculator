package service;

import jdk.jshell.spi.SPIResolutionException;

import java.util.ArrayList;
import java.util.Arrays;

public class CalculatorService {
    private double num1, num2;
    private ArrayList<String> espression;
    private String[] operations = {"/", "x", "+", "-"};

    // OPERATIONS

    private double divide(double num1, double num2) {
        return num1 / num2;
    }

    private double multiply(double num1, double num2) {
        return num1 * num2;
    }

    private double add(double num1, double num2) {
        return num1 + num2;
    }

    private double subtract(double num1, double num2) {
        return num1 - num2;
    }

    public double squareRoot(double number) {
        return Math.sqrt(number);
    }

    // ESPRESSION HANDLING
    public void addToExpression(String value) {
        if (espression == null) {
            espression = new ArrayList<>();
        }
        espression.add(value);

    }

    public void resetExpression() {
        if (espression != null) {
            espression.clear();
        }
    }

    public String getLastFromExpression() {
        if (espression != null && !espression.isEmpty()) {
            return espression.getLast();
        }
        return "";
    }

    public void removeLastFromExpression() {
        if (espression != null && !espression.isEmpty()) {
            espression.remove(espression.size() - 1);
        }
    }

    public void updateLastInExpression(String value) {
        if (espression != null && !espression.isEmpty()) {
            espression.set(espression.size() - 1, value);

        }
    }

    public ArrayList<String> getEspression() {
        return espression;
    }

    public double calculateResult() {
    for (String operation : operations) {
        num1 = Double.NaN;
        String preSign = "";
        Double num = 0.0;

        System.out.println("Processing operation: " + operation);

        for (int i = 0; i < espression.size(); i++) {
            if (espression.get(i).equals(operation) && i != 0) {
                System.out.println("        Updated expression: " + espression);
                System.out.println("            Found operation: " + operation + " at index " + i);

                num1 = Double.parseDouble(espression.get(i - 1));
                if (i != 1 && espression.get(i - 2).equals("-") ) {
                    num1 = -num1;
                }
                System.out.println("            Found num1: " + num1 + " at index " + (i - 1));

                if (i != 1 && espression.get(i - 2).matches("[x|/]")) {
                    preSign = espression.get(i - 2);
                } else {
                    preSign = "";
                }
                espression.remove(i - 1);
                i--;
                espression.remove(i);
                i--;

            } else if (!Double.isNaN(num1) && isNumber(espression.get(i))) {
                num2 = Double.parseDouble(espression.get(i));
                System.out.println("            Found num2: " + num2 + " at index " + (i));
                switch (operation) {
                    case "+" -> num = add(num1, num2);
                    case "-" -> num = subtract(num1, num2);
                    case "x" -> num = multiply(num1, num2);
                    case "/" -> num = divide(num1, num2);
                }
                System.out.println("                " + num1 + " " + operation + " " + num2 + " = " + num + " preSign: " + preSign);
                espression.set(i, Double.toString(Math.abs(num)));
                if (num < 0 && i != 0) {
                    if (preSign.isEmpty()) {
                        espression.set(i-1, "-");
                    } else {
                        espression.set(i-1, preSign);
                    }
                } else if (num >= 0 && i != 0) {
                    if (preSign.isEmpty()) {
                        espression.set(i-1, "+");
                    } else {
                        espression.set(i-1, preSign);
                    }
                } else if (num < 0 && i == 0) {
                    espression.addFirst("-");
                }
                System.out.println("                current index: " + i + "; current item: " + espression.get(i));
                i = espression.get(i).equals("-") ? i + 1 : i;
                System.out.println("        Updated expression: " + espression);
                double temp = Double.parseDouble(espression.get(i));
                num1 = Double.NaN;
                i = 0;
            }
        }
    }
    if (espression.size() > 1) {
        return Double.parseDouble(espression.getFirst() + espression.getLast());
    } else {
        return Double.parseDouble(espression.getLast());
    }
}

    public void setEspression(ArrayList<String> espression) {
        this.espression = espression;
    }

    private boolean isNumber(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
