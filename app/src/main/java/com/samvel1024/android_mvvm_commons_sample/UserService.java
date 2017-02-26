package com.samvel1024.android_mvvm_commons_sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.SingleSource;

/**
 * @author Samvel Abrahamyan
 */

public class UserService {

    Single<User> getUserById(String id){
        return Single.defer(new Callable<SingleSource<? extends User>>() {
            @Override
            public SingleSource<? extends User> call() throws Exception {
                Thread.sleep(1000);
                return Single.just(new User("Joe", "email@gmail.com"));
            }
        });
    }

    Single<List> getUsers(){
        return Single.defer(new Callable<SingleSource<? extends List>>() {
            @Override
            public SingleSource<? extends List> call() throws Exception {
                Thread.sleep(1000);
                return Single.just(new ArrayList<>(Arrays.asList(
                       new User("Marry", "email@email.com"),
                       new User("Joe", "email@gmail.com"),
                       new User("Marry", "email@email.com")
                )));
            }
        });
    }

}
