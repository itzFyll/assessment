package org.aodocs.aodocassessmentapp.model.annotation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoneConstraintValidator implements ConstraintValidator<PhoneValidator, String> {
    private final Logger _logger = LoggerFactory.getLogger(PhoneConstraintValidator.class);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phone;

        if (value == null) return true;

        try {
            phone = phoneNumberUtil.parse(value, Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());
            return phoneNumberUtil.isValidNumber(phone);
        } catch (NumberParseException e) {
            _logger.error("Invalid Phone number format", e);
            return false;
        }
    }
}
