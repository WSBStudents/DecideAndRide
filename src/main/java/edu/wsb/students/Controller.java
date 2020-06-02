package edu.wsb.students;

import edu.wsb.students.dao.CarDao;
import edu.wsb.students.dao.CustomerDao;
import edu.wsb.students.dao.OrderDao;
import edu.wsb.students.model.Car;
import edu.wsb.students.model.Customer;
import edu.wsb.students.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

class Controller {

    // Pola Dao(klas do komunikacji z bazą)
    private CarDao carDao = new CarDao();
    private CustomerDao customerDao = new CustomerDao();
    private OrderDao orderDao = new OrderDao();

    // Główna flaga
    // Gdy będzie false, program zakończy działanie
    private boolean doAction = true;

    // Scanner - służy do pobrania informacji
    private Scanner scanner;

    // Główna pętla z odpaleniem metody, która wyświetla podstawowe informacji
    // oraz metody która pobiera wybór akcji od użytkownika
    void start() {
        while (doAction) {
            writeHelpText();
            doAction();
        }
    }

    // Wyświetlenie wszystkich możliwych opcji sterowania
    private void writeHelpText() {
        System.out.println("Enter number:");
        System.out.println("1 - add new car");
        System.out.println("2 - add new customer");
        System.out.println("3 - add new order");
        System.out.println("4 - get all cars");
        System.out.println("5 - get all customers");
        System.out.println("6 - get all orders");
        System.out.println("7 - delete car");
        System.out.println("8 - delete customer");
        System.out.println("9 - complete with order");
        System.out.println("0 - exit\n");
    }

    // Pobieranie akcji od użytkownika oraz wywołanie odpowiedniej metody
    // Jeżeli użytkownik wybierze 0, flaga doAction = false i zakończy się główna pętla
    private void doAction() {
        System.out.print("Enter option: ");

        scanner = new Scanner(System.in);

        String option = scanner.nextLine();

        switch (option){
            case "1":
                addCar();
                break;
            case "2":
                addCustomer();
                break;
            case "3":
                addOrder();
                break;
            case "4":
                getAllCars();
                break;
            case "5":
                getAllCustomers();
                break;
            case "6":
                getAllOrders();
                break;
            case "7":
                deleteCar();
                break;
            case "8":
                deleteCustomer();
                break;
            case "9":
                deleteOrder();
                break;
            case "0":
                doAction = false;
        }
    }

