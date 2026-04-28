import {Component,OnInit} from '@angular/core';
import {ApiService} from '../api';
import {Sidebar} from '../sidebar/sidebar';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
interface Post{
    postid: number;
    description: string;
    timestamp: number;
    users:{
        userid:number;
        name:string;
        email:string;
    };
}
@Component({
    selector:'app-posts',
    standalone:true,
    imports:[CommonModule,Sidebar, RouterLink],
    templateUrl:'./posts.html',
    styleUrls:['./posts.css']
})
export class PostsComponent implements OnInit{
    posts:Post[]=[];
    loading:boolean=true;
    error:string='';
    constructor(private apiService:ApiService){}

    ngOnInit(){
        this.loadPosts();
    }
    loadPosts(){
        this.apiService.getAllPosts().subscribe(
            (data:Post[])=>{
                this.posts=data;
                this.loading=false;
            },
            (error)=>{
                this.error='Failed to load posts';
                this.loading=false;
            }
        );

    }
}