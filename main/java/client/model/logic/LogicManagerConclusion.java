package client.model.logic;


public class LogicManagerConclusion {

    private boolean succeed = false;
    private StepWrongState wrongState = null;
    private StepCorrectState correctState = null;


    void setWrongState(StepWrongState wrongState) {
        succeed = false;
        this.wrongState = wrongState;
    }

    void setCorrectState(StepCorrectState correctState) {
        succeed = true;
        this.correctState = correctState;
    }

    public StepCorrectState getCorrectState() {
        return correctState;
    }

    public StepWrongState getWrongState() {
        return wrongState;
    }

    public boolean isSucceed() {
        return succeed;
    }
}
