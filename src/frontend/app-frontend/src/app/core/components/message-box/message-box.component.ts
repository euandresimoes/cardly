import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-message-box',
  standalone: true,
  imports: [],
  templateUrl: './message-box.component.html',
  styleUrl: './message-box.component.css'
})
export class MessageBoxComponent {
  @Input() type: MessageType = MessageType.UNDEFINED;
  @Input() message: string = "";
  @Input() duration: number = 5000;

  isVisible: boolean = true;
  MessageType = MessageType;
}

export enum MessageType {
  SUCCESS,
  WARNING,
  ERROR,
  UNDEFINED
}