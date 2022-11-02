import {Component, ElementRef, OnInit} from '@angular/core';
import {ValidateInput} from "../../../../helper/helper";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {
  insertedFile: any;

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
