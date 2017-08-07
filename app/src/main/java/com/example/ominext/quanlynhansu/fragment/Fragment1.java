package com.example.ominext.quanlynhansu.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ominext.quanlynhansu.R;
import com.example.ominext.quanlynhansu.adapter.RecyclerViewAdapterFFrm1;
import com.example.ominext.quanlynhansu.model.EmployeesData;
import com.example.ominext.quanlynhansu.model.JSNWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import helper.LogUtils;

public class Fragment1 extends Fragment {


    @BindView(R.id.rv_listEmployees)
    RecyclerView rvListEmployees;
    @BindView(R.id.bt_add)
    Button btAdd;
    Unbinder unbinder;

    RecyclerView.LayoutManager layoutManager;
    //dataList bị trống
    //Lấy dữ liệu từ json lên
    List<EmployeesData> dataList = new ArrayList<>();
    RecyclerViewAdapterFFrm1 adapter;
    @BindView(R.id.frame_add_employees)
    FrameLayout frameAddEmployees;

    public static Fragment1 newInstance() {
        Fragment1 fragment = new Fragment1();
        return fragment;
    }

    //1
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//      Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    //2
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GetData();
        layoutManager = new GridLayoutManager(getContext(), 1);
        rvListEmployees.setLayoutManager(layoutManager);
        rvListEmployees.setHasFixedSize(true);

        adapter = new RecyclerViewAdapterFFrm1(dataList, getContext());
        rvListEmployees.setAdapter(adapter);
    }

    public void GetData() {
//      lấy dữ liệu từ file text ra, chuyển chuỗi json thành đối tượng add vào datalist đẩy lên listview
        try {
            File file = new File(Environment.getExternalStorageDirectory(), getContext().getPackageName());
            if (!file.exists())
                file.mkdir();
            String mAppDir = file.getAbsolutePath();

            file = new File(mAppDir, "Nhanvien.txt");
            if (!file.exists())
                throw new FileNotFoundException();

            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();
            String s;
            while ((s = reader.readLine()) != null) {
                builder.append(s).append("\n");
                JSONObject employeeObj = new JSONObject(s);
                EmployeesData employeesData1 = new EmployeesData();

                String idE = employeeObj.getString("id");
                String nameE = employeeObj.getString("name");
//            LogUtils.e("++++++++++++" + nameE);
                String sexE = employeeObj.getString("sex");
                String birthDayE = employeeObj.getString("birth");
                String phoneE = employeeObj.getString("phone");

                employeesData1.setmId(Integer.parseInt(idE));
                employeesData1.setmName(nameE);
                employeesData1.setmSex(sexE);
                employeesData1.setmDateOfBirth(birthDayE);
                employeesData1.setmPhone(phoneE);
                dataList.add(employeesData1);
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
        }
//        LogUtils.d("========================="+d+"");
//        StringBuffer stringBuffer = new StringBuffer();
//        try {
//            FileInputStream fileInputStream = context.openFileInput("note.txt");
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
//            String line = null;
//            while ((line = bufferedReader.readLine()) != null) {
//                stringBuffer.append(line);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_add)
    public void onViewClicked() {
        Fragment fragment = new AddEmployees();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame_add_employees, fragment);
        transaction.commit();
    }

}
