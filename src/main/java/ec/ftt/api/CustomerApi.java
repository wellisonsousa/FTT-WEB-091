package ec.ftt.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.ftt.dao.CustomerDao;
import ec.ftt.model.Customer;

@WebServlet("/customer")
public class CustomerApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerApi() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String userId = request.getParameter("user-id");
			
		    if(userId != null) {
		    	long id = Long.valueOf(userId);
		    	
		    	CustomerDao customerDao = new CustomerDao();
		    	
		    	Customer customer = customerDao.getCustomerById(id);
		     	Gson gson = new Gson();
		     	
		     	response.setStatus(200);
		    	response.getWriter().append(gson.toJson(customer));
		    	
		    } else {
		    	CustomerDao customerDao = new CustomerDao();
		    	
		    	List<Customer> customers = customerDao.getAllCustomer();
		        
		    	Gson gson = new Gson();
	
		    	response.setStatus(200);
		    	response.getWriter().append(gson.toJson(customers));
		    	
		    	for (Customer customer : customers)
		    		response.getWriter().append(customer.toString());
		    	
		    }
	    } catch (Exception e) {
	    	response.setStatus(400);
	    	response.getWriter().append(e.getMessage());
	    }
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Customer customer = new Customer(
				request.getParameter("customer-id"),
				request.getParameter("customer-name"),
				request.getParameter("customer-email"),
				request.getParameter("customer-phone"),
				request.getParameter("customer-address")
			);
			
			CustomerDao customerDao = new CustomerDao();
			customerDao.addCustomer(customer);
			
			response.setStatus(200);
			response.getWriter().append(customer.toString());
	    } catch (Exception e) {
	    	response.setStatus(400);
	    	response.getWriter().append(e.getMessage());
	    }
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("application/json");
			Customer customer = new Customer(
				request.getParameter("customer-id"),
				request.getParameter("customer-name"),
				request.getParameter("customer-email"),
				request.getParameter("customer-phone"),
				request.getParameter("customer-address")
			);
			CustomerDao customerDao = new CustomerDao();
			customerDao.updateCustomer(customer);
			
			response.setStatus(200);
			response.getWriter().append(customer.toString());
	    } catch (Exception e) {
	    	response.setStatus(400);
	    	response.getWriter().append(e.getMessage());
	    }
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getParameter("customer-id") == null)
				 response.sendError(407, "Informe o ID do cliente a ser retornado" );
			else {
				Long customerId = Long.valueOf(request.getParameter("user-id"));
				
				CustomerDao customerDAO = new CustomerDao();
				customerDAO.deleteCustomer(customerId);
				
				response.setStatus(200);
				response.getWriter().append("cliente removido -> " + request.getParameter("customer-id"));
			}
	    } catch (Exception e) {
	    	response.setStatus(400);
	    	response.getWriter().append(e.getMessage());
	    }
	}

}
