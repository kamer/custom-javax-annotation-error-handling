package com.kamer.custom_http_response_for_jsr_380_annotations;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

/**
 * Created on February, 2020
 *
 * @author kamer
 */
@Data
public class User {

	@Length(min = 2, max = 30, message = "Name must be between 2-30 characters. ")
	@Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Name is invalid.")
	private String name;

	@Length(min = 2, max = 30, message = "Surname must be between 2-30 characters.")
	@Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Surname is invalid.")
	private String surname;

	@Email(message = "Enter a valid email address.")
	private String email;
}
