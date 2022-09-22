import {Project} from "./project.model";
import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {Status} from "./status.model";

@Injectable()
export class ProjectService {
  private projectsUrl: string;

  constructor(private http: HttpClient) {
    this.projectsUrl = 'http://localhost:8080/project'
  }

  public getStatus(): Observable<Status[]> {
    return this.http.get<Status[]>('http://localhost:8080/project/getStatus')
  }

  public findAll(): Observable<Project[]> {
    return this.http.get<Project[]>(this.projectsUrl);
  }

  public findWithPage(page: number, size: number): Observable<Project[]> {
    return this.http.get<Project[]>('http://localhost:8080/project/page', {params: {page: page, size: size}})
  }

  public findOne(id: number): Observable<Project> {
    return this.http.get<Project>('http://localhost:8080/project/edit', {params: {id: id}});
  }

  public updateOne(project: Project, id: number): Observable<Project> {
    return this.http.post<Project>('http://localhost:8080/project/edit', project, {params: {id: id}});
  }

  public save(project: Project): Observable<any> {
    return this.http.post<Project>(this.projectsUrl, project)
  }

  public delete(id: number) {
    return this.http.delete<Project>(this.projectsUrl, {params: {id: id}});
  }

  public deleteList(ids: number[]) {
    return this.http.delete<Project[]>('http://localhost:8080/project/deleteList', {params: {ids: ids}})
  }


}
