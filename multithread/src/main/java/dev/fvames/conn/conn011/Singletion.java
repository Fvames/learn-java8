package dev.fvames.conn.conn011;

public class Singletion {

    private static class InnerSingletion {
        private static Singletion single = new Singletion();
    }

    public static Singletion getInstance() {
        return InnerSingletion.single;
    }

}
