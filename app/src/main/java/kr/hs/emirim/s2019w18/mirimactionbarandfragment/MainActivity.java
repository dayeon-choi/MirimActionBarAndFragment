package kr.hs.emirim.s2019w18.mirimactionbarandfragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity{
    ActionBar.Tab tabOne,tabTwo,tabThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        tabOne=bar.newTab();
        tabOne.setText("ONE");
        tabOne.setTabListener(tabListener);
        bar.addTab(tabOne);

        tabTwo=bar.newTab();
        tabTwo.setText("TWO");
        tabTwo.setTabListener(tabListener);
        bar.addTab(tabTwo);

        tabThree=bar.newTab();
        tabThree.setText("THREE");
        tabThree.setTabListener(tabListener);
        bar.addTab(tabThree);
    }

    MyFragment myFrags[] = new MyFragment[3];

    ActionBar.TabListener tabListener = new ActionBar.TabListener(){
        @Override
        public void onTabSelected(ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {
            MyFragment myFrag = null;
            if(myFrags[tab.getPosition()]==null){
                myFrag=new MyFragment();
                Bundle data = new Bundle();
                data.putString("tabName",tab.getText().toString());
                myFrag.setArguments(data);
                myFrags[tab.getPosition()]=myFrag;
            }else{
                myFrag=myFrags[tab.getPosition()];
            }
            ft.replace(android.R.id.content,myFrag);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {

        }
    };

    public static class MyFragment extends Fragment{
        String tabName;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle data = getArguments();
            tabName=data.getString("tabName");
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            LinearLayout linear = new LinearLayout(super.getActivity());
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            linear.setLayoutParams(params);
            linear.setOrientation(LinearLayout.VERTICAL);

            if(tabName.equals("ONE")){
                linear.setBackgroundColor(Color.RED);
            }
            if(tabName.equals("TWO")){
                linear.setBackgroundColor(Color.YELLOW);
            }
            if(tabName.equals("THREE")){
                linear.setBackgroundColor(Color.BLUE);
            }

            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }
}