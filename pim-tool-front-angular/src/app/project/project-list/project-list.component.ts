import {Component, OnInit} from '@angular/core';
import {Project} from "../project.model";
import {ProjectService} from "../project.service";
import {HttpClient, HttpParams} from "@angular/common/http";
import {FormBuilder, NgForm} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {TranslateService} from "@ngx-translate/core";
import {fromEvent, Observable, Subscription} from "rxjs";


@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {
  projects: Project[];
  count: number;
  checkerList: number[];
  delList: number[];
  preKeyword = "";
  preStatus = "";
  offlineEvent: Observable<Event>;
  subscriptions: Subscription[] = [];
  currentIndex = -1;
  page = 1;



  statusOptions = [
    {name: "NEW"},
    {name: "PLA"},
    {name: "INP"},
    {name: "FIN"},
  ]

  constructor(private projectService: ProjectService,
              private http: HttpClient,
              private router: Router) {
  }


  ngOnInit() {
    // this.fetchPage(0,5);
    this.offlineEvent = fromEvent(window, 'offline');

    const storeKey = localStorage.getItem('storeKey')
    const storeStatus = localStorage.getItem('storeStatus')
    let keyword = (storeKey === "null") ? "" : storeKey;
    let status = (storeStatus === "null") ? "" : storeStatus;

    if (keyword != null || status != "") {
      this.preKeyword = keyword;
      this.preStatus = status;
      this.onFilter({status, keyword})
    }
    else {
      // this.fetchPage(0,5);
      this.fetchData()
    }

  }

  fetchPage(page: number, size: number) {
    this.projectService.findWithPage(page, size).subscribe(projectData => {
      this.projects = projectData;
    },error => {
      this.router.navigate(['/error']);
      return []
    })
  }

  onFilterPage(getData: { keyword: string, status: string }, page: number, size: number) {
    localStorage.setItem('storeKey', getData.keyword)
    localStorage.setItem('storeStatus', getData.status)
    let filterParams = new HttpParams()
      .set('keyword', getData.keyword == null ? "" : getData.keyword)
      .set('status', getData.status == null ? "" : getData.status.toUpperCase())
      .set('page', page)
      .set('size', size)
    if ((getData.keyword != null && getData.keyword.length != 0)
      || (getData.status != null && getData.status.length != 0)) {

      this.http.get<Project[]>('http://localhost:8080/project/filter', {
        params: filterParams
      }).subscribe(data => {
        this.projects = data;
      },error => {
        this.router.navigate(['/error']);
        return []});
    } else {
      this.projectService.findWithPage(page, size).subscribe(data => {
        this.projects = data;
      })
    }
  }


  fetchData() {
    this.projectService.findAll().subscribe(projectData => {
      this.projects = projectData;
    },error => {
        this.router.navigate(['/error']);
      return []
    })
  }

  onDel(project: Project) {
    if (!project.status.localeCompare('NEW'))
      this.projectService.delete(project.id).subscribe(result => {
        this.fetchData()
      },error => {
        this.router.navigate(['/error']);
        return []
      });
  }

  updateCheckedOptions() {
    this.checkerList = this.projects.filter(p => p.checked == true).map(p => p.id);
    this.delList = this.projects.filter(p => p.checked == true && !p.status.localeCompare('NEW')).map(p => p.id);
  }

  onDelList() {
    if (this.delList.length == this.checkerList.length) {
      this.projectService.deleteList(this.delList).subscribe(result => {
        this.delList = [];
        this.checkerList = [];
        this.fetchData();
      },error => {
        this.router.navigate(['/error']);
        return []
      })
    }
  }

  onReset(form: NgForm) {
    localStorage.removeItem('storeKey')
    localStorage.removeItem('storeStatus')
    form.resetForm({status: "", key: ""})
  }

  onFilter(getData: { keyword: string, status: string }) {
    localStorage.setItem('storeKey', getData.keyword)
    localStorage.setItem('storeStatus', getData.status)
    let filterParams = new HttpParams()
      .set('keyword', getData.keyword == null ? "" : getData.keyword)
      .set('status', getData.status == null ? "" : getData.status.toUpperCase())
    if ((getData.keyword != null && getData.keyword.length != 0)
      || (getData.status != null && getData.status.length != 0)) {

      this.http.get<Project[]>('http://localhost:8080/project/filter', {
        params: filterParams
      }).subscribe(data => {
        this.projects = data;
      },error => {
        this.router.navigate(['/error']);
        return []});
    } else {
      this.projectService.findAll().subscribe(data => {
        this.projects = data;
      })
    }
  }



}
