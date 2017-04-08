package com.example.fanglingfeng.sortname;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 *  @项目名：  SortName 
 *  @包名：    com.example.fanglingfeng.sortname
 *  @文件名:   ContactHelper
 *  @创建者:   lingfeng
 *  @创建时间:  2017/4/8 14:34
 *  @描述：    TODO
 */
public class ContactHelper {
    private static final String TAG = "ContactHelper";
    public static List<ContactInfo> filterContact(String filterStr,List<ContactInfo> contactInfos){
        List<ContactInfo> filtedList = new ArrayList<>();
        if (TextUtils.isEmpty(filterStr)) {
            filtedList = contactInfos;
        } else {
            for (int i = 0; i <contactInfos.size() ; i++) {
                ContactInfo contactInfo = contactInfos.get(i);
                String rawName = contactInfo.getRawName();
                String pingyinName = contactInfo.getPingyinName();
                String upperString = filterStr.toUpperCase();
                if (rawName.contains(upperString) || pingyinName.startsWith(upperString)) {
                    filtedList.add(contactInfo);
                }
            }
        }
        Collections.sort(filtedList);
        return filtedList;
    }
    //测边栏字母索引
    public static List<String> setupLetterIndexList(List<ContactInfo> contactInfos){
        List<String> letterIndexList = new ArrayList<>();
        boolean find =false;
        for (int i = 0; i <contactInfos.size() ; i++) {
            String firstLetter = contactInfos.get(i).getSortLetter().substring(0,1);

            if (!letterIndexList.contains(firstLetter) && !"#".equals(firstLetter)) {
                letterIndexList.add(firstLetter);
            }
            if (!find && firstLetter.equals("#")) {
                find = true;
            }

        }
        Collections.sort(letterIndexList);
        if (find) {
            letterIndexList.add("#");
        }
        return letterIndexList;
    }
    //联系人列表
    public static List<ContactInfo> setupContactInfoList(List<String> contacts){
        List<ContactInfo> contactInfos = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            String contact = contacts.get(i);
            ContactInfo contactInfo = new ContactInfo();
            contactInfo.setRawName(contact);
            String pingyinName = PinyinUtils.toPinyinString(contact);
            pingyinName = pingyinName.toUpperCase();
            contactInfo.setPingyinName(pingyinName);
            String sortLetter =setupSortLetter(contact);
            contactInfo.setSortLetter(sortLetter);
            contactInfos.add(contactInfo);
        }
        return contactInfos;
    }

    private static String setupSortLetter(String contact) {
        String firstChar = String.valueOf(contact.charAt(0));
        int mode = PinyinUtils.CASE_UPPERCASE|PinyinUtils.TRIM_NON_CHAR;
        String pinyin = PinyinUtils.toPinyinString(firstChar,mode);
        if (!TextUtils.isEmpty(pinyin)) {
            return pinyin;
        } else {
            String word = contact.split("[^a-zA-Z]")[0];
            if (!TextUtils.isEmpty(word)) {
                return word;
            } else {
                return "#";
            }
        }
    }


}
