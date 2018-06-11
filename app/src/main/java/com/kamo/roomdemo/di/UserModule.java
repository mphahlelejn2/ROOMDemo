package com.kamo.roomdemo.di;

import com.kamo.roomdemo.db.Application_Database;
import com.kamo.roomdemo.user.IUserRepository;
import com.kamo.roomdemo.user.UserRepositoryImp;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @UserScope
    @Provides
    IUserRepository getIUserRepository(Application_Database db)
    {
        return new UserRepositoryImp(db);
    }

}
