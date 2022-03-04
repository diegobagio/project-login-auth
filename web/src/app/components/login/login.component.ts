import { MessageService } from 'primeng/api';
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
  loading = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private messageService: MessageService
  ) {
    this.entity = {};
  }

  login() {
    this.loading = true
    this.authService
      .login(this.entity.username, this.entity.password)
      .subscribe({
        next: (response) => {
          const access_token = JSON.stringify(response);
          localStorage.setItem('access_token', access_token);
          if (this.entity.remenber) {
            localStorage.setItem('username', this.entity.username);
          } else {
            localStorage.removeItem('username');
          }

          this.router.navigate(['']);
        },
        error: (error) => this.messageService.add({severity: 'error', detail: 'Usuario e/ou senha informados incorretamente!'}),
      });
      this.loading = false
  }

  cadastrarUsuario() {

  }
}
