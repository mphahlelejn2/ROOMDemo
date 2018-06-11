package com.kamo.roomdemo.user;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;
import com.kamo.roomdemo.R;
import com.kamo.roomdemo.Util.UtilTool;
import com.kamo.roomdemo.entity.User;
import butterknife.BindView;
import butterknife.ButterKnife;


public class AddUserDialog extends Dialog {

    @BindView(R.id.bCancel)
    public Button cancel;
    @BindView(R.id.etName)
    EditText name;
    @BindView(R.id.etSurname)
    EditText surname;
    @BindView(R.id.etEmail)
    EditText email ;
    @BindView(R.id.etPhone)
    EditText phone ;
    @BindView(R.id.bSave)
    public Button save;

    private IUser.View MyView;

    public AddUserDialog(@NonNull Context context,IUser.View view) {
        super(context);
        this.MyView=view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        setTitle("Add New User");
        ButterKnife.bind(this);
        initOnclick();
    }

    private void initOnclick() {

        save.setOnClickListener(view -> {
            if(dataPassedVerify()) {
                User user = new User();
                user.setName(name.getText().toString());
                user.setSurname(surname.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPhone(phone.getText().toString());
                MyView.addNewUser(user);
                dismiss();
            }
        });
        cancel.setOnClickListener(view -> {
           dismiss();
        });
    }

    private boolean dataPassedVerify() {
        boolean dataCorrect=false;
        if(UtilTool.isEmpty(name)){
              name.setError("Please enter the Name");
        }else if(UtilTool.isEmpty(surname)){
              surname.setError("Please enter the Surname");
         }else  if(!UtilTool.isEmail(email)){
              email.setError("Please enter correct Email");
        }else if(!UtilTool.isPhoneNumber(phone)){
             phone.setError("Please enter correct Numbers ");
        }else {
            dataCorrect= true;
        }
        return dataCorrect;
    }
}
