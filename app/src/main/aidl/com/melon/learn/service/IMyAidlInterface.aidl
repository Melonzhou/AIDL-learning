package com.melon.learn.service;
import com.melon.learn.service.TreeNode;


interface IMyAidlInterface {
    void setString(in String test);
    String getStringWithDate(String errStr);
    void setLinkNode(inout TreeNode node);
    TreeNode getTreeNode();
}
