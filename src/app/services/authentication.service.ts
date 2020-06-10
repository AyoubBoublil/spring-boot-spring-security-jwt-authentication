import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable()
export class AuthenticationService {

  private host: string = "http://localhost:8080";
  private jwtToken = null;
  private roles: Array<any>;

  constructor(private httpClient: HttpClient) {
  }

  login(user) {
    return this.httpClient.post(this.host + "/login", user, {observe: 'response'});
  }

  saveToken(jwt: string) {
    this.jwtToken = jwt;
    localStorage.setItem('token', jwt);

    let jwtHelper = new JwtHelperService();
    this.roles = jwtHelper.decodeToken(this.jwtToken).roles;
  }

  getAllTasks() {
    if (this.jwtToken === null) this.loadToken();
    return this.httpClient.get(this.host + "/tasks", {headers: new HttpHeaders({'Authorization': this.jwtToken})});
  }

  getAllUsers() {
    if (this.jwtToken === null) this.loadToken();
    return this.httpClient.get(this.host + "/users", {headers: new HttpHeaders({'Authorization': this.jwtToken})});
  }

  loadToken() {
    this.jwtToken = localStorage.getItem('token')
  }

  logout() {
    this.jwtToken = null;
    localStorage.removeItem('token');
  }

  isAdmin() {
    if(this.roles != null) {
      for (let role of this.roles) {
        if (role.authority === "ADMIN") return true;
      }
    }
    return false;
  }

  saveTask(task) {
/*    let headers = new HttpHeaders();
    headers.append('authorization', this.jwtToken);*/
    return this.httpClient.post(this.host + "/tasks", task, {headers: new HttpHeaders({'Authorization': this.jwtToken})});
  }

  saveUser(user) {
    /*    let headers = new HttpHeaders();
        headers.append('authorization', this.jwtToken);*/
    return this.httpClient.post(this.host + "/register", user, {headers: new HttpHeaders({'Authorization': this.jwtToken})});
  }
}
