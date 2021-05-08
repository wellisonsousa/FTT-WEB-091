package ec.ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ec.ftt.model.Customer;
import ec.ftt.model.User;
import ec.ftt.util.DBUtil;

public class CustomerDao {

    private Connection connection;

    public CustomerDao() {
        connection = DBUtil.getConnection();
    }

    public void addCustomer(Customer customer) {
        
    	try {
    		
    		System.out.println("Here we are...");
    		
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO ftt.CUSTOMER (NAME, EMAIL, PHONE, ADDRESS) VALUES (?, ?, ?)");
            
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPhone());
            preparedStatement.setString(3, customer.getAddress());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteCustomer(Long id) {
    	
    	Customer customer = new Customer();
    	customer.setId(id);
    	
    	deleteCustomer(customer);
    	
    }

    public void deleteCustomer(Customer customer) {
        try {
            
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM ftt.CUSTOMER WHERE ID=?");
           
            preparedStatement.setLong(1, customer.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

    public void updateCustomer(Customer customer) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
        		"UPDATE ftt.CUSTOMER SET NAME=?, " 
        		                      + "EMAIL=?, " 
        		                      + "PHONE=? " 
        		                      + "ADDRESS=? "
                                      + "WHERE ID=?"
            );
            
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPhone());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setLong(4, customer.getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomer() {
        
    	List<Customer> customerList = new ArrayList<Customer>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ftt.CUSTOMER");
            while (rs.next()) {
            	Customer customer = new Customer();
            	customer.setId(rs.getLong("ID"));
            	customer.setName(rs.getString("NAME"));
            	customer.setEmail(rs.getString("EMAIL"));
            	customer.setPhone(rs.getString("PHONE"));
            	customer.setAddress(rs.getString("ADDRESS"));

            	customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    public Customer getCustomerById(Long id) {
    	
    	Customer customer = new Customer();
    	customer.setId(id);
    	
    	return getCustomerById(customer);
    	
    }
    
    public Customer getCustomerById(Customer customer) {

    	Customer customerOutput = new Customer();
        
    	try {
            PreparedStatement preparedStatement = connection.prepareStatement(
            	"SELECT * from ftt.CUSTOMER WHERE ID=?"
            );
            
            preparedStatement.setLong(1, customer.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	customerOutput.setId(rs.getLong("ID"));
            	customerOutput.setName(rs.getString("NAME"));
            	customerOutput.setEmail(rs.getString("EMAIL"));
            	customerOutput.setPhone(rs.getString("PHONE"));
            	customerOutput.setAddress(rs.getString("ADDRESS"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerOutput;
    }
    
    public String getDbDate() {

    	String output="";
    	
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT now() NOW");
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	output = rs.getString("NOW");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }
    
}