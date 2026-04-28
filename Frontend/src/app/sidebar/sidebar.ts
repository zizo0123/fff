import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './sidebar.html',
  styleUrls: ['./sidebar.css']
})
export class Sidebar {
  isMenuOpen = false;
  isLoggedIn = false;
  userName = '';

  constructor(private router: Router) {
    this.checkLoginStatus();
  }

  checkLoginStatus() {
    const token = localStorage.getItem('token');
    const name = localStorage.getItem('userName');
    this.isLoggedIn = !!token;
    this.userName = name || 'User';
  }

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('userName');
    this.isLoggedIn = false;
    alert('Logged out successfully!');
    this.router.navigate(['/login']);
  }

  navigateTo(path: string) {
    this.isMenuOpen = false;
    this.router.navigate([path]);
  }
}
