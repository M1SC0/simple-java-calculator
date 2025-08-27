package view;

import service.CalculatorService;
import util.CalculatorCostants;
import util.CustomGbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class CalculatorGui extends JFrame implements ActionListener {
    private CalculatorService calculatorService;

    // display field
    private JTextField displayField;
    private JTextField previousField;
    private JButton delButton;

    // buttons
    private JButton[] buttons;

    // flags
    private boolean pressedOperator = false;
    private boolean pressedEquals = false;
    private String previousOp;
    private String previousNumber;

    public CalculatorGui() {
        // init frame
        super(CalculatorCostants.APP_NAME);

        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(CalculatorCostants.APP_SIZE[0], CalculatorCostants.APP_SIZE[1]));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);

        // init service
        calculatorService = new CalculatorService();

        // handle GUI components
        addGuiComponents();
    }

    private void addGuiComponents() {
        // add display components
        addDisplayFieldComponent();

        // add buttons components
        addNumAndOpbuttons();
    }

    private void addDisplayFieldComponent() {
        JPanel displayFieldPanel = new JPanel(new GridBagLayout());

//        // add previous number field
//        previousField = new JTextField();
//        previousField.setHorizontalAlignment(JTextField.RIGHT);
//        previousField.setFont(CalculatorCostants.PREVIOUSCALC_FONT);
//        previousField.setForeground(Color.GRAY);
//        previousField.setEditable(false);
//        previousField.setText("0");
//
//        displayFieldPanel.add(previousField, new CustomGbc.Builder().
//                setGridx(0).
//                setGridy(0).
//                setWeightx(1).
//                setWeighty(0).
//                setFill(GridBagConstraints.BOTH).
//                setInsets(new Insets(
//                        CalculatorCostants.BUTTON_TOP_INSETS,
//                        CalculatorCostants.BUTTON_LEFT_INSETS,
//                        CalculatorCostants.BUTTON_BOTTOM_INSETS,
//                        CalculatorCostants.BUTTON_RIGHT_INSETS)).
//                build());

        // display field
        displayField = new JTextField();
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(CalculatorCostants.TEXTFIELD_FONT);
        displayField.setEditable(false);
        displayField.setText("0");

        displayFieldPanel.add(displayField, new CustomGbc.Builder().
                setGridx(0).
                setGridy(1).
                setWeightx(1).
                setWeighty(1).
                setFill(GridBagConstraints.BOTH).
                setInsets(new Insets(
                        CalculatorCostants.BUTTON_TOP_INSETS,
                        CalculatorCostants.BUTTON_LEFT_INSETS,
                        CalculatorCostants.BUTTON_BOTTOM_INSETS,
                        CalculatorCostants.BUTTON_RIGHT_INSETS)).
                build());

        // delete last inserted character button
        delButton = new JButton("DEL");
        delButton.setFont(CalculatorCostants.TEXTFIELD_FONT);
        delButton.addActionListener(this);
        displayFieldPanel.add(delButton, new CustomGbc.Builder().
                setGridx(1).
                setGridy(0).
                setWeightx(0).
                setGridheight(2).
                setFill(GridBagConstraints.VERTICAL).
                setInsets(new Insets(
                        CalculatorCostants.BUTTON_TOP_INSETS,
                        CalculatorCostants.BUTTON_LEFT_INSETS,
                        CalculatorCostants.BUTTON_BOTTOM_INSETS,
                        CalculatorCostants.BUTTON_RIGHT_INSETS)).
                build());

        add(displayFieldPanel, new CustomGbc.Builder().
                setGridx(0).
                setGridy(0).
                setWeightx(1).
                setWeighty(0.1).
                setFill(GridBagConstraints.HORIZONTAL).
                setInsets(new Insets(
                        CalculatorCostants.BUTTON_TOP_INSETS,
                        CalculatorCostants.BUTTON_LEFT_INSETS,
                        CalculatorCostants.BUTTON_BOTTOM_INSETS,
                        CalculatorCostants.BUTTON_RIGHT_INSETS)).
                build());
    }

    private void addNumAndOpbuttons() {

        JPanel buttonPanel = new JPanel(new GridLayout(
                CalculatorCostants.BUTTON_ROWCOUNT,
                CalculatorCostants.BUTTON_COLCOUNT,
                CalculatorCostants.BUTTON_HGAP,
                CalculatorCostants.BUTTON_VGAP));

        this.buttons = new JButton[CalculatorCostants.BUTTON_COUNT];
        for (int i = 0; i < CalculatorCostants.BUTTON_COUNT; i++) {
            String buttonLabel = getButtonLabel(i);
            JButton button = new JButton(buttonLabel);
            button.setFont(CalculatorCostants.BUTTON_FONT);
            button.addActionListener(this);
            buttons[i] = button;
            buttonPanel.add(button);
        }

        add(buttonPanel, new CustomGbc.Builder()
                .setGridx(0)
                .setGridy(1)
                .setWeightx(1)
                .setWeighty(1)
                .setFill(GridBagConstraints.BOTH)
                .setInsets(new Insets(
                        CalculatorCostants.BUTTON_TOP_INSETS,
                        CalculatorCostants.BUTTON_LEFT_INSETS,
                        CalculatorCostants.BUTTON_BOTTOM_INSETS,
                        CalculatorCostants.BUTTON_RIGHT_INSETS))
                .build());
    }

    private String getButtonLabel(int buttonIndex) {
        switch (buttonIndex) {
            case 0: return "C";  case 1: return "CE";  case 2: return "√"; case 3: return "/";
            case 4: return "7";  case 5: return "8";  case 6: return "9"; case 7: return "x";
            case 8: return "4";  case 9: return "5";  case 10: return "6"; case 11: return "-";
            case 12: return "1"; case 13: return "2"; case 14: return "3"; case 15: return "+";
            case 16: return "."; case 17: return "0"; case 18: return "00"; case  19: return "=";

            default: return "";
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String buttonCommand = e.getActionCommand();
        double result = 0;

        if (buttonCommand.matches("[0-9]|00")) {
            if (displayField.getText().matches("0|00")) {
                displayField.setText(buttonCommand);
            } else {
                displayField.setText(displayField.getText() + buttonCommand);
            }

        } else if (buttonCommand.matches("[+\\-x/]")) {
            if (!pressedOperator) {
                System.out.println("pressed operator first time: setting pressedOperator to true");
                previousOp = buttonCommand;
                calculatorService.setResult(Double.parseDouble(displayField.getText()));
            } else {
                System.out.println("Operation button : " + buttonCommand);
                switch (previousOp) {
                    case "+":
                        System.out.println("Setting previousOp: " + "+");
                        if (!pressedEquals) {
                            calculatorService.add(Double.parseDouble(displayField.getText()));
                            pressedEquals = false;
                        }
                        break;
                    case "-":
                        System.out.println("Setting previousOp: " + "-");
                        if (!pressedEquals) {
                            calculatorService.subtract(Double.parseDouble(displayField.getText()));
                            pressedEquals = false;
                        }
                        break;
                    case "x":
                        System.out.println("Setting previousOp: " + "x");
                        if (!pressedEquals) {
                            calculatorService.multiply(Double.parseDouble(displayField.getText()));
                            pressedEquals = false;
                        }
                            break;
                    case "/":
                        System.out.println("Setting previousOp: " + "/");
                        if (!pressedEquals) {
                            calculatorService.divide(Double.parseDouble(displayField.getText()));
                            pressedEquals = false;
                        }
                        break;
                }
                pressedOperator = true;
            }

            displayField.setText("0");
            previousOp = buttonCommand;
            pressedOperator = true;
            System.out.println(calculatorService.getResult());
        } else if (buttonCommand.equals("√")) {
            System.out.println("Setting previousOp: " + "√");
            double temp = calculatorService.squareRoot(Double.parseDouble(displayField.getText()));
            displayField.setText(Double.toString(temp));
            pressedEquals = false;
        } else if (buttonCommand.equals("=")) {
            System.out.println("Operation button: =");
            System.out.println("Previous number: " + previousNumber);
            switch (previousOp) {
                case "+":
                    if (!pressedEquals) {
                        result = calculatorService.add(Double.parseDouble(displayField.getText()));
                        previousNumber = displayField.getText();
                    } else {
                    result = calculatorService.add(Double.parseDouble(previousNumber));
                    }
                    break;
                case "-":
                    if (!pressedEquals) {
                        result = calculatorService.subtract(Double.parseDouble(displayField.getText()));
                        previousNumber = displayField.getText();
                    } else {
                    result = calculatorService.subtract(Double.parseDouble(previousNumber));
                    }
                    break;
                case "x":
                    if (!pressedEquals) {
                        result = calculatorService.multiply(Double.parseDouble(displayField.getText()));
                        previousNumber = displayField.getText();
                    } else {
                        System.out.println(calculatorService.getResult() + " * " + previousNumber);
                        result = calculatorService.multiply(Double.parseDouble(previousNumber));
                    }
                    break;
                case "/":
                    if (!pressedEquals) {
                        result = calculatorService.divide(Double.parseDouble(displayField.getText()));
                        previousNumber = displayField.getText();
                    } else {
                    result = calculatorService.divide(Double.parseDouble(previousNumber));
                    }
                    break;
            }

            calculatorService.setResult(result);
            displayField.setText(String.valueOf(result));
            pressedEquals = true;

        } else if (buttonCommand.matches("C|CE|DEL")) {
            switch (buttonCommand) {
                case "C":
                    displayField.setText("0");
                    pressedOperator = false;
                    pressedEquals = false;
                    calculatorService.setResult(0);
                    break;
                case "CE":
                    displayField.setText("0");
                    calculatorService.setResult(0);
                    break;
                case "DEL":
                    if (!(displayField.getText().equals("0")) ) {
                        displayField.setText(displayField.getText().substring(0, displayField.getText().length() - 1));
                    }
                    break;
            }
        }
    }

    public void open() {
        setVisible(true);
    }
}
