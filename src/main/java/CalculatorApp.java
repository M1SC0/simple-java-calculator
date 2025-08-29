import com.formdev.flatlaf.*;
import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMTMaterialDarkerIJTheme;
import view.CalculatorGui;

import javax.swing.*;
import java.net.MalformedURLException;

public class CalculatorApp {
    public static void main(String[] args) throws MalformedURLException {
        try {
            UIManager.setLookAndFeel(new FlatMTMaterialDarkerIJTheme());
            UIManager.put( "Button.arc", 50 );
            UIManager.put( "TextComponent.arc", 25 );
        } catch (Exception e) {
            e.printStackTrace();
        }

        CalculatorGui calculator = new CalculatorGui();
        calculator.open();
    }

}
