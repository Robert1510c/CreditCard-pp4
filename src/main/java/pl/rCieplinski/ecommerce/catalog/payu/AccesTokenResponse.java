package pl.rCieplinski.ecommerce.catalog.payu;

public class AccesTokenResponse {
    String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public AccesTokenResponse setAccess_token(String access_token) {
        this.access_token = access_token;
        return this;
    }

    public String getAccessToken(){
        return access_token;
    }
}
