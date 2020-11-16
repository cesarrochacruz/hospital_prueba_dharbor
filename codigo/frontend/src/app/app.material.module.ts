import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { MatSidenavModule,MatGridListModule,MatListModule,MatInputModule,MatNativeDateModule} from '@angular/material'
import {MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatDialogModule} from '@angular/material/dialog';


@NgModule({
    imports:[
        MatSidenavModule,
        MatGridListModule,
        MatListModule,
        MatTableModule,
        MatButtonModule,
        MatIconModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatDialogModule
    ],
    exports:[
        MatSidenavModule,
        MatGridListModule,
        MatListModule,
        MatTableModule,
        MatButtonModule,
        MatIconModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatDialogModule
        
    ]
})
export class CustomMaterialModule{}