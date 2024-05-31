import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-toast-notification',
  templateUrl: './toast-notification.component.html',
  styleUrls: ['./toast-notification.component.css']
})
export class ToastNotificationComponent implements OnInit {
  message: string = '';
  show: boolean = false;

  constructor() {}

  ngOnInit(): void {}

  showMessage(message: string) {
    this.message = message;
    this.show = true;
    setTimeout(() => {
      this.show = false;
    }, 3000);
  }
}
