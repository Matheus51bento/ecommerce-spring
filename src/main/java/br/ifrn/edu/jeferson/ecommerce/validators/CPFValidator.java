package br.ifrn.edu.jeferson.ecommerce.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<ValidCPF, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        String cpf = value.replaceAll("[^\\d]", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int[] weight1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] weight2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

            int sum1 = 0;
            for (int i = 0; i < 9; i++) {
                sum1 += Character.getNumericValue(cpf.charAt(i)) * weight1[i];
            }
            int mod1 = sum1 % 11;
            int checkDigit1 = mod1 < 2 ? 0 : 11 - mod1;

            int sum2 = 0;
            for (int i = 0; i < 10; i++) {
                sum2 += Character.getNumericValue(cpf.charAt(i)) * weight2[i];
            }
            int mod2 = sum2 % 11;
            int checkDigit2 = mod2 < 2 ? 0 : 11 - mod2;

            return checkDigit1 == Character.getNumericValue(cpf.charAt(9)) &&
                    checkDigit2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }
}
