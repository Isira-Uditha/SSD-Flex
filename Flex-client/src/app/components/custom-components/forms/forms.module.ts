import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsRoutingModule } from './forms-routing.module';
import { MessageComponent } from './message/message.component';
import { FileUploadComponent } from './file-upload/file-upload.component';
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    MessageComponent,
    FileUploadComponent
  ],
  imports: [
    CommonModule,
    FormsRoutingModule,
    FormsModule
  ],
  exports: [
    MessageComponent,
    FileUploadComponent
  ],
})
export class FormModule { }
