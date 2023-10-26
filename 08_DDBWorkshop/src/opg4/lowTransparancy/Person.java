package opg4.lowTransparancy;

public class Person {
    String cpr;
    String Navn;
    String byNavn;
    int Loen;
    int skatteProcent;

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    /**
     * @return Returns the loen.
     */
    public int getLoen() {
        return Loen;
    }
    /**
     * @param loen The loen to set.
     */
    public void setLoen(int loen) {
        Loen = loen;
    }
    /**
     * @return Returns the navn.
     */
    public String getNavn() {
        return Navn;
    }
    /**
     * @param navn The navn to set.
     */
    public void setNavn(String navn) {
        Navn = navn;
    }

    public String getByNavn() {
        return byNavn;
    }

    public void setByNavn(String byNavn) {
        this.byNavn = byNavn;
    }

    public int getSkatteProcent() {
        return skatteProcent;
    }

    public void setSkatteProcent(int skatteProcent) {
        this.skatteProcent = skatteProcent;
    }
}
