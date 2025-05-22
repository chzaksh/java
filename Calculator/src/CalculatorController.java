import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

/**
 * Controller class for the calculator UI.
 * Handles button inputs and updates the display.
 */
public class CalculatorController {

    @FXML
    private TextField display;

    @FXML
    private TextField expressionDisplay;

    private CalculatorLogic logic = new CalculatorLogic();
    private StringBuilder expression = new StringBuilder();
    private boolean lastInputWasOperator = false;
    private boolean justCalculated = false;

    // Initializes the calculator display
    @FXML
    public void initialize() {
        display.setText("0");
        expressionDisplay.setText("");
    }

    // Handles any button click event
    @FXML
    private void handleButton(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if (isDigit(value)) {
            handleDigit(value);
        } else if (value.equals(".")) {
            handleDot();
        } else if (isOperator(value)) {
            handleOperator(value);
        } else if (value.equals("=")) {
            handleEquals();
        } else if (value.equals("C")) {
            handleClear();
        }
    }

    // Processes a digit input
    private void handleDigit(String digit) {
        if (justCalculated) {
            expression.setLength(0);
            expressionDisplay.setText("");
            logic.clear();
            justCalculated = false;
        }

        logic.inputDigit(Integer.parseInt(digit));
        updateDisplay();
        lastInputWasOperator = false;
    }

    // Processes a decimal point input
    private void handleDot() {
        if (justCalculated) {
            expression.setLength(0);
            expressionDisplay.setText("");
            logic.clear();
            justCalculated = false;
        }

        logic.inputDot();
        updateDisplay();
        lastInputWasOperator = false;
    }

    // Processes an operator (+, -, *, /)
    private void handleOperator(String operatorSymbol) {
        Operator op = Operator.fromSymbol(operatorSymbol);

        if (justCalculated) {
            double result = logic.getCurrentInput();
            expression.setLength(0);
            expression.append(formatNumber(result));
            justCalculated = false;
        } else if (lastInputWasOperator) {
            if (expression.length() > 0) {
                expression.setCharAt(expression.length() - 1, operatorSymbol.charAt(0));
                expressionDisplay.setText(expression.toString());
                logic.setLastOperator(op);
                return;
            }
        } else {
            expression.append(formatNumber(logic.getCurrentInput()));
        }
        expression.append(operatorSymbol);
        logic.inputOperator(op);
        expressionDisplay.setText(expression.toString());
        lastInputWasOperator = true;
    }

    // Handles equals (=) button and performs the calculation
    private void handleEquals() {
        double previousResult = logic.getStoredResult();

        if (!justCalculated) {
            if (!expression.toString().contains("+") &&
                !expression.toString().contains("-") &&
                !expression.toString().contains("*") &&
                !expression.toString().contains("/")) {
                return;
            }

            if (!lastInputWasOperator) {
                expression.append(formatNumber(logic.getCurrentInput()));
            } else {
                expression.append(formatNumber(logic.getCurrentInput()));
                expressionDisplay.setText(expression.toString());
            }
        }

        boolean success = logic.calculateResult();
        if (success) {
            if (!expression.toString().contains("=")) {
                expression.append("=");
                expression.append(formatNumber(logic.getCurrentInput()));
            } else {
                expression.setLength(0);
                expression.append(formatNumber(previousResult));
                expression.append(logic.getRepeatOperator().getSymbol());
                expression.append(formatNumber(logic.getRepeatOperand()));
                expression.append("=");
                expression.append(formatNumber(logic.getStoredResult()));
            }

            updateDisplay();
            justCalculated = true;
        }
    }

    // Handles the clear (C) button to reset calculator
    private void handleClear() {
        logic.clear();
        expression.setLength(0);
        updateDisplay();
        lastInputWasOperator = false;
        justCalculated = false;
    }

    // Updates the display and expression fields
    private void updateDisplay() {
        display.setText(formatNumber(logic.getCurrentInput()));
        expressionDisplay.setText(expression.toString());
    }

    // Checks if the input is a digit (0–9)
    private boolean isDigit(String value) {
        return value.matches("\\d");
    }

    // Checks if the input is a valid operator
    private boolean isOperator(String value) {
        return "+-*/".contains(value);
    }

    // Formats a number to string with no decimal if not needed
    private String formatNumber(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        } else {
            return String.valueOf(value);
        }
    }
}
