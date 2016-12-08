import { NgModule }      from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";

import { AppComponent }  from "./app.component";
import { AppRoutingModule } from "./app-routing.module";
import { KeepComponent } from "./keep.component";
import { KeepDetailComponent } from "./keep-detail.component";

@NgModule({
    imports: [ 
        BrowserModule,
        AppRoutingModule
    ],
    declarations: [
        AppComponent,
        KeepComponent,
        KeepDetailComponent
    ],
    bootstrap:    [ AppComponent ]
})
export class AppModule { }
