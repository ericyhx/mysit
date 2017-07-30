package manager;


import java.util.HashMap;

/**
 * Created by dasun on 2017/7/26.
 */
public class UserManager {
    private  static UserManager instance=new UserManager();
    public static UserManager getInstance(){
        return instance;
    }
    private HashMap<String,String> users=new HashMap<>();
    private UserManager(){
        users.put("zhangsan","123");
        users.put("lisi","456");
    }
    public String findByName(String name){
        return users.get(name);
    }
    public void addUser(String name,String password){
        users.put(name,password);
    }
}
