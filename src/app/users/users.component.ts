import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users;

  constructor(public authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    this.authService.getAllUsers().subscribe(data => {
        this.users = data;
    }, error => {
      this.authService.loadToken();
      this.router.navigateByUrl('login');
    });
  }

  onNewUser() {
    this.router.navigateByUrl('register');
  }
}
