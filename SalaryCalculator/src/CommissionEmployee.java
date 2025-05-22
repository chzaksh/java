import java.util.Calendar;

//CommissionEmployee.java - Employee paid by percentage of sales
public class CommissionEmployee extends Employee {
    private double grossSales;
    private double commissionRate;

    public CommissionEmployee(String firstName, String lastName, String socialSecurityNumber,Calendar birthDate, double grossSales, double commissionRate) {
        super(firstName, lastName, socialSecurityNumber,birthDate);
        setGrossSales(grossSales);
        setCommissionRate(commissionRate);
    }

    public void setGrossSales(double grossSales) {
        if (grossSales < 0.0) {
            throw new IllegalArgumentException("Gross sales must be >= 0.0");
        }
        this.grossSales = grossSales;
    }

    public double getGrossSales() { return grossSales; }

    public void setCommissionRate(double commissionRate) {
        if (commissionRate <= 0.0 || commissionRate >= 1.0) {
            throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");
        }
        this.commissionRate = commissionRate;
    }

    public double getCommissionRate() { return commissionRate; }

    @Override
    public double earnings() {
    	 return applyBirthdayBonus(getCommissionRate() * getGrossSales());
    }

    @Override
    public String toString() {
        return String.format("Commission Employee: %s\nGross Sales: $%,.2f; Commission Rate: %.2f",
                super.toString(), getGrossSales(), getCommissionRate());
    }
}
