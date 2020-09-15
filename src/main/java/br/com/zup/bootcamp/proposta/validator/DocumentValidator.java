package br.com.zup.bootcamp.proposta.validator;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DocumentValidator implements ConstraintValidator<CpfOrCnpj, String> {

    @Override
    public boolean isValid(String document, ConstraintValidatorContext context) {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);
        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);
        return cpfValidator.isValid(document, context) || cnpjValidator.isValid(document, context);
    }

}
