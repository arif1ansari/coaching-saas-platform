import {Component,inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ApiService} from '../core/api.service';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="page">
      <div class="section-head">
        <div>
          <div class="eyebrow">MANAGE</div>
          <h1>Student Assignments</h1>
        </div>
      </div>
      <div class="panel">
        <label>Search student
          <input [(ngModel)]="search" placeholder="Search by name or ID" />
        </label>
      </div>
      <div class="panel table-wrap" *ngIf="students.length">
        <table>
          <thead>
            <tr>
              <th>Student</th>
              <th>Class</th>
              <th>Subject</th>
              <th>Teacher</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <tr *ngFor="let entry of filteredStudents()">
              <td>{{entry.student?.name}}<br><small>{{entry.student?.studentId}}</small></td>
              <td>{{entry.student?.batchId}}</td>
              <td>{{entry.subject}}</td>
              <td>{{entry.teacherName || '—'}}</td>
              <td class="actions"><button type="button" (click)="remove(entry)">Remove</button></td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="panel" *ngIf="!students.length">
        <p class="muted">No assignments yet.</p>
      </div>
    </div>
  `
})
export class StudentAssignmentsComponent {
  api = inject(ApiService);
  students: any[] = [];
  search = '';

  ngOnInit() { this.load(); }

  load() {
    this.api.get<any[]>('/students').subscribe((students) => {
      this.students = students.map((student) => ({ student }));
    });
  }

  filteredStudents() {
    const term = (this.search || '').trim().toLowerCase();
    return this.students.filter((row) => {
      const student = row.student || {};
      if (!term) return true;
      return (student.name || '').toLowerCase().includes(term) || (student.studentId || '').toLowerCase().includes(term);
    });
  }

  remove(entry: any) {
    if (!confirm('Remove this assignment?')) return;
    this.api.delete('/student-teacher-assignments/' + entry.assignmentId).subscribe(() => this.load());
  }
}
