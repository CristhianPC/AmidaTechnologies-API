package amida.cristhian.coopsapi.model.request;

/**
 * @author Cristhian
 *
 */
public class CoopsapiScopeRequest {
	
	private String station;
	private String dateOfService;
	private ProductType product;
	
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getDateOfService() {
		return dateOfService;
	}
	public void setDateOfService(String dateOfService) {
		this.dateOfService = dateOfService;
	}
	public ProductType getProduct() {
		return product;
	}
	public void setProduct(ProductType product) {
		this.product = product;
	}
	
}
