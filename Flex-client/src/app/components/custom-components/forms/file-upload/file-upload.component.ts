import {Component, ElementRef, OnInit} from '@angular/core';
import {ValidateInput} from "../../../../helper/helper";
import {ToastrService} from "ngx-toastr";
import {FileService} from "../../../../services/service/file.service";
import {InsertedFile} from "../../../../models/file";
import {Store} from "../../../../services/auth/store";
import {AUTH} from "../../../../services/auth/constants";

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {
  file: any;

  constructor(
    private el: ElementRef,
    private toaster: ToastrService,
    private fileService: FileService,
    private store: Store,
  ) { }

  ngOnInit(): void {
  }

  onChange(event : any) {

    if(event.target.files[0].type == "application/pdf" || event.target.files[0].type == "image/png" || event.target.files[0].type == "image/jpg"){
      this.file = event.target.files[0];
    }else{
      this.file = null;
      this.toaster.error('You cannot enter zip files.', 'File type error!',{
        closeButton: true,
      });
    }

  }

  onSubmit(userForm: any) {
    //Check the validations
    if (ValidateInput(userForm, this.el, this.toaster)) {

      //Creating a FormData object
      const formData = new FormData();
      //Append image to formData
      formData.append('file',this.file,this.file.name)

      //This is required to upload the file
      this.fileService.createFile(formData,this.store.getData(AUTH.id)).subscribe(
        (res: any) => {
          this.toaster.success('File has been uploaded successfully.', 'File Uploaded!',{
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
