package util;

import javax.swing.JOptionPane;

public class ValidationUtil {

    public static boolean validateNotEmpty(String fieldName, String value) {
        if (value == null || value.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    fieldName + " tidak boleh kosong!",
                    "Validasi Gagal",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        return true;
    }


    public static boolean validateNumber(String fieldName, String value) {
        if (!validateNotEmpty(fieldName, value)) return false;

        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    fieldName + " harus berupa angka!",
                    "Validasi Gagal",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    public static boolean validateString(String fieldName, String value) {
        if (!validateNotEmpty(fieldName, value)) return false;

        if (!value.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(null,
                    fieldName + " harus berupa huruf!",
                    "Validasi Gagal",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        return true;
    }
}
