import com.alibaba.druid.filter.config.ConfigTools;

public class T {

    public static void main(String[] args) throws Exception {

        String password = "123456";
        // String[] arr = ConfigTools.genKeyPair(512);
        // System.out.println("privateKey:" + arr[0]);
        // System.out.println("publicKey:" + arr[1]);
        // System.out.println("password:" + ConfigTools.encrypt(arr[0], password));
        System.out.println("password:" + ConfigTools.decrypt(
                "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANCL0TcPtdLfnk0nxU47Z3JAfvpWz4oCYSQC0KT5LE" +
                "/OoOi6l28AvBoJY92xB4GtxTOws8U8Z13EpyxWGFIpkp0CAwEAAQ==",
                "Mp+668K3rB5cgx51B2lT0JDOKA5L6P2z++FgtF8vhdoG1A4eO04MxcPY4OZfdijc8RLxWHyZ2hWXaGvkyd/9Ig=="));
    }
}
