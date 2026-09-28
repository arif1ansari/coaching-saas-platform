import {Component,inject} from '@angular/core'; import {CommonModule} from '@angular/common'; import {FormsModule} from '@angular/forms'; import {ApiService} from '../core/api.service';
@Component({standalone:true,imports:[CommonModule,FormsModule],template:`
<div class="page">
  <div class="section-head"><div><div class="eyebrow">STUDENT MANAGEMENT</div><h1>Students</h1></div><button class="primary" (click)="add()">Add student</button></div>

  <div class="panel" *ngIf="form">
    <h3>{{editing ? 'Edit student' : 'Add student'}}</h3>
    <form (ngSubmit)="save()" class="student-form">
      <div class="section-block">
        <h4>Student information</h4>
        <div class="grid2">
          <label>Student Name<input name="name" [(ngModel)]="form.name" required></label>
          <label>Student Mobile<input name="mobile" [(ngModel)]="form.mobile" required></label>
          <label>Parent / Guardian Name<input name="parentName" [(ngModel)]="form.parentName"></label>
          <label>Parent / Guardian Mobile<input name="parentMobile" [(ngModel)]="form.parentMobile"></label>
          <label>Email<input name="email" [(ngModel)]="form.email" type="email"></label>
          <label>Address<input name="address" [(ngModel)]="form.address"></label>
        </div>
      </div>

      <div class="section-block">
        <h4>Academic information</h4>
        <div class="grid2">
          <label>Class / Grade<input name="grade" [(ngModel)]="form.grade"></label>
          <label>Batch<select name="batchId" [(ngModel)]="form.batchId" (ngModelChange)="onBatchChange()">
            <option value="">Select batch</option>
            <option *ngFor="let batch of batches" [value]="batch.batchId">{{batch.name}} ({{batch.course}})</option>
          </select></label>
        </div>

        <div class="subject-picker" *ngIf="selectedBatch">
          <h5>Subjects</h5>
          <div class="subject-options">
            <label *ngFor="let subject of selectedBatch.subjects || []">
              <input type="checkbox" [checked]="selectedSubjects.includes(subject)" (change)="toggleSubject(subject)">
              {{subject}}
            </label>
          </div>
        </div>

        <div class="teacher-mapping" *ngIf="selectedSubjects.length">
          <h5>Assign teacher for each subject</h5>
          <table>
            <thead><tr><th>Subject</th><th>Teacher</th></tr></thead>
            <tbody>
              <tr *ngFor="let subject of selectedSubjects">
                <td>{{subject}}</td>
                <td>
                  <select [(ngModel)]="teacherMap[subject]" [name]="'teacher_' + subject">
                    <option value="">Select teacher</option>
                    <option *ngFor="let teacher of eligibleTeachersForSubject(subject)" [value]="teacher.teacherId">{{teacher.name}}</option>
                  </select>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <div class="form-actions">
        <button class="primary" type="submit">{{editing ? 'Update' : 'Save student'}}</button>
        <button type="button" (click)="cancel()">Cancel</button>
      </div>
    </form>
  </div>

  <div class="panel table-wrap">
    <table><thead><tr><th>ID</th><th>Name</th><th>Mobile</th><th>Batch</th><th>Subjects</th><th>Status</th><th>Actions</th></tr></thead><tbody>
      <tr *ngFor="let s of list"><td>{{s.studentId}}</td><td>{{s.name}}</td><td>{{s.mobile}}</td><td>{{s.batchId || '—'}}</td><td>{{getStudentSubjectSummary(s)}}</td><td><span class="pill">{{s.status}}</span></td><td class="actions"><button type="button" (click)="edit(s)">Edit</button><button type="button" class="danger" (click)="remove(s)">Delete</button></td></tr>
    </tbody></table>
  </div>
</div>`})
export class StudentsComponent{
  api=inject(ApiService);
  list:any[]=[];
  batches:any[]=[];
  teachers:any[]=[];
  form:any=null;
  editing=false;
  selectedBatch:any=null;
  selectedSubjects:string[]=[];
  teacherMap:Record<string,string>={};

  ngOnInit(){this.load();}

  load(){
    this.api.get<any[]>('/students').subscribe(x=>this.list=x);
    this.api.get<any[]>('/batches').subscribe(x=>this.batches=x);
    this.api.get<any[]>('/teachers').subscribe(x=>this.teachers=x);
  }

  add(){
    this.editing=false;
    this.form={name:'',email:'',mobile:'',batchId:'',grade:'',parentName:'',parentMobile:'',address:''};
    this.selectedBatch=null;
    this.selectedSubjects=[];
    this.teacherMap={};
  }

  edit(student:any){
    this.editing=true;
    this.form={...student};
    this.selectedBatch=this.batches.find(b=>b.batchId===student.batchId) || null;
    this.selectedSubjects=this.selectedBatch?.subjects || [];
    this.teacherMap={};
  }

  cancel(){this.form=null;this.editing=false;this.selectedBatch=null;this.selectedSubjects=[];this.teacherMap={};}

  onBatchChange(){
    this.selectedBatch=this.batches.find(b=>b.batchId===this.form.batchId) || null;
    this.selectedSubjects=(this.selectedBatch?.subjects || []).slice();
    this.teacherMap={};
  }

  toggleSubject(subject:string){
    const exists=this.selectedSubjects.includes(subject);
    if(exists){
      this.selectedSubjects=this.selectedSubjects.filter(s=>s!==subject);
      delete this.teacherMap[subject];
    } else {
      this.selectedSubjects=[...this.selectedSubjects,subject];
      this.teacherMap[subject]='';
    }
  }

  eligibleTeachersForSubject(subject:string){
    return (this.teachers || []).filter((teacher:any)=>{
      const subjectOk=(teacher.subjects||[]).includes(subject);
      const batchOk=(teacher.batchIds||[]).includes(this.form?.batchId);
      return subjectOk && batchOk;
    });
  }

  getStudentSubjectSummary(student:any){
    const names = (student.subjects || []).length ? student.subjects.join(', ') : '—';
    return names;
  }

  save(){
    const subjectAssignments = this.selectedSubjects.map((subject:string)=>({
      subject,
      teacherId: this.teacherMap[subject],
      batchId: this.form.batchId
    })).filter(x=>x.teacherId);

    if (this.selectedSubjects.some(subject => !this.teacherMap[subject])) {
      alert('Please assign a teacher for every selected subject.');
      return;
    }

    const payload={
      name:this.form.name,
      email:this.form.email,
      mobile:this.form.mobile,
      batchId:this.form.batchId,
      grade:this.form.grade,
      parentName:this.form.parentName,
      parentMobile:this.form.parentMobile,
      address:this.form.address,
      subjectAssignments
    };

    const request=this.editing ? this.api.put('/students/'+this.form.studentId,payload) : this.api.post('/students',payload);
    request.subscribe(()=>{this.cancel();this.load();});
  }

  remove(student:any){
    if(confirm('Delete this student?')) this.api.delete('/students/'+student.studentId).subscribe(()=>this.load());
  }
}
