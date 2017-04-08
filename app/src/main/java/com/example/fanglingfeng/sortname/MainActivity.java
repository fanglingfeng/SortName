package com.example.fanglingfeng.sortname;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity
        extends AppCompatActivity
{
    private ContactAdapter contactAdapter;

    private List<ContactInfo> contactInfos;
    @BindView(R.id.et_contacts_search)
    SearchEditText mEtContactsSearch;
    @BindView(R.id.lv_contacts)
    ListView       mLvContacts;
    @BindView(R.id.tv_letter_dialog)
    TextView       mTvLetterDialog;
    @BindView(R.id.sb_index_letter)
    IndexSideView  mSbIndexLetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initListener();
        initContactAdapter();
    }

    private void initContactAdapter() {
        String[] array = getResources().getStringArray(R.array.names);
        contactInfos = ContactHelper.setupContactInfoList(Arrays.asList(array));
        List<String> indexLetters = ContactHelper.setupLetterIndexList(contactInfos);
        mSbIndexLetter.setLetterList(indexLetters);
        contactAdapter = new ContactAdapter(MainActivity.this,contactInfos);
        mLvContacts.setAdapter(contactAdapter);
    }

    private void initListener() {
        mLvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ContactInfo contactInfo = (ContactInfo) contactAdapter.getItem(i);
                Toast.makeText(MainActivity.this, contactInfo.getRawName(), Toast.LENGTH_LONG)
                     .show();
            }
        });
        mSbIndexLetter.setOnTouchLetterListener(new IndexSideView.OnTouchLetterListener() {
            @Override
            public void onTouchedLetter() {
                mTvLetterDialog.setVisibility(View.GONE);
            }

            @Override
            public void onTouchingLetter(String letter) {
                mTvLetterDialog.setText(letter);
                mTvLetterDialog.setVisibility(View.VISIBLE);
                int position = contactAdapter.getPositionForSection(letter.charAt(0));
                if (position != -1) {
                    mLvContacts.setSelection(position);

                }
            }


        });
        mEtContactsSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<ContactInfo> filterList= ContactHelper.filterContact(charSequence.toString(),contactInfos);
                contactAdapter.updateContactInfoList(filterList);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
