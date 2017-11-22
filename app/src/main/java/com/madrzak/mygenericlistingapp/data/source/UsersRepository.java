package com.madrzak.mygenericlistingapp.data.source;

import com.madrzak.mygenericlistingapp.data.dao.UserDao;
import com.madrzak.mygenericlistingapp.data.model.UserModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;


/**
 * Created by Łukasz on 04/11/2017.
 */

public class UsersRepository implements Repository<UserModel> {

    private volatile static UsersRepository INSTANCE = null;

    private final UserDao userDao;

    public static UsersRepository getInstance(UserDao userDao) {
        if (INSTANCE == null) {
            synchronized (UsersRepository.class) {
                INSTANCE = new UsersRepository(userDao);
            }
        }
        return INSTANCE;
    }

    private UsersRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Observable<Boolean> add(final UserModel item) {
        return Observable.fromCallable(() -> {
            userDao.insertAll(item);
            return true;
        });
    }

    @Override
    public Observable<Boolean> update(final UserModel item) {
        return Observable.fromCallable(() -> {
            userDao.updateAll(item);
            return true;
        });
    }

    @Override
    public void remove(UserModel item) {

    }

    @Override
    public Flowable<List<UserModel>> getAll() {
        return userDao.getAll();
    }

    @Override
    public Flowable<UserModel> getUser(int userId) {

        return userDao.getById(userId);

    }


}
