import {Component, ElementRef, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {ValidateInput} from "../../../../helper/helper";

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {
  create: any;
  message: any;

  constructor(
    private el: ElementRef,
    private toaster: ToastrService
  ) { }

  ngOnInit(): void {
  }

  onSubmit(userForm: any) {
    if (ValidateInput(userForm, this.el, this.toaster)) {

    }
  }
}
