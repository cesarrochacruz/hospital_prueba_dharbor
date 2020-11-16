import { Component, OnInit } from '@angular/core';
import { faHospital,faStethoscope,faProcedures } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  faHospital = faHospital;
  faStethoscope = faStethoscope;
  faProcedures = faProcedures;
  constructor() { }

  ngOnInit() {
  }

}
