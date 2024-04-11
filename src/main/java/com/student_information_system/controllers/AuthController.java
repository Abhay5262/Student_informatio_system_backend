package com.student_information_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_information_system.reqrep.Request;
import com.student_information_system.reqrep.Response;
import com.student_information_system.userdetail.ProjectUserDetail;
import com.student_information_system.utils.Jwtutil;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ProjectUserDetail projectUserDetails;
	
	@Autowired
	private Jwtutil jwtUtils;
	
 
	
	@PostMapping("/login")
	public ResponseEntity<Response> loginWithCredentials(@RequestBody Request loginRequest)
	{
		try {
			projectUserDetails.setRole(loginRequest.getRole());
			System.err.println(loginRequest);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		UserDetails userDetails = projectUserDetails.loadUserByUsername(loginRequest.getUsername());
		
		String token = jwtUtils.generateToken(userDetails);
		
		return new ResponseEntity<Response> (new Response(token),HttpStatus.OK);
	}
	
	
	@PostMapping("/validate")
	public ResponseEntity<String> ValidateToken()
	{
		return new ResponseEntity<String> ("JWT Token Working",HttpStatus.ACCEPTED);
	}
}
