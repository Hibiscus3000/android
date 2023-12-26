package ru.nsu.fit.sinyukov.android.fragmentapplication;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BackButtonVisibilityViewModel extends ViewModel {

    private MutableLiveData<Integer> visibility = new MutableLiveData<>(View.INVISIBLE);

    public void setVisibility(Integer visibility) {
        this.visibility.postValue(visibility);
    }

    public LiveData<Integer> getVisibility() {
        return visibility;
    }
}
