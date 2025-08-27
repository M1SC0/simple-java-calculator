/*
package util;

import java.util.Locale;

public class d {

    else if (buttonCommand.equals("=")) {
        calculatorService.setNum2(Double.parseDouble(displayField.getText()));

        switch (calculatorService.getMathSymbol()) {
            case  '+':
                result = calculatorService.add(Double.parseDouble(displayField.getText()));
                break;
            case '-':
                result = calculatorService.subtract(Double.parseDouble(displayField.getText()));
                break;
            case  'x':
                result = calculatorService.multiply(Double.parseDouble(displayField.getText()));
                break;
            case  '/':
                result = calculatorService.divide(Double.parseDouble(displayField.getText()));
                break;
        }
        displayField.setText(String.format(Locale.US, "%f", result));

        pressedEquals = true;
        pressedOperator = false;

    } else if (buttonCommand.equals("√")) {
        if (!pressedOperator) {
            calculatorService.setNum1(Double.parseDouble(displayField.getText()));
            result = calculatorService.squareRootNum1();
        } else {
            calculatorService.setNum2(calculatorService.getNum1());
            result = calculatorService.squareRootNum2();
        }
        displayField.setText(String.format(Locale.US, "%f", result));

        pressedEquals = false;
        pressedOperator = false;


        System.out.println(result);
    } else if (buttonCommand.equals(".")) {
        if (!displayField.getText().contains(".")) {
            displayField.setText(displayField.getText() + buttonCommand);
        }

        pressedEquals = false;
        pressedOperator = false;

    } else if (buttonCommand.matches("C|CE|DEL")) {
        switch (buttonCommand) {
            case "C":
                displayField.setText("0");
                calculatorService.setNum1(0);
                calculatorService.setNum2(0);
                break;
            case "CE":
                displayField.setText("0");
                break;
            case "DEL":
                if (displayField.getText().length() == 1) {
                    displayField.setText("0");
                } else {
                    displayField.setText(displayField.getText().substring(0, displayField.getText().length() - 1));
                    break;
                }
        }

        pressedEquals = false;
        pressedOperator = false;

    } else {
        if (!pressedOperator) {
            calculatorService.setNum1(Double.parseDouble(displayField.getText()));
        }
        if (!pressedOperator && displayField.getText().equals("0") && buttonCommand.equals("-")) {
            displayField.setText("-");
            pressedOperator = false;
            pressedEquals = false;
            return;
        }
        calculatorService.setMathSymbol(buttonCommand.charAt(0));
        previousField.setText(displayField.getText());



        pressedOperator = true;
        pressedEquals = false;
    }

}





}
*/
