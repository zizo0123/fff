import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../api';
import { Sidebar } from '../sidebar/sidebar';

@Component({
  selector: 'app-create-post',
  standalone: true,
  imports: [CommonModule, FormsModule, Sidebar],
  templateUrl: './create-post.html',
  styleUrls: ['./create-post.css']
})
export class CreatePost {
  post = {
    description: ''
  };

  isLoading = false;
  error = '';

  constructor(private apiService: ApiService, private router: Router) {}

  onSubmit() {
    if (!this.post.description.trim()) {
      this.error = 'Please enter some content for your post';
      return;
    }

    this.isLoading = true;
    this.error = '';

    this.apiService.createPost(this.post).subscribe(
      response => {
        this.isLoading = false;
        alert('Post created successfully!');
        this.router.navigate(['/posts']);
      },
      error => {
        console.error('Error creating post:', error);
        this.error = 'Failed to create post. Please try again.';
        this.isLoading = false;
      }
    );
  }

  cancel() {
    this.router.navigate(['/posts']);
  }
}