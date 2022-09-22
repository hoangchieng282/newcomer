import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Group} from "./group.model";


@Injectable()
export class GroupService{
  private groupUrl: string;

  constructor(private http: HttpClient) {
    this.groupUrl = 'http://localhost:8080/group'
  }

  public findAll(): Observable<Group[]>{
    return this.http.get<Group[]>(this.groupUrl);
  }
}
