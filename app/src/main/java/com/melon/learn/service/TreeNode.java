package com.melon.learn.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Melon on 2018/3/24.
 */

public class TreeNode implements Parcelable {
    public int age;
    public String name;
    String address;

    public TreeNode(){
        age = 10;
        name = "Tom";

    }

    protected TreeNode(Parcel in) {
        age = in.readInt();
        name = in.readString();
        address = in.readString();
    }

    public static final Creator<TreeNode> CREATOR = new Creator<TreeNode>() {
        @Override
        public TreeNode createFromParcel(Parcel in) {
            return new TreeNode(in);
        }

        @Override
        public TreeNode[] newArray(int size) {
            return new TreeNode[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
        dest.writeString(address);
    }
    /**
     * 参数是一个Parcel,用它来存储与传输数据
     * @param dest
     */
    public void readFromParcel(Parcel dest) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        age = dest.readInt();
        name = dest.readString();
        address = dest.readString();

    }
}
