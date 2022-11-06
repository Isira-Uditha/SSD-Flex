import {Component, ElementRef, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {ToastrService} from "ngx-toastr";
import {ValidateInput} from "../../helper/helper";
import {UserService} from "../../services/service/user.service";
import {Router} from "@angular/router";
import {Store} from "../../services/auth/store";
import {AUTH} from "../../services/auth/constants";
// @ts-ignore
import * as forge from 'node-forge';
import {RsaService} from "../../services/service/rsa.service";

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
    private rsaService: RsaService
  ) { }

  ngOnInit(): void {
    //Fetch the RSA public key
    this.rsaService.getRsaPublicKey(123).subscribe(
      (res: any) => {
        this.store.setData("rsaKey",res.data.key);
      },(error: any) => {
        this.toaster.error('Please try again latter.', 'Something went wrong!',{
          closeButton: true,
        });
      }
    )
  }

  onSubmit(userForm: any) {
    //Check the validations
    if (ValidateInput(userForm, this.el, this.toaster)) {

      //Encrypt using public key
      var rsa = forge.pki.publicKeyFromPem(this.store.getData("rsaKey"));
      // @ts-ignore
      var encryptedUsername = window.btoa(rsa.encrypt(this.user.username));
      var encryptedPassword = window.btoa(rsa.encrypt(this.user.password));
      debugger;

      //This is required to create the message
      this.userService.loginIn(encryptedUsername, encryptedPassword).subscribe(
        (res: any) => {
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
