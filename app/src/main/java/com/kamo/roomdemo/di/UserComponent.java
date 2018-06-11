package com.kamo.roomdemo.di;

import com.kamo.roomdemo.viewModel.UserViewModel;

import dagger.Component;




@UserScope
@Component(dependencies = AppComponent.class, modules = UserModule.class)
public interface UserComponent {
    void inject(UserViewModel viewModel);
}
