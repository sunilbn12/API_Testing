package apitesting;

public class Address {

	private String flatNo;
	private String city;
	private String state;
	private String country;

	public Address(String flatNo, String city, String state, String country) {

		this.flatNo = flatNo;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
