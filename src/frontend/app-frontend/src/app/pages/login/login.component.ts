import { Component } from '@angular/core';
import { MessageBoxComponent, MessageType } from '../../core/components/message-box/message-box.component';
import { FormsModule } from '@angular/forms';
import { LoginProps, LoginService } from '../../core/services/auth/login/login.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MessageBoxComponent, FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(
    private loginService: LoginService,
    private router: Router
  ) { }

  messageBox = {
    visible: false,
    type: MessageType.UNDEFINED,
    message: "",
    duration: 2500
  }

  user: LoginProps = {
    email: "",
    password: ""
  }

  handleSubmit() {
    this.loginService.execute(this.user).subscribe({
      next: (res) => {
        this.messageBox.visible = true
        this.messageBox.message = "Success."
        this.messageBox.type = MessageType.SUCCESS

        localStorage.setItem("accessToken", res.accessToken);
        this.router.navigate(["/"]);
      },
      error: (err) => {
        this.messageBox.visible = true;
        this.messageBox.message = err.error.message.length > 50
          ? "An unexpected error has occurred."
          : err.error.message;
        this.messageBox.type = MessageType.ERROR;
      }
    });
  }
}
