import java.util.Calendar;

//BasePlusCommissionEmployee.java - Employee with base salary plus commission
public class BasePlusCommissionEmployee extends CommissionEmployee {
    private double baseSalary;

    public BasePlusCommissionEmployee(String firstName, String lastName, String socialSecurityNumber,Calendar birthDate,
                                       double grossSales, double commissionRate, double baseSalary) {
        super(firstName, lastName, socialSecurityNumber,birthDate, grossSales, commissionRate);
        setBaseSalary(baseSalary);
    }

    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0.0) {
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        }
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() { return baseSalary; }

    @Override
    public double earnings() {    	
    	double total = super.earnings() + getBaseSalary();
    	return total ;
    }

    @Override
    public String toString() {
        return String.format("Base-Salaried %s\nBase Salary: $%,.2f",
                super.toString(), getBaseSalary());
    }
}
