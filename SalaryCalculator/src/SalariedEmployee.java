import java.util.Calendar;
//SalariedEmployee.java - Employee who gets a fixed weekly salary
public class SalariedEmployee extends Employee {
    private double weeklySalary;

    public SalariedEmployee(String firstName, String lastName, String socialSecurityNumber,Calendar birthDate, double weeklySalary) {
        super(firstName, lastName, socialSecurityNumber,birthDate);
        setWeeklySalary(weeklySalary);
    }

    public void setWeeklySalary(double weeklySalary) {
        if (weeklySalary < 0.0) {
            throw new IllegalArgumentException("Weekly salary must be >= 0.0");
        }
        this.weeklySalary = weeklySalary;
    }

    public double getWeeklySalary() { return weeklySalary; }
    
    
    @Override
    public double earnings() {
    	   return applyBirthdayBonus(getWeeklySalary());
    }

    @Override
    public String toString() {
        return String.format("Salaried Employee: %s\nWeekly Salary: $%,.2f",
                super.toString(), getWeeklySalary());
    }
}
