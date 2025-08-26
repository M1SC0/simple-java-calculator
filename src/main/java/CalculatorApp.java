import com.formdev.flatlaf.intellijthemes.*;
import view.CalculatorGui;

import javax.swing.*;

public class CalculatorApp {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatArcDarkOrangeIJTheme());
            UIManager.put( "Button.arc", 50 );
            UIManager.put( "TextComponent.arc", 25 );



        } catch (Exception e) {
            e.printStackTrace();
        }

        CalculatorGui calculator = new CalculatorGui();
        calculator.open();
    }

}
