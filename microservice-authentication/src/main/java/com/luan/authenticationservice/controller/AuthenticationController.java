package com.luan.authenticationservice.controller;

import com.luan.authenticationservice.dto.UserDTO;
import com.luan.authenticationservice.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.luan.authenticationservice.dto.AuthenticationResponse;

@RestController
@RequiredArgsConstructor
@Tag(name = "authentication-services")
public class AuthenticationController {
	private final AuthenticationService authService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody UserDTO request
	) {
		return ResponseEntity.ok(authService.register(request));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(
			@RequestBody UserDTO request
	) {
		return ResponseEntity.ok(authService.authenticate(request));
	}

	@PostMapping("/lock")
	public ResponseEntity<String> lockUser(@RequestParam("username") String username) {
		return ResponseEntity.ok(authService.lockUser(username));
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
		return ResponseEntity.ok(authService.processForgotPassword(email));
	}
}
