import { Component, OnInit, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common'
import {Store} from "../../services/auth/store";
import {User} from "../../models/user";
import {AUTH} from "../../services/auth/constants";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  user: User = new User();
  constructor(
    @Inject(DOCUMENT) private document: Document,
    private store: Store,
  ) { }

  ngOnInit(): void {
    this.user.id = this.store.getData(AUTH.id)
    this.user.username = this.store.getData(AUTH.username)
    this.user.name = this.store.getData(AUTH.name)
    this.user.role = this.store.getData(AUTH.role)
    this.user.password = this.store.getData(AUTH.password)
  }

  sidebarToggle()
  {
    //toggle sidebar function
    this.document.body.classList.toggle('toggle-sidebar');
    const div =  document.querySelector('.toggle-icon');
    // @ts-ignore
    div.classList.toggle('toggle-menu');
  }

  destroySession() {
    this.store.clearStore();
  }
}
