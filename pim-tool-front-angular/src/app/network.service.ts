import {Injectable} from "@angular/core";
import {fromEvent, Observable, Subscription} from "rxjs";
import {Router} from "@angular/router";


@Injectable()
export class networkService {
  onlineEvent: Observable<Event>;
  offlineEvent: Observable<Event>;
  subscriptions: Subscription[] = [];

  constructor(private router: Router){

    this.offlineEvent = fromEvent(window, 'offline');


    this.subscriptions.push(this.offlineEvent.subscribe(e => {
      this.router.navigate(['/error']);
    }));
  }


}
