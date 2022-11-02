import {ToastrService} from "ngx-toastr";

export var PhoneValidation = "^[0-9]{10}$"
export var EmailValidation = "^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$";

export function ValidateInput(form: any, el: any, toastr: ToastrService, msg='') {
  const invalidControl = el.nativeElement.querySelector('.form-control.ng-invalid');
  if (invalidControl && !invalidControl.classList) {
    el.nativeElement.querySelector('.ng-select.ng-invalid input').focus();
  } else if (invalidControl) {
    invalidControl.focus();
  }
  if (!validateForm(form.control)) {
    // toastr.error('Please enter ' + invalidControl.name);
    toastr.error(msg != ''? msg : 'Please fill the required fields');
    return false;
  } else {
    return true;
  }
}

function validateForm(form: { controls: {}; get: (arg0: string) => any; valid: any; }){
  Object.keys(form.controls).forEach(field => {
    const control = form.get(field);
    control.markAsTouched({ onlySelf: true });
  });
  return form.valid;
}

