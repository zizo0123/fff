import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Sidebar } from '../sidebar/sidebar';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [Sidebar],
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class Home implements OnInit {
  isLoggedIn = false;
  userName = '';

  constructor(private router: Router) {}

  async ngOnInit() {
    if (!localStorage.getItem('token')) {
      this.router.navigate(['/login']);
      return;
    }
    await this.fetchUserData();
    this.checkLoginStatus();
  }


  fetchUserData() {
    


  checkLoginStatus() {
    const token = localStorage.getItem('token');
    const name = localStorage.getItem('userName');
    
    if (!token) {
      // Redirect to login if not authenticated
      this.router.navigate(['/login']);
      return;
    }
    
    this.isLoggedIn = true;
    this.userName = name || 'User';
  }
}
