package util;

import error.ValidationException;
import model.User;

public class ValidationUtil {
    public static void validate(User user) throws ValidationException {
        if(user.getUsername() == null)  {
            throw new ValidationException("Username is null");
        }
        else if(user.getPassword() == null ) {
            throw new ValidationException("Password is null");
        }
        else if(user.getPassword().isBlank()){
            throw new ValidationException("Password is blank");
        }
        else if(user.getUsername().isBlank()){
            throw new ValidationException("Username is blank");
        }
    }
}
