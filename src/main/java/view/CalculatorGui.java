package view;

import service.CalculatorService;
import util.CommonCostants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGui extends JFrame implements ActionListener {
    private final SpringLayout springLayout = new SpringLayout();
    private CalculatorService calculatorService;

    // display field
    private JTextField displayField;

    // buttons
    private JButton[] buttons;

    // flags
    private boolean pressedOperator = false;
    private boolean pressedEquals = false;

    public CalculatorGui() {
        super(CommonCostants.APP_NAME);
        setSize(CommonCostants.APP_SIZE[0], CommonCostants.APP_SIZE[1]);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(springLayout);

        calculatorService = new CalculatorService();

        addGuiComponents();
    }

    private void addGuiComponents() {
        // add display components
        addDisplayFieldComponent();

        // add buttons components
        addButtonComponents();
    }

    private void addDisplayFieldComponent() {
        JPanel displayFieldPanel = new JPanel(new BorderLayout());
        displayField = new JTextField(CommonCostants.TEXTFIELD_LENGHT);
        displayField.setFont(new Font("Dialog", Font.BOLD, CommonCostants.TEXTFIELD_FONTSIZE));
        displayField.setEditable(false);
        displayField.setText("0");
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);

        displayFieldPanel.add(displayField, BorderLayout.CENTER);

        this.getContentPane().add(displayFieldPanel);
        springLayout.putConstraint(SpringLayout.NORTH, displayFieldPanel, CommonCostants.TEXTFIELD_SPRINGLAYOUT_NORTHPAD, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, displayFieldPanel, CommonCostants.TEXTFIELD_SPRINGLAYOUT_WESTPAD, SpringLayout.WEST, this);
    }

    private void addButtonComponents() {
        GridLayout gridLayout = new GridLayout(CommonCostants.BUTTON_ROWCOUNT, CommonCostants.BUTTON_COLCOUNT);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(gridLayout);
        this.buttons = new JButton[CommonCostants.BUTTON_COUNT];
        for (int i = 0; i < CommonCostants.BUTTON_COUNT; i++) {
            JButton button = new JButton(getButtonLabel(i));
            button.setFont(new Font("Dialog", Font.PLAIN, CommonCostants.BUTTON_FONTSIZE));
            button.addActionListener(this);

            buttonPanel.add(button);
        }

        gridLayout.setHgap(CommonCostants.BUTTON_HGAP);
        gridLayout.setVgap(CommonCostants.BUTTON_VGAP);

        this.getContentPane().add(buttonPanel);
        springLayout.putConstraint(SpringLayout.NORTH, buttonPanel, CommonCostants.BUTTON_SPRINGLAYOUT_NORTHPAD, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, buttonPanel, CommonCostants.BUTTON_SPRINGLAYOUT_WESTPAD, SpringLayout.WEST, this);


    }

    private String getButtonLabel(int buttonIndex) {
        switch (buttonIndex) {
            case 0: return "7";  case 1: return "8";  case 2: return "9";  case 3: return "/";
            case 4: return "4";  case 5: return "5";  case 6: return "6";  case 7: return "x";
            case 8: return "1";  case 9: return "2";  case 10: return "3"; case 11: return "-";
            case 12: return "."; case 13: return "0"; case 14: return "+"; case 15: return "=";

            default: return "";
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonCommand = e.getActionCommand();
        if (buttonCommand.matches("[0-9]")) {
            if (pressedEquals || pressedOperator || displayField.getText().equals("0")) {
                displayField.setText(buttonCommand);
            } else {
                displayField.setText(displayField.getText() + buttonCommand);
            }
            pressedOperator = false;
            pressedEquals = false;

        } else if (buttonCommand.equals("=")) {
            calculatorService.setNum2(Double.parseDouble(displayField.getText()));

            double result = 0;
            switch (calculatorService.getMathSymbol()) {
                case  '+':
                    System.out.println(calculatorService.getNum1() + " + " + calculatorService.getNum2());
                    result = calculatorService.add();
                    break;
                case '-':
                    System.out.println(calculatorService.getNum1() + " - " + calculatorService.getNum2());
                    result = calculatorService.subtract();
                    break;
                case  'x':
                    System.out.println(calculatorService.getNum1() + " * " + calculatorService.getNum2());
                    result = calculatorService.multiply();
                    break;
                case  '/':
                    System.out.println(calculatorService.getNum1() + " / " + calculatorService.getNum2());
                    result = calculatorService.divide();
            }

            displayField.setText(Double.toString(result));

            pressedEquals = true;
            pressedOperator = false;

        } else if (buttonCommand.equals(".")) {
            if (!displayField.getText().contains(".")) {
                    displayField.setText(displayField.getText() + buttonCommand);
            }
        } else {
            // operator
            if (!pressedOperator) {
                calculatorService.setNum1(Double.parseDouble(displayField.getText()));
            }
            calculatorService.setMathSymbol(buttonCommand.charAt(0));

            // update flags
            pressedOperator = true;
            pressedEquals = false;
        }
        }

    public void open() {
        setVisible(true);
    }
}
