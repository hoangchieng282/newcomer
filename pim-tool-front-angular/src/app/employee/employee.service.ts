import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Employee} from "./employee.model";

@Injectable()
export class EmployeeService{
  private employeeUrl: string;

  constructor(private http: HttpClient) {
    this.employeeUrl = 'http://localhost:8080/employee'
  }

  public  findAll(): Observable<Employee[]>{
    return this.http.get<Employee[]>(this.employeeUrl);
  }
}
