package com._blog.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import com._blog.demo.model.Entity.User;
import com._blog.demo.model.Entity.Role;
import org.springframework.security.core.Authentication;
import com._blog.demo.service.JwtService;
import com._blog.demo.service.Userservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4300"}, allowCredentials = "true")
public class UserControl {
    private static final Logger logger = LoggerFactory.getLogger(UserControl.class);
    
    @Autowired
    private Userservice userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Login returns a JSON object { token: '...' }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtService.generateToken(request.getUsername());
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            logger.info("Registering user with email: {}", user.getEmail());
            
            // Encrypt the password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            
            // Set default role if not provided
            if (user.getRole() == null) {
                user.setRole(Role.USER);
            }
            
            User savedUser = userService.saveUser(user);
            logger.info("User registered successfully with id: {}", savedUser.getUserid());
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            logger.error("Error registering user: ", e);
            return ResponseEntity.status(500).body("Error registering user: " + e.getMessage());
        }
    }

    // Protected endpoint example: expects Authorization: Bearer <token>
    @GetMapping("/protected")
    public ResponseEntity<?> protectedEndpoint(@RequestHeader(name = "Authorization", required = false) String authorization){
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Missing or invalid Authorization header");
        }
        String token = authorization.substring(7);
        if (!jwtService.validateToken(token)) {
            return ResponseEntity.status(401).body("Invalid token");
        }
        String username = jwtService.extractUsername(token);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Hello " + username + ", you accessed a protected resource.");
        return ResponseEntity.ok(resp);
    }

    // Single-file frontend to demonstrate linking front+back in one file
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String singleFileClient(){
        return "<!doctype html>\n<html>\n<head><meta charset=\"utf-8\"><title>JWT Demo</title></head>\n<body>\n<h3>Login</h3>\n<form id=\"loginForm\">\n  <input id=\"user\" placeholder=\"username\" value=\"user\"/>\n  <input id=\"pass\" placeholder=\"password\" value=\"pass\"/>\n  <button type=\"submit\">Login</button>\n</form>\n<pre id=\"out\"></pre>\n<script>\nconst out = document.getElementById('out');\nconst form = document.getElementById('loginForm');\nform.addEventListener('submit', async e => {\n  e.preventDefault();\n  const username = document.getElementById('user').value;\n  const password = document.getElementById('pass').value;\n  const res = await fetch('/auth/login', {\n    method: 'POST',\n    headers: { 'Content-Type': 'application/json' },\n    body: JSON.stringify({ username, password })\n  });\n  const data = await res.json();\n  out.textContent = JSON.stringify(data, null, 2);\n  if (data.token) {\n    // call protected\n    const p = await fetch('/auth/protected', {\n      headers: { 'Authorization': 'Bearer ' + data.token }\n    });\n    const pd = await p.json().catch(() => p.text());\n    out.textContent += '\n\nProtected response:\n' + JSON.stringify(pd, null, 2);\n  }\n});\n</script>\n</body>\n</html>";
    }
}