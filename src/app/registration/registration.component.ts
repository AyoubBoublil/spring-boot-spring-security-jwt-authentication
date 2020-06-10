import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private authService: AuthenticationService) {
  }

  ngOnInit() {
  }

  onSaveUser(user) {
    this.authService.saveUser(user).subscribe(user => {
      console.log(user);
    }, error => {
      console.log(error);
    });
  }
}
