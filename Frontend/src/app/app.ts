import { Component, signal } from '@angular/core';

import { RouterOutlet } from '@angular/router';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `
    <router-outlet></router-outlet> <!-- only outlet, no text here -->
  `,
})
export class App {
  protected readonly title = signal('Frontend');
}
