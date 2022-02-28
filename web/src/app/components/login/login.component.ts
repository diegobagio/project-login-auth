import { Router } from '@angular/router';
import { AuthService } from './../../shared/auth/auth.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  entity: any;

  constructor(private authService: AuthService, private router: Router) {
    this.entity = {};
  }

  login() {
    this.authService
      .login(this.entity.username, this.entity.password)
      .subscribe(
        (response) => {
          const access_token = JSON.stringify(response);
          localStorage.setItem('access_toke', access_token);

          if (this.entity.remenber) {
            localStorage.setItem('username', this.entity.username);
          } else {
            localStorage.removeItem('username');
          }

          this.router.navigate(['']);
        },
        (errorResponse) => {
          // this.messageService.add({ severity: 'error', detail: 'Usuário e/ou senha incorreto(s).' });
        }
      );
  }
}
