package util;

import javax.swing.JOptionPane;

public class ValidationUtil {

    public static boolean validateNotEmpty(String fieldName, String value) {
        if (value == null || value.isBlank()) {
            JOptionPane.showMessageDialog(null, 
                fieldName + " tidak boleh kosong!",
                "Validasi Gagal", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


    public static boolean validateNumber(String fieldName, String value) {
        if (!validateNotEmpty(fieldName, value)) {
            return false;
        }
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                fieldName + " harus berupa angka!",
                "Validasi Gagal",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    public static boolean validatePassword(String value) {
        if (!validateNotEmpty("Password", value)) return false;
        if (value.length() < 8) {
            JOptionPane.showMessageDialog(null,
                "Password minimal 4 karakter!",
                "Validasi Gagal",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
