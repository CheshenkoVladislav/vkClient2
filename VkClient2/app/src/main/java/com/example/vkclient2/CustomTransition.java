package com.example.vkclient2;

import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

public class CustomTransition extends TransitionSet {
    public CustomTransition(){
        setOrdering(ORDERING_TOGETHER);
        addTransition(new ChangeTransform())
                .addTransition(new ChangeImageTransform())
                .addTransition(new ChangeBounds());
    }
}
