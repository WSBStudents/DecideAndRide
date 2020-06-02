package edu.wsb.students;

import edu.wsb.students.util.HibernateUtil;

public class App {

	// Start aplikacji(nowy obiekt kontrolera)
	public static void main(String[] args) {
		System.out.println("Welcome to our Control Rent Car System!");
		Controller controller = new Controller();
		controller.start();
		HibernateUtil.shutdown();
	}

}