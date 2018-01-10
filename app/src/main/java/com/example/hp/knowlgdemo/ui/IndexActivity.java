package com.example.hp.knowlgdemo.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

import com.example.hp.knowlgdemo.R;
import com.example.hp.knowlgdemo.view.IndexableListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 索引
 */
public class IndexActivity extends AppCompatActivity {

    private IndexableListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        List<String> mItems = new ArrayList<String>();
        mItems.add("Diary of a Wimpy Kid 6: Cabin Fever");
        mItems.add("Steve Jobs");
        mItems.add("Inheritance (The Inheritance Cycle)");
        mItems.add("11/22/63: A Novel");
        mItems.add("The Hunger Games");
        mItems.add("The LEGO Ideas Book");
        mItems.add("Explosive Eighteen: A Stephanie Plum Novel");
        mItems.add("Catching Fire (The Second Book of the Hunger Games)");
        mItems.add("Elder Scrolls V: Skyrim: Prima Official Game Guide");
        mItems.add("Death Comes to Pemberley");
        mItems.add("Diary of a Wimpy Kid 6: Cabin Fever");
        mItems.add("Steve Jobs");
        mItems.add("Inheritance (The Inheritance Cycle)");
        mItems.add("11/22/63: A Novel");
        mItems.add("The Hunger Games");
        mItems.add("The LEGO Ideas Book");
        mItems.add("Explosive Eighteen: A Stephanie Plum Novel");
        mItems.add("Catching Fire (The Second Book of the Hunger Games)");
        mItems.add("Elder Scrolls V: Skyrim: Prima Official Game Guide");
        mItems.add("Death Comes to Pemberley");
        Collections.sort(mItems);

        ContentAdapter adapter = new ContentAdapter(this,
                android.R.layout.simple_list_item_1, mItems);

        lv = (IndexableListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);
        lv.setFastScrollEnabled(true);


    }

    private class ContentAdapter extends ArrayAdapter<String> implements SectionIndexer {

        private String mSection = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        public ContentAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
        }


        @Override
        public Object[] getSections() {
            String[] sections = new String[mSection.length()];
            for (int i = 0; i < mSection.length(); i++) {
                sections[i] = String.valueOf(mSection.charAt(i));
            }
            return sections;
        }

        @Override
        public int getPositionForSection(int sectionIndex) {
            for (int i = sectionIndex; i >= 0; i--) {
                for (int j = 0; j < getCount(); j++) {
                    if (i == 0) {  //查询数字
                        for (int k = 0; k < 10; k++) {
                            if (StringMatcher.matvh(String.valueOf(getItem(j).charAt(0)),
                                    String.valueOf(k))) {
                                return j;
                            }
                        }

                    } else {   //查询字母
                        if (StringMatcher.matvh(String.valueOf(getItem(j).charAt(0)),
                                String.valueOf(mSection.charAt(i)))) {
                            return j;
                        }
                    }
                }
            }
            return 0;
        }

        @Override
        public int getSectionForPosition(int position) {
            return 0;
        }
    }


    private static class StringMatcher {
        //value  item文本
        //keyword  索引列表中的字符
        public static boolean matvh(String value, String keyword) {
            if (TextUtils.isEmpty(value) || TextUtils.isEmpty(keyword)) {
                return false;
            }
            if (keyword.length() > value.length()) {
                return false;
            }

            int i = 0, j = 0;//i --value 的指针，j --keyword的指针
            do {
                if (keyword.charAt(j) == value.charAt(i)) {
                    i++;
                    j++;
                } else if (j > 0) {
                    break;
                } else {
                    i++;
                }

            } while (i < value.length() && j < keyword.length());
            return (j == keyword.length());

        }
    }


}
