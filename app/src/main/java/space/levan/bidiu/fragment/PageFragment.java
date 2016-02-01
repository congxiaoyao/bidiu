package space.levan.bidiu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import space.levan.bidiu.R;

/**
 * Created by 339 on 2016/1/29.
 */
public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        if (mPage == 1) {
            view = inflater.inflate(R.layout.activity_anime_timetable, container, false);
        } else if (mPage == 2) {
            view = inflater.inflate(R.layout.activity_anime_anime, container, false);
        } else if (mPage == 3) {
            view = inflater.inflate(R.layout.activity_anime_collection, container, false);
        } else if (mPage == 4) {
            view = inflater.inflate(R.layout.activity_anime_cache, container, false);
        }
        //TextView textView = (TextView) view;
        //textView.setText("Fragment #" + mPage);
        return view;
    }
}
