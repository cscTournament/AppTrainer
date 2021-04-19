package by.gourianova.apptrainer.util;

import by.gourianova.apptrainer.entity.User;
//import com.sun.org.apache.regexp.internal.RE;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ValidateUser {

    /**
     * Only latin or only russian letters up to 10 characters, the first character can be either uppercase or lowercase
     */
    private final static Pattern FIRST_NAME_PATTERN =
            Pattern.compile("([A-Z]?[a-z]{1,10})|([А-ЯЁ]?[а-яё]{1,10})");

    /**
     * Only latin or only russian letters up to 10 characters, the first character can be either uppercase or lowercase
     */
    private final static Pattern LAST_NAME_PATTERN =
            Pattern.compile("([A-Z]?[a-z]{1,10})|([А-ЯЁ]?[а-яё]{1,10})");

    /**
     * Only latin letters and numbers from 3 to 6 characters
     */
    private final static Pattern LOGIN_PATTERN =
            Pattern.compile("[a-zA-Z0-9]{3,6}");

    /**
     * Latin letters, digits, . , _ , or * from 3 to 6 characters
     */
    private final static Pattern PASSWORD_PATTERN =
            Pattern.compile("[a-zA-Z0-9._*]{3,6}");
    /**
     * Only digits, up to 6 characters
     */
    private final static Pattern BALANCE_PATTERN =
            Pattern.compile("[0-9]{1,6}");

    private final static String WRONG_FIRST_NAME = "firstName";
    private final static String WRONG_LAST_NAME = "lastName";
    private final static String WRONG_LOGIN = "login";
    private final static String WRONG_PASSWORD = "password";
    private final static String WRONG_BALANCE = "balance";

    public static String validate(User user){
        if (!FIRST_NAME_PATTERN.matcher(user.getFirstName()).matches()) {
            return WRONG_FIRST_NAME;
        }

        if (!LAST_NAME_PATTERN.matcher(user.getLastName()).matches()) {
            return WRONG_LAST_NAME;
        }

        if (!LOGIN_PATTERN.matcher(user.getLogin()).matches()) {
            return WRONG_LOGIN;
        }

        if (!PASSWORD_PATTERN.matcher(user.getPassword()).matches()) {
            return WRONG_PASSWORD;
        }

      if (!BALANCE_PATTERN.matcher((user.getBalance()).toString()).matches()) {
            return WRONG_BALANCE;
        }
        return null;
    }

}
