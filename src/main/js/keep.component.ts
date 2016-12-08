import { Component } from "@angular/core";

@Component({
    template: `
        <h1>Keeps</h1>
        <a [routerLink]="['/keep/1/detail']">Url Title</a>
    `
})
export class KeepComponent {
}
