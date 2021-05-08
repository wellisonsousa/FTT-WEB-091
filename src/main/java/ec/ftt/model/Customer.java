package ec.ftt.model;

import java.util.Objects;

public class Customer {

	private long id;
	private String name, email, phone, address;
	
	public Customer() {
		
	}
	public Customer(String id, String name, String email, String phone, String address) {
		super();
		setId(id);
		setName(name);
		setEmail(email);
		setPhone(phone);
		setAddress(address);
	}
	
	public Customer(long id, String name, String email, String phone, String address) {
		super();
		setId(id);
		setName(name);
		setEmail(email);
		setPhone(phone);
		setAddress(address);
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		
		if (id.length()==0)
			setId(0);
		else
			setId(Long.valueOf(id));
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address +"]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, phone, email, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Customer)) {
			return false;
		}
		Customer other = (Customer) obj;
		return Objects.equals(phone, other.phone) && 
			   Objects.equals(address, other.address) && 
			   Objects.equals(email, other.email) && 
			   id == other.id && 
			   Objects.equals(name, other.name);
	}
	
	
	
	
}
