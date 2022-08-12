package Basis.GeneralMethod;

public class GeneralMethod{

    private int id;

    private String name = "";

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if(this.hashCode() == obj.hashCode()){
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
