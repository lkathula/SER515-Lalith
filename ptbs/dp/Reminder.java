package dp;
public class Reminder {
    void displayReminder(ClassProductList productList) {
        ReminderVisitor rv = new ReminderVisitor(this);
        Facade f = new Facade();
        rv.visitFacade(f);
    }

}
