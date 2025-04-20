import { animate, AnimationEvent, style, transition, trigger } from '@angular/animations';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-message-box',
  standalone: true,
  imports: [],
  templateUrl: './message-box.component.html',
  styleUrl: './message-box.component.css',
  animations: [
    trigger('fadeSlide', [
      transition(':enter', [
        style({
          opacity: 0,
          transform: 'translateY(-40px)'
        }),
        animate(
          '300ms ease-in-out',
          style({ opacity: 1, transform: 'translateY(0)' })
        )
      ]),
      transition(':leave', [
        animate(
          '300ms ease-in-out',
          style({ opacity: 0, transform: 'translateY(-40px)' })
        )
      ])
    ])
  ]
})
export class MessageBoxComponent {
  @Input() type: MessageType = MessageType.UNDEFINED;
  @Input() message: string = "";
  @Input() duration: number = 2500;
  @Output() closed = new EventEmitter<void>();

  isVisible: boolean = true;
  MessageType = MessageType;

  ngOnInit() {
    setTimeout(() => {
      this.isVisible = false;
    }, this.duration);
  }

  onAnimationDone(event: AnimationEvent) {
    if (event.toState === "void") {
      this.closed.emit();
    }
  }
}

export enum MessageType {
  SUCCESS,
  WARNING,
  ERROR,
  UNDEFINED
}