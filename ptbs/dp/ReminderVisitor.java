package dp;
public class ReminderVisitor extends NodeVisitor {

	private Reminder m_Reminder;

	private ClassProductList classProductList;

	public ReminderVisitor(Reminder reminder) {
		super();
		m_Reminder = reminder;
	}

	public void visitProduct(Product product) {

	}

	public void visitTrading(Trading trading) {

	}

	public void visitFacade(Facade facade) {
		System.out.println("--------Visitor Design-pattern----------");
		ProductIterator productList = new ProductIterator(facade.productItemsList);
		System.out.print("Reminders");
	}

}
