package com.excilys.cdb.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.excilys.cdb.dto.ComputerDTO;

public class ComputerDTOValidator implements Validator {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	@Override
	public boolean supports(Class<?> clazz) {
		return ComputerDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ComputerDTO computerDTO = (ComputerDTO) target;

		if (StringUtils.isBlank(computerDTO.getName())) {
			errors.rejectValue("name", "addComputervalidatorMessage.length");
		} else if (computerDTO.getName().length() < 5 || computerDTO.getName().length() > 30) {
			errors.rejectValue("name", "addComputervalidatorMessage.length");
		} else if (computerDTO.getName().contains("<") || computerDTO.getName().contains(">")
		    || computerDTO.getName().contains("//") || computerDTO.getName().contains("\\")
		    || computerDTO.getName().contains("/*") || computerDTO.getName().contains("*/")) {
			errors.rejectValue("name", "addComputervalidatorMessage.illecgalcarracters");
		}

		if (computerDTO.getIntroduced()==null && computerDTO.getIntroduced()==null) {
			errors.rejectValue("introduced", "addComputervalidatorMessage.discountedDateWithoutIntroduced");
		}
		
		if (StringUtils.isNoneBlank(computerDTO.getIntroduced()) && StringUtils.isNoneBlank(computerDTO.getIntroduced())) {
			LocalDate introduced = LocalDate.parse(computerDTO.getIntroduced(), formatter);
			LocalDate discontinued = LocalDate.parse(computerDTO.getDiscontinued(), formatter);
			if(introduced.isAfter(discontinued)) {
				errors.rejectValue("introduced", "addComputervalidatorMessage.wrongOrderOfDate");
			}
		}
		
	}

}