    /* Dodanie samochodu
     * 1. Pobranie odpowiednich informacji o samochodzie
     * 2. Tworzenie obiektu Car i przekazanie go do metody CarDao */
    private void addCar() {
        System.out.print("Enter brand of car(string): ");
        String brand = scanner.nextLine();

        System.out.print("Enter model of car(string): ");
        String model = scanner.nextLine();

        System.out.print("Enter horse power(int): ");
        int horsePower = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter engine capacity(string): ");
        String engineCapacity = scanner.nextLine();

        System.out.print("Enter production year(int): ");
        int productionYear = Integer.parseInt(scanner.nextLine());

        String vinNumber = getVinNumber();

        System.out.print("Enter insurance date(YYYY-MM-DD): ");
        LocalDate insuranceDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter technical inspection date(YYYY-MM-DD): ");
        LocalDate technicalInspectionDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter daily payment(double): ");
        BigDecimal dailyPayment = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));

        System.out.print("Enter rate(string): ");
        String rate = scanner.nextLine();

        carDao.addCar(
                new Car(
                        brand, model, horsePower, engineCapacity,
                        productionYear, vinNumber, insuranceDate, technicalInspectionDate,
                        dailyPayment, rate
                )
        );
        System.out.println("Done!\n");
    }

    /* Pobieranie vinNumber oraz sprawdzenie czy występuje wpis z takim vinNumber w bazie samochodów
     * Jeżeli występuje informowanie użytkownika oraz pobieranie jeszcze raz */
    private String getVinNumber() {
        while (true) {
            System.out.print("Enter vin number(string): ");
            String vinNumber = scanner.nextLine();

            if (carDao.checkAvailableVinNumber(vinNumber)) {
                return vinNumber;
            }
            System.out.println("There are car with that vin number in system! Check number!");
        }
    }

    /* Dodanie nowego klienta
     * 1. Pobieranie danych od użytkownika
     * 2. Tworzenie obiektu Customer i przekazanie go do metody CustomerDao */
    private void addCustomer() {
        String name, documentId, drivingLicenceId, address, phoneNumber;
        LocalDate documentExpirationDate, drivingLicenceExpirationDate, birthday;

        System.out.print("Enter new customer`s name: ");
        name = scanner.nextLine();

        documentId = getDocumentId();

        System.out.print("Enter expiration date of document(YYYY-MM-DD): ");
        documentExpirationDate = LocalDate.parse(scanner.nextLine());

        drivingLicenceId = getDriverLicenseId();

        System.out.print("Enter expiration date of driving licence(YYYY-MM-DD): ");
        drivingLicenceExpirationDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter birthday(YYYY-MM-DD): ");
        birthday = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter address: ");
        address = scanner.nextLine();

        System.out.print("Enter phone number: ");
        phoneNumber = scanner.nextLine();

        customerDao.addCustomer(
                new Customer(name, documentId, documentExpirationDate, drivingLicenceId,
                        drivingLicenceExpirationDate, birthday, address, phoneNumber)
        );
        System.out.println("Done!\n");
    }

    /* Pobieranie documentId oraz sprawdzenie czy występuje dany documentId w bazie klientów
     * Jeżeli występuje informowanie użytkownika oraz pobieranie jeszcze raz */
    private String getDocumentId() {
        while (true) {
            System.out.print("Enter document id: ");
            String documentId = scanner.nextLine();

            if (customerDao.checkAvailableDocumentId(documentId)) {
                return documentId;
            }
            System.out.println("There are one customer with the same document ID! Check document ID!");
        }
    }

    /* Pobieranie licenceId oraz sprawdzenie czy występuje dany licenceId w bazie klientów
     * Jeżeli występuje informowanie użytkownika oraz pobieranie jeszcze raz */
    private String getDriverLicenseId() {
        while (true) {
            System.out.print("Enter driver license id: ");
            String driverLicenceId = scanner.nextLine();

            if (customerDao.checkAvailableDriverLicenceId(driverLicenceId)) {
                return driverLicenceId;
            }
            System.out.println("There are one customer with the same driver licence ID! Check driver licence ID!");
        }
    }

    /* Dodanie nowego zlecenia
     * 1. Pobieranie danych od zleceniu
     * 2. Tworzenie obiektu Order i przekazanie go do metody OrderDao */
    private void addOrder() {
        int customerId, carId;

        System.out.print("Enter customer id: ");
        customerId = scanner.nextInt();

        System.out.print("Enter car id: ");
        carId = scanner.nextInt();

        Car car = new Car();
        car.setId(carId);

        Customer customer = new Customer();
        customer.setId(customerId);

        orderDao.addOrder(new Order(LocalDate.now(), car, customer));
        System.out.println("Done!\n");
    }

    /* Wyświetlenie wszystkich samochodów
     * 1. Pobieranie wszystkich samochodów z bazy
     * 2. Jeżeli niema - wyświetlenie odpowiedniego komunikatu
     * 3. Jeżeli są - iteracja i wywołanie nadpisanej metody toString() */
    private void getAllCars() {
        List<Car> cars = carDao.getAllCars();
        if (cars.isEmpty()) {
            System.out.println("No any cars!\n");
            System.out.println();
            return;
        }
        for (Car car : cars) {
            System.out.println("\t" + car.toString() + "\n");
        }
    }

    /* Wyświetlenie wszystkich klientów
     * 1. Pobieranie wszystkich klientów z bazy
     * 2. Jeżeli niema - wyświetlenie odpowiedniego komunikatu
     * 3. Jeżeli są - iteracja i wywołanie nadpisanej metody toString() */
    private void getAllCustomers() {
        List<Customer> customers = customerDao.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No any customers!\n");
        }
        for (Customer customer : customers) {
            System.out.println("\t" + customer.toString() + "\n");
        }
    }

    /* Wyświetlenie wszystkich zleceń
     * 1. Pobieranie wszystkich zleceń z bazy
     * 2. Jeżeli niema - wyświetlenie odpowiedniego komunikatu
     * 3. Jeżeli są - iteracja i wywołanie nadpisanej metody toString() */
    private void getAllOrders() {
        List<Order> orders = orderDao.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No any orders!\n");
        }
        for (Order order : orders) {
            System.out.println("\t" + order.toString() + "\n");
        }
    }

    /* Usunięcie samochodu z bazy
     * 1. Pobranie odpowiedniego carId od użytkownika
     * 2. Usuwanie samochodu z bazy */
    private void deleteCar() {
        int carId;

        System.out.print("Enter car id: ");
        carId = scanner.nextInt();

        carDao.deleteCar(carId);
        System.out.println();
    }

    /* Usunięcie klienta z bazy
     * 1. Pobranie odpowiedniego customerId od użytkownika
     * 2. Usuwanie klienta z bazy */
    private void deleteCustomer() {
        int customerId;

        System.out.print("Enter customer id: ");
        customerId = scanner.nextInt();

        customerDao.deleteCustomer(customerId);
        System.out.println();
    }

    /* Usunięcie zlecenia z bazy
     * 1. Pobranie odpowiedniego orderId od użytkownika
     * 2. Usuwanie zlecenia z bazy */
    private void deleteOrder() {
        int orderId;
        System.out.print("Enter order id: ");
        orderId = scanner.nextInt();

        Order order = orderDao.getOrderById(orderId);

        if (order == null) {
            System.out.println("There are no any order with that ID! Check order ID!");
            return;
        }
        Car rentalCar = order.getRentalCar();

        BigDecimal price = rentalCar.getDailyPayment();
        LocalDate rentDate = order.getRentDate();

        long daysInRent = ChronoUnit.DAYS.between(rentDate, LocalDate.now()) + 1;
        double total = daysInRent * price.doubleValue();

        System.out.println("To pay: " + total);

        orderDao.deleteOrder(orderId);
        System.out.println();
    }

}
