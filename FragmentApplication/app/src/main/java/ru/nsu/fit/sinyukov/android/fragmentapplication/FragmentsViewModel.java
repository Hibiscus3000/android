package ru.nsu.fit.sinyukov.android.fragmentapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.FragmentDescription;
import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.FragmentType;

public class FragmentsViewModel extends ViewModel {

    private final MutableLiveData<FragmentDescription> fragmentDescription = new MutableLiveData<>(null);

    private final MutableLiveData<Boolean> backPressed = new MutableLiveData<>(false);

    public LiveData<FragmentDescription> getFragmentDescription() {
        return fragmentDescription;
    }

    public void setFragmentDescription(FragmentDescription fragmentDescription) {
        this.fragmentDescription.postValue(fragmentDescription);
    }

    public void setFragmentType(FragmentType fragmentType, boolean changeView) {
        final FragmentDescription fragmentDescription = this.fragmentDescription.getValue();
        setFragmentDescription(new FragmentDescription(fragmentType, fragmentDescription.getText(), changeView));
    }

    public void setText(String text, boolean changeView) {
        final FragmentDescription fragmentDescription = this.fragmentDescription.getValue();
        setFragmentDescription(new FragmentDescription(fragmentDescription.getFragmentType(), text, changeView));
    }

    public LiveData<Boolean> getBackPressed() {
        return backPressed;
    }

    public void setBackPressed(Boolean backPressed) {
        this.backPressed.postValue(backPressed);
    }
}
