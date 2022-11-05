import {Component, ElementRef, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {ToastrService} from "ngx-toastr";
import {ValidateInput} from "../../helper/helper";
import {UserService} from "../../services/service/user.service";
import {Router} from "@angular/router";
import {Store} from "../../services/auth/store";
import {AUTH} from "../../services/auth/constants";

@Component({
  selector: 'app-pages-login',
  templateUrl: './pages-login.component.html',
  styleUrls: ['./pages-login.component.css']
})
export class PagesLoginComponent implements OnInit {
  user: User = new User();

  constructor(
    private el: ElementRef,
    private toaster: ToastrService,
    private userService: UserService,
    private router: Router,
    private store: Store,
  ) { }

  ngOnInit(): void {
  }

  onSubmit(userForm: any) {
    //Check the validations
    if (ValidateInput(userForm, this.el, this.toaster)) {
      //This is required to create the message
      this.userService.loginIn(this.user.username, this.user.password).subscribe(
        (res: any) => {
          debugger;
          // this.router.navigate(['/dashboard']);
          // @ts-ignore
          if(res != null){

            //Set session data
            this.store.setData(AUTH.id, res.id);
            this.store.setData(AUTH.username, res.username);
            this.store.setData(AUTH.role, res.role);
            this.store.setData(AUTH.token, res.access_token);

            //If validated navigate to the dashboard
            this.router.navigate(['/dashboard']);

            this.toaster.success('You have been logged successfully.', 'Login Successful!',{
              closeButton: true,
            });
          }else{
            this.toaster.error('Please provide valid details.', 'Login Unsuccessful!',{
              closeButton: true,
            });
          }

        },(error: any) => {
          this.toaster.error('Please provide valid details.', 'Login Unsuccessful!',{
            closeButton: true,
          });
        }
      )
    }
  }

}
