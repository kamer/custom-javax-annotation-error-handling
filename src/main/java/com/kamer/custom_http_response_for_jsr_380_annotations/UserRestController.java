package com.kamer.custom_http_response_for_jsr_380_annotations;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on February, 2020
 *
 * @author kamer
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@PostMapping
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {

		return ResponseEntity.ok(user);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(MethodArgumentNotValidException ex, WebRequest request) {

		final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

		final List<CustomFieldError> customFieldErrors = new ArrayList<>();

		for (FieldError fieldError : fieldErrors) {

			final String field = fieldError.getField();

			final String message = fieldError.getDefaultMessage();

			final CustomFieldError customFieldError = CustomFieldError.builder().field(field).message(message).build();

			customFieldErrors.add(customFieldError);

		}

		return ResponseEntity.badRequest().body(customFieldErrors);
	}

}
