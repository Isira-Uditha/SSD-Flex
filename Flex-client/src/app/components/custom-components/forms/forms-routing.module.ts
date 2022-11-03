import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MessageComponent} from "./message/message.component";
import {FileUploadComponent} from "./file-upload/file-upload.component";
import {AuthGuard} from "../../../services/auth/shared/auth.guard";
import {RoleGuard} from "../../../services/auth/shared/role.guard";

const routes: Routes = [
  { path: 'message', component: MessageComponent, data: {title: 'Message'}},
  { path: 'file',canActivate:[RoleGuard], component: FileUploadComponent, data: {title: 'File Upload'}},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FormsRoutingModule { }
