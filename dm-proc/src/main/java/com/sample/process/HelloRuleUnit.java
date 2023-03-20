package com.sample.process;

import org.kie.kogito.rules.DataSource;
import org.kie.kogito.rules.RuleUnitData;
import org.kie.kogito.rules.SingletonStore;

import com.sample.model.Message;

public class HelloRuleUnit implements RuleUnitData {
    private SingletonStore<Message> message;

    public HelloRuleUnit(){
        this(DataSource.createSingleton());
    }

    public HelloRuleUnit(SingletonStore<Message> message) {
        this.message = message;
    }

    public SingletonStore<Message> getMessage() {
        return message;
    }

    public void setMessage(SingletonStore<Message> message) {
        this.message = message;
    }
}
