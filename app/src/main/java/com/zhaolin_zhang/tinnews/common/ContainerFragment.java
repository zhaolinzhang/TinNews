package com.zhaolin_zhang.tinnews.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhaolin_zhang.tinnews.R;
import com.zhaolin_zhang.tinnews.profile.TinProfileFragment;
import com.zhaolin_zhang.tinnews.save.SavedNewsFragment;
import com.zhaolin_zhang.tinnews.tin.TinGalleryFragment;

public class ContainerFragment extends TinBasicFragment {

    private int pageIndex;
    public static final int HOME_PAGE = 0;
    public static final int SAVE_PAGE = 1;
    public static final int PROFILE_PAGE = 2;
    public static final String HOME_PAGE_TAG = "home_page";
    public static final String SAVE_PAGE_TAG = "save_page";
    public static final String PROFILE_PAGE_TAG = "profile_page";
    private Fragment initFragment;

    public static ContainerFragment newInstance(int pageIndex) {
        ContainerFragment containerFragment = new ContainerFragment();
        containerFragment.pageIndex = pageIndex;
        containerFragment.initFragment = createInitFragmentByIndex(pageIndex);
        return containerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        return inflater.inflate(R.layout.child_fragment_container, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (initFragment != null && !initFragment.isAdded()) {
            getChildFragmentManager().beginTransaction().replace(R.id.child_fragment_container,
                    initFragment, getCurrentTag(pageIndex)).commit();
        }
    }

    public static int getPositionById(int id) {
        switch (id) {
            case R.id.action_tin:
                return HOME_PAGE;
            case R.id.action_save:
                return SAVE_PAGE;
            case R.id.action_profile:
                return PROFILE_PAGE;
                default:
                    throw new IndexOutOfBoundsException();
        }
    }

    private static Fragment createInitFragmentByIndex(int pageIndex) {
        switch (pageIndex) {
            case HOME_PAGE:
                return TinGalleryFragment.newInstance();
            case SAVE_PAGE:
                return SavedNewsFragment.newInstance();
            case PROFILE_PAGE:
                return TinProfileFragment.newInstance();
                default:
                    throw new IndexOutOfBoundsException();
        }
    }

    public static String getCurrentTag(int position) {
        switch (position) {
            case HOME_PAGE:
                return HOME_PAGE_TAG;
            case SAVE_PAGE:
                return SAVE_PAGE_TAG;
            case PROFILE_PAGE:
                return PROFILE_PAGE_TAG;
                default:
                    return null;
        }
    }
}
