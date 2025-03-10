package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.sql.Driver;
import java.sql.DriverManager;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.context.ApplicationContext;
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.demo.repository")
@EntityScan(basePackages = "com.example.demo.model") 
@ComponentScan(basePackages = "com.example.demo")



	public class DemoApplication implements CommandLineRunner {

	    @Autowired
	    private EmployeeService employeeService;

	    public static void main(String[] args) {
	        SpringApplication.run(DemoApplication.class, args);
	    }

	    @Override
	    public void run(String... args) throws Exception{
	        // Creating an employee object
	        Employee employee = new Employee();
	      employee.setFirstName("Vanya");
	     employee.setLastName("Sen");
	     employee.setJobTitle("Software Engineer");
	 //    employee.setBirthDate("1990-05-15");


	        employeeService.updateEmployee(2L, employee);

	        // Print the updated employee data
	   
	        System.out.println("Updated Employee: " );
	    }
	
    
    
    public static void testConnection() {
        // Connection details
        String jdbcUrl = "jdbc:sqlserver://curdserver.database.windows.net:1433;database=CURDDB;user=CloudSAaae59258@curdserver;password=Vanya123*;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        String username = "CloudSAaae59258@curdserver";
        String password = "Vanya123*";
        System.out.println("Connection established successfully!!!!");
        // Establish connection to the Azure SQL Database
        
       
        try  {
        	//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	 Enumeration<Driver> drivers = DriverManager.getDrivers();
        	  System.out.println("Driver established successfully========="+drivers);
        	  
        	  if (!drivers.hasMoreElements()) {
                  System.out.println("No JDBC drivers found. Ensure the driver is added to dependencies.");
              } else {
                  while (drivers.hasMoreElements()) {
                      Driver driver = drivers.nextElement();
                      System.out.println("Loaded JDBC Driver: " + driver.getClass().getName());
                  }
              }
        	  
        	  
        	Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection established successfully========="+connection);
          //  com.mysql.cj.jdbc.Driver ss;
            // Create a statement and execute a query
            String query = "SELECT * FROM Employee"; // Assuming 'Employee' table exists
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                // Process the result set
                while (rs.next()) {
                    int employeeId = rs.getInt("EmployeeID");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    System.out.println("EmployeeID: " + employeeId + ", FirstName: " + firstName + ", LastName: " + lastName);
                }
            } catch (Exception e) {
                System.out.println("Error while fetching data: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error while connecting to the database: " + e.getMessage());
        }
    }
}
