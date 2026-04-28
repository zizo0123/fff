import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../api';
import { Sidebar } from '../sidebar/sidebar';

interface User {
  userid: number;
  name: string;
  email: string;
}

interface Post {
  postid: number;
  description: string;
  timestamp: number;
}

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, FormsModule, Sidebar],
  templateUrl: 'profile.html',
  styleUrls: ['profile.css']
})
export class Profile implements OnInit {
  user: User | null = null;
  userPosts: Post[] = [];
  isEditing = false;
  isLoading = false;
  activeTab = 'details';

  editForm = {
    name: '',
    email: ''
  };

  constructor(private apiService: ApiService, private router: Router) {}

  ngOnInit() {
    this.checkAuth();
    this.loadUserProfile();
    this.loadUserPosts();
  }

  checkAuth() {
    const token = localStorage.getItem('token');
    if (!token) {
      this.router.navigate(['/login']);
    }
  }

  loadUserProfile() {
    this.isLoading = true;
    this.apiService.getUserProfile().subscribe(
      (user: User) => {
        this.user = user;
        this.editForm.name = user.name;
        this.editForm.email = user.email;
        this.isLoading = false;
      },
      (error: any) => {
        console.error('Error loading profile:', error);
        this.isLoading = false;
      }
    );
  }

  loadUserPosts() {
    this.apiService.getUserPosts().subscribe(
      (posts: Post[]) => {
        this.userPosts = posts;
      },
      (error: any) => {
        console.error('Error loading user posts:', error);
      }
    );
  }

  toggleEdit() {
    this.isEditing = !this.isEditing;
    if (!this.isEditing && this.user) {
      // Reset form if canceling edit
      this.editForm.name = this.user.name;
      this.editForm.email = this.user.email;
    }
  }

  saveProfile() {
    if (!this.editForm.name.trim() || !this.editForm.email.trim()) {
      alert('Please fill in all fields');
      return;
    }

    this.isLoading = true;
    this.apiService.updateProfile(this.editForm).subscribe(
      (response: any) => {
        if (this.user) {
          this.user.name = this.editForm.name;
          this.user.email = this.editForm.email;
          localStorage.setItem('userName', this.editForm.name);
        }
        this.isEditing = false;
        this.isLoading = false;
        alert('Profile updated successfully!');
      },
      (error: any) => {
        console.error('Error updating profile:', error);
        this.isLoading = false;
        alert('Error updating profile. Please try again.');
      }
    );
  }

  setActiveTab(tab: string) {
    this.activeTab = tab;
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('userName');
    this.router.navigate(['/login']);
  }
}
