package br.com.personagem.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.personagem.enums.RolesEnum;

public class RolesOfEnumValidator implements ConstraintValidator<RolesOfEnum, RolesEnum> {
	
	 private RolesEnum[] roles;
	 
	@Override
    public void initialize(RolesOfEnum constraint) {
		this.roles =  constraint.anyOf();
	}
	

	@Override
	public boolean isValid(RolesEnum value, ConstraintValidatorContext context) {
		return value == null || Arrays.asList(roles).contains(value);
	}

}
