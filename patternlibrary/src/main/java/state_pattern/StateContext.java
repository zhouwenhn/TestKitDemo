package state_pattern;

/**
 * Copyright (c) 2017.  All rights reserved.
 * Created by zhouwen on 2017/6/14.
 */

public class StateContext {

    private IStates mStates;

    public void setStates(IStates states) {
        this.mStates = mStates;
    }

    public void handle(){
        if (mStates != null){
            mStates.handleState();
        }
    }
}
