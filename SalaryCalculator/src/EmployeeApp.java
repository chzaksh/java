import java.util.Calendar;

//EmployeeApp.java - Main program to test different types of employees
public class EmployeeApp {
    public static void main(String[] args) {
      
    	Employee[] employees = null;
    	
        // Create births date         
        Calendar birthDateDanny = Calendar.getInstance();
        birthDateDanny.set(1990, Calendar.APRIL, 5);
        
        Calendar birthDateMichal = Calendar.getInstance();
        birthDateMichal.set(1988, Calendar.AUGUST, 12);

        Calendar birthDateRon = Calendar.getInstance();
        birthDateRon.set(1995, Calendar.APRIL, 20);

        Calendar birthDateNaama = Calendar.getInstance();
        birthDateNaama.set(1993, Calendar.NOVEMBER, 3);

        Calendar birthDateAvi = Calendar.getInstance();
        birthDateAvi.set(1992, Calendar.FEBRUARY, 28);
        try {
            employees = new Employee[] {
        		new SalariedEmployee("Danny", "Cohen", "111-11-1111", birthDateDanny, 800.00),
        		new HourlyEmployee("Michal", "Levi", "222-22-2222", birthDateMichal, 16.75, 40),
        		new CommissionEmployee("Ron", "Israeli", "333-33-3333", birthDateRon, 10000, 0.06),
        		new BasePlusCommissionEmployee("Naama", "Friedman", "444-44-4444", birthDateNaama, 5000, 0.04, 300),
        		new PieceWorker("Avi", "Cohen", "555-55-5555", birthDateAvi, 100, 5.5)
            };
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating employees: " + e.getMessage());
            System.exit(1);
        }
     
        for (Employee currentEmployee : employees) {
            if (currentEmployee != null) { 
                System.out.println(currentEmployee);
                System.out.printf("Earned: $%,.2f\n\n", currentEmployee.earnings());
            }
        }
    }
}
