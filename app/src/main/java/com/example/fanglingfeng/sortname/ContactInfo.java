package com.example.fanglingfeng.sortname;

/*
 *  @项目名：  SortName 
 *  @包名：    com.example.fanglingfeng.sortname
 *  @文件名:   ContactInfo
 *  @创建者:   lingfeng
 *  @创建时间:  2017/4/8 14:27
 *  @描述：    TODO
 */
public class ContactInfo
        implements Comparable<ContactInfo>
{
    private static final String TAG = "ContactInfo";
    private String rawName;

    public String getSortLetter() {
        return sortLetter;
    }

    public void setSortLetter(String sortLetter) {
        this.sortLetter = sortLetter;
    }

    public static String getTAG() {
        return TAG;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public String getPingyinName() {
        return pingyinName;
    }

    public void setPingyinName(String pingyinName) {
        this.pingyinName = pingyinName;
    }

    private String sortLetter;
    private String pingyinName;

    @Override

    public int compareTo(ContactInfo contactInfo) {
        if (sortLetter.startsWith("#")) {
            return 1;
        } else if (contactInfo.sortLetter.startsWith("#")) {
            return -1;
        } else {
            return sortLetter.compareTo(contactInfo.sortLetter);
        }


    }
}
