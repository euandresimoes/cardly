import { RegisterService } from './../../core/services/auth/register/register.service';
import { Component } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';
import { UserModel } from '../../core/models/UserModel';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MessageBoxComponent, MessageType } from "../../core/components/message-box/message-box.component";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MessageBoxComponent
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  message_box: boolean = true;
  message_box_message: string = "";
  message_box_duration: number = 5000;
  message_box_type: MessageType = MessageType.UNDEFINED;

  user: UserModel = {
    displayName: "",
    email: "",
    password: ""
  }

  constructor(
    private registerService: RegisterService,
    private router: Router
  ) { }

  handleSubmit() {
    this.registerService.execute(this.user)
      .subscribe({
        next: () => this.router.navigate(["/"]),
        error: (err) => console.log(err.message

        )
      })
  }
}
