import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.util.Pair;

public class Polynom {
    private List<Pair<Double, Integer>> polynom = new ArrayList<>();

    public Polynom(double[] coefficients, int[] exponents) {
        if (coefficients.length != exponents.length) {
            throw new IllegalArgumentException("Arrays must have the same length.");
        }

        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != 0) { 
            	polynom.add(new Pair<>(coefficients[i], exponents[i]));
            }
        }

        Collections.sort(polynom, new Comparator<Pair<Double, Integer>>() {
            public int compare(Pair<Double, Integer> o1, Pair<Double, Integer> o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });
    }
    
    
    public Polynom plus(Polynom other) {
        List<Pair<Double, Integer>> result = new ArrayList<>();

        int i = 0, j = 0;
        while (i < this.polynom.size() && j < other.polynom.size()) {
            Pair<Double, Integer> term1 = this.polynom.get(i);
            Pair<Double, Integer> term2 = other.polynom.get(j);

            if (term1.getValue().equals(term2.getValue())) {
                double sum = term1.getKey() + term2.getKey();
                if (sum != 0) {
                    result.add(new Pair<>(sum, term1.getValue()));
                }
                i++;
                j++;
            } else if (term1.getValue() > term2.getValue()) {
                result.add(term1);
                i++;
            } else {
                result.add(term2);
                j++;
            }
        }

        while (i < this.polynom.size()) {
            result.add(this.polynom.get(i++));
        }

        while (j < other.polynom.size()) {
            result.add(other.polynom.get(j++));
        }

        return createPolynomFromTerms(result);
    }
    
    
    public Polynom minus(Polynom other) {
    	 List<Pair<Double, Integer>> negativePolynom = new ArrayList<>();
    	    for (Pair<Double, Integer> term : other.polynom) {
    	    	negativePolynom.add(new Pair<>(-term.getKey(), term.getValue()));
    	    }
    	    return this.plus(createPolynomFromTerms(negativePolynom));
    }

    public Polynom derivative() {
        List<Pair<Double, Integer>> derivedPolynom = new ArrayList<>();

        for (Pair<Double, Integer> term : polynom) {
            if (term.getValue() != 0) { 
                double newCoefficient = term.getKey() * term.getValue();
                int newExponent = term.getValue() - 1;
                derivedPolynom.add(new Pair<>(newCoefficient, newExponent));
            }
        }

        return createPolynomFromTerms(derivedPolynom);
    }

    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Polynom other = (Polynom) obj;
        if (this.polynom.size() != other.polynom.size()) {
            return false;
        }
        for (int i = 0; i < this.polynom.size(); i++) {
            Pair<Double, Integer> polynom1 = this.polynom.get(i);
            Pair<Double, Integer> polynom2 = other.polynom.get(i);
            if (!polynom1.getValue().equals(polynom2.getValue()) ||
                Math.abs(polynom1.getKey() - polynom2.getKey()) > 1e-9) { 
                return false;
            }
        }
        return true;
    }
    
    private static Polynom createPolynomFromTerms(List<Pair<Double, Integer>> terms) {
        double[] coefficients = new double[terms.size()];
        int[] exponents = new int[terms.size()];
        for (int i = 0; i < terms.size(); i++) {
            coefficients[i] = terms.get(i).getKey();
            exponents[i] = terms.get(i).getValue();
        }
        return new Polynom(coefficients, exponents);
    }
    
    public List<Pair<Double, Integer>> getTerms() {
        return polynom;
    }



}
