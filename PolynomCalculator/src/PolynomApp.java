import java.util.Scanner;
import javafx.util.Pair;

public class PolynomApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Polynom polynom1 = null;
        Polynom polynom2 = null;

        // Read the first polynom
        while (polynom1 == null) {
            try {
                System.out.println("Enter the coefficients of the first polynom (separated by commas):");
                String[] coeffsInput1 = scanner.nextLine().split(",");
                double[] coefficients1 = new double[coeffsInput1.length];
                for (int i = 0; i < coeffsInput1.length; i++) {
                    coefficients1[i] = Double.parseDouble(coeffsInput1[i].trim());
                }

                System.out.println("Enter the exponents of the first polynom (separated by commas):");
                String[] expsInput1 = scanner.nextLine().split(",");
                int[] exponents1 = new int[expsInput1.length];
                for (int i = 0; i < expsInput1.length; i++) {
                    exponents1[i] = Integer.parseInt(expsInput1[i].trim());
                }

                polynom1 = new Polynom(coefficients1, exponents1);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.\n");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter valid numbers separated by commas.\n");
            }
        }

        // Read the second polynom
        while (polynom2 == null) {
            try {
                System.out.println("Enter the coefficients of the second polynom (separated by commas):");
                String[] coeffsInput2 = scanner.nextLine().split(",");
                double[] coefficients2 = new double[coeffsInput2.length];
                for (int i = 0; i < coeffsInput2.length; i++) {
                    coefficients2[i] = Double.parseDouble(coeffsInput2[i].trim());
                }

                System.out.println("Enter the exponents of the second polynom (separated by commas):");
                String[] expsInput2 = scanner.nextLine().split(",");
                int[] exponents2 = new int[expsInput2.length];
                for (int i = 0; i < expsInput2.length; i++) {
                    exponents2[i] = Integer.parseInt(expsInput2[i].trim());
                }

                polynom2 = new Polynom(coefficients2, exponents2);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.\n");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter valid numbers separated by commas.\n");
            }
        }

        // Perform operations
        Polynom sum = polynom1.plus(polynom2);
        Polynom difference = polynom1.minus(polynom2);
        Polynom derivative = polynom1.derivative();
        Polynom derivative2 = polynom2.derivative();
        
        // Print results
        System.out.println("\nFirst Polynom:");
        printPolynom(polynom1);

        System.out.println("\nSecond Polynom:");
        printPolynom(polynom2);

        System.out.println("\nSum of the polynoms:");
        printPolynom(sum);

        System.out.println("\nDifference of the polynoms (first - second):");
        printPolynom(difference);

        System.out.println("\nDerivative of the first polynom:");             
        printPolynom(derivative);
        
        System.out.println("\nDerivative of the Second polynom:");             
        printPolynom(derivative2);
        
        boolean eq = polynom1.equals(polynom2);
        if(eq)
        {
        	System.out.println("\nThe polynoms is equle");
        }
        else {
        	System.out.println("\nThe polynoms not equle");
        }
    }

    private static void printPolynom(Polynom polynom) {
        for (Pair<Double, Integer> term : polynom.getTerms()) {
            System.out.println("Coefficient: " + term.getKey() + ", Exponent: " + term.getValue());
        }
    }
}
