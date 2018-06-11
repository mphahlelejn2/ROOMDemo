package com.kamo.roomdemo.user;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kamo.roomdemo.R;
import com.kamo.roomdemo.entity.User;
import com.kamo.roomdemo.viewModel.UserViewModel;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserFragment extends Fragment implements IUser.View {

    private UserAdapter userAdapter;
    private IUser.IUserViewModel viewModel;
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;
    @BindView(R.id.fab)
    public FloatingActionButton add;


    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initRecycleView();
        initViewModel();
        userAdapter=new UserAdapter(this,new ArrayList<>());
        recyclerView.setAdapter(userAdapter);
        initAddNewUser();
    }

    private void initAddNewUser() {
        add.setOnClickListener(view -> {
            addUserDialog();
        });
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.getListOfUsersFromDatabase().observe(UserFragment.this, users -> {
            userAdapter.addUsers(users);
        });
    }

    private void initRecycleView() {
        RecyclerView.LayoutManager manager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
    }

    public void addUserDialog() {
        AddUserDialog addUserDialog=new AddUserDialog(getActivity(),this);
        addUserDialog.show();
    }

    @Override
    public void addNewUser(User user) {
        viewModel.addUserToDatabase(user);
    }

    @Override
    public void deleteUser(User user) {
        viewModel.deleteUserFromDatabase(user);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void filter(String newText) {
        userAdapter.getFilter().filter(newText);
    }

}
