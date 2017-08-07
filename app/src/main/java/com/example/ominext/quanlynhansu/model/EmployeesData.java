package com.example.ominext.quanlynhansu.model;

import android.support.v7.widget.RecyclerView;

import com.example.ominext.quanlynhansu.adapter.SlidingMenuAdapter;

/**
 * Created by Ominext on 8/4/2017.
 */

public class EmployeesData {
    private int mId;
    private String mName;
    private String mDateOfBirth;
    private String mSex;
    private String mPhone;

    public EmployeesData() {
    }

    public EmployeesData(int mId, String mName, String mDateOfBirth, String mSex, String mPhone) {
        this.mId = mId;
        this.mName = mName;
        this.mDateOfBirth = mDateOfBirth;
        this.mSex = mSex;
        this.mPhone = mPhone;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDateOfBirth() {
        return mDateOfBirth;
    }

    public void setmDateOfBirth(String mDateOfBirth) {
        this.mDateOfBirth = mDateOfBirth;
    }

    public String getmSex() {
        return mSex;
    }

    public void setmSex(String mSex) {
        this.mSex = mSex;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }
}
