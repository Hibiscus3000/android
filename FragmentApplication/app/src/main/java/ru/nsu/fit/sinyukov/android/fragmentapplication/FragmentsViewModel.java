package ru.nsu.fit.sinyukov.android.fragmentapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FragmentsViewModel extends ViewModel {

    public enum FragmentType {
        BUTTON, TEXT
    }

    private MutableLiveData<String> text = new MutableLiveData<>();
    private MutableLiveData<FragmentType> fragmentType = new MutableLiveData<>(FragmentType.BUTTON);

    public LiveData<String> getText() {
        return text;
    }

    public void setText(String text) {
        this.text.postValue(text);
    }

    public LiveData<FragmentType> getFragmentType() {
        return fragmentType;
    }

    public void setFragmentType(FragmentType fragmentType) {
        this.fragmentType.postValue(fragmentType);
    }
}
