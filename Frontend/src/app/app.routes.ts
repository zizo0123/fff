import { Routes } from '@angular/router';
import { Login} from './login/login';
import {Register} from './register/register'
import {Profile} from './profile/profile'
import { Home } from './home/home';
import { App } from './app';
import { PostsComponent } from './posts/posts';
import { CreatePost } from './create-post/create-post';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: Home }, 
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'profile', component: Profile },
  {path:'posts', component: PostsComponent},
  { path: 'create-post', component: CreatePost }
];