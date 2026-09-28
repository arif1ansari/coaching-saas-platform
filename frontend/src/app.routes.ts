import {Routes} from '@angular/router';
import {LoginComponent} from './pages/login.component';
import {SignupComponent} from './pages/signup.component';
import {DashboardComponent} from './pages/dashboard.component';
import {StudentsComponent} from './pages/students.component';
import {TeachersComponent} from './pages/teachers.component';
import {OperationsComponent} from './pages/operations.component';
import {SuperAdminComponent} from './pages/super-admin.component';
import {TeacherDashboardComponent} from './pages/teacher-dashboard.component';
import {SubjectsComponent} from './pages/subjects.component';
import {SettingsComponent} from './pages/settings.component';
import {PublicComponent} from './pages/public.component';
import {StudentAssignmentsComponent} from './pages/student-assignments.component';
import {authGuard} from './core/auth.guard';
import {roleGuard} from './core/role.guard';
export const routes:Routes=[
 {path:'',component:PublicComponent},
 {path:'features',component:PublicComponent},
 {path:'pricing',component:PublicComponent},
 {path:'login',component:LoginComponent},
 {path:'signup',component:SignupComponent},
 {path:'dashboard',component:DashboardComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'admin/dashboard',component:DashboardComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'students',component:StudentsComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'admin/students',component:StudentsComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'teachers',component:TeachersComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'admin/teachers',component:TeachersComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'subjects',component:SubjectsComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'admin/subjects',component:SubjectsComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'settings',component:SettingsComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN','TEACHER'])]},
 {path:'operations',component:OperationsComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'admin/batches',component:OperationsComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'admin/attendance',component:OperationsComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'admin/fees',component:OperationsComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'admin/tests',component:OperationsComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'admin/results',component:OperationsComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'admin/timetable',component:OperationsComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'admin/assignments',component:StudentAssignmentsComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'admin/settings',component:SettingsComponent,canActivate:[authGuard,roleGuard(['COACHING_ADMIN'])]},
 {path:'teacher/dashboard',component:TeacherDashboardComponent,canActivate:[authGuard,roleGuard(['TEACHER'])]},
 {path:'teacher/attendance',component:TeacherDashboardComponent,canActivate:[authGuard,roleGuard(['TEACHER'])]},
 {path:'super-admin',component:SuperAdminComponent,canActivate:[authGuard,roleGuard(['SUPER_ADMIN'])]},
 {path:'teacher/tests',component:TeacherDashboardComponent,canActivate:[authGuard,roleGuard(['TEACHER'])]},
 {path:'teacher/timetable',component:TeacherDashboardComponent,canActivate:[authGuard,roleGuard(['TEACHER'])]},
 {path:'super-admin/dashboard',component:SuperAdminComponent,canActivate:[authGuard,roleGuard(['SUPER_ADMIN'])]},
 {path:'super-admin/coaching-centres',component:SuperAdminComponent,canActivate:[authGuard,roleGuard(['SUPER_ADMIN'])]},
 {path:'**',redirectTo:'dashboard'}
];
