package view;

import controller.PhoneBookManagement;
import model.PhoneBook;

import java.time.LocalDate;

import static view.Const.SCANNER;

public class PhoneBookRun {
    public static PhoneBookManagement phoneBooks = new PhoneBookManagement();

    public static void run() {
        int choose;
        do {
            menu();
            choose = AppException.inputNumber();
            AppException.inputNumber(choose, 1, 8);
            switch (choose) {
                case 1:
                    showPhoneBook(5);
                    break;
                case 2:
                    addPhoneBook();
                    break;
                case 3:
                    editPhoneBook();
                    break;
                case 4:
                    removePhoneBook();
                    break;
                case 5:
                    searchPhoneNumber();
                    break;
                case 6:
                    read();
                    break;
                case 7:
                    saveFile();
                    break;
            }
        } while (choose != 8);
    }

    private static void saveFile() {
        phoneBooks.saveFile();
    }

    private static void read() {
        phoneBooks.readFile();
    }

    private static void searchPhoneNumber() {
        System.out.println("Nhập số điện thoại tìm kiếm");
        String phoneSearch = SCANNER.nextLine();
        for (PhoneBook phoneBook : phoneBooks.getPhoneBooks()) {
            if (phoneBook.getPhoneNumber().contains(phoneSearch)) {
                System.out.println(phoneBook);
            }
        }
    }

    private static void showPhoneBook(int number) {
        String choose;
        int count = number;
        int index = 0;
        do {
            if (phoneBooks.getPhoneBooks().size() == index) {
                System.out.println("Không có số điện thoại nào");
                break;
            }
            while (count > 0 && index < phoneBooks.getPhoneBooks().size()) {
                PhoneBook phoneBook = phoneBooks.getPhoneBooks().get(index);
                index++;
                System.out.println(phoneBook);
                count--;
            }
            System.out.println("Nhấn enter đế tiếp tục");
            choose = SCANNER.nextLine();
            count = number;
        } while (choose.equals(""));
    }

    private static void removePhoneBook() {
        String phoneNumber;
        do {
            System.out.println("Nhập vào số điện thoại bạn muốn xóa");
            phoneNumber = SCANNER.nextLine();
            int index = phoneBooks.searchIndexByPhoneNumber(phoneNumber);
            if (index == -1) {
                System.err.println("Không tìm được danh bạ với số điện thoại trên.");
            } else {
                System.out.println("Chọn \"y\" để xác nhận xóa");
                String check = SCANNER.nextLine();
                if (check.equals("y")) {
                    phoneBooks.remove(index);
                    break;
                } else {
                    break;
                }
            }
        } while (phoneNumber != "");

    }

    private static void editPhoneBook() {
        String phoneNumber;
        do {
            System.out.println("Nhập vào số điện thoại bạn cần sửa");
            phoneNumber = SCANNER.nextLine();
            int index = phoneBooks.searchIndexByPhoneNumber(phoneNumber);
            if (index == -1) {
                System.err.println("Không tìm được danh bạ với số điện thoại trên.");
            } else {
                PhoneBook phoneBook = inputPhoneBookEdit();
                phoneBook.setPhoneNumber(phoneNumber);
                phoneBooks.edit(index, phoneBook);
                break;
            }
        } while (phoneNumber != "");
    }

    public static void addPhoneBook() {
        PhoneBook phoneBook = inputPhoneBook();
        phoneBooks.add(phoneBook);
    }

    public static PhoneBook inputPhoneBookEdit() {
        System.out.println("Nhóm của danh bạ.");
        String group = SCANNER.nextLine();
        System.out.println("Họ tên.");
        String fullName = SCANNER.nextLine();
        System.out.println("Giới tính");
        String gender = SCANNER.nextLine();
        System.out.println("Địa chỉ.");
        String address = SCANNER.nextLine();
        LocalDate dateOfBirth;
        do {
            System.out.println("Ngày sinh.");
            String dateString = SCANNER.nextLine();
            dateOfBirth = AppException.inputDate(dateString);
        } while (dateOfBirth == null);
        System.out.println("Email.");
        String email = SCANNER.nextLine();
        return new PhoneBook(group, fullName, gender, address, dateOfBirth, email);
    }


    public static PhoneBook inputPhoneBook() {
        String phoneNumber;
        boolean checkPhoneNumber;
        do {
            System.out.println("Số điện thoại.");
            phoneNumber = SCANNER.nextLine();
            checkPhoneNumber = AppException.checkPhoneNumber(phoneNumber, phoneBooks.getPhoneBooks());
        } while (checkPhoneNumber);
        PhoneBook phoneBook = inputPhoneBookEdit();
        phoneBook.setPhoneNumber(phoneNumber);
        return phoneBook;
    }

    public static void menu() {
        System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ----");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhập");
        System.out.println("4. Xóa");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Đọc từ file");
        System.out.println("7. Ghi vào file");
        System.out.println("8. Thoát");
        System.out.println("Chọn chức năng");
    }

}
