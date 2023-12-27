package ru.nsu.fit.sinyukov.android.fragmentapplication.fragment;

public enum FragmentType {
    BUTTON {
        @Override
        public boolean isBackVisible() {
            return false;
        }

        @Override
        public FragmentType getPrevious() {
            return NOTHING;
        }
    }, TEXT {
        @Override
        public boolean isBackVisible() {
            return true;
        }

        @Override
        public FragmentType getPrevious() {
            return BUTTON;
        }
    }, NOTHING {
        @Override
        public boolean isBackVisible() {
            return false;
        }

        @Override
        public FragmentType getPrevious() {
            return NOTHING;
        }
    };

    public static FragmentType[] getSequence() {
        return new FragmentType[]{BUTTON, TEXT};
    }

    public abstract boolean isBackVisible();

    public abstract FragmentType getPrevious();
}
