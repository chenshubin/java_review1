package cn.murphy.enum1;

import jdk.nashorn.internal.objects.annotations.Getter;

public enum CountryEnum {
    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"赵"),
    FIVE(5,"魏"),
    SIX(6,"韩");

    private Integer retcode;

    private String retMsg;


    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    CountryEnum(Integer retcode, String retMsg) {
        this.retcode = retcode;
        this.retMsg = retMsg;
    }


    public static CountryEnum forEach(int code){

        CountryEnum[] att = CountryEnum.values();
        for(CountryEnum en : att){
            if(code == en.getRetcode()){
                return en;
            }
        }

        return null;



    }

}
