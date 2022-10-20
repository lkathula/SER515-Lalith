package dp;
public class Buyer extends Person {

	public void showMenu() {

	}

	//creating product menu for user (buyer and seller)

	public void CreateProductMenu(int pCategory) {

		System.out.println("--------Factory design-pattern--------");
		if (pCategory != 0) {
			productMenu = new ProduceProductMenu();
			System.out.println("--------Creation of PRODUCE PRODUCT MENU for BUYER-------");
		} else {
			productMenu = new MeatProductMenu();
			System.out.println("--------Creation of MEAT PRODUCT MENU for BUYER-------");
		}
	}

}
