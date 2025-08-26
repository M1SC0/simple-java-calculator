package view;

import service.CalculatorService;
import util.CalculatorCostants;
import util.CustomGbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.Locale;

public class CalculatorGui extends JFrame implements ActionListener {
    private CalculatorService calculatorService;

    // display field
    private JTextField displayField;
    private JButton delButton;

    // buttons
    private JButton[] buttons;

    // flags
    private boolean pressedOperator = false;
    private boolean pressedEquals = false;

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
        // add display field
        JPanel displayFieldPanel = new JPanel(new GridBagLayout());
        displayField = new JTextField();
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(CalculatorCostants.TEXTFIELD_FONT);
        displayField.setEditable(false);
        displayField.setText("0");

        displayFieldPanel.add(displayField, new CustomGbc.Builder().
                setGridx(0).
                setGridy(0).
                setWeightx(1).
                setWeighty(1).
                setFill(GridBagConstraints.BOTH).
                setInsets(new Insets(
                        CalculatorCostants.BUTTON_TOP_INSETS,
                        CalculatorCostants.BUTTON_LEFT_INSETS,
                        CalculatorCostants.BUTTON_BOTTOM_INSETS,
                        CalculatorCostants.BUTTON_RIGHT_INSETS)).
                build());

        delButton = new JButton("DEL");
        delButton.setFont(CalculatorCostants.TEXTFIELD_FONT);
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (displayField.getText().length() == 1) {
                    displayField.setText("0");
                } else {
                    displayField.setText(displayField.getText().substring(0, displayField.getText().length() - 1)); }
            }
        });
        displayFieldPanel.add(delButton, new CustomGbc.Builder().
                setGridx(1).
                setGridy(0).
                setWeightx(0).
                setWeighty(1).
                setFill(GridBagConstraints.BOTH).
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
            if (pressedEquals || pressedOperator || displayField.getText().matches("0|00")) {
                displayField.setText(buttonCommand);
            } else {
                displayField.setText(displayField.getText() + buttonCommand);
            }

            pressedOperator = false;
            pressedEquals = false;

        } else if (buttonCommand.equals("=")) {
            calculatorService.setNum2(Double.parseDouble(displayField.getText()));

            switch (calculatorService.getMathSymbol()) {
                case  '+':
                    result = calculatorService.add();
                    break;
                case '-':
                    result = calculatorService.subtract();
                    break;
                case  'x':
                    result = calculatorService.multiply();
                    break;
                case  '/':
                    result = calculatorService.divide();
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


            pressedOperator = true;
            pressedEquals = false;
        }

    }

    public void open() {
        setVisible(true);
    }
}
