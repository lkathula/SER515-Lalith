package dp;
public class Seller extends Person {

	public void showMenu() {
		System.out.println("-------Bridge Pattern-------");
		System.out.println("Menu Items");
	}

	public void CreateProductMenu(int pCategory) {
		System.out.println("-----Factory Design-Pattern----");
		switch (pCategory) {
			case 0:
				productMenu = new MeatProductMenu();
				System.out.println("Creation of MEAT PRODUCT MENU for SELLER");
				break;
			default:
				productMenu = new ProduceProductMenu();
				System.out.println("Creation of PRODUCE PRODUCT MENU FOR SELLER");
				break;
		}
	}

}
