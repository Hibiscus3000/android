package ru.nsu.fit.sinyukov.android.fragmentapplication.fragment;

public class FragmentDescription {

    private final FragmentType fragmentType;
    private final String text;
    private final boolean changeView;

    public FragmentDescription(FragmentType fragmentType, String text, boolean changeView) {
        this.fragmentType = fragmentType;
        this.text = text;
        this.changeView = changeView;
    }

    public FragmentType getFragmentType() {
        return fragmentType;
    }

    public String getText() {
        return text;
    }

    public boolean getChangeView() {
        return changeView;
    }
}
