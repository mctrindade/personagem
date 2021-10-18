package br.com.personagem.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.personagem.enums.PatrounsEnum;

public class PatrounsOfEnumValidator implements ConstraintValidator<PatrounsOfEnum, PatrounsEnum> {
	
	 private PatrounsEnum[] patrouns;
	 
	@Override
    public void initialize(PatrounsOfEnum constraint) {
		this.patrouns =  constraint.anyOf();
	}
	
	@Override
	public boolean isValid(PatrounsEnum value, ConstraintValidatorContext context) {
		return value == null || Arrays.asList(patrouns).contains(value);
	}

}
