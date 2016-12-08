import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { KeepComponent } from "./keep.component";
import { KeepDetailComponent } from "./keep-detail.component";

const routes: Routes = [
    { path: "", redirectTo: "/keep", pathMatch: "full" },
    { path: "keep",  component: KeepComponent },
    { path: "keep/:id/detail",  component: KeepDetailComponent }
];

@NgModule({
    imports: [ RouterModule.forRoot(routes, { useHash: true }) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
