import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './layouts/header/header.component';
import { SidebarComponent } from './layouts/sidebar/sidebar.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { MessageComponent } from './components/custom-components/forms/message/message.component';
import {PagesLoginComponent} from "./pages/pages-login/pages-login.component";
import {FormModule} from "./components/custom-components/forms/forms.module";
import {ToastNoAnimationModule, ToastrModule} from "ngx-toastr";
import {NgxPermissionsModule} from "ngx-permissions";
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    DashboardComponent,
    PagesLoginComponent,
  ],
    imports: [
        BrowserModule,
        CommonModule,
        AppRoutingModule,
        FormModule,
        HttpClientModule,
        ToastrModule.forRoot(),
        ToastNoAnimationModule.forRoot(),
        NgxPermissionsModule.forRoot(),
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
