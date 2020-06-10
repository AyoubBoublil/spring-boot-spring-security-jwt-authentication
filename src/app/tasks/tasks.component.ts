import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  tasks;

  constructor(public authService: AuthenticationService, private router: Router) {
  }

  ngOnInit() {
    this.authService.getAllTasks()
      .subscribe(data => {
        console.log(data);
        this.tasks = data;
      }, error => {
        this.authService.loadToken();
        this.router.navigateByUrl('login');
      });
  }

  onSaveTask() {
    this.router.navigateByUrl("/new-task");
  }

  currentUserIsAdmin() {
    this.authService.isAdmin();
  }
}
