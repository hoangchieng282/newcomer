import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';

import {TranslateService} from "@ngx-translate/core";

import {OnlineStatusType, OnlineStatusService} from "ngx-online-status";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],

})
export class AppComponent {
  title = 'client';


  constructor(private translate: TranslateService,public router: Router, private onlineStatusService: OnlineStatusService) {
    translate.addLangs([ 'fr', 'en']);
    translate.setDefaultLang('en');
  }


}
