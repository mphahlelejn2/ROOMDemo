package com.kamo.roomdemo.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.kamo.roomdemo.di.App;
import com.kamo.roomdemo.di.DaggerUserComponent;
import com.kamo.roomdemo.di.UserModule;
import com.kamo.roomdemo.entity.User;
import com.kamo.roomdemo.rxJava.BaseSchedulerProvider;
import com.kamo.roomdemo.user.IUser;
import com.kamo.roomdemo.user.IUserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;


public class UserViewModel extends AndroidViewModel implements IUser.IUserViewModel{

    @Inject
    public IUserRepository repository;
    @Inject
    public BaseSchedulerProvider provider;

    @Inject
    CompositeDisposable compositeDisposable=new CompositeDisposable();

    public UserViewModel(@NonNull Application application) {
        super(application);
        initInjection(application);
    }

    private void initInjection(Application application) {
        DaggerUserComponent.builder().appComponent(((App)application)
                .getAppComponent())
                .userModule(new UserModule()).build().inject(UserViewModel.this);
    }

    @Override
    public LiveData<List<User>> getListOfUsersFromDatabase() {
        return repository.getAllUser();
    }

    @Override
    public void getUserFromDatabase(String name) {
        Disposable disposable=repository.getUserByName(name)
                .subscribeOn(provider.io())
                .observeOn(provider.ui()).subscribeWith(new DisposableMaybeObserver<User>() {
                    @Override
                    public void onSuccess(User user) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposable);

    }

    @Override
    public void deleteUserFromDatabase(User user) {
        Disposable disposable=repository.deleteUser(user)
                .subscribeOn(provider.io()).observeOn(provider.ui())
                .subscribeWith(new DisposableCompletableObserver(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        compositeDisposable.add(disposable);

    }

    @Override
    public void updateUserOnDatabase(User user) {
        Disposable disposable=repository.updateUser(user)
                .subscribeOn(provider.io()).observeOn(provider.ui())
                .subscribeWith(new DisposableCompletableObserver(){
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        compositeDisposable.add(disposable);

    }


    @Override
    public void addUserToDatabase(User user) {
        Disposable disposable=repository.addUser(user)
                .subscribeOn(provider.io()).observeOn(provider.ui())
                .subscribeWith(new DisposableCompletableObserver(){
                    @Override
                    public void onComplete() {
                        Toast.makeText(getApplication(),"User Added To Database",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        compositeDisposable.add(disposable);
    }

}
