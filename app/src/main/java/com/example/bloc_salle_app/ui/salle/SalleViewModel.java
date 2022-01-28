package com.example.bloc_salle_app.ui.salle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SalleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SalleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is salle fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}