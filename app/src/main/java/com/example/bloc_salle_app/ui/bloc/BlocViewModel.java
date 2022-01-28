package com.example.bloc_salle_app.ui.bloc;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BlocViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BlocViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is bloc fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}