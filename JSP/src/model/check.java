package model;

public class check {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public boolean checkUsername() {
        if(username == null||username.length() < 8||username.length() > 12) return false;
        //检查字母开头
        if (!Character.isLetter(username.charAt(0))) {
            return false;
        }
        // 检查用户名是否只包含数字、字母、下划线
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }
    public String message(){
        if(checkUsername()){
            return "用户格式正确！";
        }else{
            return "用户格式不正确！";
        }
    }
}
