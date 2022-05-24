package com.example.teateashops.ui.staff;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teateashops.MainMenu;
import com.example.teateashops.R;
import com.example.teateashops.Vendor;
import com.example.teateashops.loginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;
import static com.example.teateashops.MainMenu.main_menu_act;
import static com.example.teateashops.signupActivity.isValidAddress;
import static com.example.teateashops.signupActivity.isValidContact;
import static com.example.teateashops.signupActivity.isValidEmail;
import static com.example.teateashops.signupActivity.isValidName;

public class StaffFragment extends Fragment {

    private StaffViewModel mViewModel;


    private DatabaseReference db,dbread;
    private static int i,val;

    public static StaffFragment newInstance() {
        return new StaffFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_staff, container, false);

        EditText viewName = root.findViewById(R.id.viewName);
        EditText viewAddress = root.findViewById(R.id.viewAdd);
        EditText viewEmail = root.findViewById(R.id.viewEmail);
        EditText viewUser = root.findViewById(R.id.viewUser);
        EditText viewContact = root.findViewById(R.id.viewContact);
        EditText viewPass = root.findViewById(R.id.viewPass);

        TextView warningName = (TextView) root.findViewById(R.id.warningName_profile);
        TextView warningAdd = (TextView) root.findViewById(R.id.warningAdd_profile);
        TextView warningEmail = (TextView) root.findViewById(R.id.warningEmail_profile);
        TextView warningContact = (TextView) root.findViewById(R.id.warningContact_profile);
        TextView warningUser = (TextView) root.findViewById(R.id.warningUser_profile);
        TextView warningPass = (TextView) root.findViewById(R.id.warningPass_profile);

        MainMenu main = (MainMenu) getActivity();

        Vendor vend = main.getVendor();

        db = FirebaseDatabase.getInstance().getReference().child("Staffs").child(vend.id);
        dbread = FirebaseDatabase.getInstance().getReference().child("Staffs");


        CheckBox showPass = root.findViewById(R.id.checkpass_profile);

        viewName.setText(vend.name);
        viewAddress.setText(vend.address);
        viewEmail.setText(vend.email);
        viewContact.setText(vend.number);
        viewUser.setText(vend.username);
        viewPass.setText(vend.password);

        Button btn_logout = root.findViewById(R.id.btn_logout);
        Button btn_update = root.findViewById(R.id.btn_update_user);

        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    viewPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    viewPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout_sess = new Intent(getActivity(), loginActivity.class);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure to Log Out?");
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // nothing to see here desu
                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(logout_sess);
                        main_menu_act.finish();
                    }
                });
                builder.create();
                builder.show();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val=0;
                String nameGet = viewName.getText().toString().trim();
                String addressGet = viewAddress.getText().toString().trim();
                String emailGet = viewEmail.getText().toString().trim();
                String contactGet = viewContact.getText().toString().trim();
                String userGet = viewUser.getText().toString().trim();
                String passGet = viewPass.getText().toString().trim();

                if (viewName.length() == 0) {
                    warningName.setText("This Field is REQUIRED.");
                    val=1;
                } else if (!isValidName(nameGet)) {
                    warningName.setText("This Name is INVALID.");
                    val=1;
                } else {
                    warningName.setText("");
                }
                if (viewAddress.length() == 0) {
                    warningAdd.setText("This Field is REQUIRED.");
                    val=1;
                } else if (isValidAddress(addressGet)) {
                    warningAdd.setText("This Address is INVALID.");
                    val=1;
                } else {
                    warningAdd.setText("");
                }
                if (viewEmail.length() == 0) {
                    warningEmail.setText("This Field is REQUIRED.");
                    val=1;
                } else if (!isValidEmail(emailGet)) {
                    warningEmail.setText("This Email is INVALID.");
                    val=1;
                } else {
                    warningEmail.setText("");
                }
                if (viewContact.length() == 0) {
                    warningContact.setText("This Field is REQUIRED.");
                    val=1;
                } else if (!isValidContact(contactGet)) {
                    warningContact.setText("This Contact is INVALID.");
                    val=1;
                } else {
                    warningContact.setText("");
                }
                if (viewUser.length() == 0) {
                    warningUser.setText("This Field is REQUIRED.");
                    val=1;
                } else if (viewUser.length() < 4) {
                    warningUser.setText("This Username is too SHORT.");
                    val=1;
                } else {
                    warningUser.setText("");
                }
                if (viewPass.length() == 0) {
                    warningPass.setText("This Field is REQUIRED.");
                    val=1;
                } else if (viewPass.length() < 6) {
                    warningPass.setText("Password must be at least 6 characters.");
                    val=1;
                } else {
                    warningPass.setText("");
                }


                dbread.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (nameGet.equals(vend.name)
                                && addressGet.equals(vend.address)
                                && emailGet.equals(vend.email)
                                && contactGet.equals(vend.number)
                                && userGet.equals(vend.username)
                                && passGet.equals(vend.password)) {
                            val = 1;
                            Toast.makeText(getActivity(),"No Changes Have Been Made.",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        for (i=0;i<snapshot.getChildrenCount()+1 && i!=new Integer(vend.id);i++) {
                            String nameGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("name").getValue());
                            if (nameGetter.equals(nameGet)) {
                                warningName.setText("This Name has EXISTED.");
                                val = 1;
                            }
                            String userGetter = String.valueOf(snapshot.child(String.valueOf(i)).child("username").getValue());
                            if (userGetter.equals(userGet)) {
                                warningUser.setText("This Username has EXISTED.");
                                val = 1;
                            }
                            Log.d("Name Index "+ String.valueOf(i),userGetter);
                        }


                        if (val == 0) {
                            warningName.setText("");
                            warningAdd.setText("");
                            warningEmail.setText("");
                            warningContact.setText("");
                            warningUser.setText("");
                            warningPass.setText("");

                            Log.d("profile name",nameGet);
                            Log.d("profile address",addressGet);
                            Log.d("profile email",emailGet);
                            Log.d("profile contact",contactGet);
                            Log.d("profile username",userGet);
                            Log.d("profile password",passGet);

                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                            builder.setMessage("To Have Effective Changes to your Profile, You must Log Out your Session. Are you Sure to Proceed?");
                            builder.setMessage("Are you sure with those changes you made?");
                            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // nothing to see here desu
                                }
                            });
                            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.setValue(new Vendor(nameGet,addressGet,emailGet,contactGet,userGet,passGet));
//                                    Intent intent_logout = new Intent(getActivity(),loginActivity.class);
//                                    startActivity(intent_logout);
//                                    menu_act.finish();
                                    return;
                                }
                            });
                            builder.create();
                            builder.show();

                            return;

                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    warningName.setText("");
                                    warningAdd.setText("");
                                    warningEmail.setText("");
                                    warningContact.setText("");
                                    warningUser.setText("");
                                    warningPass.setText("");
                                }
                            },3000);
                        }
                        return;
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w(TAG, "loadPost:onCancelled",error.toException());
                    }
                });

            }
        });



        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StaffViewModel.class);
        // TODO: Use the ViewModel
    }

}