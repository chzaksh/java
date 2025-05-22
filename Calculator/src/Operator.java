/**
 * Enum representing mathematical operators and their behavior.
 */
public enum Operator {
    ADD("+") {
        public double apply(double a, double b) { return a + b; }
    },
    SUBTRACT("-") {
        public double apply(double a, double b) { return a - b; }
    },
    MULTIPLY("*") {
        public double apply(double a, double b) { return a * b; }
    },
    DIVIDE("/") {
        public double apply(double a, double b) {
            if (b == 0) {
                return 0;
            }
            return a / b;
        }
    };

    private final String symbol;

    // Constructor to assign the symbol
    Operator(String symbol) {
        this.symbol = symbol;
    }

    // Returns the symbol of the operator
    public String getSymbol() {
        return symbol;
    }

    // Returns the Operator enum based on its symbol
    public static Operator fromSymbol(String symbol) {
        for (Operator op : values()) {
            if (op.getSymbol().equals(symbol)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unknown operator symbol: " + symbol);
    }

    // Performs the operator’s calculation on two operands
    public abstract double apply(double a, double b);

    // Returns the symbol as string representation
    @Override
    public String toString() {
        return symbol;
    }
}
