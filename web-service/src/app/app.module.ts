import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { LanguageTranslationModule } from './shared/modules/language-translation/language-translation.module'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthGuard } from './shared';

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        HttpClientModule,
        LanguageTranslationModule,
        AppRoutingModule
    ],
    declarations: [AppComponent],
    providers: [AuthGuard],
    bootstrap: [AppComponent]
})
export class AppModule {}
