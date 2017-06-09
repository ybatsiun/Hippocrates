package validation;

import java.time.LocalTime;
import java.util.ArrayList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidScheduleImpl implements ConstraintValidator<ValidSchedule, ArrayList<LocalTime>> {

	private int min;

	@Override
	public void initialize(ValidSchedule constraintAnnotation) {
		min = constraintAnnotation.min();
	}

	@Override
	public boolean isValid(ArrayList<LocalTime> chosenSchedule, ConstraintValidatorContext context) {
		try {
		if (chosenSchedule.size() < 3 ) {
			return false;
		}}
		catch (java.lang.NullPointerException ex){
			return false;
		}
		return true;
	}

}
