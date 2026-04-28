import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../api';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class Login {
  constructor(private api: ApiService, private router: Router) {}

  loginData = {
    username: '',
    password: ''
  }
  
  loginError: string = '';
  isLoading: boolean = false;
 
  onSubmit() {
    if (!this.loginData.username || !this.loginData.password) {
      this.loginError = 'Please enter both username and password';
      return;
    }
    
    this.isLoading = true;
    this.loginError = '';
    console.log('Component sending:', this.loginData);

    this.api.testLOgin(this.loginData).subscribe(
      response => {
        console.log('Login successful:', response);
        if (response.token) {
          // Store the token in localStorage
          localStorage.setItem('token', response.token);
          // Store the username for display
          localStorage.setItem('userName', this.loginData.username);
          alert('Login successful!');
          // Reset form
          this.loginData = { username: '', password: '' };
          this.isLoading = false;
          // Navigate to home
          this.router.navigate(['/home']);
        } else {
          this.loginError = 'No token received from server';
          this.isLoading = false;
        }
      },
      error => {
        console.error('Login error:', error);
        this.isLoading = false;
        
        let errorMessage = 'Login failed. Please try again.';
        if (error.error && typeof error.error === 'string') {
          errorMessage = error.error;
        } else if (error.status === 401) {
          errorMessage = 'Invalid username or password';
        } else if (error.status === 0) {
          errorMessage = 'Cannot connect to server';
        }
        
        this.loginError = errorMessage;
        alert('Error: ' + errorMessage);
      }
    );
  }
}
