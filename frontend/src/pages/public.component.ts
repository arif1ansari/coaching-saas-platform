import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({standalone:true,imports:[CommonModule,RouterLink],template:`<section class="public-page"><div class="public-hero"><div class="eyebrow">COACHFLOW</div><h1>One calm workspace for every coaching centre.</h1><p>Manage students, teachers, batches, attendance, fees, tests, and schedules with secure tenant isolation.</p><div class="public-actions"><a class="primary" routerLink="/signup">Start free trial</a><a class="secondary-action" routerLink="/login">Sign in</a></div></div><div class="public-grid"><article><h2>Built for daily operations</h2><p>Keep class rosters, teacher assignments, attendance, fees, and results in one place.</p></article><article><h2>Secure by tenant</h2><p>Every coaching centre sees only its own records. Teachers see only their assigned classes.</p></article><article><h2>Ready for growth</h2><p>Start with the MVP and move from local Docker to managed infrastructure when you are ready.</p></article></div></section>`})
export class PublicComponent{}
