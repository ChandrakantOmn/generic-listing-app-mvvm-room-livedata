package com.madrzak.mygenericlistingapp.ui.users;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madrzak.mygenericlistingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Łukasz on 18/11/2017.
 */

public class UsersFragment extends Fragment {

    private UsersViewModel mUsersViewModel;

    private RecyclerView.LayoutManager mLayoutManager;
    private UsersAdapter mUsersAdapter;


    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        mUsersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.users_frag, container, false);

        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mUsersAdapter = new UsersAdapter(mUsersViewModel);
        mRecyclerView.setAdapter(mUsersAdapter);

        mUsersViewModel.getUsers().observe(this, userModels -> {
            Timber.i("got users " + userModels.size());
            mUsersAdapter.replaceData(userModels);
        });

        // TODO add states to fragment for empty list and loading

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
