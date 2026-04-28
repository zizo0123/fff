import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseUrl = 'http://localhost:8080';
  
  constructor(private http:HttpClient){}
  
  testLOgin(data:any){
    console.log('Service received:', data);
    return this.http.post<any>(`${this.baseUrl}/auth/login`, data);
  }

  getAllPosts(){
    return this.http.get<any>(`${this.baseUrl}/post/allposts`);
  }

  createPost(post: any){
    return this.http.post<any>(`${this.baseUrl}/post/addpost`, post);
  }

  getUserProfile(){
    return this.http.get<any>(`${this.baseUrl}/user/profile`);
  }

  getUserPosts(){
    return this.http.get<any[]>(`${this.baseUrl}/post/userposts`);
  }

  updateProfile(profileData: any){
    return this.http.put<any>(`${this.baseUrl}/user/profile`, profileData);
  }
}
