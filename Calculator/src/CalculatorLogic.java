/**
 * CalculatorLogic handles input, operators, and calculation logic for a basic calculator.
 */
public class CalculatorLogic {
    private StringBuilder currentInputText = new StringBuilder();
    private double storedResult = 0;
    private Operator lastOperator = null;
    private double lastInput = 0;
    private boolean isNewInput = true;  
    private Operator repeatOperator = null;
    private double repeatOperand = 0;

    // Adds a digit to the current input
    public void inputDigit(int digit) {
        if (isNewInput) {
            currentInputText.setLength(0);
            isNewInput = false;
        }
        currentInputText.append(digit);
        lastInput = getCurrentInput();
    }

    // Adds a decimal point to the current input if not already present
    public void inputDot() {
        if (isNewInput) {
            currentInputText.setLength(0);
            currentInputText.append("0");
            isNewInput = false;
        }

        if (!currentInputText.toString().contains(".")) {
            if (currentInputText.length() == 0) {
                currentInputText.append("0");
            }
            currentInputText.append(".");
        }
        lastInput = getCurrentInput();
        System.out.println("inputDot: " + currentInputText);
    }

    // Processes an operator input and updates the stored result
    public void inputOperator(Operator op) {
        if (currentInputText.length() > 0) {
            double currentValue = getCurrentInput();

            if (lastOperator != null) {
                storedResult = lastOperator.apply(storedResult, currentValue);
            } else {
                storedResult = currentValue;
            }

            lastInput = currentValue;
            isNewInput = true;
        }
        lastOperator = op;
        repeatOperator = op;
        repeatOperand = lastInput;
    }

    // Performs the calculation based on the last or repeat operator
    public boolean calculateResult() {
        if (lastOperator == null && repeatOperator == null) {
            return false;
        }

        double currentValue = getCurrentInput();
        double result;

        if (lastOperator != null) {
            result = lastOperator.apply(storedResult, currentValue);
            repeatOperand = currentValue;
            repeatOperator = lastOperator;
        } else if (repeatOperator != null) {
            if (repeatOperand == 0 && currentValue != 0) {
                repeatOperand = currentValue;
            }
            result = repeatOperator.apply(storedResult, repeatOperand);
        } else {
            return false;
        }

        storedResult = result;
        currentInputText = new StringBuilder(formatNumber(result));
        lastInput = result;
        lastOperator = null;
        isNewInput = true;
        return true;
    }

    // Clears all calculator state and resets input
    public void clear() {
        currentInputText.setLength(0);
        storedResult = 0;
        lastOperator = null;
        lastInput = 0;
        repeatOperand = 0;
        repeatOperator = null;
        isNewInput = true;
    }

    // Returns the last operator entered
    public Operator getLastOperator() {
        return lastOperator;
    }

    // Returns the repeat operator used for repeated calculations
    public Operator getRepeatOperator() {
        return repeatOperator;
    }

    // Clears only the current input, keeping stored result
    public void clearInputOnly() {
        currentInputText.setLength(0);
        isNewInput = true;
    }

    // Returns the current number being input
    public double getCurrentInput() {
        if (currentInputText.length() == 0) return 0;
        try {
            return Double.parseDouble(currentInputText.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Returns the last input number
    public double getLastInput() {
        return lastInput;
    }

    // Returns the result stored from previous calculations
    public double getStoredResult() {
        return storedResult;
    }

    // Sets the last operator manually
    public void setLastOperator(Operator op) {
        this.lastOperator = op;
    }

    // Formats a number to remove decimal if not needed
    private String formatNumber(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        } else {
            return String.valueOf(value);
        }
    }

    // Returns the operand used for repeated operations
    public double getRepeatOperand() {
        return repeatOperand;
    }
}
