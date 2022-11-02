import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MessageComponent} from "./message/message.component";
import {FileUploadComponent} from "./file-upload/file-upload.component";

const routes: Routes = [
  { path: 'message', component: MessageComponent, data: {title: 'Message'}},
  { path: 'file', component: FileUploadComponent, data: {title: 'File Upload'}},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FormsRoutingModule { }
