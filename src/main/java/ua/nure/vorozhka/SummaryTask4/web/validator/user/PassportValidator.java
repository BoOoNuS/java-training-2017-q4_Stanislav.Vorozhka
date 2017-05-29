package ua.nure.vorozhka.SummaryTask4.web.validator.user;

import ua.nure.vorozhka.SummaryTask4.exception.validate.user.IncorrectPassportValidateException;
import ua.nure.vorozhka.SummaryTask4.exception.validate.ValidateException;
import ua.nure.vorozhka.SummaryTask4.web.validator.Validator;
import ua.nure.vorozhka.SummaryTask4.web.validator.util.ValidatorUtils;

import java.util.regex.Pattern;

/**
 * Created by Stanislav on 24.05.2017.
 */
public class PassportValidator implements Validator<String> {

    private static final String REGEX_FOR_PASSPORT = "(\\w||\\d){8}";

    private static final Pattern PATTERN_FOR_PASSPORT = Pattern.compile(REGEX_FOR_PASSPORT);

    private static PassportValidator passportValidator = new PassportValidator();

    private PassportValidator() {
    }

    public static PassportValidator getInstance(){
        return passportValidator;
    }

    @Override
    public void validate(String passport) throws ValidateException {
        if (passport == null || !ValidatorUtils.suitPatter(PATTERN_FOR_PASSPORT, passport)) {
            throw new IncorrectPassportValidateException("Incorrect user passport, " + System.lineSeparator() +
                    "user passport consist only English latter and numbers, and " + System.lineSeparator() +
                    "fixed length of passport - 8");
        }
    }
}
