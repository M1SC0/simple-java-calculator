package view;

import service.CalculatorService;
import util.CalculatorCostants;
import util.CustomGbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGui extends JFrame implements ActionListener {
    // calculator service
    private final CalculatorService CALCULATOR_SERVICE;

    // display field
    private JTextField displayField;
    private JButton delButton;

    // buttons
    private JButton[] buttons;

    // flags
    private boolean pressedOperator = false;
    private boolean pressedEquals = false;
    private boolean pressedNumber = false;

    public CalculatorGui() {
        // init frame
        super(CalculatorCostants.APP_NAME);

        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(CalculatorCostants.APP_SIZE[0], CalculatorCostants.APP_SIZE[1]));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon(getClass().getResource("/icon/small_icon.png"));
        setIconImage(icon.getImage());

        // init service
        CALCULATOR_SERVICE = new CalculatorService();

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
        delButton = new JButton("⌫");
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
            if (buttonLabel.matches("[0-9]|\\.|\\+/-")) {
                button.setBackground(CalculatorCostants.BUTTON_DARKGRAY_COLOR);
                button.setForeground(CalculatorCostants.BUTTON_WHITE_COLOR);
            } else if (buttonLabel.matches("[+\\-x/=]")) {
                button.setBackground(CalculatorCostants.BUTTON_ORANGE_COLOR);
                button.setForeground(CalculatorCostants.BUTTON_WHITE_COLOR);
            } else {
                button.setBackground(CalculatorCostants.BUTTON_LIGHTGRAY_COLOR);
                button.setForeground(CalculatorCostants.BUTTON_BLACK_COLOR);
            }
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
            case 16: return "."; case 17: return "0"; case 18: return "+/-"; case  19: return "=";

            default: return "";
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String buttonCommand = e.getActionCommand();
        double result = 0;

        // handle number buttons
        if (buttonCommand.matches("[0-9]")) {
            if (displayField.getText().matches("0")) {
                displayField.setText(buttonCommand);
            } else {
                displayField.setText(displayField.getText() + buttonCommand);
            }
            pressedNumber = true;
            pressedOperator = false;
            pressedEquals = false;
        }

        // handle operator buttons
        else if (buttonCommand.matches("[+\\-x/]"))
        {
            if (CALCULATOR_SERVICE.getLastFromExpression().matches("[+\\-x/]") && !pressedNumber)
            {
                CALCULATOR_SERVICE.updateLastInExpression(buttonCommand);
            } else {
                CALCULATOR_SERVICE.addToExpression(displayField.getText());
                CALCULATOR_SERVICE.addToExpression(buttonCommand);
                pressedNumber = false;
                pressedOperator = true;
                pressedEquals = false;
        }
            displayField.setText("0");
        }

        // handle equals button
        else if (buttonCommand.equals("=")) {
            if (pressedNumber && !pressedEquals) {
                CALCULATOR_SERVICE.addToExpression(displayField.getText());
                pressedEquals = true;
            } else if (!pressedNumber && pressedOperator && !pressedEquals) {
                CALCULATOR_SERVICE.removeLastFromExpression();
                pressedEquals = true;
            }
            result = CALCULATOR_SERVICE.calculateResult();
            displayField.setText(Double.toString(result));
        }

        // handle special buttons
        else if (buttonCommand.matches("√|\\.|\\+/-"))
        {
            switch (buttonCommand) {
                case ".":
                    if (!displayField.getText().contains(".")) {
                        displayField.setText(displayField.getText() + ".");
                    }
                    break;
                case "√":
                    if (pressedNumber) {
                        CALCULATOR_SERVICE.squareRoot(Double.parseDouble(displayField.getText()));
                        double number = CALCULATOR_SERVICE.squareRoot(Double.parseDouble(displayField.getText()));
                        displayField.setText(number + "");
                    }
                case "+/-":
                    if (!displayField.getText().equals("0")) {
                        if (!displayField.getText().contains("-")) {
                            displayField.setText("-" + displayField.getText());
                        } else {
                            displayField.setText(displayField.getText().substring(1));
                        }
                    }
            }
        }

        // handle clear and delete buttons
        else if (buttonCommand.matches("C|CE|⌫")) {
            switch (buttonCommand) {
                case "C":
                    displayField.setText("0");
                    CALCULATOR_SERVICE.resetExpression();
                    pressedEquals = false;
                    pressedNumber = false;
                    pressedOperator = false;
                    break;
                case "CE":
                    displayField.setText("0");
                    pressedNumber = false;
                    break;
                case "⌫":
                    String currentText = displayField.getText();
                    if (currentText.length() > 1) {
                        displayField.setText(currentText.substring(0, currentText.length() - 1));
                    } else {
                        displayField.setText("0");
                    }
                    break;
            }
        }
    }

    public void open() {
        setVisible(true);
    }
}
