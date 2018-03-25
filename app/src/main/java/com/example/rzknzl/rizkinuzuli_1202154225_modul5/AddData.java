package com.example.rzknzl.rizkinuzuli_1202154225_modul5;

/**
 * Created by RZKNZL on 25/03/2018.
 */

public class AddData {

    //deklarasi komponen yang akan digunakan
    String mTodo, mDescription, mPriority;

    //Constructor
    public AddData(String todo, String desc, String prior){
        this.mTodo=todo;
        this.mDescription=desc;
        this.mPriority=prior;
    }

    //method setter dan getter untuk to do, description dan priority
    public String getTodo() {
        return mTodo;
    }

    public void setTodo(String todo) {
        this.mTodo = todo;
    }

    public String getDesc() {
        return mDescription;
    }

    public void setDesc(String desc) {
        this.mDescription = desc;
    }

    public String getPrior() {
        return mPriority;
    }

    public void setPrior(String prior) {
        this.mDescription = prior;
    }

}
