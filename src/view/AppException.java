package view;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.InputMismatchException;

import static view.Const.SCANNER;

public class AppException {
    public static int inputNumber() {
        int choose;
        try {
            choose = SCANNER.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Giá trị nhập vào không phải là số");
            choose = -1;
        } finally {
            SCANNER.nextLine();
        }
        return choose;
    }

    public static int inputNumber(int choose, int fistNumber, int lastNumber) {
        if (choose < fistNumber || choose > lastNumber) {
            System.err.println("Không có lựa chọn này mời bạn nhập lại");
            choose = -1;
        }
        return choose;
    }

    public static LocalDate inputDate(String date) {
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeException e) {
            System.err.println("Nhập sai định dạng ngày (yyyy-mm-dd)");
        } finally {
            return localDate;
        }
    }


}
