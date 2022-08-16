import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Project} from "../project.model";
import {ProjectService} from "../project.service";
import {GroupService} from "../../group/group.service";
import {Group} from "../../group/group.model";
import {HttpClient} from "@angular/common/http";
import {TranslateService} from "@ngx-translate/core";
import {EmployeeService} from "../../employee/employee.service";
import {Employee} from "../../employee/employee.model";
import {catchError} from "rxjs";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-project-form',
  templateUrl: './project-form.component.html',
  styleUrls: ['./project-form.component.css']
})
export class ProjectFormComponent implements OnInit {

  id: number;
  editMode = false;
  projectForm: FormGroup;
  project: Project;
  groups: Group[];
  employees: Employee[];
  notfoundVisaArray = null;
  isSubmitted = false;
  projectExisted = false;
  dateNotValid = false;


  statusOptions = [
    {name: "NEW"},
    {name: "PLA"},
    {name: "INP"},
    {name: "FIN"},
  ]

  constructor(private route: ActivatedRoute,
              private router: Router,
              private http: HttpClient,
              private projectService: ProjectService,
              private groupService: GroupService,
              private employeeService: EmployeeService,
              public translate: TranslateService) {
    this.project = new Project();
  }

  ngOnInit() {
    this.isSubmitted = false;

    this.groupService.findAll().subscribe(groupData => {
        this.groups = groupData;
      }, error => {
        this.router.navigate(['/error']);
        return []
      }
    )
    this.employeeService.findAll().subscribe(employeeData => {
      this.employees = employeeData;
    }, error => {
      this.router.navigate(['/error']);
      return []
    })

    this.route.queryParams.subscribe(params => {
      this.id = +params['id']
      this.editMode = params['id'] != null;
      this.initForm();
    })
  }

  onCheckmandatory() {
    if (this.isSubmitted == true) {
      if (this.projectForm.invalid) {
        return true;
      }
    }
    return false;
  }

  onCheckNumber() {
    if (this.isSubmitted == true) {
      if (this.projectForm.get('projectNumber').invalid) {
        return true;
      }
    }
    return false;
  }

  onCheckName() {
    if (this.isSubmitted == true) {
      if (this.projectForm.get('projectName').invalid) {
        return true;
      }
    }
    return false;
  }

  onCheckCustomer() {
    if (this.isSubmitted == true) {
      if (this.projectForm.get('customer').invalid) {
        return true;
      }
    }
    return false;
  }

  onCheckGroupLeader() {
    if (this.isSubmitted == true) {
      if (this.projectForm.get('groupLeader').invalid) {
        return true;
      }
    }
    return false;
  }

  onCheckMember() {
    if (this.isSubmitted == true) {
      if (this.projectForm.get('employees').invalid) {
        return true;
      }
    }
    return false;
  }

  onCheckStartDate() {
    if (this.isSubmitted == true) {
      if (this.projectForm.get('startDate').invalid) {
        return true;
      }
    }
    return false;
  }

  onCheckEndDate() {
    if (this.isSubmitted == true) {
      if (this.projectForm.get('endDate').invalid) {
        return true;
      }
    }
    return false;
  }

  private initForm() {
    this.projectForm = new FormGroup({
      'projectNumber': new FormControl('', [Validators.required,Validators.maxLength(4), Validators.min(0), Validators.pattern('[0-9]+')],),
      'projectName': new FormControl('', [Validators.required,Validators.maxLength(50)]),
      'customer': new FormControl('', [Validators.required,Validators.maxLength(50)]),
      'groupLeader': new FormControl('', [Validators.required]),
      'employees': new FormControl('', [Validators.pattern('[a-zA-Z,]+[a-zA-Z]'),]),
      'status': new FormControl('NEW',),
      'startDate': new FormControl('', [Validators.required]),
      'endDate': new FormControl('', ),
      'version': new FormControl(0,),
    })
    if (this.editMode) {
      this.projectService.findOne(this.id).subscribe(data => {
        this.project = data;
        this.projectForm.setValue({
          projectNumber: this.project.projectNumber.toString(),
          projectName: this.project.projectName,
          customer: this.project.customer,
          groupLeader: this.project.groupLeader,
          employees: this.project.employees,
          status: this.project.status,
          startDate: this.project.startDate,
          endDate: this.project.endDate,
          version: this.project.version,
        })
      })
    }


  }


  onSubmit() {
    this.notfoundVisaArray = null;
    this.projectExisted = false;
    this.dateNotValid = false;
    this.isSubmitted = true;
    this.project = this.projectForm.value;
    if (this.projectForm.invalid || (this.project.employees.length != 0 && this.checkVisaValid(this.project.employees).length != 0)) {
      return
    }
    if (this.editMode) {
      console.log(this.project)

      this.projectService.updateOne(this.project, this.id).subscribe(result => this.gotoProjectList(),
        error => {
          if (error.error.toString() == "vn.elca.training.model.exception.StartDateAfterEndDateException") {
            this.dateNotValid = true;
          } else {
            this.router.navigate(['/error']);
          }
          return []
        })
    } else {
      this.projectService.save(this.project).subscribe(result => {
          this.gotoProjectList();
        }, error => {
          if (error.error.toString() == "vn.elca.training.model.exception.ProjectNumberAlreadyExistsException") {
            this.projectExisted = true;
          } else if (error.error.toString() == "vn.elca.training.model.exception.StartDateAfterEndDateException") {
            this.dateNotValid = true;
          } else {
            this.router.navigate(['/error']);
          }
          return []
        }
      );
    }
  }

  checkVisaValid(members: String) {
    const visaArray = members.toUpperCase().replace(/\s/g, '').split(',');
    // console.log(visaArray)
    const employeeVisa = this.employees.map(e => e.visa)
    this.notfoundVisaArray = visaArray.filter(e => (employeeVisa.indexOf(e) == -1))
    return this.notfoundVisaArray;
  }

  gotoProjectList() {
    this.router.navigate(['/projectList']);
  }


}
