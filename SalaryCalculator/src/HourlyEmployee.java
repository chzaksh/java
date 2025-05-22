import java.util.Calendar;

//HourlyEmployee.java - Employee paid by the hour
public class HourlyEmployee extends Employee {
    private double wage;
    private double hours;

    public HourlyEmployee(String firstName, String lastName, String socialSecurityNumber,Calendar birthDate, double wage, double hours) {
        super(firstName, lastName, socialSecurityNumber,birthDate);
        setWage(wage);
        setHours(hours);
    }

    public void setWage(double wage) {
        if (wage < 0.0) {
            throw new IllegalArgumentException("Hourly wage must be >= 0.0");
        }
        this.wage = wage;
    }

    public double getWage() { return wage; }

    public void setHours(double hours) {
        if (hours < 0.0 || hours > 168.0) {
            throw new IllegalArgumentException("Hours worked must be >= 0.0 and <= 168.0");
        }
        this.hours = hours;
    }

    public double getHours() { return hours; }

    @Override
    public double earnings() {
    	double regularPay = (getHours() <= 40) ? getWage() * getHours()
    		        : (40 * getWage() + (getHours() - 40) * getWage() * 1.5);
    		
    	return applyBirthdayBonus(regularPay);
    }

    @Override
    public String toString() {
        return String.format("Hourly Employee: %s\nHourly Wage: $%,.2f; Hours Worked: %,.2f",
                super.toString(), getWage(), getHours());
    }
}
