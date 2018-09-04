package test;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

public class FaceTest {
    //设置APPID/AK/SK
    public static final String APP_ID = "10611221";
    public static final String API_KEY = "0CSee5SLiazykbR9hSHKg9mj";
    public static final String SECRET_KEY = "dPXIfgNXBrCZvnNmsCMPpaGXnenuRHA1";
    
    public void register(AipFace client, String uid, String userInfo, String groupId, String imagePath) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("action_type", "replace");

        // 参数为本地图片路径
        String image = imagePath;
        JSONObject res = client.addUser(uid, userInfo, groupId, image, options);
        System.out.println(res.toString(2));

//        // 参数为本地图片二进制数组
//        byte[] file = readImageFile(image);
//        res = client.addUser(file, uid, userInfo, groupId, options);
//        System.out.println(res.toString(2));
    }
    
    public void faceLogin(AipFace client, String uid, String groupId, String imagePath) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("top_num", "3");
        options.put("ext_fields", "faceliveness");

        // 参数为本地图片路径
        String image = imagePath;
        JSONObject res = client.verifyUser(uid, groupId, image, options);
        String temp = res.optQuery("/result").toString();
        double similarity = Double.valueOf(temp.substring(1, temp.length()-1));
        System.out.println(similarity);

//        // 参数为本地图片二进制数组
//        byte[] file = readImageFile(image);
//        res = client.verifyUser(file, uid, groupId, options);
//        System.out.println(res.toString(2));
    }

    public static void main(String[] args) {
    	//初始化该类
    	FaceTest sample = new FaceTest();
    	
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

//        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
//
//        // 调用接口
//        String path = "E://test.jpg";
//        JSONObject res = client.detect(path, new HashMap<String, String>());
//        System.out.println(res.toString(2));
        
        //本地文件路径
        String localImagePath = "E://test03.jpg";
        //用户id，由数字、字母、下划线组成
        String uid = "KC_000001";
        //用户信息描述
        String userInfo = "test : a StockManager staff zyb...";
        /* 如果需要将一个uid注册到多个group下，group_id需要用多个逗号分隔，
         * 每个group_id长度限制为48个英文字符，
         * group无需单独创建，注册用户时则会自动创建group。*/
        String groupId = "StockManager";
        //String groupId = "Manager,FinancialStaff,StockManager,SalesMan";
        sample.register(client, uid, userInfo, groupId, localImagePath);
        //sample.faceLogin(client, uid, groupId, localImagePath);
    }
}
