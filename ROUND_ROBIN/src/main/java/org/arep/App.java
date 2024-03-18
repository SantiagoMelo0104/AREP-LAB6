package org.arep;


import static spark.Spark.*;

public class App {
    
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("/log",(req,res) -> {
            String val = req.queryParams("value");
            return RRInvoker.getLogs(val);
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
