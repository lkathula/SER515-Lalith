package dp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Facade implements ActionListener{

	private int userType;



	private int pCategory;

	ClassProductList productItemsList;

	private Person person;

	public int optionSelected =0;
	private static JRadioButton buyer, seller;
	private static JLabel username, password;
	private static JTextField userNameField;
	private static JPasswordField passwordField;
	private static JButton login, reset;
	private static ButtonGroup buttonGroup;

	public boolean login() throws IOException {
		JFrame jf = new JFrame("Product Trading and Bidding System");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.BASELINE_TRAILING,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 4, 6);

		buyer=new JRadioButton("Buyer");
		jp.add(buyer,gbc);
		gbc.gridx=1;
		seller=new JRadioButton("Seller");
		jp.add(seller,gbc);
		gbc.gridx=0;
		gbc.gridy=1;

		buttonGroup = new ButtonGroup();
		buyer.setActionCommand("Buyer");
		seller.setActionCommand("Seller");
		buttonGroup.add(buyer);
		buttonGroup.add(seller);
		buyer.addActionListener((ActionListener) new Facade());
		seller.addActionListener((ActionListener) new Facade());

		username = new JLabel("Enter username");

		jp.add(username,gbc);
		gbc.gridx=1;
		gbc.gridy = 1;
		userNameField = new JTextField(10);
		jp.add(userNameField,gbc);
		gbc.gridx=0;
		gbc.gridy = 2;


		password = new JLabel("Enter password");
		jp.add(password,gbc);
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.gridx = 1;
		gbc.gridy = 2;
		passwordField = new JPasswordField(10);
		jp.add(passwordField, gbc);
		gbc.gridy=4;

		login = new JButton("Login");
		reset = new JButton("Reset");
		login.addActionListener((ActionListener) new Facade());
		reset.addActionListener((ActionListener) new Facade());
		jp.add(login,gbc);
		gbc.gridy=5;
		jp.add(reset,gbc);


		jf.setSize(800, 300);

		jf.add(jp);
		jf.setVisible(true);
		jp.setBackground(Color.decode("#FFFF00"));
		return true;
	}

	public void addTrading() {

	}

	public void viewTrading() {

	}

	public void decideBidding() {

	}

	public void discussBidding() {

	}

	public void submitBidding() {

	}

	public void remind() {
		Reminder reminder = new Reminder();
		reminder.displayReminder(productItemsList);
	}

	public void createUser(int selection) {

		person = selection==0 ? new Buyer(): new Seller();
		switch (selection) {
			case 0:
				System.out.println("Created Buyer instance");
				break;
			default:
				System.out.println("Created Seller instance");
				break;
		}

	}

	public void createProductList() {

	}

	public void AttachProductToUser(String username) throws IOException {
		Map<String,ArrayList<String>> allProducts= new HashMap<>();
		String f = "E:\\ser515\\DesignPatterns\\src\\ProductInfo.txt";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
		String tempStr;

		if ((tempStr = bufferedReader.readLine()) != null) {
			do {
				if (!allProducts.containsKey(tempStr.split(":", -1)[0])) {
					ArrayList<String> prod = new ArrayList() {
					};
					prod.add(tempStr.split(":", -1)[1]);
					allProducts.put(tempStr.split(":", -1)[0], prod);
				} else {
					allProducts.get(tempStr.split(":", -1)[0]).add(tempStr.split(":", -1)[1]);
				}
			} while ((tempStr = bufferedReader.readLine()) != null);
		}
		productItemsList = new ClassProductList(allProducts.get(username));


	}

	public boolean SelectProduct() throws IOException{

		System.out.println("Using Iterator Design Pattern");
		System.out.println("Select product category (0) Meat or (1) Produce: ");
		Scanner sc = new Scanner(System.in);
		int categoryType = sc.nextInt();
		Map<String,String> category = new HashMap<String,String>();
		String f = "src/ProductInfo.txt";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
		String tempStr;

		while((tempStr = bufferedReader.readLine())!=null) {
			category.put(tempStr.split(":")[1],tempStr.split(":",-1)[0]);
		}
		if(categoryType>1)
			return false;

		pCategory = categoryType;
		if(categoryType == 0){
			System.out.println("Meat options are as follows");
		}
		else {
			System.out.println("Produce options are as follows");
		}
		ProductIterator iterator = new ProductIterator(productItemsList);
		while (iterator.hasNext()) {
			String st = (String) iterator.next();
			if(categoryType == 0 && category.get(st).equals("Meat"))
				System.out.println(st);
			if(categoryType == 1 && category.get(st).equals("Produce"))
				System.out.println(st);
		}
		return true;
	}

	public int productOperation() {

		System.out.println("Enter 1. Generate Product Menu 2. View Product Menu 3. Reminders 4. Logout");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice) {
			case 1:
				person.CreateProductMenu(pCategory);
				break;
			case 2:
				person.showMenu();
				break;
			case 3:
				remind();
				break;
			case 4:
				System.exit(0);
			default:
				System.out.println("Invalid input. Enter a valid choice");
				break;
		}
		return 0;
	}

	public void actionPerformed(ActionEvent e) {
		Facade facade = new Facade();
		String u = userNameField.getText();
		String p = passwordField.getText();
		optionSelected = 0;
		if (!buyer.isSelected()) {
			optionSelected = 1;
			System.out.println("Welcome Seller!");
		} else {
			optionSelected = 0;
			System.out.println("Welcome Buyer!");
		}

		if (e.getSource() != login) {
			userNameField.setText("");
			passwordField.setText("");
		} else {
			Map<String, String> users = new HashMap<>();
			switch (optionSelected) {
				case 0:
					try {
						String f = "E:\\ser515\\DesignPatterns\\src\\BuyerInfo.txt";
						BufferedReader br = new BufferedReader(new FileReader(f));
						String s;
						if ((s = br.readLine()) != null) {
							do {
								users.put(s.split(":", -1)[0], s.split(":", -1)[1]);
							} while ((s = br.readLine()) != null);
						}
						if (p.equals(users.get(u))) {
							System.out.println("Hey " + u + " Welcome");
							System.out.println("User Info " + u + ", " + p);
							//JOptionPane.showMessageDialog(null, "Login successful");

							Frame jFrame = new JFrame();
							jFrame.setSize(500, 300);
							JPanel jPanel = new JPanel();
							jPanel.setBackground(Color.decode("#90EE90"));
							JLabel jLabel1 = new JLabel("Buyer Instance");
							JLabel jLabel = new JLabel("Please select a product category from the options (0) Meat or (1) Produce");
							JTextField jTextField = new JTextField(10);
							JButton submit = new JButton("Submit");
							jPanel.add(jLabel1);
							jPanel.add(jLabel);
							jPanel.add(jTextField);
							jPanel.add(submit);
							jFrame.add(jPanel);
							jFrame.setVisible(true);
							submit.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									System.out.println(submit);
									String input = jTextField.getText();
									System.out.println(input);
									facade.displayMenu(optionSelected, input);
								}
							});
						} else {
							JOptionPane.showMessageDialog(null, "Incorrect username or password.");
						}

					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					break;
				default:
					try {
						String f = "E:\\ser515\\DesignPatterns\\src\\SellerInfo.txt";
						BufferedReader br = new BufferedReader(new FileReader(f));
						String s;
						while ((s = br.readLine()) != null) {
							users.put(s.split(":", -1)[0], s.split(":", -1)[1]);
						}
						if (!p.equals(users.get(u))) {
							JOptionPane.showMessageDialog(null, "Incorrect username or password.");
						} else {
							System.out.println("Hey " + u);
							System.out.println("Details " + u + ", " + p + " Welcome");

							//JOptionPane.showMessageDialog(null, "Login successful");
							Frame jFrame = new JFrame();
							jFrame.setSize(500, 500);
							JPanel jPanel = new JPanel();
							jPanel.setBackground(Color.decode("#FBF4DA"));
							JLabel jLabel1 = new JLabel("Seller Instance");
							JLabel jLabel = new JLabel("Please select a product category from the options (0) Meat or (1) Produce");
							JTextField jTextField = new JTextField(10);
							JButton submit = new JButton("Submit");
							jPanel.add(jLabel1);
							jPanel.add(jLabel);
							jPanel.add(jTextField);
							jPanel.add(submit);
							jFrame.add(jPanel);
							jFrame.setVisible(true);
							submit.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									System.out.println(submit);
									String input = jTextField.getText();
									System.out.println(input);
									facade.displayMenu(optionSelected, input);
								}
							});
						}
					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					break;
			}
		}
		createUser(optionSelected);
		try {
			AttachProductToUser(u);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void displayMenu(int selection,String input){
		JFrame jFrame = new JFrame("Menu Frame");
		jFrame.setSize(500,300);

		JPanel jp1 = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.BASELINE_TRAILING,
				GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 4, 6);
		jp1.setBackground(Color.decode("#CFFFA3"));
		JLabel msg;
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Meat Menu");
		JMenu menu2 = new JMenu("Produce Menu");

		switch (selection) {
			case 0: {
				msg = new JLabel("Hey there Buyer!");
				jp1.add(msg);
				JLabel msg1 = new JLabel("The available options for you are...");
				jp1.add(msg1);
				if (!input.contains("0")) {
					if (input.contains("1")) {
						JMenuItem item2 = new JMenuItem("Tomato");
						//JMenuItem item3 = new JMenuItem("Onion");
						menu2.add(item2);
						//menu2.add(item3);
						menuBar.add(menu2);
					} else {
						JLabel jLabel = new JLabel("Invalid choice");
						jp1.add(jLabel);
					}
				} else {
					JMenuItem item1 = new JMenuItem("Beef");
					menu1.add(item1);
					menuBar.add(menu1);
				}
				break;
			}
			default: {
				msg = new JLabel("Hey there Seller!");
				jp1.add(msg);
				JLabel msg1 = new JLabel("The available options for you are...");
				jp1.add(msg1);
				if (input.contains("0")) {
					System.out.println("Test");
					JMenuItem item1 = new JMenuItem("Beef");
					menu1.add(item1);
					menuBar.add(menu1);
				} else if (input.contains("1")) {
					JMenuItem item2 = new JMenuItem("Tomato");
					JMenuItem item3 = new JMenuItem("Onion");
					menu2.add(item2);
					menu2.add(item3);
					menuBar.add(menu2);
				} else {
					JLabel jLabel = new JLabel("Invalid choice");
					jp1.add(jLabel);
				}
				break;
			}
		}
		jp1.add(menuBar);
		jFrame.add(jp1);
		jFrame.setVisible(true);
		//productOperation();
	}

}
