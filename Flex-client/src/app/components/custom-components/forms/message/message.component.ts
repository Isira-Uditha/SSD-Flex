import {Component, ElementRef, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {ValidateInput} from "../../../../helper/helper";
import {Message} from "../../../../models/message";
import {MessageService} from "../../../../services/service/message.service";
import {Store} from "../../../../services/auth/store";
import {AUTH} from "../../../../services/auth/constants";

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {
  message: Message = new Message();


  constructor(
    private el: ElementRef,
    private toaster: ToastrService,
    private messageService: MessageService,
    private store: Store,
  ) { }

  ngOnInit(): void {
  }

  onSubmit(userForm: any) {
    //Check the validations
    if (ValidateInput(userForm, this.el, this.toaster)) {

      //Set the user ID to the message object
      this.message.userId = this.store.getData(AUTH.id);

      //This is required to create the message
        this.messageService.createMessage(this.message).subscribe(
          (res: any) => {
            this.toaster.success('Message has been created successfully.', 'Message Created!',{
              closeButton: true,
            });
          },(error: any) => {
            this.toaster.error('Please try again latter.', 'Something went wrong!',{
              closeButton: true,
            });
          }
        )
    }
  }
}
