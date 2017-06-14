package state_pattern;

/**
 * Copyright (c) 2017.  All rights reserved.
 * Created by zhouwen on 2017/6/14.
 */

public class StateClient {

    private IStates states = null;

    public void handleState() {

        StateContext context = new StateContext();
        context.setStates(new StataA());
        context.handle();

        context.setStates(new StateB());
        context.handle();
    }
}
