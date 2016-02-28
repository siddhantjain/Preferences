package com.tdw.preferences.models;

/**
 * Created by akash.jatangi on 2/29/16.
 */
public class TestUserResult {
    String slider1Iv;
    String slider1Fv;
    String slider2Iv;
    String slider2Fv;

    public TestUserResult(){
        slider1Iv = "";
        slider1Fv = "";
        slider2Iv = "";
        slider2Fv = "";
    }

    public TestUserResult(String sl1Iv, String sl1fv,String sl2Iv, String sl2fv){
        slider1Iv = sl1Iv;
        slider1Fv = sl1fv;
        slider2Iv = sl2Iv;
        slider2Fv = sl2fv;
    }

    public String getSlider1Iv() {
        return slider1Iv;
    }

    public void setSlider1Iv(String slider1Iv) {
        this.slider1Iv = slider1Iv;
    }

    public String getSlider1Fv() {
        return slider1Fv;
    }

    public void setSlider1Fv(String slider1Fv) {
        this.slider1Fv = slider1Fv;
    }

    public String getSlider2Iv() {
        return slider2Iv;
    }

    public void setSlider2Iv(String slider2Iv) {
        this.slider2Iv = slider2Iv;
    }

    public String getSlider2Fv() {
        return slider2Fv;
    }

    public void setSlider2Fv(String slider2Fv) {
        this.slider2Fv = slider2Fv;
    }
}
