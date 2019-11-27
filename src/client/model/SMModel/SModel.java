package client.model.SMModel;

public class SModel implements ISModel {
    private String modelName;

    public SModel(String modelName){
        this.modelName=modelName;
    }

    public String getModelName() {
        return modelName;
    }
}
