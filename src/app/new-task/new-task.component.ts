import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.css']
})
export class NewTaskComponent implements OnInit {
  mode: number;
  tasks;

  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    this.reloadData();
  }

  onSaveTask(task: any) {
   this.authService.saveTask(task).subscribe(task => {
    // this.router.navigateByUrl('tasks');
     this.reloadData();
   }, error => {
      console.log(error);
   });
  }

  reloadData() {
    this.authService.getAllTasks()
      .subscribe(data => {
        console.log(data);
        this.tasks = data;
      }, error => {
        this.authService.loadToken();
        this.router.navigateByUrl('login');
      });
  }
}
