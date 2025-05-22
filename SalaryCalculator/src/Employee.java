import java.util.Calendar;
//Employee.java - Abstract superclass for all employee types
public abstract class Employee {
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    private final Calendar birthDate;   

    
    public Employee(String firstName, String lastName, String socialSecurityNumber,Calendar birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        Calendar today = Calendar.getInstance();
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("Birth date cannot be in the future.");
        }
        this.birthDate = birthDate;
    }

    
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getSocialSecurityNumber() { return socialSecurityNumber; }

    @Override
    public String toString() {
    	 return String.format("%s %s\nSocial Security Number: %s\nBirth Date: %d/%d/%d",
                 getFirstName(), getLastName(), getSocialSecurityNumber(),
                 birthDate.get(Calendar.DAY_OF_MONTH),
                 birthDate.get(Calendar.MONTH) + 1,
                 birthDate.get(Calendar.YEAR));
    }
    
    // Apply birthday bonus if current month matches birth month
    protected double applyBirthdayBonus(double baseEarnings) {
        Calendar today = Calendar.getInstance();
        double finalEarnings = baseEarnings;

        if (birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
            finalEarnings += 200;
            System.out.println("***** Happy Birthday Month! Bonus of 200 added! *****");            
        } else {
            System.out.println("No birthday bonus.");
        }
        return finalEarnings;   
        }

    // Abstract method must be implemented by subclasses
    public abstract double earnings();
}
