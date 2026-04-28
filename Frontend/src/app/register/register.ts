import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: `./register.html`,
  styleUrls: ['./register.css']
})
export class Register {
  constructor(private http: HttpClient) {}

  user = {
    name: '',
    email: '',
    password: ''
  };

  onSubmit() {
    console.log('Submitting user data:', this.user);
    
    this.http.post('http://localhost:8080/auth/register', this.user).subscribe(
      response => {
        console.log('User registered successfully:', response);
        alert('Registration successful!');
        // Reset form
        this.user = { name: '', email: '', password: '' };
      },
      error => {
        console.error('Error registering user:', error);
        console.error('Error status:', error.status);
        console.error('Error message:', error.message);
        if (error.error) {
          console.error('Error body:', error.error);
        }
        
        // Extract meaningful error message
        let errorMessage = 'Please try again.';
        if (error.error && typeof error.error === 'string') {
          errorMessage = error.error;
        } else if (error.error && error.error.message) {
          errorMessage = error.error.message;
        } else if (error.message) {
          errorMessage = error.message;
        } else if (error.status) {
          errorMessage = `Server error (${error.status})`;
        }
        
        alert('Error registering user: ' + errorMessage);
      }
    );
  }
}
