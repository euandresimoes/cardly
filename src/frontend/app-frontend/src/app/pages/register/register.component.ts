import { RegisterService } from './../../core/services/auth/register/register.service';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserModel } from '../../core/models/UserModel';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { MessageBoxComponent, MessageType } from "../../core/components/message-box/message-box.component";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, MessageBoxComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  constructor(
    private registerService: RegisterService,
    private router: Router
  ) { }

  messageBox = {
    visible: false,
    type: MessageType.UNDEFINED,
    message: "",
    duration: 2500
  }

  user: UserModel = {
    displayName: "",
    email: "",
    password: ""
  }

  handleSubmit() {
    this.registerService.execute(this.user).subscribe({
      next: () => {
        this.messageBox.visible = true;
        this.messageBox.message = "Success!"
        this.messageBox.type = MessageType.SUCCESS;
        this.router.navigate(["/login"])
      },
      error: (err) => {
        this.messageBox.visible = true;
        this.messageBox.message = err.error.message.length > 50
          ? "An unexpected error has occurred."
          : err.error.message;
        this.messageBox.type = MessageType.ERROR;
      }
    })
  }
}
