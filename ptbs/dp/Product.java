package dp;
public class Product {

	private ClassProductList classProductList;

	private Trading trading;
	private String productItemName;
	private String productItemCategory;

	public Product(String productName, String productItemCategory) {
		this.productItemName = productName;
		this.productItemCategory = productItemCategory;
	}

	public String getProductItemName() {
		return productItemName;
	}

	public String getProductItemCategory() {
		return productItemCategory;
	}

}
