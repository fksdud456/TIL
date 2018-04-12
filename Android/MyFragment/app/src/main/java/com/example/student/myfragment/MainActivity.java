package com.example.student.myfragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MainFragment main;
    LoginFragment login;
    RegisterFragment register;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = new MainFragment();
        login = new LoginFragment();
        register = new RegisterFragment();

        manager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(
                    R.id.container,
                    new MainFragment()
            ).commit();
        }
    }

    public void clickBt(View v) {
        // 최초 transaction은 add() 로
        // 그 후부터 replace( )
        // container ->
        // home
        if (v.getId() == R.id.button) {
            manager.beginTransaction().replace(R.id.container, main).commit();
        } else if (v.getId() == R.id.bt_login) {
            manager.beginTransaction().replace(R.id.container, login).commit();
        } else if (v.getId() == R.id.bt_register) {
            register.setType(1);
            manager.beginTransaction().replace(R.id.container, register).commit();
        } else if (v.getId() == R.id.bt_r) {
            register.setType(2);
            manager.beginTransaction().replace(R.id.container, register).commit();
        }
    }

    public static class MainFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // mainlayout을 container 에 붙일 건데 ?
            View v = inflater.inflate(R.layout.mainlayout, container, false);
            return v;
        }
    }

    public static class LoginFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.loginlayout, container, false);
            return v;
        }
    }

    public static class RegisterFragment extends Fragment {
        int type;

        public void setType(int type) {
            this.type = type;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.registerlayout, container, false);

            TextView tv = v.findViewById(R.id.textView2);
            if (type == 1) {
                tv.setText("FROM HOME");
            } else if (type == 2) {
                tv.setText("FROM LOGIN");
            }
            return v;
        }
    }

}
