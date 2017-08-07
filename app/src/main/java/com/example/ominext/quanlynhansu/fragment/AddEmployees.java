package com.example.ominext.quanlynhansu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ominext.quanlynhansu.R;
import com.example.ominext.quanlynhansu.model.EmployeesData;
import com.example.ominext.quanlynhansu.model.JSNWriter;
import com.example.ominext.quanlynhansu.model.ReadWrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import helper.FileHelper;
import helper.LogUtils;

import static android.content.ContentValues.TAG;

public class AddEmployees extends Fragment {

    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.cb_man)
    CheckBox cbMan;
    @BindView(R.id.cb_woman)
    CheckBox cbWoman;
    @BindView(R.id.edt_dateOfBirth)
    EditText edtDateOfBirth;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.esc)
    Button esc;
    Unbinder unbinder;
    String mAppDir = null;
    @BindView(R.id.edt_id)
    EditText edtId;
    private File file;
    private final String fileName = "Nhanvien.txt";

    public AddEmployees() {
        // Required empty public constructor
    }


    public static AddEmployees newInstance() {
        AddEmployees fragment = new AddEmployees();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_employees, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void init() {
        file = new File(Environment.getExternalStorageDirectory(), getContext().getPackageName());
        if (!file.exists())
            file.mkdir();
        mAppDir = file.getAbsolutePath() + "/";
    }

    //do file sai, do hàm sai
    public String readFromFile() throws IOException {
        //tạo 1 file có đường dẫn
        file = new File(mAppDir, "Nhan.txt");
        if (!file.exists())
            throw new FileNotFoundException();

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);

        String receiveString;
        StringBuilder data = new StringBuilder();

        while ((receiveString = bufferedReader.readLine()) != null) {
            data.append(receiveString);
        }
        isr.close();
        fis.close();
        Log.e("==============>", data.toString());
        return data.toString();
    }

    @OnClick({R.id.cb_man, R.id.cb_woman, R.id.save, R.id.esc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_man:
                if (cbWoman.isChecked()) {
                    cbWoman.setChecked(false);
                }
                break;
            case R.id.cb_woman:
                if (cbMan.isChecked()) {
                    cbMan.setChecked(false);
                }
                break;
            case R.id.save:
//              1 nhân viên kiểu employee
                EmployeesData employee = new EmployeesData();
                employee.setmId(Integer.parseInt(edtId.getText().toString()));
                employee.setmName(edtName.getText().toString());
                if (cbMan.isChecked()) {
                    employee.setmSex("Nam");
                } else if (cbWoman.isChecked()) {
                    employee.setmSex("Nữ");
                } else employee.setmSex("Chưa xác định");
                employee.setmDateOfBirth(edtDateOfBirth.getText().toString());
                employee.setmPhone(edtPhone.getText().toString());
                init();
                //lưu vào trong file text: 1- có một file text từ trước; 2- Load dữ liệu từ file text đó lên; 3- Ghép dữ liệu từ file text đó với dữ liệu mới; 4- Lưu vào file text
                try {
                    StringWriter output = new StringWriter();
                    JSNWriter.writeJsonStream(output, employee);
                    String jSonText = output.toString();
                    String tmp = "";
                    String s=FileHelper.ReadFile(getContext(),mAppDir,fileName);
                    LogUtils.d("===="+s);
                    FileHelper.saveToFile(mAppDir, fileName, jSonText);

                  /*  if (!file.exists()) {//chưa tồn tại thì tạo mới
                        file.createNewFile();
                        tmp = jSonText;
                    } else {
                        saveToFile(jSonText);
                        tmp = readFromFile();
                        tmp = tmp.substring(0, tmp.length() - 1);
                        tmp += "\n" + jSonText;
                    }*/
                   /* FileOutputStream out = new FileOutputStream(file);
                    OutputStreamWriter osw = new OutputStreamWriter(out);//không thực hiện dược
                    osw.write(tmp);
                    osw.flush();
                    osw.close();
                    out.flush();
                    out.close();
                    Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();*/
                } catch (Exception e) {
                   e.printStackTrace();
                }
                break;
            case R.id.esc:
                edtId.setText("");
                edtDateOfBirth.setText("");
                edtName.setText("");
                edtPhone.setText("");
                cbWoman.setChecked(false);
                cbMan.setChecked(false);
                break;
        }
    }
}
