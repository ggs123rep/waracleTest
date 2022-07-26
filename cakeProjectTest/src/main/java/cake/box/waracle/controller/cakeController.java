package cake.box.waracle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cake.box.waracle.dto.UserRequest;
import cake.box.waracle.dto.UserResponse;
import cake.box.waracle.dto.cakeEntity;
import cake.box.waracle.dto.userInfo;
import cake.box.waracle.service.cakeSService;
import cake.box.waracle.util.JwtUtil;


@RestController
@CrossOrigin(origins="*")

public class cakeController {
	@Autowired 
	cakeSService service;
	@Autowired 
	JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/")
	public ResponseEntity<Object> getCakes() {
		return new ResponseEntity<Object>(service.getCakes(), HttpStatus.OK);
	}
	
	@PostMapping("/cakes")
	public ResponseEntity<Object> addCakes(@RequestBody List<cakeEntity> entity) {
		return new ResponseEntity<Object>(service.addCakes(entity), HttpStatus.OK);
	}
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody userInfo info) {
		Integer id=service.saveUser(info);
		return ResponseEntity.ok("User saved with id"+id);
	}
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest userRequest)
	{
		System.out.println("2");

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						userRequest.getUsername(), 
						userRequest.getPassword()
						)
				);
		String token=jwtUtil.generateToken(userRequest.getUsername());

		return ResponseEntity.ok(new UserResponse(token,"GENERATED BY Girish"));
	}

}
