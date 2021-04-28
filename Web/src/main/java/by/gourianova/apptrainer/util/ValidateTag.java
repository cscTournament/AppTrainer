package by.gourianova.apptrainer.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ResourceBundle;


public class ValidateTag extends TagSupport {
    private String input;
    private final static String WRONG_FIRST_NAME = "firstName";
    private final static String WRONG_LAST_NAME = "lastName";
    private final static String WRONG_LOGIN = "login";
    private final static String WRONG_PASSWORD = "password";
    private final static String WRONG_BALANCE = "balance";

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public int doStartTag() throws JspException {
        ResourceBundle bundle = ResourceBundle.getBundle((String) pageContext.getSession().getAttribute("locale"));
        try {
            if (input.equals(WRONG_FIRST_NAME)) {
                pageContext.getOut().write(bundle.getString("alert.wrong.first.name"));
            }
            if (input.equals(WRONG_LAST_NAME)) {
                pageContext.getOut().write(bundle.getString("alert.wrong.last.name"));
            }
            if (input.equals(WRONG_LOGIN)) {
                pageContext.getOut().write(bundle.getString("alert.wrong.login"));
            }
            if (input.equals(WRONG_PASSWORD)) {
                pageContext.getOut().write(bundle.getString("alert.wrong.password"));
            }
            if (input.equals(WRONG_BALANCE)) {
                pageContext.getOut().write(bundle.getString("alert.wrong.balance"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
}
