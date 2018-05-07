package com.melon.learn.client;

/**
 * Created by Melon on 2018/3/19.
 */

public class ResultData {

    public int status;
    public ContentData content;

    public ResultData(String param) {

    }

    public static class ContentData{
        public String from;
        public String to;
        public String vendor;
        public String out;
        public int errNo;
    }

    public void show() {
        System.out.println(status);

        System.out.println(content.from);
        System.out.println(content.to);
        System.out.println(content.vendor);
        System.out.println(content.out);
        System.out.println(content.errNo);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
